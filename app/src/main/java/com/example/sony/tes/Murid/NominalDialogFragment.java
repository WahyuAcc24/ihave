package com.example.sony.tes.Murid;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sony.tes.Model.Pembayaran;
import com.example.sony.tes.R;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import io.isfaaghyth.rak.Rak;

/**
 * Created by SONY on 13/9/2018.
 */
public class NominalDialogFragment extends BottomSheetDialogFragment {

    private String URL = "http://demo.t-hisyam.net/ihave/api/saldo/request_topup";
    private RequestQueue requestQueue;

    ConnectivityManager conMgr;
    private static final String TAG = NominalDialogFragment.class.getSimpleName();
    String tag_json_obj = "json_obj_req";
    private Gson gson;

    private EditText edtNominal;

    public static NominalDialogFragment getInstance() {
        return new NominalDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.buttom_sheet, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Rak.initialize(getContext());
        edtNominal = (EditText) view.findViewById(R.id.edt_nominal);
        view.findViewById(R.id.btnOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rak.entry("bayar", true); //belum bayar (true), sudah bayar(false)
                String idMurid = Rak.grab("id");
//                String invoice = Rak.grab("nomor_invoice");
                String saldo = edtNominal.getText().toString();
                isiSaldo(saldo,idMurid);

            }
        });
    }

    private void isiSaldo(final String saldo, final String idMurid) {
        StringRequest strReq = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Silahkan : " + response);
                try {
                    Rak.entry("saldo_topup", saldo);
//                    Rak.entry("nomor_invoice",invoice);

                    Pembayaran pembayaran = new Gson().fromJson(response, Pembayaran.class);

                    Rak.entry("nomor_invoice", pembayaran.getInvoice());
                    Rak.entry("total", pembayaran.getData().getSubtotal());

                    Intent i = new Intent(getActivity(),FormPembayaranActivity.class);
                    startActivity(i);

                } catch (Exception e) {
                    Log.e("erorr boss", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error :" + error.getMessage());
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("saldo_topup", saldo);
                params.put("id_murid",idMurid);
                return params;
            }


        };
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);

    }

}
