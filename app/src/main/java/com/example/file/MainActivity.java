package com.example.file;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn1, btn2;
    private EditText et;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.b1);
        btn2 = (Button)findViewById(R.id.b2);
        et= (EditText)findViewById(R.id.et1);
        tv= (TextView) findViewById(R.id.result);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.b1) {
            writeInternal();

        } else if (v.getId() == R.id.b2) {
            readInternal();
        }
    }
    private void writeInternal() {
        String message  = et.getText().toString();
        String filename= "internal_file.txt";

        try {
            FileOutputStream fos = openFileOutput(filename, MODE_PRIVATE);
            fos.write(message.getBytes());
            fos.close();
            Toast.makeText(this, "Message", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void readInternal() {
        try {
            String message;
            FileInputStream fis = openFileInput("internal_file.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer stringBuffer  = new StringBuffer();
            while ((message = br.readLine()) != null) {
                stringBuffer.append(message+"\n");
            }
            tv.setText(stringBuffer.toString());

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}