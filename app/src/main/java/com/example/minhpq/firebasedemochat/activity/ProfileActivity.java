package com.example.minhpq.firebasedemochat.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.minhpq.firebasedemochat.R;

import butterknife.ButterKnife;

public class ProfileActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_profile;
    }

    @Override
    public void bindViews() {
        ButterKnife.bind(this);
    }
}
