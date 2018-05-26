package com.example.vasyl.thirdlab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class OpenFileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_file);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String text = intent.getStringExtra(MainActivity.EXTRA_TEXT);
        String size = intent.getStringExtra(MainActivity.EXTRA_SIZE);


        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setText(text);
        textView.setTextSize(Float.parseFloat(size));
    }
}
