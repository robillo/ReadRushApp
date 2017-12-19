package com.robillo.readrush.ui.custom;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robillo.readrush.R;
import com.robillo.readrush.utils.KeyboardUtils;

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

    void init(final Context context, @Nullable AttributeSet set){
        inflate(context, R.layout.row_edittext_chat, this);

        if(set == null){
            return;
        }

        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.MyChatEditText);
        String chatString = ta.getString(R.styleable.MyChatEditText_hint_text);
        ((EditText) findViewById(R.id.edit_text)).setHint(chatString==null?context.getString(R.string.sample_email):chatString);
        ((EditText) findViewById(R.id.edit_text)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    Log.i("MY CHAT EDITTEXT","Enter pressed");
                    KeyboardUtils.INSTANCE.hideSoftInput((Activity) context);
                }
                return false;
            }
        });
        postInvalidate();
        ta.recycle();
    }

    public void hintText(String hint) {
        ((EditText) findViewById(R.id.edit_text)).setHint(hint);
        postInvalidate();
    }

    public String getText() {
        return ((EditText) findViewById(R.id.edit_text)).getText().toString();
    }

    public void nullify() {
        ((EditText) findViewById(R.id.edit_text)).setText(null);
        postInvalidate();
    }
}
