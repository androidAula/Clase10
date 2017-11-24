package com.example.leonardo.clase5;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.leonardo.clase5.Utils.MusicService;
import com.example.leonardo.clase5.Utils.MyPreferences;

/**
 * Created by Leonardo on 20/11/2017.
 */

public class LoginActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,intent, 0);


        Notification.Builder builder = new Notification.Builder(this)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentText("Android Notifications")
                .setPriority(Notification.PRIORITY_MAX);;
        builder.setContentTitle("Maximun priority notification");


        notificationManager.notify(0,builder.build());

        Button buttonLogin=(Button) findViewById(R.id.bt_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usrName = (EditText)findViewById(R.id.et_nombre);
                EditText usrLastName = (EditText)findViewById(R.id.et_apellido);
                MyPreferences pref = new MyPreferences(LoginActivity.this);
                pref.setUserName(usrName.getText().toString().trim());
                pref.setOld(true);
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                finish();
            }
        });


        MyPreferences pref = new MyPreferences(LoginActivity.this);
        if(!pref.isFirstTime()){

            Intent in=new Intent(this, MusicService.class);
            startService(in);

            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(i);
            finish();
        }
    }
}
