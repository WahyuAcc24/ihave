package com.example.sony.tes.Murid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.example.sony.tes.Adapter.DetailOrderAdapter;
import com.example.sony.tes.Adapter.ItemClickListenerHour;
import com.example.sony.tes.Adapter.JamAdapter;
import com.example.sony.tes.BuildConfig;
import com.example.sony.tes.Model.DetailOrder;
import com.example.sony.tes.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SONY on 28/8/2018.
 */
public class DetailOrderActivity extends AppCompatActivity {

    ImageView logout, histori, setting, transaksi;
    private RequestQueue requestQueue;
    private com.google.gson.Gson gson;
    private RecyclerView lstHari, lstJam;
    ProgressBar pgList;
    public List<DetailOrder> detailorders;

    public TextView txt_namaguru, txt_mp, txt_rp, txt_hobi;
    private DetailOrderAdapter adapterhari;
    private JamAdapter adapterjam;


    String collectTime ="";




    //    private  static  final String url = "http://t-hisyam.net/demo/whyri/vote/list_vote.php";
 private String url;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_detailguru);

        url = "http://demo.t-hisyam.net/ihave/api/schedule/list_jadwal?id_guru=" + getIntent().getStringExtra("guruId") + "&id_lesson=" + getIntent().getStringExtra("lessonId");
        lstHari = (RecyclerView) findViewById(R.id.lst_hari);
        lstJam = (RecyclerView) findViewById(R.id.lst_jam);

        requestQueue = Volley.newRequestQueue(this);
        // definisikan objek gsonbuilder yang bertugas melakukan deserialisasi data json ke gson
        GsonBuilder gsonBuilder = new GsonBuilder();
        // inisialisasi obj gson
        gson = gsonBuilder.create();

        lstHari = (RecyclerView) findViewById(R.id.lst_hari);
        lstHari.setLayoutManager(new LinearLayoutManager(this));

        lstJam = (RecyclerView) findViewById(R.id.lst_jam);
        lstJam.setLayoutManager(new LinearLayoutManager(this));


        detailorders = new ArrayList<>();

    ambilGuru();
    ambilJam();



        histori = (ImageView) findViewById(R.id.imgMenuHistory);
        histori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailOrderActivity.this, HistoryMuridActivity.class);
                startActivity(i);
            }
        });

        setting = (ImageView) findViewById(R.id.imgMenuSetting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailOrderActivity.this, SettingMuridActivity.class);
                startActivity(i);
            }
        });

        transaksi = (ImageView) findViewById(R.id.imgMenuSaldo);
        transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailOrderActivity.this, TransaksiMuridActivity.class);
                startActivity(i);
            }
        });




        txt_namaguru = (TextView) findViewById(R.id.txtnama_guru);
        txt_mp = (TextView) findViewById(R.id.txtMatpelOrder);
        txt_hobi = (TextView) findViewById(R.id.txthobi);
        txt_rp = (TextView) findViewById(R.id.txtRpOrder);


//  tombol back
        ImageView backButton = (ImageView) this.findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void ambilGuru() {
        //mengambil dengan method GET dan sesuaikan dengan url

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(BuildConfig.APPLICATION_ID, url+"\n"+response);
                        pgList = (ProgressBar) findViewById(R.id.progressBar);
                        pgList.setVisibility(View.GONE);
                        try {
                            DetailOrder result = new Gson().fromJson(response, DetailOrder.class);
                            txt_rp.setText(result.getLulusan());
                            txt_mp.setText(result.getPelajaran());
                            txt_hobi.setText(result.getHobby());
                            txt_namaguru.setText(result.getFullname());

                            adapterhari = new DetailOrderAdapter(result.getJadwal());
                            adapterjam = new JamAdapter(result.getJadwal());

                            lstHari.setAdapter(adapterhari);
                            lstJam.setAdapter(adapterjam);
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
    private void ambilJam(){

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(BuildConfig.APPLICATION_ID, url+"\n"+response);
                        pgList = (ProgressBar) findViewById(R.id.progressBar);
                        pgList.setVisibility(View.GONE);
                        try {
                            DetailOrder result = new Gson().fromJson(response, DetailOrder.class);
                            adapterjam = new JamAdapter(result.getJadwal());
                            for (String time : collectTime.split(",")){
                                System.out.println(time);
                            }
                            adapterjam.setListener(new ItemClickListenerHour() {
                                @Override
                                public void onClickedHour(List<Integer> hours) {
                                    collectTime = collectTime + "," + String.valueOf(hours);
                                }
                            });
                            lstJam.setAdapter(adapterjam);
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

