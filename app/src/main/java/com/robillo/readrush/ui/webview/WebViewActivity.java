package com.robillo.readrush.ui.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.robillo.readrush.R;
import com.robillo.readrush.ui.base.BaseActivity;
import com.robillo.readrush.ui.splash.SplashActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity implements WebViewMvpView {

    String mStringHeader, mStringLoadUrl;

    @BindView(R.id.web_view)
    WebView mWebView;

    @BindView(R.id.header)
    TextView mHeader;

    public static Intent getStartIntent(Context context, String header, String loadUrl) {
        Intent i = new Intent(context, SplashActivity.class);
        i.putExtra("header", header);
        i.putExtra("load_url", loadUrl);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        ButterKnife.bind(this);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void setUp() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.setWebViewClient(new MyBrowser());

        mStringHeader = getIntent().getStringExtra("header");
        mStringLoadUrl = getIntent().getStringExtra("load_url");

        mHeader.setText(mStringHeader);
        mWebView.loadUrl(mStringLoadUrl);
    }
}
