package com.dolores.store.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dolores.store.Constants;
import com.dolores.store.DoloresApplication;
import com.dolores.store.R;
import com.dolores.store.http.StringRequest;
import com.dolores.store.ui.base.BaseActivity;
import com.dolores.store.util.HttpUtil;
import com.dolores.store.util.TitleUtils;
import com.dolores.store.util.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.et_mobile)
    EditText etMobile;
    @Bind(R.id.et_password)
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        TitleUtils.hideBack(this);
        TitleUtils.setTitle(this, R.string.action_login);
    }

    @OnClick({R.id.btn_ok, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                postLogin("admin", "123456");
                break;
            case R.id.tv_register:
                break;
        }
    }

    private void postLogin(String username, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        StringRequest request = new StringRequest(Constants.LOGIN_URL, Request.Method.POST, map, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.showToast(LoginActivity.this, HttpUtil.checkErrorType(error));
            }
        });
        DoloresApplication.httpClient.addRequest(request);
    }
}