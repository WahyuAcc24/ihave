package com.example.sony.tes.Guru;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sony.tes.Murid.LoginMuridActivity;
import com.example.sony.tes.R;

import io.isfaaghyth.rak.Rak;

/**
 * Created by SONY on 7/8/2018.
 */
public class HomeGuruActivity extends AppCompatActivity {

    ImageView home, history, setting , transaksi, logout, imageView;


    public static String urlGambar = "http://hardrockfm.com/wp-content/uploads/2016/06/raisa.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_guru);

        imageView = (ImageView) findViewById(R.id.imgGuru);
        Glide.with(HomeGuruActivity.this)
                .load(urlGambar)
                .placeholder(R.drawable.clock_loading)
                .into(imageView);

        //  tombol back







//        home = (ImageView) findViewById(R.id.imgMenuHome);
//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(HomeGuruActivity.this, HomeGuruActivity.class);
//                startActivity(i);
//            }
//        });


        history = (ImageView) findViewById(R.id.imgMenuHistory);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeGuruActivity.this, HistoryGuruActivity.class);
                startActivity(i);
            }
        });
//
        setting = (ImageView) findViewById(R.id.imgMenuSetting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeGuruActivity.this,SettingGuruActivity.class);
                startActivity(i);
            }
        });
//
        transaksi = (ImageView) findViewById(R.id.imgMenuSaldo);
        transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeGuruActivity.this, TransaksiGuruActivity.class);
                startActivity(i);
            }
        });

        logout = (ImageView) findViewById(R.id.imgMenuLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeGuruActivity.this, LoginMuridActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                Rak.entry("login", false);
                Rak.removeAll(getApplicationContext());
                finishAffinity();
            }
        });



    }
}

