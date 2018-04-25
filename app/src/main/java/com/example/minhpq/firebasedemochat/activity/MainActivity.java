package com.example.minhpq.firebasedemochat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.minhpq.firebasedemochat.OnclickInterface;
import com.example.minhpq.firebasedemochat.R;
import com.example.minhpq.firebasedemochat.RealmService;
import com.example.minhpq.firebasedemochat.adapter.ListMemberAdapter;
import com.example.minhpq.firebasedemochat.model.Member;
import com.example.minhpq.firebasedemochat.presenter.HomePresenter;
import com.example.minhpq.firebasedemochat.util.CheckConnection;
import com.example.minhpq.firebasedemochat.view.HomeView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class MainActivity extends BaseActivity implements HomeView {
    CheckConnection checkConnection;
    RealmService realmService;
    Realm realm;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private List<Member> memberList = new ArrayList<>();
    @BindView(R.id.rv_user)
    RecyclerView rv_user;
    ListMemberAdapter listMemberAdapter;
    DatabaseReference mRef;
    DatabaseReference mMemberRef;
    private HomePresenter homePresenter;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        rv_user.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRef = FirebaseDatabase.getInstance().getReference();
        mMemberRef = mRef.child("Member");
        realm=Realm.getDefaultInstance();
        realmService=new RealmService(realm);
        checkConnection=new CheckConnection(getApplicationContext());
        homePresenter = new HomePresenter(realmService,memberList, this, mMemberRef, firebaseAuth);
        homePresenter.getListmember();

    }

    @Override
    public void bindViews() {
        ButterKnife.bind(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void showListmember(List<Member> list) {
        if(checkConnection.isOnline()){
            listMemberAdapter = new ListMemberAdapter(new OnclickInterface() {
                @Override
                public void onItemClickListener(int position) {
                    startActivity(new Intent(MainActivity.this,ChatLayoutActivity.class));
                    finish();
                }
            },
                    MainActivity.this, list, homePresenter);
            rv_user.setAdapter(listMemberAdapter);
        }else {
            listMemberAdapter.setListMemberAdapter(realmService.getAllMember());
        }
    }

}
