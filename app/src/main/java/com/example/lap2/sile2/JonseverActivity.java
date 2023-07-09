package com.example.lap2.sile2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lap2.R;

public class JonseverActivity extends AppCompatActivity {
    ProgressBar process;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jonsever);
        process=findViewById(R.id.pro);
        Button btn =findViewById(R.id.load);
        TextView tv =findViewById(R.id.tv);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        Thread thread = new Thread(new Runnable() { // luong thu 2
            @Override
            public void run() {
                for (int i = 0; i < 10000000; i++) {
                    Log.d("LongTask", i + "");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        process.setVisibility(View.GONE);

                        tv.setText("MD17301 - MOB403");
                    }
                });

            }
        });

        AsyncTask<Void, Integer, String> asyncTask = new AsyncTask<Void, Integer, String>() {
            @Override
            protected String doInBackground(Void... voids) {

                for (int i = 1; i < 10000000; i++) {
                    Log.d("LongTask", i + "");

                    if (i % 1000000 == 0) {
                        publishProgress((i / 1000000) * 10);
                    }

                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                process.setVisibility(View.GONE);

                tv.setText("MD17301 - MOB403");
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);

                process.setProgress(values[0]);
            }
        };

        asyncTask.execute();

        //thread.start();

        // xu ly don luong

    }
}