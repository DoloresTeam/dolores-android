package com.dolores.store.util;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dolores.store.R;
import com.dolores.store.ui.base.BaseActivity;

public class TitleUtils {

    public static void setTitle(BaseActivity activity, int resource) {
        TextView tvTitle = (TextView) activity.findViewById(R.id.tv_title);
        if (tvTitle != null) {
            tvTitle.setText(resource);
        }
    }

    public static void setTitle(BaseActivity activity, String resource) {
        TextView tvTitle = (TextView) activity.findViewById(R.id.tv_title);
        if (tvTitle != null) {
            tvTitle.setText(resource);
        }
    }

    public static void hideBack(final BaseActivity activity) {
        ImageButton imgBackBtn = (ImageButton) activity.findViewById(R.id.btn_left);
        if (imgBackBtn != null) {
            imgBackBtn.setVisibility(View.GONE);
        }
    }

    public static void setBack(final BaseActivity activity) {
        ImageButton imgBackBtn = (ImageButton) activity.findViewById(R.id.btn_left);
        if (imgBackBtn != null) {
            imgBackBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    activity.onBackPressed();
                }
            });
        }
    }

    public static void setOkButton(final BaseActivity activity, int resource) {
        TextView tvOk = (TextView) activity.findViewById(R.id.tv_right);
        ImageButton btnOk = (ImageButton) activity.findViewById(R.id.btn_right);
        if (btnOk != null) {
            tvOk.setVisibility(View.GONE);
            if (resource != 0) {
                btnOk.setImageDrawable(activity.getResources().getDrawable(resource));
            }
            btnOk.setVisibility(View.VISIBLE);
            btnOk.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    activity.onOkButtonClick();
                }
            });
        }
    }

    public static void setOkText(final BaseActivity activity, int resource) {
        TextView tvOk = (TextView) activity.findViewById(R.id.tv_right);
        ImageButton btnOk = (ImageButton) activity.findViewById(R.id.btn_right);
        if (tvOk != null) {
            btnOk.setVisibility(View.GONE);
            if (resource != 0) {
                tvOk.setText(resource);
            }
            tvOk.setVisibility(View.VISIBLE);
            tvOk.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    activity.onOkButtonClick();
                }
            });
        }
    }

    public static void setOkText(final BaseActivity activity, String resource) {
        TextView tvOk = (TextView) activity.findViewById(R.id.tv_right);
        if (tvOk != null) {
            if (!TextUtils.isEmpty(resource)) {
                tvOk.setText(resource);
            }
            tvOk.setVisibility(View.VISIBLE);
            tvOk.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    activity.onOkButtonClick();
                }
            });
        }
    }
}
