package com.example.sony.tes.Pelajaran;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sony.tes.Murid.HistoryMuridActivity;
import com.example.sony.tes.Murid.SettingMuridActivity;
import com.example.sony.tes.Murid.TransaksiMuridActivity;
import com.example.sony.tes.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


public class PelajaranActivity extends AppCompatActivity {

    ImageView logout, histori, setting, transaksi;

    CarouselView carouselView;
    int[] gambarSlide = {R.drawable.iklan1, R.drawable.iklan2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title

        setContentView(R.layout.activity_pelajaran_sd);

//  ubah title dan subtitle
        TextView title = (TextView) findViewById(R.id.jumbotron_title);
        title.setText("CLASS");
        TextView title1 = (TextView) findViewById(R.id.jumbotron_subtitle);
        title1.setText("1");
        TextView title2 = (TextView) findViewById(R.id.main_title);
        title2.setText("Course");

//  mata plejaran
        String[] array_item = {"Matematika", "B. Indonesia", "B. Inggris"};
        GridView gridView = (GridView) findViewById(R.id.gridView_course);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_course_sd, array_item);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });

//        GridView gridView = (GridView) findViewById(R.id.gridView_course);
//        gridView.setAdapter(new textCourseAdapter(this, array_item));


//  carousel
        carouselView = (CarouselView) findViewById(R.id.carouselViewKelas);
        carouselView.setImageListener(imageListener);
        carouselView.setPageCount(gambarSlide.length);

        histori = (ImageView) findViewById(R.id.imgMenuHistory);
        histori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PelajaranActivity.this, HistoryMuridActivity.class);
                startActivity(i);
            }
        });

        setting = (ImageView) findViewById(R.id.imgMenuSetting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PelajaranActivity.this, SettingMuridActivity.class);
                startActivity(i);
            }
        });

        transaksi = (ImageView) findViewById(R.id.imgMenuSaldo);
        transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PelajaranActivity.this, TransaksiMuridActivity.class);
                startActivity(i);
            }
        });



//  tombol back
        ImageView backButton = (ImageView) this.findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(gambarSlide[position]);
        }
    };




}
