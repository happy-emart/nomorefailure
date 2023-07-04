package com.example.fw5_nmf;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        customAdapter.setOnItemLongClickListener(new CustomAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int position) {
                testDataSet.remove(position);
                customAdapter.notifyDataSetChanged();
            }
        });

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
                    String data = name + ": " + number;
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
    public static void addToTestDataSet(String data) {
        testDataSet.add(data);
        customAdapter.notifyDataSetChanged();
    }
}

