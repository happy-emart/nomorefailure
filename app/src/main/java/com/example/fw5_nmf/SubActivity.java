package com.example.fw5_nmf;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data);
        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText1 = findViewById(R.id.editText1);
                EditText editText2 = findViewById(R.id.editText2);

                String name = editText1.getText().toString();
                String number = editText2.getText().toString();
                if (name.isEmpty() || number.isEmpty()) {
                    Toast.makeText(SubActivity.this, "이름 또는 번호가 공백입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Fragment1.addToTestDataSet(name, number);
                    finish();
                }
            }
        });
    }
}
