package com.example.minhpq.firebasedemochat.model;

/**
 * Created by minhpq on 4/23/2018.
 */

public class Member {
    private String userId;
    private String displayName;
    private String email;
    private String password;

    public Member() {
    }

    public Member(String userId, String displayName, String email) {
        this.userId = userId;
        this.displayName = displayName;
        this.email = email;
    }

    public Member(String email,String userId) {
        this.userId=userId;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
