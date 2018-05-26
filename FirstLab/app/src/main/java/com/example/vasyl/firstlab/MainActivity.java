package com.example.vasyl.firstlab;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.font_size, android.R.layout.simple_spinner_dropdown_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner. setAdapter(adapter);
        spinner.setPrompt("Size");
        spinner.setSelection(1);
        final TextView textView=(TextView)  findViewById(R.id.editText);
        // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                textView.setTextSize(TypedValue.COMPLEX_UNIT_IN, ((float)id)/10);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }



}
