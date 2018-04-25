package com.example.minhpq.firebasedemochat;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.minhpq.firebasedemochat.model.Member;
import com.example.minhpq.firebasedemochat.util.CheckConnection;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class RealmService {
    private Realm realm;

    public RealmService(Realm realm) {
        this.realm = realm;
    }

    public void closeRealm(){
        realm.close();
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void addListMember(final List<Member>listMember){
        try(Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    List<Member> newsList = new ArrayList<>();
                    newsList.addAll(listMember);
                    realm.insertOrUpdate(newsList);
                    closeRealm();
                }
            });
        }

    }
    public List<Member> getAllMember() {
        List<Member> memberList = null;
        try {
            memberList = new ArrayList<>();
            memberList.addAll(realm.where(Member.class).findAll());
        } catch (Exception e) {
            realm.close();
        }

        return memberList;
    }

}
