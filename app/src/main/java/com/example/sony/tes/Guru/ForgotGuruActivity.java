package com.example.sony.tes.Guru;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.sony.tes.Murid.HomeMuridActivity;
import com.example.sony.tes.R;

/**
 * Created by SONY on 18/7/2018.
 */
public class ForgotGuruActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_forgot);

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
