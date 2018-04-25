package com.example.minhpq.firebasedemochat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.minhpq.firebasedemochat.R;
import com.example.minhpq.firebasedemochat.presenter.RegisterPresenter;
import com.example.minhpq.firebasedemochat.view.RegisterView;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements RegisterView {


    RegisterPresenter registerPresenter;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    @BindView(R.id.ed_emailRegister)
    EditText edEmailRegister;
    @BindView(R.id.ed_emailPassRegister)
    EditText edEmailPassRegister;
    @BindView(R.id.btn_register2)
    Button btnRegister2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Tag","RegisterActivity");
        registerPresenter = new RegisterPresenter(getApplicationContext(), firebaseAuth, this);

    }

    @Override
    public void bindViews() {
        ButterKnife.bind(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @OnClick(R.id.btn_register2)
    void registerMember() {
        registerPresenter.setNewmember(edEmailRegister.getText().toString(), edEmailPassRegister.getText().toString());
    }

    @Override
    public void showSuccess() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }
}
