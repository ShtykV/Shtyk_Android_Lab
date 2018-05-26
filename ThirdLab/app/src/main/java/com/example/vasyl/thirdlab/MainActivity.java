package com.example.vasyl.thirdlab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT = "Text", EXTRA_SIZE="Size";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.font_size));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner. setAdapter(adapter);


    }

    public void OkClick(View V){
        EditText editText = (EditText) findViewById(R.id.editText);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        editText.setTextSize(Float.parseFloat(spinner.getSelectedItem().toString()));

        try {
            // отрываем поток для записи
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput("file", MODE_PRIVATE)));
            // пишем данные
            bw.write(editText.getText().toString()+"\n"+spinner.getSelectedItem().toString());
            // закрываем поток
            bw.close();
            Log.d("MyLog", "File saved");
            Toast.makeText(this, "File saved", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void CancelClick(View V){
        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setText(null);
    }

    public void OpenClick(View V){
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput("file")));
            String str = "", str2 = "";
            // читаем содержимое
            str = br.readLine();
            str2 = br.readLine();
            if(str2 == "")
            {
                Toast.makeText(this, "File is empty!", Toast.LENGTH_LONG).show();
            }
            else
            {
                try {
                    Intent intent = new Intent(this, OpenFileActivity.class);
                    intent.putExtra(EXTRA_TEXT, str);
                    intent.putExtra(EXTRA_SIZE, str2);
                    startActivity(intent);
                }
                catch (Exception e){
                    Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
