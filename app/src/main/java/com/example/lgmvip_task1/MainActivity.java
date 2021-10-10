package com.example.lgmvip_task1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String url = "https://data.covid19india.org/state_district_wise.json";
    ListView list;
    Model model;
    Adapter adapter;
    public static List<Model> modellist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.listview);

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest fetch =new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    Iterator<String> keys =obj.keys();
                    while(keys.hasNext())
                    {
                        String keyName = keys.next();
                        JSONObject object = obj.getJSONObject(keyName).getJSONObject("districtData");
                        Iterator<String> keys2 = object.keys();
                        while(keys2.hasNext())
                        {
                            String cityName = keys2.next();
                            JSONObject dataFetcher = object.getJSONObject(cityName);

                            String notes = dataFetcher.getString("notes");
                            String active = dataFetcher.getString("active");
                            String confirmed = dataFetcher.getString("confirmed");
                            String migratedother = dataFetcher.getString("migratedother");
                            String deceased = dataFetcher.getString("deceased");
                            String recovered = dataFetcher.getString("recovered");
                            String dconfirmed = dataFetcher.getJSONObject("delta").getString("confirmed");
                            String ddeceased = dataFetcher.getJSONObject("delta").getString("deceased");
                            String drecovered = dataFetcher.getJSONObject("delta").getString("recovered");

                            model = new Model(cityName, notes, active,confirmed,migratedother,deceased,recovered,dconfirmed,ddeceased,drecovered);
                            modellist.add(model);
                        }
                        adapter = new Adapter(MainActivity.this,modellist);
                        list.setAdapter(adapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        queue.add(fetch);

    }
}