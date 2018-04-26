package com.example.minhpq.firebasedemochat.presenter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.minhpq.firebasedemochat.R;
import com.example.minhpq.firebasedemochat.activity.LoginActivity;
import com.example.minhpq.firebasedemochat.model.Member;
import com.example.minhpq.firebasedemochat.view.LoginView;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

import javax.security.auth.callback.Callback;

/**
 * Created by minhpq on 4/23/2018.
 */

public class LoginPresenter {
    private Context mContext;
    private CallbackManager mCallbackManager;
    private LoginButton loginButton;
    private LoginActivity loginActivity;
    private FirebaseAuth firebaseAuth;
    private LoginView loginView;

    public LoginPresenter(Context mContext, CallbackManager mCallbackManager, LoginButton loginButton, LoginActivity loginActivity, FirebaseAuth firebaseAuth, LoginView loginView) {
        this.mContext = mContext;
        this.mCallbackManager = mCallbackManager;
        this.loginButton = loginButton;
        this.loginActivity = loginActivity;
        this.firebaseAuth = firebaseAuth;
        this.loginView = loginView;
    }

    public void setLoginMember(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(loginActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            loginView.showSuccess();
                        } else {
                            loginView.showError();
                        }
                    }
                });
    }

    public void setLoginFacebook() {
        mCallbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential authCredential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(authCredential)
                .addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

                        }
                    }
                });
    }


}
