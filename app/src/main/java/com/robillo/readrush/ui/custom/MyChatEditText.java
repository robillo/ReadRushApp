package com.robillo.readrush.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.robillo.readrush.R;

/**
 * Created by robinkamboj on 12/10/17.
 */

public class MyChatEditText extends LinearLayout {

    public MyChatEditText(Context context) {
        super(context);
        init(context, null);
    }

    public MyChatEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyChatEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyChatEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    void init(Context context, @Nullable AttributeSet set){
        inflate(context, R.layout.row_edittext_chat, this);

        if(set == null){
            return;
        }

        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.MyChatEditText);
        String chatString = ta.getString(R.styleable.MyChatEditText_hint_text);
        ((EditText) findViewById(R.id.edit_text)).setHint(chatString==null?context.getString(R.string.sample_email):chatString);
        postInvalidate();
        ta.recycle();
    }

    public void hintText(String hint) {
        ((EditText) findViewById(R.id.edit_text)).setHint(hint);
        postInvalidate();
    }
}
