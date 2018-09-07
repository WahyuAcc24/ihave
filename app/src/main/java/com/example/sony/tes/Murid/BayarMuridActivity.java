package com.example.sony.tes.Murid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.sony.tes.R;

/**
 * Created by SONY on 9/8/2018.
 */
public class BayarMuridActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaksi_murid);

        Button ok = (Button) findViewById(R.id.btnOkmrd);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BayarMuridActivity.this, LoginMuridActivity.class);
                startActivity(i);
            }
        });
    }
}
