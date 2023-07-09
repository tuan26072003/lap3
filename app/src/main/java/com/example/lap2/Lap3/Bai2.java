package com.example.lap2.Lap3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lap2.R;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Bai2 extends AppCompatActivity {
    private EditText editTextLength;
    private EditText editTextWidth;
    private Button buttonSubmit;
    private TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai5);

        editTextWidth = findViewById(R.id.editTextWidth);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        textViewResult = findViewById(R.id.textViewResult);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String length = editTextLength.getText().toString();
                String width = editTextWidth.getText().toString();

                if (!length.isEmpty() && !width.isEmpty()) {
                    String url = "http://http://localhost:8686/web/student_Get.php?name=tuan&score=21";
                    String postData = "length=" + URLEncoder.encode(length) + "&width=" + URLEncoder.encode(width);
                    new CalculateRectangleTask().execute(url, postData);
                }
            }
        });
    }

    private class CalculateRectangleTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            String postData = params[1];
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            StringBuilder result = new StringBuilder();

            try {
                URL requestUrl = new URL(url);
                urlConnection = (HttpURLConnection) requestUrl.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);

                OutputStream outputStream = urlConnection.getOutputStream();
                outputStream.write(postData.getBytes());
                outputStream.flush();
                outputStream.close();

                InputStream inputStream = urlConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            textViewResult.setText(result);
        }
    }


}