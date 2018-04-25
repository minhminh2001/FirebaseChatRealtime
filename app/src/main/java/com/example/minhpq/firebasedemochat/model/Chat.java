package com.example.minhpq.firebasedemochat.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by minhpq on 4/24/2018.
 */

public class Chat extends RealmObject{
    @PrimaryKey
    private String idSender;
    private String idReciver;
    private String message;


    public Chat() {
    }

    public Chat(String idSender, String idReciver, String message) {
        this.idSender = idSender;
        this.idReciver = idReciver;
        this.message = message;
    }

    public String getIdSender() {
        return idSender;
    }

    public void setIdSender(String idSender) {
        this.idSender = idSender;
    }

    public String getIdReciver() {
        return idReciver;
    }

    public void setIdReciver(String idReciver) {
        this.idReciver = idReciver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
