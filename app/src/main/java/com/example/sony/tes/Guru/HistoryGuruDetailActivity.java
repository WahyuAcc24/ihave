package com.example.sony.tes.Guru;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.example.sony.tes.Adapter.JadwalAdapter;
import com.example.sony.tes.Model.History;
import com.example.sony.tes.R;
import com.example.sony.tes.Video.VideoChatActivity;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import io.isfaaghyth.rak.Rak;

public class HistoryGuruDetailActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private Gson gson;
    private RecyclerView lstJadwal;
    ProgressDialog pDialog;
    ProgressBar pgList;
    ConnectivityManager conMgr;

    public TextView txt_namamurid, txt_mp, txtTglDetail;
    private Button btnOk, btnselesai;
    private JadwalAdapter adapter;
    private static final String TAG = HistoryGuruDetailActivity.class.getSimpleName();
    String tag_json_obj = "json_obj_req";

    private String url, url2, url3;
    private History history;
    private ImageView home, setting , transaksi, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historyguru_detail);

        txt_namamurid = (TextView) findViewById(R.id.txtnama_murid);
        txt_mp = (TextView) findViewById(R.id.txtnama_matpel);
        txtTglDetail = (TextView) findViewById(R.id.txtTglDetail);
        lstJadwal = (RecyclerView) findViewById(R.id.lst_jadwal);
        btnOk = (Button) findViewById(R.id.btnOK);
        btnselesai = (Button) findViewById(R.id.btnselesai);

        url3 = "http://demo.t-hisyam.net/ihave/api/streaming/done_guru";
        url2 = "http://demo.t-hisyam.net/ihave/api/streaming/live_guru";
        url = "http://demo.t-hisyam.net/ihave/api/order/approved_order"; //TODO(ganti untuk verifikasi aja)

        final RequestQueue queue = Volley.newRequestQueue(this);

        history = new Gson().fromJson(getIntent().getStringExtra("data"), History.class);

        Log.e("tag", getIntent().getStringExtra("data"));

        txt_namamurid.setText(history.getNama_murid());
        txt_mp.setText(history.getNama_pelajaran());
        txtTglDetail.setText(history.getOrder_date());

        lstJadwal.setLayoutManager(new LinearLayoutManager(this));
        lstJadwal.setAdapter(new JadwalAdapter(history.getJadwal()));

        if (history.getStatus().equalsIgnoreCase("wait"))  {
            btnOk.setText("KONFIRMASI");
            btnOk.setBackgroundColor(Color.BLUE);
            btnOk.setTextColor(Color.WHITE);
            btnOk.setEnabled(true);
            btnselesai.setVisibility(View.GONE);
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("TAG", "\n" + history.getId_guru() + "\n" + history.getInvoice() + "\n");
                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            onBackPressed();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "terjadi kesalahan", Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("id_guru", history.getId_guru()); //TODO(minta ke yang bikin API)
                            params.put("invoice", history.getInvoice());
                            return params;
                        }
                    };
                    queue.add(request);
                }
            });

        } else if (history.getStatus().equalsIgnoreCase("paid") || history.getStatus().equalsIgnoreCase("live")) {
            btnOk.setText("Mulai Streaming");
            btnOk.setBackgroundColor(Color.YELLOW);
            btnOk.setTextColor(Color.BLACK);
            btnOk.setEnabled(true);
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("TAG", history.getId_guru() + "\n" + history.getInvoice() + "\n");
                    StringRequest request = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Intent i = new Intent(HistoryGuruDetailActivity.this, VideoChatActivity.class);
                            startActivity(i);
                            onBackPressed();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "terjadi kesalahan", Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("id_guru", history.getId_guru()); //TODO(minta ke yang bikin API)
                            params.put("invoice", history.getInvoice());
                            return params;
                        }
                    };
                    queue.add(request);
                }
            });
            btnselesai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("TAG", history.getId_guru() + "\n" + history.getInvoice() + "\n");
                    StringRequest request = new StringRequest(Request.Method.POST, url3, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Intent i = new Intent(HistoryGuruDetailActivity.this, HomeGuruActivity.class);
                            startActivity(i);
                            onBackPressed();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "terjadi kesalahan", Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("id_guru", history.getId_guru()); //TODO(minta ke yang bikin API)
                            params.put("invoice", history.getInvoice());
                            return params;
                        }
                    };
                    queue.add(request);
                }
            });

        } else if (history.getStatus().equalsIgnoreCase("done")) {
            btnOk.setText("Sesi Telah Berakhir");
            btnOk.setBackgroundColor(Color.GRAY);
            btnOk.setTextColor(Color.BLACK);
        }
//            btnOk.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i = new Intent(HistoryGuruDetailActivity.this, VideoChatActivity.class);
//                    startActivity(i);
//                }
//            });


//        }else if (history.getStatus().equalsIgnoreCase("done")) {
//            btnOk.setText("Terimakasih");
//            btnOk.setBackgroundColor(Color.BLACK);
//            btnOk.setTextColor(Color.WHITE);
//            btnOk.setEnabled(true);
//        }
            //  tombol back
            ImageView backButton = (ImageView) this.findViewById(R.id.btn_back);
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        home = (ImageView) findViewById(R.id.imgMenuHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HistoryGuruDetailActivity.this, HomeGuruActivity.class);
                startActivity(i);
            }
        });

        transaksi = (ImageView) findViewById(R.id.imgMenuSaldo);
        transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HistoryGuruDetailActivity.this, TransaksiGuruActivity.class);
                startActivity(i);
            }
        });

        setting = (ImageView) findViewById(R.id.imgMenuSetting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HistoryGuruDetailActivity.this, SettingGuruActivity.class);
                startActivity(i);

            }
        });

        logout = (ImageView) findViewById(R.id.imgMenuLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HistoryGuruDetailActivity.this, LoginGuruActivity.class);
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


