package com.example.sony.tes.Murid;

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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
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

public class HistoryMuridDetailActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private Gson gson;
    private RecyclerView lstJadwal;
    ProgressDialog pDialog;
    ProgressBar pgList;
    ConnectivityManager conMgr;

    public TextView txt_namaguru, txt_mp, txt_rp, txtTglDetail;
    private Button btnOk;
    private JadwalAdapter adapter;
    private static final String TAG = HistoryMuridDetailActivity.class.getSimpleName();
    String tag_json_obj = "json_obj_req";

    private String url;
    private String urlrate;
    private History history;
    RatingBar rb;
    EditText edtkomen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historymurid_detail);

        txt_namaguru = (TextView) findViewById(R.id.txtnama_guru);
        txt_mp = (TextView) findViewById(R.id.txtnama_matpel);
        txt_rp = (TextView) findViewById(R.id.txtTotalDetail);
        txtTglDetail = (TextView) findViewById(R.id.txtTglDetail);
        lstJadwal = (RecyclerView) findViewById(R.id.lst_jadwal);
        btnOk = (Button) findViewById(R.id.btnOK);

        url = "http://demo.t-hisyam.net/ihave/api/order/pay_order"; //TODO(ganti untuk verifikasi aja)
        urlrate ="http://demo.t-hisyam.net/ihave/api/order/review";

        final RequestQueue queue = Volley.newRequestQueue(this);

        history = new Gson().fromJson(getIntent().getStringExtra("data"), History.class);

        Log.e("tag", getIntent().getStringExtra("data"));

        txt_namaguru.setText(history.getNama_guru());
        txt_mp.setText(history.getNama_pelajaran());
        txt_rp.setText(history.getTotal());
        txtTglDetail.setText(history.getOrder_date());

        lstJadwal.setLayoutManager(new LinearLayoutManager(this));
        lstJadwal.setAdapter(new JadwalAdapter(history.getJadwal()));

        if (history.getStatus().equalsIgnoreCase("conf")) {
            btnOk.setText("BAYAR KELAS");
            btnOk.setBackgroundColor(Color.BLUE);
            btnOk.setTextColor(Color.WHITE);
            pDialog = new ProgressDialog(this);
            btnOk.setEnabled(true);
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //pertama, harus cek saldonya dia dlu
                    int mySaldo = Integer.valueOf(((String) Rak.grab("saldo")));
                    int total = Integer.valueOf(history.getTotal());

                    if (mySaldo == 0) {
                        new AlertDialog.Builder(HistoryMuridDetailActivity.this)
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
                        new AlertDialog.Builder(HistoryMuridDetailActivity.this)
                                .setTitle(R.string.app_name)
                                .setMessage("apakah yakin ingin membayar?")
                                .setNegativeButton("BATALKAN", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {}
                                })
                                .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
//                                        pDialog = new ProgressDialog(this);
                                        pDialog.setCancelable(false);
                                        pDialog.setMessage("Pembayaran sedang diproses...");
                                        showDialog();

                                        Log.e("TAG", history.getId_guru()+"\n"+history.getInvoice()+"\n");
                                        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                hideDialog();
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
                                                params.put("id_murid", history.getId_murid()); //TODO(minta ke yang bikin API)
                                                params.put("invoice", history.getInvoice());
                                                return params;
                                            }
                                        };
                                        queue.add(request);
                                    }
                                })
                                .show();
                    } else {
                        new AlertDialog.Builder(HistoryMuridDetailActivity.this)
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

        } else if (history.getStatus().equalsIgnoreCase("paid")) {
            btnOk.setText("Menunggu Guru Live");
            btnOk.setBackgroundColor(Color.GRAY);
            btnOk.setTextColor(Color.BLACK);

        } else if (history.getStatus().equalsIgnoreCase("live")) {
            btnOk.setText("Ayooo mulai belajar..!");
            btnOk.setBackgroundColor(Color.YELLOW);
            btnOk.setTextColor(Color.BLACK);
            btnOk.setEnabled(true);
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(HistoryMuridDetailActivity.this, VideoChatActivity.class);
                    startActivity(i);

                }
            });
        }else if (history.getStatus().equalsIgnoreCase("done")){
            btnOk.setText("Beri Ulasan");
            btnOk.setBackgroundColor(Color.GREEN);
            btnOk.setTextColor(Color.WHITE);
            btnOk.setEnabled(true);
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog dialog = new AlertDialog.Builder(HistoryMuridDetailActivity.this).create();
                    View view = LayoutInflater.from(HistoryMuridDetailActivity.this).inflate(R.layout.rating, null);


                    rb = (RatingBar) view.findViewById(R.id.ratingBar);
                    edtkomen = (EditText) view.findViewById(R.id.edtKomen);


                    view.findViewById(R.id.btnRating).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String komen = edtkomen.getText().toString();
                            String rating = String.valueOf(rb.getRating());

                            doRate(komen,rating);
                            dialog.dismiss();

                        }
                    });
                    dialog.setView(view);
                    dialog.show();
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
    public void doRate(final String komen, final String rating){

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Mohon Tunggu .....");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, urlrate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hideDialog();
                Intent i = new Intent(HistoryMuridDetailActivity.this, HomeMuridActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Terimakasih", Toast.LENGTH_SHORT).show();
            }


    }, new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(TAG, "Rate Error: " + error.getMessage());
            Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
        }
    }) {

        @Override
        protected Map<String, String> getParams() {
            // Posting parameters to login url
            Map<String, String> params = new HashMap<String, String>();
            params.put("komentar", komen);
            params.put("rating", String.valueOf(rating));
            params.put("id_murid", history.getId_murid());
            params.put("invoice", history.getInvoice());
            return params;
        }

    };

    // Adding request to request queue
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


}

