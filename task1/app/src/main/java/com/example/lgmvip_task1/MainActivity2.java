package com.example.lgmvip_task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MainActivity2 extends AppCompatActivity {

    Model model;
    Adapter adapter;
    List<Model> modelList = new ArrayList<>();
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        list = findViewById(R.id.listview2);

        Intent intent = getIntent();
        List<String> cityNames = intent.getStringArrayListExtra("cityNames");
//        System.out.println(cityNames.get(0));
        int i = 0;

        ListIterator iterator = cityNames.listIterator();
        while(iterator.hasNext())
        {
            try{
                List<String> data = intent.getStringArrayListExtra(cityNames.get(i));
                String cityName = data.get(0);
                String notes = data.get(1);
                String active = data.get(2);
                String confirmed = data.get(3);
                String migrate = data.get(4);
                String deceased = data.get(5);
                String recovered = data.get(6);
                String dconfirmed = data.get(7);
                String ddeceased = data.get(8);
                String drecovered = data.get(9);
                model = new Model(cityName,notes,active,confirmed,migrate,deceased,recovered,dconfirmed,
                        ddeceased,drecovered);
                modelList.add(model);

                i++;
            }catch(IndexOutOfBoundsException e)
            {
                break;
            }

        }
        adapter = new Adapter(MainActivity2.this,modelList);
        list.setAdapter(adapter);

    }
}