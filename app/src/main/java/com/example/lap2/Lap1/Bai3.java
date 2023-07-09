package com.example.lap2.Lap1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lap2.R;

public class Bai3 extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgLoad;
    private TextView tvMsg;
    private Button btnLoadEx3;
    private static final String IMAGE_URL = "https://ap.poly.edu.vn/images/logo.png";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);
        imgLoad = (ImageView) findViewById(R.id.imgLoad);
        tvMsg = (TextView) findViewById(R.id.tvMsg);
        btnLoadEx3 = (Button) findViewById(R.id.btnLoadEx3);

        btnLoadEx3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //new LoadImageTask(this, this).execute(IMAGE_URL);

        new LoadImageTask().execute(IMAGE_URL);
    }

    class LoadImageTask extends AsyncTask<String, Void, Bitmap> {


        @Override
        protected Bitmap doInBackground(String... strings) {

            Bitmap bitmap = Bai2.downloadBitmap(strings[0]);

            return bitmap;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(Bai3.this);
            progressDialog.show();

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            imgLoad.setImageBitmap(bitmap);
        }
    }

//    @Override
//    public void onImageLoaded(Bitmap bitmap) {
//        imgLoad.setImageBitmap(bitmap);
//        tvMsg.setText("Image Downloaded!");
//    }

//    @Override
//    public void onError() {
//        tvMsg.setText("Error download image!");
//    }
}