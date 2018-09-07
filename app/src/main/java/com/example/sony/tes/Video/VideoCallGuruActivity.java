package com.example.sony.tes.Video;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.sony.tes.R;
import com.vidyo.VidyoClient.Connector.Connector;
import com.vidyo.VidyoClient.Connector.ConnectorPkg;


public class VideoCallGuruActivity extends AppCompatActivity implements Connector.IConnect {

    private Connector vc;
    private FrameLayout videoFrame;
    Button start,konek,gakKonek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_call);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }
        ConnectorPkg.setApplicationUIContext(this);
        ConnectorPkg.initialize();
        videoFrame = (FrameLayout) findViewById(R.id.videoFrameCall);



        start = (Button) findViewById(R.id.btnStart);
        konek = (Button) findViewById(R.id.btnKonek);
        gakKonek = (Button) findViewById(R.id.btnDiskonek);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vc = new Connector(videoFrame, Connector.ConnectorViewStyle.VIDYO_CONNECTORVIEWSTYLE_Default, 16, "", "", 0);
                vc.showViewAt(videoFrame, 0, 0, videoFrame.getWidth(), videoFrame.getHeight());

            }


        });

        konek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String token = "cHJvdmlzaW9uAGloYXZlQGY1MTRlNC52aWR5by5pbwA2MzcwMDU1MjY3OAAAYTAwYmFhYTA0ZTQ2OTczNmJhOTkzMWUyNDczZDA2YTRiYzUxZDhiNTE2MTljYjljNDVhOTNjZjFjNDA4OGI5MzllZjk0ZTE2YTY1NzgyODg5MjY3MDYzZGVhZDU1NTQ2";
                vc.connect("prod.vidyo.io", token, "DemoUser", "DemoRoom", VideoCallGuruActivity.this);

            }
        });

        gakKonek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vc.disconnect();
            }
        });

    }



    public void onSuccess() {}

    public void onFailure(Connector.ConnectorFailReason reason) {}

    public void onDisconnected(Connector.ConnectorDisconnectReason reason) {}
}
