package com.example.minhpq.firebasedemochat.view;

import com.example.minhpq.firebasedemochat.model.Chat;

import java.util.List;

/**
 * Created by minhpq on 4/24/2018.
 */

public interface ChatView {
    void showChatmessage(List<Chat> chatList);

    void showSuccess();

    void showError();
}
