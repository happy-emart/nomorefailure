package com.example.fw5_nmf;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Fragment1 extends Fragment {
    private static ArrayList<String> testDataSet;
    private static CustomAdapter customAdapter;
    public Fragment1() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        testDataSet = new ArrayList<>();
        loadJSONData();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        customAdapter = new CustomAdapter(testDataSet);
        recyclerView.setAdapter(customAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            private Drawable leftBackground;
            private Drawable rightBackground;
            private boolean initiated;
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                if (direction == ItemTouchHelper.LEFT) {
                    makePhoneCall(position);
                } else if (direction == ItemTouchHelper.RIGHT) {
                    removeItem(position);
                }
            }
            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                View itemView = viewHolder.itemView;
                int customGreenColor = Color.parseColor("#46AA46"); // 사용자 정의 초록색
                int customRedColor = Color.parseColor("#B90000"); // 사용자 정의 빨간색
                if (!initiated) {
                    leftBackground = new ColorDrawable(customGreenColor);
                    rightBackground = new ColorDrawable(customRedColor);
                    initiated = true;
                }

                if (dX > 0) {
                    // 오른쪽으로 스왑하는 경우
                    rightBackground.setBounds(itemView.getLeft(), itemView.getTop(), itemView.getLeft() + (int) dX, itemView.getBottom());
                    rightBackground.draw(c);
                } else if (dX < 0) {
                    // 왼쪽으로 스왑하는 경우
                    leftBackground.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
                    leftBackground.draw(c);
                }

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
        FloatingActionButton fabMain = view.findViewById(R.id.fab_main);
        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), SubActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void loadJSONData() {
        try {
            String json = loadJSONFromAsset();
            if (json != null) {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject entry = jsonArray.getJSONObject(i);
                    String name = entry.getString("name");
                    String number = entry.getString("number");
                    String data = name + "\n" + number;
                    testDataSet.add(data);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String loadJSONFromAsset() {
        String json;
        try {
            AssetManager assetManager = requireContext().getAssets();
            InputStream inputStream = assetManager.open("phone-number.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void addToTestDataSet(String name, String number) {
        String data = name + "\n" + number;
        testDataSet.add(data);
        customAdapter.notifyDataSetChanged();
    }
    private void makePhoneCall(int position) {
        // 전화 거는 동작을 수행하는 코드를 작성하세요.
        // 예시로 Toast 메시지를 보여주는 코드를 작성하였습니다.
        String number = getPhoneNumber(position);
        Toast.makeText(requireContext(), "전화 거는 동작: " + number, Toast.LENGTH_SHORT).show();
    }
    private void removeItem(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage("아이템을 삭제하시겠습니까?")
                .setPositiveButton("삭제", (dialog, which) -> {
                    // 확인 버튼을 누른 경우 아이템 삭제
                    testDataSet.remove(position);
                    customAdapter.notifyItemRemoved(position);
                })
                .setNegativeButton("취소", (dialog, which) -> {
                    // 취소 버튼을 누른 경우 삭제 동작 취소
                    customAdapter.notifyItemChanged(position);
                })
                .show();
    }
    private String getPhoneNumber(int position) {
        // 해당 position에 해당하는 아이템의 전화번호를 가져오는 코드를 작성하세요.
        String[] data = testDataSet.get(position).split("\n");
        return data[1];
    }
}

