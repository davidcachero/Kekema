package com.proyecto.kekema.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.proyecto.kekema.R;
import com.proyecto.kekema.adapter.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private String[] tabTitles = new String[]{"Login", "Register"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        sectionsPagerAdapter = new SectionsPagerAdapter(this);

        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout.setVisibility(View.GONE);
        //new TabLayoutMediator(tabLayout, viewPager,((tab, position) -> tab.setText(tabTitles[position]))).attach();
    }
}