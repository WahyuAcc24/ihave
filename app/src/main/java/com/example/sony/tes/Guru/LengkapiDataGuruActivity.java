package com.example.sony.tes.Guru;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.sony.tes.BuildConfig;
import com.example.sony.tes.Model.DetailOrder;
import com.example.sony.tes.R;
import com.example.sony.tes.util.TimeListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LengkapiDataGuruActivity extends AppCompatActivity {

    EditText edtSertifikat;
    RecyclerView listjam;
    Button ok;
    ProgressDialog pDialog;
    ProgressBar pgList;
    public List<DetailOrder> detailorders;
    ConnectivityManager conMgr;
    Gson gson;
    private RequestQueue requestQueue;
    public TextView txt_namaguru, txt_mp, txt_rp, txt_hobi;
    private DetailOrderAdapter adapterJadwal;
    private static final String TAG = LengkapiDataGuruActivity.class.getSimpleName();
    String tag_json_obj = "json_obj_req";

    private Map<String, List<Integer>> collectTime; //PENAMPUNG JAM YANG DI PILIH


    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lengkapi_data_guru);

        url = "http://demo.t-hisyam.net/ihave/api/schedule/list_jadwal?id_guru=1&id_lesson=1";
        listjam = (RecyclerView) findViewById(R.id.rvJam);
        listjam.setLayoutManager(new LinearLayoutManager(this));

        collectTime = new LinkedHashMap<>();

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        requestQueue = Volley.newRequestQueue(this);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        detailorders = new ArrayList<>();





        edtSertifikat =(EditText) findViewById(R.id.edtSertifikat);
        listjam = (RecyclerView) findViewById(R.id.rvJam);
        ok =(Button) findViewById(R.id.btnOkUpdate);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    Edit(day);


                } else {
                    Toast.makeText(getApplicationContext(), "cek internet anda", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void Edit(List<String> day){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(BuildConfig.APPLICATION_ID, url + "\n" + response);
                pgList = (ProgressBar) findViewById(R.id.progressBar);
                pgList.setVisibility(View.GONE);
                try {
                    DetailOrder result = new Gson().fromJson(response, DetailOrder.class);

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
//                            Intent i = new Intent(DetailOrderActivity.this, HomeMuridActivity.class);
//                            startActivity(i);
                    listjam.setAdapter(adapterJadwal);
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
        requestQueue.add(stringRequest);
    }

    public boolean inMap(Map<String, List<Integer>> map, String key, Integer value) {
        final List<Integer> values = map.get(key);
        return values != null && values.contains(value);
    }



}
