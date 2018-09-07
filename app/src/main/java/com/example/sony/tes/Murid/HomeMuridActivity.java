package com.example.sony.tes.Murid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.sony.tes.Pelajaran.PelajaranSdActivity;
import com.example.sony.tes.Pelajaran.PelajaranSmaActivity;
import com.example.sony.tes.Pelajaran.PelajaranSmpActivity;
import com.example.sony.tes.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import io.isfaaghyth.rak.Rak;

public class HomeMuridActivity extends AppCompatActivity {

    private ProgressDialog pd;
    private RecyclerView lstLevel;
    private RequestQueue requestQueue;
    private com.google.gson.Gson gson;
    ProgressBar pgList;

    TextView lvlSD,lvlSMP,lvlSMA;

    private static final String TAG = HomeMuridActivity.class.getSimpleName();
    ImageView logout, histori, setting, transaksi;

    CarouselView carouselView;
    int[] gambarSlide = {R.drawable.iklan1, R.drawable.iklan2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Rak.initialize(this);
        setContentView(R.layout.menu_murid);

        //  carousel
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(gambarSlide.length);

        carouselView.setImageListener(imageListener);
        View v = LayoutInflater.from(HomeMuridActivity.this).inflate(R.layout.layout_bottomenu, null);

        logout = (ImageView) findViewById(R.id.imgMenuLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeMuridActivity.this, LoginMuridActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                Rak.entry("login", false);
                Rak.removeAll(getApplicationContext());
                finishAffinity();
            }
        });

        histori = (ImageView) findViewById(R.id.imgMenuHistory);
        histori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeMuridActivity.this, HistoryMuridActivity.class);
                startActivity(i);
            }
        });

        setting = (ImageView) findViewById(R.id.imgMenuSetting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(HomeMuridActivity.this, SettingMuridActivity.class);
                Intent i = new Intent(HomeMuridActivity.this, SettingMuridActivity.class);
                startActivity(i);
            }
        });

        transaksi = (ImageView) findViewById(R.id.imgMenuSaldo);
        transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeMuridActivity.this, TransaksiMuridActivity.class);
                startActivity(i);
            }
        });

        TextView lain = (TextView) findViewById(R.id.lainnya);
        lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (HomeMuridActivity.this, LainnyaActivity.class);
                startActivity(i);
            }
        });

        lvlSD = (TextView) findViewById(R.id.btnLevelSD);
        lvlSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeMuridActivity.this, PelajaranSdActivity.class);
                startActivity(i);
            }
        });

        lvlSMP = (TextView) findViewById(R.id.btnLevelSMP);
        lvlSMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeMuridActivity.this, PelajaranSmpActivity.class);
                startActivity(i);
            }
        });

        lvlSMA = (TextView) findViewById(R.id.btnLevelSMA);
        lvlSMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeMuridActivity.this, PelajaranSmaActivity.class);
                startActivity(i);
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

//        // definisi antrian akses data web service
//        requestQueue = Volley.newRequestQueue(this);
//        // definisikan objek gsonbuilder yang bertugas melakukan deserialisasi data json ke gson
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        // inisialisasi obj gson
//        gson = gsonBuilder.create();
//
//        lstLevel = (RecyclerView) findViewById(R.id.lst_level);
//        lstLevel.setLayoutManager(new LinearLayoutManager(this));
//
//        levels = new ArrayList<>();
//
//        pd = new ProgressDialog(HomeMuridActivity.this);
//        pd.setMessage("loading");
//        load_data();
//    }
//        private void load_data(){
//            pd.show();
//            String urlLevel = server.URL + "category/get";
//
//            StringRequest request = new StringRequest(Request.Method.GET, urlLevel, onPostsLoaded, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    AlertDialog.Builder inetErr = new AlertDialog.Builder(HomeMuridActivity.this);
//                    inetErr.setTitle("Terjadi Kesalahan");
//                    inetErr.setMessage("Periksa kembali koneksi internet anda.");
//                    inetErr.setNegativeButton("RETRY", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            load_data();
//                        }
//                    });
//                    inetErr.show();
//                }
//            });
//            requestQueue.add(request);
//        }
//
//
//    //merespon apa yang akan dilakukan di listview
//    private final Response.Listener<String> onPostsLoaded = new Response.Listener<String>() {
//        @Override
//        public void onResponse(String response) {
//            Log.e(TAG, "Response: " + response.toString());
//            //gambar loading akan menghilang ketika proses berjalan dengan baik
//
//            //menjadikan data gson menjadi array dan adapter dan bisa diklik sesuai isi database
//            List<mrd> test = Arrays.asList(gson.fromJson(response, mrd[].class));
//            adapter = new KelasSdAdapter(test);
//            adapter.setListener(new ItemClickListener() {
//                  @Override
//                  public void onClicked(final mrd mhs, int position, View view) {
//                     final AlertDialog dialog = new AlertDialog.Builder(HomeMuridActivity.this).create();
//                      View v = LayoutInflater.from(HomeMuridActivity.this).inflate(R.layout.layout_level, null);
//
//                      TextView txtName = (TextView) v.findViewById(R.id.btnLevel);
//                      txtName.setText(mhs.getName());
//
//                      dialog.setView(v);
//                      dialog.show();
//                  }
//            });
//            lstLevel.setAdapter(adapter);
//        }};
//
//
//





//

