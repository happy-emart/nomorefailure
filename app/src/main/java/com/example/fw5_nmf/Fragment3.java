package com.example.fw5_nmf;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Fragment3 extends Fragment {

    public Fragment3() {
        // Required empty public constructor
    }

//    private ArrayList<Todo> data;
    private ArrayList<String> data;
//    private TodoAdapter todoAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);
        data = new ArrayList<>();
//        data.add(new Todo("aa", false));
        data.add("aaa");
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);  // LayoutManager 설정
        TodoAdapter todoAdapter = new TodoAdapter(data);
        recyclerView.setAdapter(todoAdapter);
        todoAdapter.setOnItemClickListener(new TodoAdapter.OnItemClickListener(){
            @Override
            public void onClickDeleteIcon(int todo) {
                data.remove(todo);
                todoAdapter.notifyItemRemoved(todo);
            }
        });

        Button addButton = view.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText editText = view.findViewById(R.id.editText);
                String todoText = editText.getText().toString();
//                Todo todo = new Todo(todoText, false);
                data.add(todoText);
//                data.add(todo);

                todoAdapter.notifyItemInserted(data.size() -1);
                editText.setText("");
                System.out.println(data.get(data.size()-1));
            }
        });

        return view;
    }
}