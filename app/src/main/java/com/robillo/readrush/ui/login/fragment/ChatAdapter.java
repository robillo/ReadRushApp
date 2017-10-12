package com.robillo.readrush.ui.login.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robillo.readrush.R;
import com.robillo.readrush.ui.custom.MyChatEdittext;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by robinkamboj on 12/10/17.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {


    @Override
    public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ChatHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ChatHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text)
        TextView chatText;

        public ChatHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
