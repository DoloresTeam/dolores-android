package com.dolores.store.ui.main;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dolores.store.R;
import com.dolores.store.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.ding_layout)
    RelativeLayout dingLayout;
    @Bind(R.id.book_layout)
    RelativeLayout bookLayout;
    @Bind(R.id.mine_layout)
    RelativeLayout mineLayout;
    private DingFragment dingFragment;
    private BookFragment bookFragment;
    private MineFragment mineFragment;
    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        switchFragment(0);
    }

    private void switchFragment(int which) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (dingFragment != null) {
            ft.hide(dingFragment);
        }
        if (bookFragment != null) {
            ft.hide(bookFragment);
        }
        if (mineFragment != null) {
            ft.hide(mineFragment);
        }
        switch (which) {
            case 0://消息
                dingLayout.setSelected(true);
                bookLayout.setSelected(false);
                mineLayout.setSelected(false);
                if (dingFragment == null) {
                    dingFragment = new DingFragment();
                    ft.add(R.id.fragment_container, dingFragment);
                } else {
                    ft.show(dingFragment);
                }
                break;
            case 1://联系人
                dingLayout.setSelected(false);
                bookLayout.setSelected(true);
                mineLayout.setSelected(false);
                if (bookFragment == null) {
                    bookFragment = new BookFragment();
                    ft.add(R.id.fragment_container, bookFragment);
                } else {
                    ft.show(bookFragment);
                }
                break;
            case 2://我的
                dingLayout.setSelected(false);
                bookLayout.setSelected(false);
                mineLayout.setSelected(true);
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    ft.add(R.id.fragment_container, mineFragment);
                } else {
                    ft.show(mineFragment);
                }
                break;
        }
        ft.commitAllowingStateLoss();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, R.string.msg_quit, Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    @OnClick({R.id.ding_layout, R.id.book_layout, R.id.mine_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ding_layout:
                switchFragment(0);
                break;
            case R.id.book_layout:
                switchFragment(1);
                break;
            case R.id.mine_layout:
                switchFragment(2);
                break;
        }
    }


}