package com.example.minhpq.firebasedemochat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.minhpq.firebasedemochat.R;
import com.example.minhpq.firebasedemochat.model.Chat;
import com.example.minhpq.firebasedemochat.model.Member;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

/**
 * Created by minhpq on 4/24/2018.
 */

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.ViewHolder> {
    private List<Chat> chatList;
    private Context mContext;
    public static final int ITEM_TYPE_SENT = 0;
    public static final int ITEM_TYPE_RECEIVED = 1;

    public ChatMessageAdapter(List<Chat> chatList, Context mContext) {
        this.chatList = chatList;
        this.mContext = mContext;
    }

    @Override
    public ChatMessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == ITEM_TYPE_SENT) {
            view = LayoutInflater.from(mContext).inflate(R.layout.rc_item_message_send, parent, false);
        } else if (viewType == ITEM_TYPE_RECEIVED) {
            view = LayoutInflater.from(mContext).inflate(R.layout.rc_item_message_reciver, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatMessageAdapter.ViewHolder holder, int position) {
        holder.tv_content.setText(chatList.get(position).getMessage());
    }

    @Override
    public int getItemViewType(int position) {
        if (chatList.get(position).getIdSender().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
            return ITEM_TYPE_SENT;
        } else {
            return ITEM_TYPE_RECEIVED;
        }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_content;
        public View layout;


        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
    public void setListMessegaAdapter(List<Chat>chatList){
        chatList.addAll(chatList);
        notifyDataSetChanged();
    }
}
