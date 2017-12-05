package com.robillo.readrush.ui.search.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robillo.readrush.R;
import com.robillo.readrush.data.db.model.SearchName;
import com.robillo.readrush.ui.search.SearchActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by robinkamboj on 30/11/17.
 */

public class SearchNamesAdapter extends RecyclerView.Adapter<SearchNamesAdapter.SearchesHolder> {

    private List<SearchName> mList;
    private Context context;

    public SearchNamesAdapter(List<SearchName> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public SearchesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new SearchesHolder(LayoutInflater.from(context).inflate(R.layout.row_search_name, parent, false));
    }

    @Override
    public void onBindViewHolder(final SearchesHolder holder, int position) {
        holder.mSearchName.setText(mList.get(position).getmSearchName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SearchActivity) context).loadSuggestions(holder.mSearchName.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class SearchesHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.search_name)
        TextView mSearchName;

        SearchesHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
