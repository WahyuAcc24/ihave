package com.example.sony.tes.Guru;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sony.tes.Adapter.DetailOrderAdapter;
import com.example.sony.tes.BuildConfig;
import com.example.sony.tes.Model.DetailOrder;
import com.example.sony.tes.Murid.AppController;
import com.example.sony.tes.Murid.HistoryMuridActivity;
import com.example.sony.tes.Murid.SettingMuridActivity;
import com.example.sony.tes.Murid.TransaksiMuridActivity;
import com.example.sony.tes.R;
import com.example.sony.tes.util.TimeListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.isfaaghyth.rak.Rak;

/**
 * Created by SONY on 28/8/2018.
 */
public class GuruActivity extends AppCompatActivity {

    ImageView logout, histori, setting, transaksi;
    private RequestQueue requestQueue;
    private Gson gson;
    private RecyclerView lstJadwal;
    ProgressDialog pDialog;
    ProgressBar pgList;
    public List<DetailOrder> detailorders;
    ConnectivityManager conMgr;

    public TextView txt_namaguru, txt_mp, txt_rp, txt_hobi;
    private DetailOrderAdapter adapterJadwal;
    private static final String TAG = GuruActivity.class.getSimpleName();
    String tag_json_obj = "json_obj_req";

    private String url;

    private String urlSave = "http://demo.t-hisyam.net/ihave/api/order/save_order";
    /**
     * @TODO(Silahkan diganti sesuai kebutuhan)
     */
    private Map<String, List<Integer>> collectTime; //PENAMPUNG JAM YANG DI PILIH

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_detailguru);

        url = "http://demo.t-hisyam.net/ihave/api/schedule/list_jadwal?id_guru=" + getIntent().getStringExtra("guruId") + "&id_lesson=" + getIntent().getStringExtra("lessonId");
        lstJadwal = (RecyclerView) findViewById(R.id.lst_jadwal);
        lstJadwal.setLayoutManager(new LinearLayoutManager(this));

        collectTime = new LinkedHashMap<>();

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        requestQueue = Volley.newRequestQueue(this);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        detailorders = new ArrayList<>();

        ambilGuru();


        histori = (ImageView) findViewById(R.id.imgMenuHistory);
        histori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GuruActivity.this, HistoryMuridActivity.class);
                startActivity(i);
            }
        });

        setting = (ImageView) findViewById(R.id.imgMenuSetting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GuruActivity.this, SettingMuridActivity.class);
                startActivity(i);
            }
        });

        transaksi = (ImageView) findViewById(R.id.imgMenuSaldo);
        transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GuruActivity.this, TransaksiMuridActivity.class);
                startActivity(i);
            }
        });


        txt_namaguru = (TextView) findViewById(R.id.txtnama_guru);
        txt_mp = (TextView) findViewById(R.id.txtMatpelOrder);
        txt_hobi = (TextView) findViewById(R.id.txthobi);
        txt_rp = (TextView) findViewById(R.id.txtRpOrder);

//        txt_namaguru.setText(getIntent().getStringExtra("blaa"));


        //tombol back
        ImageView backButton = (ImageView) this.findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //untuk tombol OK, ambil semua informasi order
        findViewById(R.id.btnOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idMurid = Rak.grab("id"); //ini ane reference dari LoginMuridActivity
                String idLesson = getIntent().getStringExtra("lessonId"); //dari intent
                String idGuru = getIntent().getStringExtra("guruId"); //dari intent
                List<String> day = new ArrayList<>();
                for (Map.Entry<String, List<Integer>> data : collectTime.entrySet()) {
                    String times = "";
                    for (Integer time : data.getValue()) {
                        times = times + "," + time;
                    }
                    day.add(times.substring(1));
                }

                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    Order(idMurid, idLesson, idGuru, day);
                } else {
                    Toast.makeText(getApplicationContext(), "cek internet anda", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//
//
//                //contoh, data yang di dapetin dan di butuhin di API (sesuai postman yang ente kirim ke ane di whastapp, udah ku grab semua, abang tinggal olah data-data ini, misal taroh di body post.
//                Toast.makeText(getApplicationContext(),
//                         "ID Murid : " + idMurid + "\n" +
//                         "ID Lesson : " + idLesson + "\n" +
//                         "ID Guru : " + idGuru + "\n" +
//                         "Daftar Jam Pilihan : " + "\n" +
//                         fullData,
//                         Toast.LENGTH_LONG).show();
//            }

    private void ambilGuru() {
        //mengambil dengan method GET dan sesuaikan dengan url

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(BuildConfig.APPLICATION_ID, url + "\n" + response);
                        pgList = (ProgressBar) findViewById(R.id.progressBar);
                        pgList.setVisibility(View.GONE);
                        try {
                            DetailOrder result = new Gson().fromJson(response, DetailOrder.class);
                            txt_rp.setText(result.getLulusan());
                            txt_mp.setText(result.getPelajaran());
                            txt_hobi.setText(result.getHobby());
                            txt_namaguru.setText(result.getFullname());

                            adapterJadwal = new DetailOrderAdapter(getApplicationContext(), result.getJadwal());

                            adapterJadwal.setListener(new TimeListener() {
                                @Override
                                public void onTimeClicked(View view, String dayName, int dayNumber, Integer timeSelected) {
                                    if (inMap(collectTime, dayName, timeSelected)) {
                                        collectTime.get(dayName).remove(timeSelected);
                                        view.setBackgroundColor(Color.WHITE);
                                    } else {
                                        List<Integer> time = new ArrayList<>();
                                        time.add(timeSelected);
                                        if (!collectTime.containsKey(dayName)) {
                                            collectTime.put(dayName, time);
                                        } else {
                                            time = collectTime.get(dayName);
                                            time.add(timeSelected);
                                            collectTime.put(dayName, time);
                                        }
                                        view.setBackgroundColor(Color.YELLOW);
                                    }
                                }
                            });

                            lstJadwal.setAdapter(adapterJadwal);
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

    public boolean inMap(Map<String, List<Integer>> map, String key, Integer value) {
        final List<Integer> values = map.get(key);
        return values != null && values.contains(value);
    }

    private void Order(final String idMurid, final String idGuru, final String idLesson, final List<String> time) {

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Registrasi sedang diproses...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, urlSave, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hideDialog();
                startActivity(new Intent(GuruActivity.this, HistoryMuridActivity.class));
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_lesson", idLesson);
                params.put("id_murid", idMurid);
                params.put("id_guru", idGuru);
                for (int i=0; i<time.size(); i++) {
                    params.put("jadwal[" + (i+1) + "]", time.get(i));
                }
                return params;
            }


        };
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);

    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    @Override
    public void onBackPressed() {
        finish();
    }


}