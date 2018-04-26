package com.example.minhpq.firebasedemochat.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.minhpq.firebasedemochat.activity.LoginActivity;
import com.example.minhpq.firebasedemochat.model.Member;
import com.example.minhpq.firebasedemochat.view.LoginView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;

/**
 * Created by minhpq on 4/23/2018.
 */

public class LoginPresenter {
    private LoginActivity loginActivity;
    private FirebaseAuth firebaseAuth;
    private LoginView loginView;

    public LoginPresenter(LoginView loginView, FirebaseAuth firebaseAuth, LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
        this.firebaseAuth = firebaseAuth;
        this.loginView = loginView;
    }

    public void setLoginMember(String email,String password){
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(loginActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            loginView.showSuccess();
                        }else {
                            loginView.showError();
                        }
                    }
                });
    }

}
