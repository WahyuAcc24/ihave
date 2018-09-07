package com.example.sony.tes.Murid;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sony.tes.Guru.LoginGuruActivity;
import com.example.sony.tes.Model.LoginMurid;
import com.example.sony.tes.R;
import com.example.sony.tes.Server.server;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import io.isfaaghyth.rak.Rak;

public class LoginMuridActivity extends AppCompatActivity {


    EditText txt_email,txt_pass;

    RadioButton guru, murid;
    RadioGroup radioGroup;
    Button masuk, regis, lupa;
    ProgressDialog pDialog;
    ConnectivityManager conMgr;
    String emailS, passwordS;

    private String url = server.URL + "murid/get";
    private static final String TAG = LoginMuridActivity.class.getSimpleName();

    public final static String TAG_EMAIL = "email";
    public final static String TAG_PASSWORD = "password";

    String tag_json_obj = "json_obj_req";

    SharedPreferences sharedpreferences;
    Boolean session = false;

    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
//        getSupportActionBar().hide();
        setContentView(R.layout.login_murid);

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

        guru = (RadioButton) findViewById(R.id.radioGuru);
        murid = (RadioButton) findViewById(R.id.radioMurid);
        masuk = (Button) findViewById(R.id.buttonLogin);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroupLogin);
        regis = (Button) findViewById(R.id.RegisBtnMurid);
        lupa = (Button) findViewById(R.id.lupaBtnMurid);
        txt_email = (EditText) super.findViewById(R.id.txtEmail);
        txt_pass = (EditText) super.findViewById(R.id.txtPassword);
        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        passwordS = sharedpreferences.getString(TAG_PASSWORD, null);
        emailS = sharedpreferences.getString(TAG_EMAIL, null);

        if (Rak.isExist("login")) {
            if (Rak.grab("login")) {
                startActivity(new Intent(this, HomeMuridActivity.class));
                finish();
            }
        }

        if (session) {
            Intent intent = new Intent(LoginMuridActivity.this, HomeMuridActivity.class);
            intent.putExtra(TAG_PASSWORD, passwordS);
            intent.putExtra(TAG_EMAIL, emailS);
            startActivity(intent);
            finish();
        }

        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txt_email.getText().toString();
                String password = txt_pass.getText().toString();

                if (email.trim().length() > 0 && password.trim().length() > 0) {
                    if (conMgr.getActiveNetworkInfo() != null
                            && conMgr.getActiveNetworkInfo().isAvailable()
                            && conMgr.getActiveNetworkInfo().isConnected()) {
                        checkLogin(email, password);
                    } else {
                        Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(), "Kolom tidak boleh kosong", Toast.LENGTH_LONG).show();
                }


//                Intent i = new Intent(LoginMuridActivity.this, HomeMuridActivity.class);
//                startActivity(i);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioMurid){
                    Toast.makeText(getApplicationContext(),"Selamat Datang Murid !!!", Toast.LENGTH_SHORT).show();
                } else if(checkedId == R.id.radioGuru){
                    Intent i = new Intent(LoginMuridActivity.this, LoginGuruActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),"Selamat Datang Guru !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });



        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginMuridActivity.this, RegisterMuridActivity.class);
                startActivity(i);
            }
        });
        lupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginMuridActivity.this, ForgotMuridActivity.class);
                startActivity(i);
            }
        });


    }

    private void checkLogin(final String email, final String password) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Mohon Tunggu sebentar ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url+"?email="+email+"&password="+password, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "LoginMurid Response: " + response.toString());

                LoginMurid res = new Gson().fromJson(response, LoginMurid.class);

                if (res.isStatus()) {
                    Rak.entry("id", res.getData_Mrd().getId());
                    Rak.entry("email", res.getData_Mrd().getEmail());
                    Rak.entry("passsword", res.getData_Mrd().getPassword());
                    Rak.entry("fullname", res.getData_Mrd().getFullname());
                    Rak.entry("code", res.getData_Mrd().getCode());
                    Rak.entry("birthdate", res.getData_Mrd().getBirthdate());
                    Rak.entry("birthplace", res.getData_Mrd().getBirthplace());
                    Rak.entry("phone", res.getData_Mrd().getPhone());
                    Rak.entry("address", res.getData_Mrd().getAddress());
//                    Rak.entry("gender", res.getData_Mrd().getGender());
//                    Rak.entry("hobby", res.getData_Mrd().getHobby());
                    Rak.entry("images", res.getData_Mrd().getImages());
                    Rak.entry("login", true);


                    hideDialog();
                    startActivity(new Intent(LoginMuridActivity.this, HomeMuridActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "gagal bro", Toast.LENGTH_SHORT).show();
                    hideDialog();

                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "LoginMurid Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();

                params.put("email", email);
                params.put("password", password);

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
