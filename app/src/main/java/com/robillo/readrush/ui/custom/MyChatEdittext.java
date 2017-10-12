package com.robillo.readrush.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robillo.readrush.R;

/**
 * Created by robinkamboj on 12/10/17.
 */

public class MyChatEdittext extends LinearLayout {

    public MyChatEdittext(Context context) {
        super(context);
        init(context, null);
    }

    public MyChatEdittext(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyChatEdittext(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyChatEdittext(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    void init(Context context, @Nullable AttributeSet set){
        inflate(context, R.layout.row_edittext_chat, this);

        if(set == null){
            return;
        }

        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.MyChatEdittext);
        String chatString = ta.getString(R.styleable.MyChatEdittext_hint_text);
        ((EditText) findViewById(R.id.edit_text)).setHint(chatString==null?context.getString(R.string.sample_email):chatString);
        postInvalidate();
        ta.recycle();
    }
}
