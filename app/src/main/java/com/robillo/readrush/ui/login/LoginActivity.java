package com.robillo.readrush.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.ArrayRes;
import android.support.annotation.StringRes;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.robillo.readrush.R;
import com.robillo.readrush.ReadRushApp;
import com.robillo.readrush.data.others.Conversation;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.custom.MyChatEditText;
import com.robillo.readrush.ui.custom.MyChatView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements LoginMvpView {

    List<Conversation> mConversations = new ArrayList<>();

    @Inject
    LoginMvpPresenter<LoginMvpView> mPresenter;

    @BindView(R.id.prev)
    TextView mTextPrevious;

    @BindView(R.id.next)
    LinearLayout mLinearNext;

    @BindView(R.id.chat_primary)
    MyChatView mChatPrimary;

    @BindView(R.id.chat_secondary)
    MyChatView mChatSecondary;

    @BindView(R.id.chat_edit_text)
    MyChatEditText myChatEditText;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getActivityComponent().inject(LoginActivity.this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(LoginActivity.this);

        setUp();
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    protected void setUp() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                loadConversations();
            }
        });
    }

    @Override
    public void loadConversations() {
        mConversations = mPresenter.loadLists(loadArray(R.array.ken_text), loadArray(R.array.chat_edit_text_hint), loadArray(R.array.chat_primary), loadArray(R.array.chat_secondary));

    }

    @Override
    public String[] loadArray(@ArrayRes int id) {
        return getResources().getStringArray(id);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

}
