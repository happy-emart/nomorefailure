package com.example.fw5_nmf;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.tabs.TabLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout = findViewById(R.id.tabs);

        fragmentManager = getSupportFragmentManager();
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        if (fragment1 != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contents, fragment1);
            fragmentTransaction.commit();
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (position) {
                    case 0:
                        if (fragment1 != null) {
                            fragmentTransaction.replace(R.id.text1contents, fragment1);
                        }
                        break;
                    case 1:
                        if (fragment2 != null) {
                            fragmentTransaction.replace(R.id.gallery, fragment2);
                        }
                        break;
                    case 2:
                        if (fragment3 != null) {
                            fragmentTransaction.replace(R.id.fragment3, fragment3);
                        }
                        break;
                }
                fragmentTransaction.commit(); // commit() 호출은 switch 문 이후에 한 번만 수행되도록 수정
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // do nothing
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // do nothing
            }
        });
    }
}
