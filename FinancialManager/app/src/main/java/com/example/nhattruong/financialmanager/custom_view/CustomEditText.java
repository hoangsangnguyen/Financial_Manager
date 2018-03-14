package com.example.nhattruong.financialmanager.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.nhattruong.financialmanager.R;

@SuppressLint("AppCompatCustomView")
public class CustomEditText extends EditText {
    private boolean isShowClear = true;

    public CustomEditText(Context context) {
        super(context);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        final Drawable x = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_text);
        x.setBounds(0, 0, x.getIntrinsicWidth(), x.getIntrinsicHeight());

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if ((event.getX() > (getWidth() - getPaddingRight() - getPaddingLeft() - x.getIntrinsicWidth())) && hasFocus()) {
                    setText("");

                    if (listenerClear != null) {
                        listenerClear.onClearText(CustomEditText.this);
                        return true;
                    }
                }
                return false;
            }
        });

        addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                if (hasFocus() && getText().length() > 0) {
                    setCompoundDrawables(null, null, x, null);
                } else {
                    setCompoundDrawables(null, null, null, null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });
    }

    @Override
    public void setCompoundDrawables(@Nullable Drawable left, @Nullable Drawable top, @Nullable Drawable right, @Nullable Drawable bottom) {
        if (isShowClear)
            super.setCompoundDrawables(left, top, right, bottom);
    }

    public void setShowClear(boolean showClear) {
        isShowClear = showClear;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {

            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.showSoftInput(this, 0);


            if (getText().length() > 0) {
                final Drawable x = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_text);
                x.setBounds(0, 0, x.getIntrinsicWidth(), x.getIntrinsicHeight());
                setCompoundDrawables(null, null, x, null);
            }

        } else {
            setCompoundDrawables(null, null, null, null);
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(this.getWindowToken(), 0);

            if (listener != null) {
                listener.onDismissKeyBoard(this);
            }

        }
    }

    @Override
    public boolean onKeyPreIme(int keyCode, @NonNull KeyEvent event) {
        if (isFocusable()) {
            if ((keyCode == KeyEvent.KEYCODE_BACK
                    || keyCode == KeyEvent.ACTION_DOWN
                    || keyCode == KeyEvent.KEYCODE_ENTER)
                    && event.getAction() == KeyEvent.ACTION_UP) {
                clearFocus();
                return true;
            }
        }
        return super.onKeyPreIme(keyCode, event);
    }

    @Override
    public void onEditorAction(int actionId) {
        super.onEditorAction(actionId);
        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_NEXT) {
            clearFocus();
        }
    }

    KeyboardListener listener;
    ClearTextListener listenerClear;

    public void setOnKeyboardListener(KeyboardListener listener) {
        this.listener = listener;
    }

    public void setOnClearTextListener(ClearTextListener listener) {
        this.listenerClear = listener;
    }

    public interface KeyboardListener {
        void onDismissKeyBoard(CustomEditText keyboardEditText);
    }

    public interface ClearTextListener {
        void onClearText(CustomEditText keyboardEditText);
    }

}
