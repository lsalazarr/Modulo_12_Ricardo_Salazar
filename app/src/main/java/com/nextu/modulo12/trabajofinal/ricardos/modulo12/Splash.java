package com.nextu.modulo12.trabajofinal.ricardos.modulo12;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Splash extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    Intent mainIntent = new Intent(Splash.this,MainActivity.class);
                    Splash.this.startActivity(mainIntent);
                    Splash.this.finish();
                }
            }, SPLASH_DISPLAY_LENGTH);


    }
}
