package com.example.lab_5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayList<String> listItems_2 = new ArrayList<>();
    ArrayAdapter<String> adapter_2;

    TextView tvContent;
    ListView lvContent;
    ListView lvContent_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tvContent = findViewById(R.id.tvContent);
        this.lvContent = findViewById(R.id.lvContent);
        this.lvContent_2 = findViewById(R.id.lvContent_2);

        this.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.listItems);
        this.lvContent.setAdapter(adapter);
        this.adapter_2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.listItems_2);
        this.lvContent_2.setAdapter(adapter_2);
    }

    @SuppressLint({"SetTextI18n", "StaticFieldLeak"})
    public void onBtnDownloadClick(View view) {
        this.tvContent.setText("Loading...");
        listItems.clear();
        String[] oneOfCities = {"Kaunas","Kiev","Warsaw","Barcelona","Paris","London"};
        for(int i = 0; i< oneOfCities.length; ++i) {
            new DataLoader() {
                @Override
                public void onPostExecute(String result) {
                    String[] output = result.split(" ");

                    listItems.add(output[0]);
                    adapter.notifyDataSetChanged();
                    listItems_2.add(output[1]);
                    adapter_2.notifyDataSetChanged();
                    tvContent.setText("Weather downloaded!");
                }
            }.execute(oneOfCities[i]);
        }
    }
}