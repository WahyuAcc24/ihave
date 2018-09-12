package com.example.sony.tes.Guru;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.sony.tes.R;

/**
 * Created by SONY on 7/8/2018.
 */
public class SettingGuruActivity extends AppCompatActivity {

    ImageView home, history, transaksi, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_guru);

        ImageView backButton = (ImageView) this.findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


//        home = (ImageView) findViewById(R.id.imgMenuHome);
//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(SettingGuruActivity.this, HomeGuruActivity.class);
//                startActivity(i);
//            }
//        });
//
//        History = (ImageView) findViewById(R.id.imgMenuHistory);
//        History.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(SettingGuruActivity.this, HistoryGuruActivity.class);
//                startActivity(i);
//            }
//        });



    }
}
