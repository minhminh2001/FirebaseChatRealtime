package com.example.minhpq.firebasedemochat.Application;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by minhpq on 4/25/2018.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
