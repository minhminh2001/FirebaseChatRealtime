package com.example.minhpq.firebasedemochat.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.minhpq.firebasedemochat.RealmService;
import com.example.minhpq.firebasedemochat.activity.ChatLayoutActivity;
import com.example.minhpq.firebasedemochat.model.Chat;
import com.example.minhpq.firebasedemochat.model.Member;
import com.example.minhpq.firebasedemochat.view.ChatView;
import com.example.minhpq.firebasedemochat.view.HomeView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by minhpq on 4/24/2018.
 */

public class ChatPresenter {
    private RealmService realmService;
    DatabaseReference mChatRef;
    private ChatView chatView;
    private List<Chat> mListChat;


    public ChatPresenter(RealmService realmService,DatabaseReference mChatRef, ChatView chatView, List<Chat> chatList) {
        this.realmService=realmService;
        this.mChatRef = mChatRef;
        this.chatView = chatView;
        mListChat = chatList;
    }

    public void senMesageToFirebase(String s, String idSend, String recive) {
        mListChat.clear();
        Chat chat = new Chat(idSend, recive, s);
        mChatRef.push().setValue(chat).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (!task.isSuccessful()) {
                    chatView.showError();
                } else {
                    chatView.showSuccess();
                }
            }
        });
    }

    public void showListChat(final String idReciver) {
        mChatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mListChat.clear();
                if (dataSnapshot.getChildrenCount() > 0) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Chat chat = snapshot.getValue(Chat.class);
                        if (chat.getIdSender().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                && chat.getIdReciver().equals(idReciver) || chat.getIdSender().equals(idReciver)
                                && chat.getIdReciver().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                            mListChat.add(chat);
                            realmService.addListMessega(mListChat);


                        }

                    }

                    chatView.showChatmessage(mListChat);

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
