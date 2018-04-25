package com.example.minhpq.firebasedemochat.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.minhpq.firebasedemochat.model.Member;
import com.example.minhpq.firebasedemochat.view.LoginView;
import com.example.minhpq.firebasedemochat.view.RegisterView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by minhpq on 4/23/2018.
 */

public class RegisterPresenter {
    private Context mContext;
    private FirebaseAuth firebaseAuth;
    private RegisterView registerView;
    private DatabaseReference databaseReference;

    public RegisterPresenter(Context mContext, FirebaseAuth firebaseAuth, RegisterView registerView) {
        this.mContext = mContext;
        this.firebaseAuth = firebaseAuth;
        this.registerView = registerView;
    }

    public void setNewmember(String email, String pass) {
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {

                        } else {
                            FirebaseUser newUser = task.getResult().getUser();
                            Log.e("newUser",newUser.getUid());
                            createUser(newUser.getUid(), newUser.getDisplayName(), newUser.getEmail());
                        }
                    }
                });
    }


    public void createUser(String userId, String displayName, String email) {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Member");
        Member member = new Member(userId, displayName, email);
        databaseReference.child(userId).setValue(member).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (!task.isSuccessful()) {
                    registerView.showError();
                } else {
                    registerView.showSuccess();
                }
            }
        });

    }

}
