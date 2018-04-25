package com.example.minhpq.firebasedemochat.Application;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by minhpq on 4/25/2018.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("notes.realm")
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }
}
