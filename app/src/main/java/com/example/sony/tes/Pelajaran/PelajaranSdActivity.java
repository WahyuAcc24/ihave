package com.example.sony.tes.Pelajaran;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sony.tes.Adapter.ItemClickListenerPelajaran;
import com.example.sony.tes.Adapter.PelajaranSdAdapter;
import com.example.sony.tes.Model.Pelajaran;
import com.example.sony.tes.Model.pel;
import com.example.sony.tes.Murid.HistoryMuridActivity;
import com.example.sony.tes.Murid.ListGuruSdActivity;
import com.example.sony.tes.Murid.SettingMuridActivity;
import com.example.sony.tes.Murid.TransaksiMuridActivity;
import com.example.sony.tes.R;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by SONY on 28/8/2018.
 */
public class PelajaranSdActivity extends AppCompatActivity {

    ImageView logout, histori, setting, transaksi;
    private RequestQueue requestQueue;
    private com.google.gson.Gson gson;
    private RecyclerView lstPelajaran;
    ProgressBar pgList;
    public List<Pelajaran> pelajaran;

    private PelajaranSdAdapter adapter;




    //    private  static  final String url = "http://t-hisyam.net/demo/whyri/vote/list_vote.php";
    private static final String url = "http://demo.t-hisyam.net/ihave/api/lesson/get_data?id_category=2";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_pelajaran_sd);

        lstPelajaran = (RecyclerView) findViewById(R.id.lst_pel_sd);

        requestQueue = Volley.newRequestQueue(this);
        // definisikan objek gsonbuilder yang bertugas melakukan deserialisasi data json ke gson
        GsonBuilder gsonBuilder = new GsonBuilder();
        // inisialisasi obj gson
        gson = gsonBuilder.create();

        lstPelajaran = (RecyclerView) findViewById(R.id.lst_pel_sd);
        lstPelajaran.setLayoutManager(new LinearLayoutManager(this));


        pelajaran = new ArrayList<>();
        fetchPelajaran();




        histori = (ImageView) findViewById(R.id.imgMenuHistory);
        histori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PelajaranSdActivity.this, HistoryMuridActivity.class);
                startActivity(i);
            }
        });

        setting = (ImageView) findViewById(R.id.imgMenuSetting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PelajaranSdActivity.this, SettingMuridActivity.class);
                startActivity(i);
            }
        });

        transaksi = (ImageView) findViewById(R.id.imgMenuSaldo);
        transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PelajaranSdActivity.this, TransaksiMuridActivity.class);
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

    private void fetchPelajaran() {
        //mengambil dengan method GET dan sesuaikan dengan url

        StringRequest request = new StringRequest(Request.Method.GET, url, onPostsLoaded, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder inetErr = new AlertDialog.Builder(PelajaranSdActivity.this);
                inetErr.setTitle("Terjadi Kesalahan");
                inetErr.setMessage("Periksa kembali koneksi internet anda.");

                Log.e("TAG", error.getMessage());

                inetErr.setNegativeButton("RETRY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        fetchPelajaran();
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
            Log.d("ini jsonnya", response);

            pgList = (ProgressBar) findViewById(R.id.progressBar);
            pgList.setVisibility(View.GONE);

//            pel data = new Gson().fromJson(getIntent().getStringExtra("data"), pel.class);
            final List<pel> test = Arrays.asList(gson.fromJson(response, pel[].class));
            adapter = new PelajaranSdAdapter(test);

            adapter.setListener(new ItemClickListenerPelajaran() {
                @Override
                public void onClicked(pel pel, int position, View view) {
                    Intent i = new Intent(PelajaranSdActivity.this, ListGuruSdActivity.class);
                    i.putExtra("lessonId", String.valueOf(pel.getId()));
                    i.putExtra("name",pel.getName());
                    startActivity(i);

                }
            });
            lstPelajaran.setAdapter(adapter);


        }

    };

}

