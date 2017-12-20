package com.robillo.readrush.ui.main.discover.collections;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.robillo.readrush.R;
import com.robillo.readrush.data.network.retrofit.ApiClient;
import com.robillo.readrush.data.network.retrofit.ApiInterface;
import com.robillo.readrush.data.network.retrofit.model.CollectionListItem;
import com.robillo.readrush.data.network.retrofit.model.CollectionListItemSuper;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.main.MainActivity;
import com.robillo.readrush.ui.main.discover.adapters.CollectionListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectionsActivity extends BaseActivity implements CollectionsMvpView {

    String mCollId;
    CollectionListAdapter mCollectionListAdapter;
    @SuppressWarnings("FieldCanBeLocal")
    private ApiInterface mApiService;

    List<CollectionListItem> mCollectionListItems = new ArrayList<>();

    @BindView(R.id.coll_name)
    TextView mCollName;

    @BindView(R.id.collections_list)
    RecyclerView mCollectionsRv;

    public static Intent getStartIntent(Context context, String collectionId, String collectionName) {
        Intent intent = new Intent(context, CollectionsActivity.class);
        intent.putExtra("collection_id", collectionId);
        intent.putExtra("collection_name", collectionName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections);

        ButterKnife.bind(this);

        setUp();
    }

    @Override
    protected void setUp() {
        mCollId = getIntent().getStringExtra("collection_id");
        mCollName.setText(getIntent().getStringExtra("collection_name"));

        mApiService = ApiClient.getClient().create(ApiInterface.class);

        retrofit2.Call<CollectionListItemSuper> call = mApiService.getCollectionFromCid(mCollId);
        if(call!=null){
            call.enqueue(new Callback<CollectionListItemSuper>() {
                @SuppressWarnings("ConstantConditions")
                @Override
                public void onResponse(@NonNull retrofit2.Call<CollectionListItemSuper> call, @NonNull Response<CollectionListItemSuper> response) {
                    if(response.body().getMessage()!=null){
                        mCollectionListItems = response.body().getMessage();
                        mCollectionListAdapter = new CollectionListAdapter(mCollectionListItems, CollectionsActivity.this);
                        mCollectionsRv.setLayoutManager(new LinearLayoutManager(CollectionsActivity.this, LinearLayoutManager.VERTICAL, false));
                        mCollectionsRv.setAdapter(mCollectionListAdapter);
                        mCollectionsRv.setVisibility(View.VISIBLE);
//                        mProgressCollections.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(@NonNull retrofit2.Call<CollectionListItemSuper> call, @NonNull Throwable t) {
                    Toast.makeText(CollectionsActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
