package com.example.sony.tes.Guru;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.sony.tes.Adapter.JamHomeAdapter;
import com.example.sony.tes.Model.LoginGuru;
import com.example.sony.tes.R;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Map;

import io.isfaaghyth.rak.Rak;

/**
 * Created by SONY on 7/8/2018.
 */
public class HomeGuruActivity extends AppCompatActivity {

    ImageView home, history, setting , transaksi, logout, imageView;
    TextView txtjudulguru, txtmatpelguru,txtrpguru, txthobiguru;

    private RequestQueue requestQueue;
    private com.google.gson.Gson gson;
    private RecyclerView lstJadwal;
    ProgressDialog pDialog;
    ProgressBar pgList;
    public List<LoginGuru> loginguru;
    ConnectivityManager conMgr;

    private JamHomeAdapter adapterJadwal;
    private static final String TAG = HomeGuruActivity.class.getSimpleName();
    String tag_json_obj = "json_obj_req";

    private String url;
    private Map<String, List<Integer>> collectTime; //PENAMPUNG JAM YANG DI PILIH



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_guru);
        Rak.initialize(this);

        url ="http://demo.t-hisyam.net/ihave/api/guru/get";
//        lstJadwal = (RecyclerView) findViewById(R.id.jadwal_home_guru);
//        lstJadwal.setLayoutManager(new LinearLayoutManager(this));

//        collectTime = new LinkedHashMap<>();

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        requestQueue = Volley.newRequestQueue(this);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

//        loginguru = new ArrayList<>();


        txtjudulguru = (TextView) findViewById(R.id.judulGuru);
        txtjudulguru.setText((String)Rak.grab("fullname"));

        txtmatpelguru = (TextView) findViewById(R.id.txtMatpelguru);
        txtmatpelguru.setText((String)Rak.grab("pelajaran"));

        txtrpguru = (TextView) findViewById(R.id.txtRpguru);
        txtrpguru.setText((String)Rak.grab("lulusan"));

        txthobiguru = (TextView) findViewById(R.id.txthobiguru);
        txthobiguru.setText((String)Rak.grab("hobby"));

//        List<Jadwal> jadwal = getIntent().("jadwalguru");


//        List<Jadwal> jadwalJson = Rak.grab("jadwal");
//        List<Jadwal> jadwalGuru = new Gson().fromJson(jadwalJson, new TypeToken<List<Jadwal>>(){}.getType());

//        adapterJadwal = new JamHomeAdapter(getApplicationContext(), jadwalJson);
//        lstJadwal.setAdapter(adapterJadwal);


        imageView = (ImageView) findViewById(R.id.imgGuru);
        Glide.with(this)
                .load("http:/lkselkge/" + Rak.grab("images"))
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

