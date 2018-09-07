package com.example.sony.tes.Flash;

/**
 * Created by SONY on 17/7/2018.
 */

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.content.Intent;

import com.example.sony.tes.Murid.LoginMuridActivity;
import com.example.sony.tes.R;

public class SplashActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_splash);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), LoginMuridActivity.class));
                finish();
            }
        }, 2000L); //3000 L = 3 detik
    }

}
