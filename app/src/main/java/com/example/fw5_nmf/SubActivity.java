package com.example.fw5_nmf;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {
    private Fragment1 fragment1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data);
        fragment1 = new Fragment1();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.add_contents, fragment1)
                .commit();
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
                    if (fragment1 != null) {
                        Toast.makeText(SubActivity.this, "성공적으로 저장했습니다.", Toast.LENGTH_SHORT).show();
                        String data = name + ": " + number;
                        fragment1.addToTestDataSet(data);
                    } else {
                        Toast.makeText(SubActivity.this, "Fragment1이 초기화되지 않았습니다.", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }
            }
        });
    }
}
