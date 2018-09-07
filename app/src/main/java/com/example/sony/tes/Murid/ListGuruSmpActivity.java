package com.example.sony.tes.Murid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sony.tes.Adapter.GuruSmpAdapter;
import com.example.sony.tes.Adapter.ItemClickListenerBebas;
import com.example.sony.tes.Model.Guru;
import com.example.sony.tes.Model.GuruSd;
import com.example.sony.tes.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SONY on 28/8/2018.
 */
public class ListGuruSmpActivity extends AppCompatActivity {

    ImageView logout, histori, setting, transaksi;
    private RequestQueue requestQueue;
    private Gson gson;
    private RecyclerView lstGuru;
    TextView txt_judul;
    ProgressBar pgList;
    public List<GuruSd> gurusd;


    private GuruSmpAdapter adapter;




    //    private  static  final String url = "http://t-hisyam.net/demo/whyri/vote/list_vote.php";
    private String url;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_listguru_smp);

        url = "http://demo.t-hisyam.net/ihave/api/schedule/list_guru?id_lesson=" + getIntent().getStringExtra("lessonId");

        fetchPelajaran();

        lstGuru = (RecyclerView) findViewById(R.id.lst_guru_smp);

        requestQueue = Volley.newRequestQueue(this);
        // definisikan objek gsonbuilder yang bertugas melakukan deserialisasi data json ke gson
        GsonBuilder gsonBuilder = new GsonBuilder();
        // inisialisasi obj gson
        gson = gsonBuilder.create();

        lstGuru = (RecyclerView) findViewById(R.id.lst_guru_smp);
        lstGuru.setLayoutManager(new LinearLayoutManager(this));


        gurusd = new ArrayList<>();




        histori = (ImageView) findViewById(R.id.imgMenuHistory);
        histori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListGuruSmpActivity.this, HistoryMuridActivity.class);
                startActivity(i);
            }
        });

        setting = (ImageView) findViewById(R.id.imgMenuSetting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListGuruSmpActivity.this, SettingMuridActivity.class);
                startActivity(i);
            }
        });

        transaksi = (ImageView) findViewById(R.id.imgMenuSaldo);
        transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListGuruSmpActivity.this, TransaksiMuridActivity.class);
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

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pgList = (ProgressBar) findViewById(R.id.progressBar);
                        pgList.setVisibility(View.GONE);
                        try {
                            Guru[] result = new Gson().fromJson(response, Guru[].class);
                            adapter = new GuruSmpAdapter(result, getApplicationContext());
                            adapter.setListener(new ItemClickListenerBebas() {
                                @Override
                                public void onClicked(Guru guru, int position, View view) {
                                    Intent i = new Intent(ListGuruSmpActivity.this, DetailOrderActivity.class);
                                    i.putExtra("guruId", guru.getId());
                                    i.putExtra("fullname", guru.getFullname());
                                    i.putExtra("pelajaran", guru.getPelajaran());
                                    i.putExtra("hobby",guru.getHobby());
                                    i.putExtra("lessonId", getIntent().getStringExtra("lessonId"));
                                    startActivity(i);
                                }
                            });

                            lstGuru.setAdapter(adapter);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);



    }
}

