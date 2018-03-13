package com.example.nhattruong.financialmanager.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.nhattruong.financialmanager.R;

public class DialogOk extends Dialog {

    private String mTitle, mMessage;
    private IOkDialogListener mListener;

    public DialogOk(Context context, String title, String message, IOkDialogListener listener) {
        super(context, R.style.FullscreenDialog);
        mTitle = title;
        mMessage = message;
        mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Window window = getWindow();

        assert window != null;
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(
                    ContextCompat.getColor(
                            getContext(),
                            R.color.status_bar_edit_text_dialog)
            );
        }

        setContentView(R.layout.dialog_ok);

        TextView mTvTitle = (TextView) findViewById(R.id.dialog_title);
        TextView mTvMessage = (TextView) findViewById(R.id.dialog_message);
        Button mBtnOk = (Button) findViewById(R.id.dialog_btn_ok);

        mTvTitle.setText(mTitle);
        mTvMessage.setText(mMessage);
        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onIOkDialogAnswerOk(DialogOk.this);
                } else {
                    dismiss();
                }
            }
        });
    }

    public interface IOkDialogListener {
        void onIOkDialogAnswerOk(DialogOk dialog);
    }

}
