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
import java.net.MalformedURLException;
import java.nio.Buffer;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


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

        String mainQuote = jsonParser(requestQuote());
        if (mainQuote.length() > 100) mainQuote = "태어난 김에 산다는 마인드가 일류다 - 장영욱";
        binding.mainQuote.setText(mainQuote);

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
                View dlgTitle = getActivity().getLayoutInflater().inflate(R.layout.custom_dlg_title, null);
                TextView dlgTitleContents = dlgTitle.findViewById(R.id.text);
                Todo target = data.get(todo);
                CheckBox checkBox = dialogView.findViewById(R.id.todoCheckBox);
                AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
                TextView popup = dialogView.findViewById(R.id.popupTodoText);
                TextView quote = dialogView.findViewById(R.id.quoteText);
                popup.setText(target.getText());
                if(target.isDone()) {
                    checkBox.setChecked(true);
                    popup.setPaintFlags(popup.getPaintFlags()|Paint.STRIKE_THRU_TEXT_FLAG);
                }
                String text = requestQuote();
                Log.d("greentea", text);
                String niceQuote = jsonParser(requestQuote());
                if (niceQuote.length() > 100) niceQuote = "태어난 김에 산다는 마인드가 일류다 - 장영욱";
                quote.setText(niceQuote);
                dlgTitleContents.setText("TO-DO LIST");
                dlg.setCustomTitle(dlgTitle);
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
    public String requestQuote() {
        String result;
        String apiUrl = "https://api.qwer.pw/request/helpful_text";
        String apiKey = "guest";
        String urlWithApiKey = apiUrl + "?apikey=" + apiKey;
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(() -> {
            try {
                URL url = new URL(urlWithApiKey);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(300);


                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) response.append(line);
                    reader.close();
                    return response.toString();
                } else {
                    return "[{\"result\":\"success\"},{\"respond\":\"태어난 김에 산다는 마인드가 일류다 - 장영욱\"}]";
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "[{\"result\":\"success\"},{\"respond\":\"태어난 김에 산다는 마인드가 일류다 - 장영욱\"}]";
            }
        });

        try {
            result = future.get();
        } catch (Exception e) {
            e.printStackTrace();
            return "[{\"result\":\"success\"},{\"respond\":\"태어난 김에 산다는 마인드가 일류다 - 장영욱\"}]";
        } finally {
            executor.shutdown();
        }
        return result;
    }

    private String jsonParser(String target) {
        String result = "태어난 김에 산다는 마인드가 일류다 - 장영욱";
        try {
            JSONArray jsonArray = new JSONArray(target);
            if (jsonArray.length() > 0) {
                JSONObject jsonObject = jsonArray.getJSONObject(1);  // 두 번째 객체 선택
                result = jsonObject.getString("respond");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}