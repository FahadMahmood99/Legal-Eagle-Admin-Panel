package com.example.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Intent iHome = new Intent(SplashScreen.this,MainActivity.class);

        img=findViewById(R.id.img);

        Animation move= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale);
        img.startAnimation(move);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                startActivity(iHome);

                finish();
            }
        },4000);

    }
}