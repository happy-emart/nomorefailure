package com.example.fw5_nmf;

import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fw5_nmf.databinding.Fragment3Binding;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Fragment3 extends Fragment {

    public Fragment3() {
        // Required empty public constructor
    }

    private Fragment3Binding binding;
    private ArrayList<Todo> data;
    private Toast msg;
//    private ArrayList<String> data;
//    private TodoAdapter todoAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = Fragment3Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        data = new ArrayList<>();
        TodoAdapter todoAdapter = new TodoAdapter(data);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(todoAdapter);

        todoAdapter.setOnDeleteClickListener(new TodoAdapter.OnDeleteClickListener(){
            @Override
            public void onClickDeleteIcon(int todo) {
                data.remove(todo);
                todoAdapter.notifyItemRemoved(todo);
            }
        });

        todoAdapter.setOnTextClickListener(new TodoAdapter.OnTextClickListener(){
            @Override
            public void onClickText(int todo) {
                View dialogView = View.inflate(getActivity(), R.layout.todofloat, null);
                Todo target = data.get(todo);
                CheckBox checkBox = dialogView.findViewById(R.id.todoCheckBox);
                AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
                TextView popup = dialogView.findViewById(R.id.popupTodoText);
                popup.setText(target.getText());
                if(target.isDone()) {
                    checkBox.setChecked(true);
                    popup.setPaintFlags(popup.getPaintFlags()|Paint.STRIKE_THRU_TEXT_FLAG);
                }
                dlg.setTitle("TO-DO LIST");
                dlg.setIcon(R.mipmap.ic_launcher);
                dlg.setView(dialogView);
                dlg.setNeutralButton("확인", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_NEUTRAL) {
                            if (checkBox.isChecked()) {
                                todoChecked(todo, data);
//                                Log.d("greentea","CHECKED CLICK");
                            } else {
                                todoUnchecked(todo, data);
                            }
                            //
                        }
                    }
                });
                dlg.show();
            }
        });

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoText = binding.editText.getText().toString();
                if(todoText.length()>0){
                    Todo todo = new Todo(todoText, false);
                    data.add(todo);
                    todoAdapter.notifyItemInserted(data.size() - 1);
                    binding.editText.setText("");
                    if (msg != null) msg.cancel();
                    msg = Toast.makeText(getActivity(), "추가했습니다!", Toast.LENGTH_SHORT);
                    msg.show();

                } else {
                    // 반투명 플로팅으로 텍스트를 입력해주세요 띄우기
                    if (msg != null) msg.cancel();
                    msg = Toast.makeText(getActivity(), "내용을 입력해주세요!", Toast.LENGTH_SHORT);
                    msg.show();
                }
            }
        });
    }

    public void todoChecked(int todo, ArrayList<Todo> data) {
        View viewOfTodo = binding.recyclerView.getLayoutManager().findViewByPosition(todo);
        TextView textTodo = viewOfTodo.findViewById(R.id.todoText);
        Log.d("greentea", String.valueOf(textTodo.getText()));
        textTodo.setPaintFlags(textTodo.getPaintFlags()|Paint.STRIKE_THRU_TEXT_FLAG);
        data.get(todo).setDone(true);
    };
    //드디어 다했다!!!!!!
    public void todoUnchecked(int todo, ArrayList<Todo> data) {
        View viewOfTodo = binding.recyclerView.getLayoutManager().findViewByPosition(todo);
        TextView textTodo = viewOfTodo.findViewById(R.id.todoText);
        textTodo.setPaintFlags(textTodo.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        data.get(todo).setDone(false);
    };
    //앗 좀 남았네,,,,,,


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}