package com.dolores.store.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.dolores.store.R;
import com.dolores.store.util.DpPxUtil;

public class DefineToast {
    private Context mContext;
    private WindowManager wdm;
    private double second;
    private View mView;
    private WindowManager.LayoutParams params;
    private Handler handler;

    public DefineToast(Context context) {
        this(context, null, 1);
    }

    public DefineToast(Context context, String toastContent, double time) {
        mContext = context;
        wdm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        params = new WindowManager.LayoutParams();
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.format = PixelFormat.TRANSLUCENT;
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        params.y = DpPxUtil.dip2px(context, 125);
        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;

        if (!TextUtils.isEmpty(toastContent)) {
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 1000) {
                        if (mView != null) {
                            if (mView.getParent() != null) {
                                wdm.removeView(mView);
                            }
                            mView = null;
                            mContext = null;
                        }
                    }
                }
            };
            mView = LayoutInflater.from(context).inflate(R.layout.layout_define_toast, null);
            ((TextView) mView).setText(toastContent);
        }
        this.second = time;
    }

    public void setDefineView(View view) {
        this.mView = view;
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1000) {
                    if (mView != null) {
                        if (mView.getParent() != null) {
                            wdm.removeView(mView);
                        }
                        mView = null;
                        mContext = null;
                    }
                }
            }
        };
    }

    public void setTime(double second) {
        this.second = second;
    }

    public void setLayoutParams(WindowManager.LayoutParams layoutParams) {
        this.params = layoutParams;
    }

    public void show() {
        if (mContext instanceof Activity && !((Activity) mContext).isFinishing()) {
            if (mView.getParent() != null) {
                wdm.removeView(mView);
            }
            wdm.addView(mView, params);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(1000);
                }
            }, (long) (second * 1000));
        }
    }

    public void cancel() {
        wdm.removeView(mView);
        handler.removeMessages(1000);
    }
}
