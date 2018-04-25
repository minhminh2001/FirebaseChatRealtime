package com.example.minhpq.firebasedemochat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.minhpq.firebasedemochat.R;
import com.example.minhpq.firebasedemochat.util.CheckConnection;

import butterknife.ButterKnife;

public class SplashScreen extends BaseActivity{
    CheckConnection checkConnection;
    private static int SPLASH_TIME_OUT = 1000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkConnection=new CheckConnection(getApplicationContext());
        if(checkConnection.isOnline()){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }else {
            Toast.makeText(this, "No Network", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    public int getLayout() {
        return R.layout.activity_splash;

    }

    @Override
    public void bindViews() {
        ButterKnife.bind(this);
    }
}
