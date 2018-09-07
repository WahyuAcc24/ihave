package com.example.sony.tes.Guru;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.sony.tes.R;
import com.example.sony.tes.Video.VideoCallActivity;

/**
 * Created by SONY on 7/8/2018.
 */
public class HistoryGuruActivity extends AppCompatActivity {

    ImageView home, transaksi, setting, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_historyguru);


        LinearLayout lnHistory = (LinearLayout) findViewById(R.id.linearHistoryGuru1);
        lnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HistoryGuruActivity.this, VideoCallActivity.class);
                startActivity(i);
            }
        });



        home = (ImageView) findViewById(R.id.imgMenuHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HistoryGuruActivity.this, HomeGuruActivity.class);
                startActivity(i);
            }
        });

        transaksi = (ImageView) findViewById(R.id.imgMenuSaldo);
        transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HistoryGuruActivity.this,TransaksiGuruActivity.class );
            }
        });

        setting = (ImageView) findViewById(R.id.imgMenuSetting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HistoryGuruActivity.this, SettingGuruActivity.class);
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



    }



}
