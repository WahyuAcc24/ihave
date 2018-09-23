package com.example.sony.tes.Guru;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sony.tes.Adapter.DetailOrderAdapter;
import com.example.sony.tes.BuildConfig;
import com.example.sony.tes.Model.DetailOrder;
import com.example.sony.tes.Model.Jadwal;
import com.example.sony.tes.Murid.AppController;
import com.example.sony.tes.R;
import com.example.sony.tes.util.PathUtil;
import com.example.sony.tes.util.TimeListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.mime.MultipartEntityBuilder;
import cz.msebera.android.httpclient.entity.mime.content.FileBody;

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

    private File certificateFile;

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lengkapi_data_guru);

        url = "http://demo.t-hisyam.net/ihave/api/guru/update_jadwal_file";
        listjam = (RecyclerView) findViewById(R.id.rvJam);
        listjam.setLayoutManager(new LinearLayoutManager(this));

        collectTime = new LinkedHashMap<>();

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        requestQueue = Volley.newRequestQueue(this);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        detailorders = new ArrayList<>();

        Edit();

        edtSertifikat =(EditText) findViewById(R.id.edtSertifikat);
        listjam = (RecyclerView) findViewById(R.id.rvJam);
        ok =(Button) findViewById(R.id.btnOkUpdate);

        edtSertifikat.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("file/*");
                startActivityForResult(intent, 123);
            }
        });

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
                    Log.d("TAG", data.getKey() + ":" + times);
                }
                requestLengkapiGuru("1", day, certificateFile);
            }
        });
    }

    private void setFile(Uri uri) {
        certificateFile = new File(PathUtil.getPath(getApplicationContext(), uri));
        Log.d("certificateFile", uri.toString());
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 123) {
                setFile(data.getData());
                edtSertifikat.setText(PathUtil.getFileName(this, data.getData()));
            }
        }
    }

    private Jadwal jadwalGuru(String namaHari) {
        List<Integer> jam = new ArrayList<>();
        for (int i = 9; i <= 20; i++) {
            jam.add(i);
        }
        return new Jadwal()
                .setDay_name(namaHari)
                .setDay_number(0)
                .setHours(jam);
    }

    private void requestLengkapiGuru(final String guruId, final List<String> jadwal, File certificate) {

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addPart("certificate1", new FileBody(certificate));
        final HttpEntity partBody = builder.build();

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override public void onResponse(String response) {
                Log.d("TAG", response);
            }
        }, new Response.ErrorListener() {
            @Override public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage());
            }
        }) {

            @Override protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_guru", guruId);
                for (int i = 0; i < jadwal.size(); i++) {
                    params.put("jadwal[" + (i+1) + "]", jadwal.get(i));
                }
                return params;
            }

            @Override public String getBodyContentType() {
                return partBody.getContentType().getValue();
            }

            @Override public byte[] getBody() throws AuthFailureError {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                try {
                    partBody.writeTo(bos);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return bos.toByteArray();
            }

        };

        AppController.getInstance().addToRequestQueue(request, tag_json_obj);

    }

    private void Edit(){
        List<Jadwal> jadwals = new ArrayList<>();
        String[] haris = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"};
        for (String hari: haris) {
            jadwals.add(jadwalGuru(hari));
        }

        adapterJadwal = new DetailOrderAdapter(getApplicationContext(), jadwals);

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
        listjam.setAdapter(adapterJadwal);
    }

    public boolean inMap(Map<String, List<Integer>> map, String key, Integer value) {
        final List<Integer> values = map.get(key);
        return values != null && values.contains(value);
    }

}
