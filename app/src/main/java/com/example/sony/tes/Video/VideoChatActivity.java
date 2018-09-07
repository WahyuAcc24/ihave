package com.example.sony.tes.Video;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.sony.tes.Murid.HomeMuridActivity;
import com.example.sony.tes.R;
import com.opentok.android.OpentokError;
import com.opentok.android.Publisher;
import com.opentok.android.PublisherKit;
import com.opentok.android.Session;
import com.opentok.android.Stream;
import com.opentok.android.Subscriber;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by SONY on 14/8/2018.
 */
public class VideoChatActivity extends AppCompatActivity implements Session.SessionListener, PublisherKit.PublisherListener{

    private static String API_KEY = "46171282";
    private static String SESSION_ID = "2_MX40NjE3MTI4Mn5-MTUzNDc0Njc5MTA2NH5zb0lKT2xuUFNnbFFQL1JhdXVHMU5CNGN-fg";
    private static String TOKEN = "T1==cGFydG5lcl9pZD00NjE3MTI4MiZzaWc9ZmVhMmY0NjhlYjc0YTdmZWY3NTgyNmRlZGJkODhlM2U5NjUzNzNlODpzZXNzaW9uX2lkPTJfTVg0ME5qRTNNVEk0TW41LU1UVXpORGMwTmpjNU1UQTJOSDV6YjBsS1QyeHVVRk5uYkZGUUwxSmhkWFZITVU1Q05HTi1mZyZjcmVhdGVfdGltZT0xNTM0NzQ2ODUwJm5vbmNlPTAuNzc5NDk4ODM2NTQxODM5NiZyb2xlPXB1Ymxpc2hlciZleHBpcmVfdGltZT0xNTM3MzM4ODU2JmluaXRpYWxfbGF5b3V0X2NsYXNzX2xpc3Q9";
    private static final String LOG_TAG = VideoChatActivity.class.getSimpleName();
    private static final int RC_SETTINGS_SCREEN_PERM = 123;
    private static final int RC_VIDEO_APP_PERM = 124;
    private Session session;

    private Publisher publisher;
    private Subscriber subscriber;
    private FrameLayout pb,sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        requestPermissions();

        pb = (FrameLayout) findViewById(R.id.publisher_container);
        sb = (FrameLayout) findViewById(R.id.subscribe_container);



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);

    }

    @AfterPermissionGranted(RC_SETTINGS_SCREEN_PERM)
    private void requestPermissions(){
        String [] perm = {Manifest.permission.INTERNET,Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
        if(EasyPermissions.hasPermissions(this,perm))
        {

            session = new Session.Builder(this, API_KEY,SESSION_ID).build();
            session.setSessionListener(this);
            session.connect(TOKEN);

        }else{
            EasyPermissions.requestPermissions(this,"Aplikasi ini butuh mengakses kamera Anda", RC_SETTINGS_SCREEN_PERM,perm);
        }

    }

    @Override
    public void  onConnected(Session session) {

        publisher = new Publisher.Builder(this).build();
        publisher.setPublisherListener(this);

        pb.addView(publisher.getView());
        session.publish(publisher);


    }

    @Override
    public void onDisconnected(Session session) {



    }

    @Override
    public void onStreamReceived(Session session, Stream stream) {

        if(subscriber == null){
            subscriber = new Subscriber.Builder(this,stream).build();
            session.subscribe(subscriber);
            sb.addView(subscriber.getView());
        }

    }

    @Override
    public void onStreamDropped(Session session, Stream stream) {

        if(subscriber !=null){
            subscriber = null;
            sb.removeAllViews();
        }

    }

    @Override
    public void onError(Session session, OpentokError opentokError) {

    }

    @Override
    public void onStreamCreated(PublisherKit publisherKit, Stream stream) {

    }

    @Override
    public void onStreamDestroyed(PublisherKit publisherKit, Stream stream) {

    }

    @Override
    public void onError(PublisherKit publisherKit, OpentokError opentokError) {

    }

    public void onBackPressed() {

        if(subscriber !=null){
            subscriber = null;
            sb.removeAllViews();
        }
        Intent startMain = new Intent(VideoChatActivity.this, HomeMuridActivity.class);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);

    }

}
