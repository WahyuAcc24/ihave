package com.example.sony.tes.Murid;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sony.tes.Model.Saldo;
import com.example.sony.tes.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.isfaaghyth.rak.Rak;

/**
 * Created by SONY on 7/8/2018.
 */
public class TransaksiMuridActivity extends AppCompatActivity {


    private Gson gson;
    TextView txtNama, txtSaldo;
    LinearLayout lnhistori, lnbca, lnbersama, lnbni, lnbri;
    private RequestQueue requestQueue;
    ProgressDialog pDialog;
    ProgressBar pgList;
    ConnectivityManager conMgr;
    private List<Saldo> saldo;

    private String url;
    TextView namaRek;
    ImageView fotoRek;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saldo_murid);
        Rak.initialize(this);

        url = "http://demo.t-hisyam.net/ihave/api/saldo/get_saldo_murid?id_murid=" + Rak.grab("id");
        requestQueue = Volley.newRequestQueue(this);
        ambilSaldo();
        // definisikan objek gsonbuilder yang bertugas melakukan deserialisasi data json ke gson
        GsonBuilder gsonBuilder = new GsonBuilder();
        // inisialisasi obj gson
        gson = gsonBuilder.create();

        saldo = new ArrayList<>();


        txtNama = (TextView) findViewById(R.id.namaSaldo);
        txtNama.setText((String) Rak.grab("fullname"));


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

        LinearLayout bersama = (LinearLayout) findViewById(R.id.atmbersama);
        bersama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NominalDialogFragment dialog = NominalDialogFragment.getInstance();
                dialog.show(getSupportFragmentManager(), "Home");
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

    private void ambilSaldo() {
        StringRequest request = new StringRequest(Request.Method.GET, url, onPostsLoaded, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder inetErr = new AlertDialog.Builder(TransaksiMuridActivity.this);
                inetErr.setTitle("Terjadi Kesalahan");
                inetErr.setMessage("Periksa kembali koneksi internet anda.");
                inetErr.setNegativeButton("RETRY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ambilSaldo();
                    }
                });
                inetErr.show();
            }
        });
        requestQueue.add(request);

    }

    private final Response.Listener<String> onPostsLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            //gambar loading akan menghilang ketika proses berjalan dengan baik

            Log.d("ini json", response);


            Saldo saldo = new Gson().fromJson(response, Saldo.class);

            txtSaldo = (TextView) findViewById(R.id.totalsaldo);
            txtSaldo.setText(saldo.getSaldo());


        }
    };
}