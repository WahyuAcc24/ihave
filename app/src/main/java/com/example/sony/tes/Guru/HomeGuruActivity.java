package com.example.sony.tes.Guru;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sony.tes.Murid.LoginMuridActivity;
import com.example.sony.tes.R;

import io.isfaaghyth.rak.Rak;

/**
 * Created by SONY on 7/8/2018.
 */
public class HomeGuruActivity extends AppCompatActivity {

    ImageView home, history, setting , transaksi, logout, imageView;
    TextView txtjudulguru, txtmatpelguru,txtrpguru, txthobiguru;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_guru);
        Rak.initialize(this);

        txtjudulguru = (TextView) findViewById(R.id.judulGuru);
        txtjudulguru.setText((String)Rak.grab("fullname"));

        txtmatpelguru = (TextView) findViewById(R.id.txtMatpelguru);
        txtmatpelguru.setText((String)Rak.grab("pelajaran"));

        txtrpguru = (TextView) findViewById(R.id.txtRpguru);
        txtrpguru.setText((String)Rak.grab("lulusan"));

        txthobiguru = (TextView) findViewById(R.id.txthobiguru);
        txthobiguru.setText((String)Rak.grab("hobby"));


        imageView = (ImageView) findViewById(R.id.imgGuru);
        Glide.with(imageView.getContext())
                .load(Rak.grab("images"))
                .error(R.drawable.ihave_logo_blue)
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
                Intent i = new Intent(HomeGuruActivity.this, LoginGuruActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                Rak.entry("loginguru", false);
                Rak.removeAll(getApplicationContext());
                finishAffinity();
            }
        });



    }
}

