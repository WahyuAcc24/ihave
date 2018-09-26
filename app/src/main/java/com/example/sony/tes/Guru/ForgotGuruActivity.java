package com.example.sony.tes.Guru;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sony.tes.Murid.HomeMuridActivity;
import com.example.sony.tes.R;

/**
 * Created by SONY on 18/7/2018.
 */
public class ForgotGuruActivity extends AppCompatActivity {

    Button btnproses;
    ProgressDialog pDialog;

    private String url = "http://demo.t-hisyam.net/ihave/api/murid/reset_password";
    private static final String TAG = ForgotGuruActivity.class.getSimpleName();

    String tag_json_obj = "json_obj_req";
    ConnectivityManager conMgr;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_forgot);

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



        btnproses = (Button) findViewById(R.id.btnLupa);



        //  tombol back
        ImageView backButton = (ImageView) this.findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void Masuk(View view) {
        Intent intent = new Intent(this, HomeMuridActivity.class);
        startActivity(intent);
    }


}
