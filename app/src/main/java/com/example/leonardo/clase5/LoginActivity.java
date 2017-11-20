package com.example.leonardo.clase5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Leonardo on 20/11/2017.
 */

public class LoginActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
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
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(i);
            finish();
        }
    }
}
