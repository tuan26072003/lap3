package com.example.lap2.Lap1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lap2.R;

public class MainActivity2 extends AppCompatActivity {
    private Button btnEx1;
    private Button btnEx2;
    private Button btnEx3;
    private Button btnEx4;

    static ImageView ivBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnEx1 = (Button) findViewById(R.id.btnEx1);
        btnEx2 = (Button) findViewById(R.id.btnEx2);
        btnEx3 = (Button) findViewById(R.id.btnEx3);
        btnEx4 = (Button) findViewById(R.id.btnEx4);

        ivBanner = findViewById(R.id.iv);
    }

    public static Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            Bitmap bitmap = bundle.getParcelable("bitmap");
            if (bitmap != null) {
                ivBanner.setImageBitmap(bitmap);
            }

        }
    };

    public  void Bai1 (View view){
        Intent intent = new Intent(MainActivity2.this,MainActivity.class);
        startActivity(intent);
    }
    public  void Bai2 (View view){
        Intent intent = new Intent(MainActivity2.this,Bai2.class);
        startActivity(intent);
    }
    public  void Bai3 (View view){
        Intent intent = new Intent(MainActivity2.this,Bai3.class);
        startActivity(intent);
    }
    public  void Bai4 (View view){
        Intent intent = new Intent(MainActivity2.this,Bai4.class);
        startActivity(intent);
    }
}