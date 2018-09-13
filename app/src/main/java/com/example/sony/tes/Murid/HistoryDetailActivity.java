package com.example.sony.tes.Murid;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import io.isfaaghyth.rak.Rak;

public class HistoryDetailActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private Gson gson;
    private RecyclerView lstJadwal;
    ProgressDialog pDialog;
    ProgressBar pgList;
    ConnectivityManager conMgr;

    public TextView txt_namaguru, txt_mp, txt_rp, txtTglDetail;
    private Button btnOk;
    private JadwalAdapter adapter;
    private static final String TAG = HistoryDetailActivity.class.getSimpleName();
    String tag_json_obj = "json_obj_req";

    private String url;
    private History history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);

        txt_namaguru = (TextView) findViewById(R.id.txtnama_guru);
        txt_mp = (TextView) findViewById(R.id.txtnama_matpel);
        txt_rp = (TextView) findViewById(R.id.txtTotalDetail);
        txtTglDetail = (TextView) findViewById(R.id.txtTglDetail);
        lstJadwal = (RecyclerView) findViewById(R.id.lst_jadwal);
        btnOk = (Button) findViewById(R.id.btnOK);

        url = "http://demo.t-hisyam.net/ihave/api/order/approved_order"; //TODO(ganti untuk verifikasi aja)

        final RequestQueue queue = Volley.newRequestQueue(this);

        history = new Gson().fromJson(getIntent().getStringExtra("data"), History.class);

        txt_namaguru.setText(history.getNama_guru());
        txt_mp.setText(history.getNama_pelajaran());
        txt_rp.setText(history.getTotal());
        txtTglDetail.setText(history.getOrder_date());

        lstJadwal.setLayoutManager(new LinearLayoutManager(this));
        lstJadwal.setAdapter(new JadwalAdapter(history.getJadwal()));

        if (history.getStatus().equalsIgnoreCase("waiting") || history.getStatus().equalsIgnoreCase("wait")) {
            btnOk.setText("BAYAR KELAS");
            btnOk.setBackgroundColor(Color.BLUE);
            btnOk.setTextColor(Color.WHITE);
            btnOk.setEnabled(true);
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //pertama, harus cek saldonya dia dlu
                    int mySaldo = Integer.valueOf(((String) Rak.grab("saldo")));
                    int total = Integer.valueOf(history.getTotal());

                    if (mySaldo == 0) {
                        new AlertDialog.Builder(HistoryDetailActivity.this)
                                .setTitle(R.string.app_name)
                                .setMessage("Maaf, saldo anda tidak cukup")
                                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {}
                                })
                                .show();
                        return;
                    }

                    if (mySaldo >= total) {
                        new AlertDialog.Builder(HistoryDetailActivity.this)
                                .setTitle(R.string.app_name)
                                .setMessage("apakah yakin ingin membayar?")
                                .setNegativeButton("BATALKAN", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {}
                                })
                                .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
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
                                })
                                .show();
                    } else {
                        new AlertDialog.Builder(HistoryDetailActivity.this)
                                .setTitle(R.string.app_name)
                                .setMessage("Maaf, saldo anda tidak cukup")
                                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {}
                                })
                                .show();
                    }
                }
            });
        } else if (history.getStatus().equalsIgnoreCase("live") || history.getStatus().equalsIgnoreCase("paid")) {
            btnOk.setText("NONTON VIDEO");
            btnOk.setBackgroundColor(Color.YELLOW);
            btnOk.setTextColor(Color.BLACK);
            btnOk.setEnabled(true);
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //intent ke nonton video
                }
            });
        }

        //  tombol back
        ImageView backButton = (ImageView) this.findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}

