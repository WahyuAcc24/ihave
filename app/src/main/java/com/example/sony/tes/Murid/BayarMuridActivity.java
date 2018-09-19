package com.example.sony.tes.Murid;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sony.tes.R;

import java.util.HashMap;
import java.util.Map;

import io.isfaaghyth.rak.Rak;

/**
 * Created by SONY on 9/8/2018.
 */
public class BayarMuridActivity extends AppCompatActivity {

    Button btnver;
    EditText edtnorek, edtnamarek,edtnamabank;
    Intent intent;
    ProgressDialog pDialog;

    private String url = "http://demo.t-hisyam.net/ihave/api/saldo/verification_topup";
    private static final String TAG = BayarMuridActivity.class.getSimpleName();

    String tag_json_obj = "json_obj_req";
    ConnectivityManager conMgr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verifikasi_main);
        Rak.initialize(this);


        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), " Silahkan Cek Lagi Koneksi Anda ",
                        Toast.LENGTH_LONG).show();
            }
        }


        edtnamabank = (EditText) findViewById(R.id.edtNamaBank);
        edtnorek = (EditText) findViewById(R.id.edtNoRek);
        edtnamarek = (EditText) findViewById(R.id.edtNamaRek);
        btnver = (Button) findViewById(R.id.btnVerifikasi);
        btnver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String invoice = Rak.grab("nomor_invoice");
                String norek = edtnorek.getText().toString();
                String namarek = edtnamarek.getText().toString();
                String namabank = edtnamabank.getText().toString();

                Log.e("TAG", invoice+"\n"+norek+"\n"+namarek+"\n"+namabank+"\n");

                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    Verifikasi(invoice,norek,namarek,namabank);
                } else {
                    Toast.makeText(getApplicationContext(), "cek internet anda", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void Verifikasi(final String invoice,final String norek, final String namarek, final String namabank){

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Verifikasi sedang diproses...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Verifikasi Merespon: " + response);
                Intent i = new Intent(BayarMuridActivity.this, TransaksiMuridActivity.class);
                startActivity(i);
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
                    params.put("invoice", invoice);
                    params.put("no_rekening",norek);
                    params.put("pemilik_rekening", namarek);
                    params.put("nama_bank", namabank);
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
