package com.example.sony.tes.Murid;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sony.tes.Adapter.HistoryAdapter;
import com.example.sony.tes.Adapter.ItemClickListener;
import com.example.sony.tes.Model.History;
import com.example.sony.tes.Model.Histories;
import com.example.sony.tes.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.isfaaghyth.rak.Rak;

/**
 * Created by SONY on 7/8/2018.
 */
public class HistoryMuridActivity extends AppCompatActivity {

    LinearLayout lnHistory;
    ImageView imageView, home, transaksi, logout, setting;
    public String url;

    private RecyclerView lstHistori;
    ProgressBar pgList;

    private List<History> histories;
    private HistoryAdapter adapter;


    private RequestQueue requestQueue;
    private com.google.gson.Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_historymurid);

        Rak.initialize(this);
        url = "http://demo.t-hisyam.net/ihave/api/order/list_order_murid?id_murid=" + Rak.grab("id");

        Log.d("TAG", url);

        lstHistori = (RecyclerView) findViewById(R.id.lst_historyMurid);

        // definisi antrian akses data web service
        requestQueue = Volley.newRequestQueue(this);
        // definisikan objek gsonbuilder yang bertugas melakukan deserialisasi data json ke gson
        GsonBuilder gsonBuilder = new GsonBuilder();
        // inisialisasi obj gson
        gson = gsonBuilder.create();

        // inisialisasi object listView , pastikan anda telah menambahkan object ListView pada layout xml activity
        lstHistori = (RecyclerView) findViewById(R.id.lst_historyMurid);
        lstHistori.setLayoutManager(new LinearLayoutManager(this));

        histories = new ArrayList<>();
        // ambil data
        ambilHistory();


//        lnHistory = (LinearLayout) findViewById(R.id.linearHistory);
//        lnHistory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(HistoryMuridActivity.this, VideoChatActivity.class);
//                startActivity(i);
//            }
//        });


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

    @Override
    protected void onResume() {
        super.onResume();
        requestQueue = Volley.newRequestQueue(this);
        ambilHistory();
    }

    //menagmbil data dari database mysql dengan gson JSON
    private void ambilHistory() {
        //mengambil dengan method GET dan sesuaikan dengan url
        StringRequest request = new StringRequest(Request.Method.GET, url, onPostsLoaded, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder inetErr = new AlertDialog.Builder(HistoryMuridActivity.this);
                inetErr.setTitle("Terjadi Kesalahan");
                inetErr.setMessage("Periksa kembali koneksi internet anda.");
                inetErr.setNegativeButton("RETRY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ambilHistory();
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
            Log.e("TAG", response);
            //gambar loading akan menghilang ketika proses berjalan dengan baik

            Type collectionType = new TypeToken<Histories<History>>(){}.getType();
            Histories<History> histories = new Gson().fromJson(response, collectionType);

            if (histories.isStatus()) {
                try {
                    pgList = (ProgressBar) findViewById(R.id.progressBar);
                    pgList.setVisibility(View.GONE);

                    adapter = new HistoryAdapter(histories.getData());

                    adapter.setListener(new ItemClickListener<History>() {
                        @Override
                        public void onClicked(History History, int position, View view) {
                            Intent i = new Intent(HistoryMuridActivity.this, HistoryMuridDetailActivity.class);
                            i.putExtra("data", new Gson().toJson(History));
                            startActivity(i);
                        }
                    });
                    lstHistori.setAdapter(adapter);
                } catch (Exception ignored) {}
            } else {
                Toast.makeText(HistoryMuridActivity.this, "Tidak ada data", Toast.LENGTH_LONG).show();
            }

        }

    };
}