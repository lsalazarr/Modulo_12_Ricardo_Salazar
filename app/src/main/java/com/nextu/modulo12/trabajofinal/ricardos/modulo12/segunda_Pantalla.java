package com.nextu.modulo12.trabajofinal.ricardos.modulo12;

import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.squareup.picasso.Picasso;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

public class segunda_Pantalla extends AppCompatActivity {

    CircleImageView imageView;
    private InterstitialAd interstitialAd;

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

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                verPublicidad();
            }
        }, 4000);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        iniciar();
    }

    //metodo que permite iniciar la publicidad y cargar admob
    private void iniciar() {
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
    }
    //metodo que permite cargar la publicidad
    private void verPublicidad() {
        if(interstitialAd != null && interstitialAd.isLoaded()){
            interstitialAd.show();
        }else {
            Toast.makeText(this, "No se cargo la Publicidad", Toast.LENGTH_SHORT).show();
            iniciar();
        }
    }

    @Override
    public void onBackPressed() {
        new SweetAlertDialog(segunda_Pantalla.this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Quiere Salir")
                .setContentText("Usted saldra de su cuenta")
                .setCancelText("No")
                .setConfirmText("Si")
                .showCancelButton(true)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent mainIntent = new Intent(segunda_Pantalla.this,MainActivity.class);
                        segunda_Pantalla.this.startActivity(mainIntent);
                        segunda_Pantalla.this.finish();
                    }
                })
                .show();
    }
}
