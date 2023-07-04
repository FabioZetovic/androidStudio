package com.example.farcade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mainPL;
    Button btnPetar;
    Button xo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPetar = (Button) findViewById(R.id.toPetar);
        xo = (Button)findViewById(R.id.xo);


        mainPL = MediaPlayer.create(MainActivity.this,R.raw.sunce);
        mainPL.setLooping(true);
        mainPL.start();

        ConstraintLayout constraintLayout = findViewById(R.id.glavniLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();



 View.OnClickListener ubime = new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         otvoriPetra();
         mainPL.stop();
     }
    };btnPetar.setOnClickListener(ubime);


    View.OnClickListener xxo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            krizic();
            mainPL.stop();
        }

    };xo.setOnClickListener(xxo);







        }

        public void otvoriPetra(){
            Intent intentP = new Intent(this,petar.class);
            startActivity(intentP);
    }

    public void krizic(){
        Intent intentK = new Intent(this,TikTak.class);
        startActivity(intentK);
    }
}