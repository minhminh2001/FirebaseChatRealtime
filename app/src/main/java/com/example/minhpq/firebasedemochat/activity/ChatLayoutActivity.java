package com.example.minhpq.firebasedemochat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.minhpq.firebasedemochat.R;
import com.example.minhpq.firebasedemochat.adapter.ChatMessageAdapter;
import com.example.minhpq.firebasedemochat.model.Chat;
import com.example.minhpq.firebasedemochat.presenter.ChatPresenter;
import com.example.minhpq.firebasedemochat.view.ChatView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minhpq on 4/24/2018.
 */

public class ChatLayoutActivity extends BaseActivity implements ChatView {
    @BindView(R.id.rv_massage)
    RecyclerView rvMassage;
    @BindView(R.id.ed_input_message)
    EditText edInputMessage;
    @BindView(R.id.btn_sendmessage)
    ImageButton btnSendmessage;
    private ChatMessageAdapter chatMessageAdapter;
    private List<Chat> chatList = new ArrayList<>();
    String idSend;
    String recive;
    DatabaseReference mMemberRef;
    ChatPresenter chatPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recive = getIntent().getExtras().getString("userId");
        mMemberRef = FirebaseDatabase.getInstance().getReference().child("Chat");
        chatPresenter = new ChatPresenter(mMemberRef, this, chatList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMassage.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setStackFromEnd(true);
        idSend = FirebaseAuth.getInstance().getCurrentUser().getUid();
        chatPresenter.showListChat(recive);
        btnSendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edInputMessage.getText().toString().equals("")){
                    chatPresenter.senMesageToFirebase(edInputMessage.getText().toString(), idSend, recive);
                }else {
                    Toast.makeText(ChatLayoutActivity.this, "please add messages", Toast.LENGTH_SHORT).show();
                    edInputMessage.requestFocus();

                }
                
            }
        });
    }


    @Override
    public int getLayout() {
        return R.layout.chat_layout;
    }

    @Override
    public void bindViews() {
        ButterKnife.bind(this);
    }

    @Override
    public void showChatmessage(List<Chat> chatList) {
        chatMessageAdapter = new ChatMessageAdapter(chatList, this);
        rvMassage.setAdapter(chatMessageAdapter);
    }

    @Override
    public void showSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        edInputMessage.setText("");

    }

    @Override
    public void showError() {
        Toast.makeText(ChatLayoutActivity.this, "message null", Toast.LENGTH_SHORT).show();
    }
}
