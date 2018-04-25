package com.example.minhpq.firebasedemochat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.minhpq.firebasedemochat.R;
import com.example.minhpq.firebasedemochat.presenter.LoginPresenter;
import com.example.minhpq.firebasedemochat.util.CheckConnection;
import com.example.minhpq.firebasedemochat.view.LoginView;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginView {
    LoginPresenter loginPresenter;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    @BindView(R.id.ed_emailLogin)
    EditText edEmailLogin;
    @BindView(R.id.ed_emailPass)
    EditText edEmailPass;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Tag", "LoginActivity");
        loginPresenter = new LoginPresenter(this, firebaseAuth, this);
    }


    @OnClick(R.id.btn_login)
    public void login() {

        loginPresenter.getNewMember((edEmailLogin.getText().toString()), edEmailPass.getText().toString());
    }

    @OnClick(R.id.btn_register)
    public void register() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void bindViews() {
        ButterKnife.bind(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void showSuccess() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }
}
