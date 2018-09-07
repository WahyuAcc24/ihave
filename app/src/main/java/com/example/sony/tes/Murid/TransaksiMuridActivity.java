package com.example.sony.tes.Murid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sony.tes.R;

import io.isfaaghyth.rak.Rak;

/**
 * Created by SONY on 7/8/2018.
 */
public class TransaksiMuridActivity extends AppCompatActivity {

    TextView namaRek;
    ImageView fotoRek;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saldo_murid);
        Rak.initialize(this);


        ImageView histori = (ImageView) findViewById(R.id.imgMenuHistory);
        histori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransaksiMuridActivity.this, HistoryMuridActivity.class);
                startActivity(i);

            }
        });

        ImageView home = (ImageView) findViewById(R.id.imgMenuHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransaksiMuridActivity.this, HomeMuridActivity.class);
                startActivity(i);

            }
        });

        ImageView setting = (ImageView) findViewById(R.id.imgMenuSetting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransaksiMuridActivity.this, SettingMuridActivity.class);
                startActivity(i);

            }
        });

        ImageView logout = (ImageView) findViewById(R.id.imgMenuLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransaksiMuridActivity.this, LoginMuridActivity.class);
                startActivity(i);

            }
        });

        //  tombol back
        ImageView backButton = (ImageView) this.findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fotoRek = (ImageView) findViewById(R.id.imgSaldo);
        namaRek = (TextView) findViewById(R.id.namaSaldo);







    }
}
