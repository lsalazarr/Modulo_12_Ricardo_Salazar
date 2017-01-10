package com.nextu.modulo12.trabajofinal.ricardos.modulo12;

import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.InterstitialAd;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class segunda_Pantalla extends AppCompatActivity {

    CircleImageView imageView;

    private InterstitialAd interstitialAd;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda__pantalla);
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.id_publicidad));
        imageView = (CircleImageView) findViewById(R.id.profile_image);
        String userID= getIntent().getStringExtra("id");

        Picasso.with(this)
                .load("https://graph.facebook.com/" + userID+ "/picture?type=large").
                into(imageView);
        ((TextView) findViewById(R.id.nombre)).setText(getIntent().getStringExtra("nombre"));

    }

    @Override
    protected void onPause() {
        countDownTimer.cancel();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        iniciar();
    }
}
