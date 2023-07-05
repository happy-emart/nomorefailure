package com.example.fw5_nmf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

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
        ViewPager2Adapter viewPager2Adapter = new ViewPager2Adapter(getSupportFragmentManager(), getLifecycle());
        ViewPager2 viewPager2 = findViewById(R.id.pager);
        viewPager2.setAdapter(viewPager2Adapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Contact");
                        break;
                    case 1:
                        tab.setText("Gallery");
                        break;
                    case 2:
                        tab.setText("To-Do");
                        break;
                }
            }
        }).attach();

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                // 키보드 숨기기
                hideKeyboard();
            }
        });
//        fragmentManager = getSupportFragmentManager();
//        fragment1 = new Fragment1();
//        fragment2 = new Fragment2();
//        fragment3 = new Fragment3();

//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.contents, fragment1);
//        fragmentTransaction.commit();
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                if (tab.getPosition() == 0) {
//                    transaction.replace(R.id.contents, fragment1);
//                } else if (tab.getPosition() == 1) {
//                    transaction.replace(R.id.contents, fragment2);
//                } else if (tab.getPosition() == 2) {
//                    transaction.replace(R.id.contents, fragment3);
//                }
//                transaction.commit();
//            }
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                // do nothing
//            }
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//                // do nothing
//            }
//        });
    }
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
