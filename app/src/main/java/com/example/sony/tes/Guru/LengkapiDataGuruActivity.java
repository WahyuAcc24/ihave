package com.example.sony.tes.Guru;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
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
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sony.tes.Adapter.DetailOrderAdapter;
import com.example.sony.tes.Adapter.ItemClickListenerRb;
import com.example.sony.tes.Adapter.rbAdapter;
import com.example.sony.tes.Model.Data;
import com.example.sony.tes.Model.DataPart;
import com.example.sony.tes.Model.DetailOrder;
import com.example.sony.tes.Model.Jadwal;
import com.example.sony.tes.Model.MatpelGuru;
import com.example.sony.tes.Murid.AppController;
import com.example.sony.tes.R;
import com.example.sony.tes.util.PathUtil;
import com.example.sony.tes.util.TimeListener;
import com.example.sony.tes.util.VolleyMultipartRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.mime.MultipartEntityBuilder;
import cz.msebera.android.httpclient.entity.mime.content.FileBody;

public class LengkapiDataGuruActivity extends AppCompatActivity {

    String[] permissions = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    EditText edtSertifikat;
    RecyclerView listjam, listmatpeledit;
    Button ok;
    ProgressDialog pDialog;
    ProgressBar pgList;
    public List<DetailOrder> detailorders;
    public List<MatpelGuru> matpeledt;
    private List<Data> datas;

    ConnectivityManager conMgr;
    Gson gson;
    private RequestQueue requestQueue;
    public TextView txt_namaguru, txt_mp, txt_rp, txt_hobi;
    private DetailOrderAdapter adapterJadwal;
    private rbAdapter adapterMatpel;
    private static final String TAG = LengkapiDataGuruActivity.class.getSimpleName();
    String tag_json_obj = "json_obj_req";

    private Map<String, List<Integer>> collectTime; //PENAMPUNG JAM YANG DI PILIH

    private File certificateFile;

    private String url, url2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lengkapi_data_guru);

        url = "http://demo.t-hisyam.net/ihave/api/guru/update_jadwal_file";
        url2 ="http://demo.t-hisyam.net/ihave/api/lesson/all";

        listjam = (RecyclerView) findViewById(R.id.rvJam);
        listjam.setLayoutManager(new LinearLayoutManager(this));

        listmatpeledit = (RecyclerView) findViewById(R.id.rvPel);
        LinearLayoutManager recyclematpel= new LinearLayoutManager(this);
        listmatpeledit.setLayoutManager(recyclematpel);




        requestQueue = Volley.newRequestQueue(this);

        matpeledt = new ArrayList<>();
        collectTime = new LinkedHashMap<>();

        checkPermissions();

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        requestQueue = Volley.newRequestQueue(this);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        detailorders = new ArrayList<>();

        Edit();
        MatpelEdt();

        edtSertifikat =(EditText) findViewById(R.id.edtSertifikat);
        listjam = (RecyclerView) findViewById(R.id.rvJam);
        ok =(Button) findViewById(R.id.btnOkUpdate);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(listmatpeledit.getContext(),recyclematpel.getOrientation());
        listmatpeledit.addItemDecoration(dividerItemDecoration);

        edtSertifikat.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/* video/* file/*");
                startActivityForResult(intent, 123);
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String idlesson = String.valueOf(listmatpeledit.getId());

                int idlesson = listmatpeledit.getId();


                List<String> day = new ArrayList<>();
                for (Map.Entry<String, List<Integer>> data : collectTime.entrySet()) {
                    String times = "";
                    for (Integer time : data.getValue()) {
                        times = times + "," + time;
                    }
                    day.add(times.substring(1));
                    Log.d("TAG", data.getKey() + ":" + times);
                }

                requestLengkapiGuru("1", day, certificateFile, idlesson);


            }
        });

    }

    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
            return false;
        }
        return true;
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
        for (int i = 9; i <= 24; i++) {
            jam.add(i);
        }
        return new Jadwal()
                .setDay_name(namaHari)
                .setDay_number(0)
                .setHours(jam);
    }

    private void requestLengkapiGuru(final String guruId, final List<String> jadwal, final File certificate, final int idlesson) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addPart("certificate1", new FileBody(certificate));
        final HttpEntity partBody = builder.build();

        VolleyMultipartRequest req = new VolleyMultipartRequest(Request.Method.POST,url,
                new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                Log.e(TAG, "Daftar Response: " + response.statusCode);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_guru", guruId);
                params.put("id_lesson", String.valueOf(idlesson));
                for (int i = 0; i < jadwal.size(); i++) {
                    params.put("jadwal[" + (i + 1) + "]", jadwal.get(i));
                }
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() throws AuthFailureError {
                Map<String, DataPart> params = new HashMap<>();
                if (certificateFile != null) {
                    byte[] bytesArray = new byte[(int) certificateFile.length()];
                    try {
                        FileInputStream fis = new FileInputStream(certificateFile);
                        fis.read(bytesArray); //read file into bytes[]
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    params.put("certificate1", new DataPart(
                            PathUtil.getFileName(getApplicationContext(), Uri.fromFile(certificateFile)),
                            bytesArray)
                    );
                }
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(req, tag_json_obj);
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

    private void MatpelEdt(){

        StringRequest request = new StringRequest(Request.Method.GET, url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            MatpelGuru result = new Gson().fromJson(response, MatpelGuru.class);


                            adapterMatpel = new rbAdapter(getApplicationContext(), result.getData());

                            adapterMatpel.setListenerRb(new ItemClickListenerRb() {
                                @Override
                                public void onClickedRb(Data data, int position, View view) {
                                    Toast.makeText(LengkapiDataGuruActivity.this,
                                            "selected offer is " + data.getId(),
                                            Toast.LENGTH_LONG).show();

                                }
                            });

                            listmatpeledit.setAdapter(adapterMatpel);

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



