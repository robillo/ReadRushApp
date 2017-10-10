package com.robillo.readrush.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robillo.readrush.R;

/**
 * Created by robinkamboj on 27/09/17.
 */

public class MyChatView extends LinearLayout {

    public MyChatView(Context context) {
        super(context);
        init(context, null);
    }

    public MyChatView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyChatView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyChatView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    void init(Context context, @Nullable AttributeSet set){
        inflate(context, R.layout.row_chat, this);

        if(set == null){
            return;
        }

        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.MyChatView);
        String chatString = ta.getString(R.styleable.MyChatView_chat_text);
        ((TextView) findViewById(R.id.text)).setText(chatString==null?context.getString(R.string.dummy_text):chatString);
        postInvalidate();
        ta.recycle();
    }
}
