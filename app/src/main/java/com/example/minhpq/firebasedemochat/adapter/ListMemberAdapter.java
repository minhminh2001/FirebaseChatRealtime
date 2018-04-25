package com.example.minhpq.firebasedemochat.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.minhpq.firebasedemochat.OnclickInterface;
import com.example.minhpq.firebasedemochat.R;
import com.example.minhpq.firebasedemochat.activity.ChatLayoutActivity;
import com.example.minhpq.firebasedemochat.model.Member;
import com.example.minhpq.firebasedemochat.presenter.HomePresenter;
import com.example.minhpq.firebasedemochat.presenter.RegisterPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by minhpq on 4/23/2018.
 */

public class ListMemberAdapter extends RecyclerView.Adapter<ListMemberAdapter.ViewHolder>{
    private OnclickInterface onclickInterface;
    private Context mContext;
    private List<Member> memberList = new ArrayList<>();
    private HomePresenter homePresenter;

    public ListMemberAdapter( OnclickInterface onclickInterface,Context mContext, List<Member> memberList, HomePresenter homePresenter) {
        this.onclickInterface =onclickInterface;
        this.mContext = mContext;
        this.memberList = memberList;
        this.homePresenter = homePresenter;
    }
    public void setListMemberAdapter(List<Member>listMember){
        memberList.addAll(listMember);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_memver, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_Name.setText(homePresenter.usernameFromEmail(memberList.get(position).getEmail()));
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_Name;

        public ViewHolder(final View itemView) {
            super(itemView);
            tv_Name = itemView.findViewById(R.id.tv_Namemember);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sendId(memberList.get(getAdapterPosition()).getUserId());
                }
            });

        }
    }

    public void sendId(String idreciver){
        Intent intent=new Intent(mContext, ChatLayoutActivity.class);
        intent.putExtra("userId",idreciver);
        mContext.startActivity(intent);
    }


}
