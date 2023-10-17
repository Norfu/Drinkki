package com.estiam.drinkki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

   public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}