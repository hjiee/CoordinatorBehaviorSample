package com.feel.horizontalscrollview;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.listview);
        arrayList = new ArrayList<String>();
        initList();
        recyclerAdapter = new RecyclerAdapter(this,arrayList);
//        recyclerView.addHeaderView(header);
//        recyclerView.addFooterView(footer);
        recyclerView.setAdapter(recyclerAdapter);


    }

    private void initList() {
        for(int i=1; i<=100; i++) {
            arrayList.add(String.valueOf(i));
        }
    }
}
