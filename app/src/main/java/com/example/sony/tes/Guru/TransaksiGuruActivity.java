package com.example.sony.tes.Guru;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.sony.tes.Murid.HistoryMuridActivity;
import com.example.sony.tes.Murid.HomeMuridActivity;
import com.example.sony.tes.Murid.LoginMuridActivity;
import com.example.sony.tes.R;

import io.isfaaghyth.rak.Rak;

/**
 * Created by SONY on 8/8/2018.
 */
public class TransaksiGuruActivity extends AppCompatActivity {

    ImageView home,histori,logout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
//        getSupportActionBar().hide();
        setContentView(R.layout.saldo_guru);

        ImageView backButton = (ImageView) this.findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        home = (ImageView) findViewById(R.id.imgMenuHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransaksiGuruActivity.this, HomeMuridActivity.class);
                startActivity(i);
            }
        });

        histori = (ImageView) findViewById(R.id.imgMenuHistory);
        histori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransaksiGuruActivity.this, HistoryMuridActivity.class);
                startActivity(i);
            }
        });

        logout = (ImageView) findViewById(R.id.imgMenuLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransaksiGuruActivity.this, LoginMuridActivity.class);
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