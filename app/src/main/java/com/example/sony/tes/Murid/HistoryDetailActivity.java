package com.example.sony.tes.Murid;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.sony.tes.Adapter.JadwalAdapter;
import com.example.sony.tes.Model.History;
import com.example.sony.tes.R;
import com.google.gson.Gson;

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

        url = "http://demo.t-hisyam.net/ihave/api/order/list_order_murid?id_murid=1"; //TODO(ganti untuk verifikasi aja)

        history = new Gson().fromJson(getIntent().getStringExtra("data"), History.class);

        txt_namaguru.setText(history.getNama_guru());
        txt_mp.setText(history.getNama_pelajaran());
        txt_rp.setText(history.getTotal());
        txtTglDetail.setText(history.getOrder_date());

        lstJadwal.setLayoutManager(new LinearLayoutManager(this));
        lstJadwal.setAdapter(new JadwalAdapter(history.getJadwal()));

        if (history.getStatus().equalsIgnoreCase("waiting")) {
            btnOk.setText("MENUNGGU KONFIRMASI");
            btnOk.setEnabled(false);
        } else if (history.getStatus().equalsIgnoreCase("live")) {
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

