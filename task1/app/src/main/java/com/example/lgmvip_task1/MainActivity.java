package com.example.lgmvip_task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    private String url = "https://data.covid19india.org/state_district_wise.json";
    ListView list;
    Model modelState;
    Adapter adapter;
    public static List<List<List<String>>> modelListCity = new ArrayList<>();
    public static List<Model> modelListState = new ArrayList<>();

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
                        String stateName = keys.next();

                        Long totalActive = 0L, totalConfirmed= 0L, totalMigrated= 0L, totalDeceased= 0L,
                                totalRecovered= 0L, totalDconfimed= 0L, totalDdeceased= 0L, totalDrecovered= 0L;

                        List<List<String>> stateCityData = new ArrayList<>();

                        JSONObject object = obj.getJSONObject(stateName).getJSONObject("districtData");
                        Iterator<String> keys2 = object.keys();
                        while(keys2.hasNext())
                        {
                            String cityName = keys2.next();
                            JSONObject dataFetcher = object.getJSONObject(cityName);

                            String notes = dataFetcher.getString("notes");

                            String active = dataFetcher.getString("active");
                            totalActive = sum(totalActive,active);

                            String confirmed = dataFetcher.getString("confirmed");
                            totalConfirmed = sum(totalConfirmed,confirmed);

                            String migratedother = dataFetcher.getString("migratedother");
                            totalMigrated = sum(totalMigrated,confirmed);

                            String deceased = dataFetcher.getString("deceased");
                            totalDeceased = sum(totalDeceased,confirmed);

                            String recovered = dataFetcher.getString("recovered");
                            totalRecovered = sum(totalRecovered,confirmed);

                            String dconfirmed = dataFetcher.getJSONObject("delta").getString("confirmed");
                            totalDconfimed = sum(totalDconfimed,confirmed);

                            String ddeceased = dataFetcher.getJSONObject("delta").getString("deceased");
                            totalDdeceased = sum(totalDdeceased,confirmed);

                            String drecovered = dataFetcher.getJSONObject("delta").getString("recovered");
                            totalDrecovered = sum(totalDrecovered,confirmed);

                            List<String> cityData = new ArrayList<>();
                            cityData.add(cityName);
                            cityData.add(notes);
                            cityData.add(active);
                            cityData.add(confirmed);
                            cityData.add(migratedother);
                            cityData.add(deceased);
                            cityData.add(recovered);
                            cityData.add(dconfirmed);
                            cityData.add(ddeceased);
                            cityData.add(drecovered);

                            stateCityData.add(cityData);
                        }
                        modelListCity.add(stateCityData);

                        modelState = new Model(stateName, totalActive.toString(),totalConfirmed.toString(),
                                totalMigrated.toString(),totalDeceased.toString(), totalRecovered.toString(),
                                totalDconfimed.toString(), totalDdeceased.toString(),
                                totalDrecovered.toString());

                        modelListState.add(modelState);

                        adapter = new Adapter(MainActivity.this, modelListState);
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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);

                List<List<String>> a = modelListCity.get(position);
                int i = 0;
                List<String> data = new ArrayList<>();
                ListIterator iterator = a.listIterator();
                while(iterator.hasNext())
                {

                    try {
                        intent.putStringArrayListExtra(a.get(i).get(0), (ArrayList<String>) a.get(i));

                        data.add(a.get(i).get(0));
                        i++;
                    }catch(IndexOutOfBoundsException e)
                    {
//                        Toast.makeText(MainActivity.this, "IndexOutOfBoundsException" , Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

                intent.putStringArrayListExtra("cityNames", (ArrayList<String>) data);
                startActivity(intent);

            }
        });

    }

    public static Long sum(Long a, String b)
    {
        return a+Long.parseLong(b);
    }
}