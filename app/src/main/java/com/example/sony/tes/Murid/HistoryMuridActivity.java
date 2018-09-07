package com.example.sony.tes.Murid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.sony.tes.R;
import com.example.sony.tes.Video.VideoChatActivity;

/**
 * Created by SONY on 7/8/2018.
 */
public class HistoryMuridActivity extends AppCompatActivity {

    LinearLayout lnHistory;
    ImageView imageView , home, transaksi, logout,setting;
    public static String urlGambar = "http://hardrockfm.com/wp-content/uploads/2016/06/raisa.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_historymurid);

        lnHistory = (LinearLayout) findViewById(R.id.linearHistory);
        lnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HistoryMuridActivity.this, VideoChatActivity.class);
                startActivity(i);
            }
        });


        imageView = (ImageView) findViewById(R.id.imgGuruhistory);
        Glide.with(HistoryMuridActivity.this)
                .load(urlGambar)
                .placeholder(R.drawable.clock_loading)
                .into(imageView);

        imageView = (ImageView) findViewById(R.id.imgGuruhistory2);
        Glide.with(HistoryMuridActivity.this)
                .load(urlGambar)
                .placeholder(R.drawable.clock_loading)
                .into(imageView);

        imageView = (ImageView) findViewById(R.id.imgGuruhistory3);
        Glide.with(HistoryMuridActivity.this)
                .load(urlGambar)
                .placeholder(R.drawable.clock_loading)
                .into(imageView);


        home = (ImageView) findViewById(R.id.imgMenuHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HistoryMuridActivity.this, HomeMuridActivity.class);
                startActivity(i);
            }
        });

        transaksi = (ImageView) findViewById(R.id.imgMenuSaldo);
        transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HistoryMuridActivity.this, TransaksiMuridActivity.class);
                startActivity(i);
            }
        });

        setting = (ImageView) findViewById(R.id.imgMenuSetting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HistoryMuridActivity.this, SettingMuridActivity.class);
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