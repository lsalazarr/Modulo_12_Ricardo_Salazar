package com.nextu.modulo12.trabajofinal.ricardos.modulo12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

public class segunda_Pantalla extends AppCompatActivity {
    CircleImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda__pantalla);
        imageView = (CircleImageView) findViewById(R.id.profile_image);
        String userID= getIntent().getStringExtra("id");


        Picasso.with(this)
                .load("https://graph.facebook.com/" + userID+ "/picture?type=large").
                into(imageView);
        ((TextView) findViewById(R.id.nombre)).setText(getIntent().getStringExtra("nombre"));
        ((TextView) findViewById(R.id.correo)).setText(getIntent().getStringExtra("email"));


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
