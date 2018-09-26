package com.example.sony.tes.Guru;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sony.tes.Model.Pembayaran;
import com.example.sony.tes.Murid.AppController;
import com.example.sony.tes.Murid.FormPembayaranActivity;
import com.example.sony.tes.Murid.HistoryMuridActivity;
import com.example.sony.tes.Murid.HomeMuridActivity;
import com.example.sony.tes.Murid.LoginMuridActivity;
import com.example.sony.tes.Murid.TransaksiMuridActivity;
import com.example.sony.tes.R;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import io.isfaaghyth.rak.Rak;

/**
 * Created by SONY on 8/8/2018.
 */
public class TransaksiGuruActivity extends AppCompatActivity {


    private String URL = "http://demo.t-hisyam.net/ihave/api/saldo/request_withdraw_guru";
    private RequestQueue requestQueue;

    private static final String TAG = TransaksiGuruActivity.class.getSimpleName();
    String tag_json_obj = "json_obj_req";
    private Gson gson;
    ProgressDialog pDialog;
    private TextView txtsaldo;
    ConnectivityManager conMgr;

    private EditText edtNominal;
    private Button btnok;

    ImageView home,histori,logout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
//        getSupportActionBar().hide();
        setContentView(R.layout.saldo_guru);
        Rak.initialize(this);

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }


        txtsaldo = (TextView) findViewById(R.id.saldo);
        txtsaldo.setText("Saldo Anda : " + Rak.grab("saldo"));

        edtNominal = (EditText) findViewById(R.id.EdtRp);
        btnok = (Button) findViewById(R.id.button);

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String saldo = edtNominal.getText().toString();
                String idguru = Rak.grab("id_guru");

                Log.e("TAG", idguru+"\n"+saldo+"\n");

                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    withdraw(saldo,idguru);
                } else {
                    Toast.makeText(getApplicationContext(), "cek internet anda", Toast.LENGTH_SHORT).show();
                }

            }
        });

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
                Intent i = new Intent(TransaksiGuruActivity.this, HomeGuruActivity.class);
                startActivity(i);
            }
        });

        histori = (ImageView) findViewById(R.id.imgMenuHistory);
        histori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransaksiGuruActivity.this, HistoryGuruActivity.class);
                startActivity(i);
            }
        });

        logout = (ImageView) findViewById(R.id.imgMenuLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TransaksiGuruActivity.this, LoginGuruActivity.class);
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

    private void withdraw(final String saldo, final String idguru){
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Mohon Tunggu....");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Verifikasi Merespon: " + response);
                Intent i = new Intent(TransaksiGuruActivity.this, HomeGuruActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"Silahkan cek email anda, permintaan anda sedang diproses",Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error :" + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }

        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_guru", idguru);
                params.put("total_withdraw",saldo);
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
