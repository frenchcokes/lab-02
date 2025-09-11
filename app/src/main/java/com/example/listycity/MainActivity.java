package com.example.listycity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private ListView cityList;
    private ArrayList<String> dataList;
    private ArrayAdapter<String> cityAdapter;
    public int selectedCityIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        dataList = new ArrayList<>(Arrays.asList("Toronto", "Alberta", "Saskatoon", "Victoria"));
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        final LinearLayout add_city_ui = findViewById(R.id.add_city_ui);
        final Button addCityShowButton = findViewById(R.id.add_city_show);
        addCityShowButton.setOnClickListener(v -> {
            final int add_city_ui_visibility = add_city_ui.getVisibility();
            if (add_city_ui_visibility == View.VISIBLE) {
                add_city_ui.setVisibility(View.GONE);
            } else {
                add_city_ui.setVisibility(View.VISIBLE);
            }
        });

        final Button addCitySubmitButton = findViewById(R.id.add_city_submit);
        final EditText addCityTextInput = findViewById(R.id.add_city_text_input);
        addCitySubmitButton.setOnClickListener(v -> {
            final String newCity = String.valueOf(addCityTextInput.getText());
            dataList.add(newCity);
            cityAdapter.notifyDataSetChanged();
        });

        final Button removeCitySubmitButton = findViewById(R.id.remove_city_submit);
        removeCitySubmitButton.setOnClickListener(v -> {
            if (selectedCityIndex != -1) {
                dataList.remove(selectedCityIndex);
                cityAdapter.notifyDataSetChanged();
                cityList.getChildAt(selectedCityIndex).setBackgroundColor(Color.TRANSPARENT);
                selectedCityIndex = -1;
                removeCitySubmitButton.setEnabled(false);
            }
        });

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            if (selectedCityIndex != -1) {
                cityList.getChildAt(selectedCityIndex).setBackgroundColor(Color.TRANSPARENT);
            }
            selectedCityIndex = position;
            view.setBackgroundColor(Color.LTGRAY);
            removeCitySubmitButton.setEnabled(true);
        });

        // Start State
        add_city_ui.setVisibility(View.GONE);
        removeCitySubmitButton.setEnabled(false);
        selectedCityIndex = -1;
    }
}