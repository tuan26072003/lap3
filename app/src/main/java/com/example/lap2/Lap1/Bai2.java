package com.example.lap2.Lap1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lap2.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Bai2 extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgLoad;
    private TextView tvMsg;
    private String url = "https://ap.poly.edu.vn/images/logo.png";
    private Button btnLoadEx2;
    private Bitmap bitmap = null;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);
        imgLoad = (ImageView) findViewById(R.id.imgLoad);
        tvMsg = (TextView) findViewById(R.id.tvMsg);
        btnLoadEx2 = (Button) findViewById(R.id.btnLoadEx2);

        btnLoadEx2.setOnClickListener(this);

        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
    }

    private Handler messageHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String message = bundle.getString("message");
            tvMsg.setText(message);
            imgLoad.setImageBitmap(bitmap);
            progressDialog.dismiss();
        }
    };

    public static Bitmap downloadBitmap(String link) {
        try {
            URL url1 = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap1 = BitmapFactory.decodeStream(inputStream);
            return bitmap1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnLoadEx2) {
            progressDialog = ProgressDialog.show(Bai2.this, "", "Downloading ...");
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    bitmap = downloadBitmap(url);
                    Message msg = messageHandler.obtainMessage();
                    Bundle bundle = new Bundle();
                    String threadMessage = "Image downloaded";
                    bundle.putString("message", threadMessage);
                    bundle.putParcelable("bitmap", bitmap);
                    msg.setData(bundle);
                    messageHandler.sendMessage(msg);

                }
            };
            Thread myThread = new Thread(runnable);
            myThread.start();
        } else if (view.getId() == R.id.btn_back) {

            Message msg2 = MainActivity2.handler.obtainMessage();

            Bundle bundle = new Bundle();
            bundle.putParcelable("bitmap", bitmap);

            msg2.setData(bundle);

            MainActivity2.handler.sendMessage(msg2);

            finish();
            //onBackPressed();
        }


    }
}