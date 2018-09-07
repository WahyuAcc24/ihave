package com.example.sony.tes.Pelajaran;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.sony.tes.Model.mrd;
import com.example.sony.tes.R;
import com.google.gson.Gson;

/**
 * Created by SONY on 28/8/2018.
 */
public class Coba extends AppCompatActivity {

    TextView ts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coba);

    ts = (TextView) findViewById(R.id.coba);

        mrd data = new Gson().fromJson(getIntent().getStringExtra("data"), mrd.class);

        ts.setText(data.getName());



    }
}