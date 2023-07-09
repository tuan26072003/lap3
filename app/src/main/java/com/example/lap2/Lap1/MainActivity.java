package com.example.lap2.Lap1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lap2.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgLoad;
    private TextView tvMsg;
    private String url = "https://caodang.fpt.edu.vn/wp-content/uploads/anhcover-01.jpg";

    // Unable to resolve host "ap.poly.edu.vn": No address associated with hostname

    private Bitmap bitmap = null;
    private ProgressDialog progressDialog;
    private Button btnLoadEx1;
    private Button btnLoadEx3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgLoad = (ImageView) findViewById(R.id.imgLoad);
        tvMsg = (TextView) findViewById(R.id.tvMsg);
        btnLoadEx1 = (Button) findViewById(R.id.btnLoadEx1);

        btnLoadEx1.setOnClickListener(this);


    }

    private Bitmap loadImageFromNetWork(String link) {
        URL url;
        Bitmap bmp = null;
        try {
            url = new URL(link);
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }

    @Override
    public void onClick(View view) {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = loadImageFromNetWork(url);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvMsg.setText("Image Downloaded!");
                        imgLoad.setImageBitmap(bitmap);
                    }
                });

            }
        });
        thread.start();
    }
}
