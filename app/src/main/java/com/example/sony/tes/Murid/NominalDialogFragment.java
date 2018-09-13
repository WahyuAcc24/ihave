package com.example.sony.tes.Murid;

import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sony.tes.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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


        // definisi antrian akses data web service
        requestQueue = Volley.newRequestQueue(this);
        // definisikan objek gsonbuilder yang bertugas melakukan deserialisasi data json ke gson
        GsonBuilder gsonBuilder = new GsonBuilder();
        // inisialisasi obj gson
        gson = gsonBuilder.create();

        ambilNominal();
    }
    private void ambilNominal() {
        //mengambil dengan method GET dan sesuaikan dengan url
        StringRequest request = new StringRequest(Request.Method.POST, URL, onPostsLoaded, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder inetErr = new AlertDialog.Builder(NominalDialogFragment.this);
                inetErr.setTitle("Terjadi Kesalahan");
                inetErr.setMessage("Periksa kembali koneksi internet anda.");
                inetErr.setNegativeButton("RETRY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ambilNominal();
                    }
                });
                inetErr.show();
            }
        });
        requestQueue.add(request);
    }
        edtNominal = (EditText) view.findViewById(R.id.edt_nominal);
        view.findViewById(R.id.btnOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest

            }
        });


    }
}
