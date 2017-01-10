package com.nextu.modulo12.trabajofinal.ricardos.modulo12;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    private CallbackManager cM;
    private LoginButton lB;
    private PublisherAdView publisherAdView;
    String nombre,foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        FacebookSdk.sdkInitialize(getApplicationContext());
        cM = CallbackManager.Factory.create();

        getFbKeyHash("2SUkDCEM6T/vKAAmolnu5Vbcpbg=");

        setContentView(R.layout.activity_main);
        publisherAdView = (PublisherAdView) findViewById(R.id.ad_view);
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder().build();
        publisherAdView.loadAd(adRequest);
        lB = (LoginButton)findViewById(R.id.login_facebook);

        lB.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends"));

        lB.registerCallback(cM, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Toast.makeText(MainActivity.this, "¡Inicio de sesión exitoso!", Toast.LENGTH_LONG).show();
                Profile profile = Profile.getCurrentProfile();
                nombre = profile.getFirstName() +" " +profile.getMiddleName() + " " +profile.getLastName();
                foto = profile.getId().toString();
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(MainActivity.this,segunda_Pantalla.class);
                intent.putExtra("nombre",nombre);
                intent.putExtra("id",foto);
                startActivity(intent);


            }

            @Override
            public void onCancel() {

                Toast.makeText(MainActivity.this, "¡Inicio de sesión cancelado!", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(FacebookException error) {

                Toast.makeText(MainActivity.this, "¡Inicio de sesión NO exitoso!", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void getFbKeyHash(String packageName){

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    packageName, PackageManager.GET_SIGNATURES);
            for(Signature signature : info.signatures){
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash :", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                System.out.println("KeyHash: " + Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }

        } catch (PackageManager.NameNotFoundException e){

        }catch (NoSuchAlgorithmException e){

        }

    }
    protected void onActivityResult(int reqCode, int resCode, Intent i){
        cM.onActivityResult(reqCode, resCode, i);
    }

    @Override
    protected void onDestroy() {
        if(publisherAdView != null){
            publisherAdView.destroy();
        }
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(publisherAdView != null){
            publisherAdView.resume();
        }
    }

    @Override
    protected void onPause() {
        if(publisherAdView != null){
            publisherAdView.pause();
        }
        super.onPause();
    }


    @Override
    public void onBackPressed() {
        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Quiere Salir")
                .setContentText("Usted saldra de la aplicación")
                .setCancelText("No")
                .setConfirmText("Si")
                .showCancelButton(true)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        MainActivity.this.finish();
                    }
                })
                .show();
    }

}
