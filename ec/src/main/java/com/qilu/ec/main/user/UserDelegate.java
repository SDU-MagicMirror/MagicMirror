package com.qilu.ec.main.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.qilu.core.app.Qilu;
import com.qilu.core.delegates.bottom.BottomItemDelegate;
import com.qilu.core.ec.R;
import com.qilu.core.net.RestClient;
import com.qilu.core.net.callback.IError;
import com.qilu.core.net.callback.IFailure;
import com.qilu.core.net.callback.ISuccess;
import com.qilu.core.util.storage.QiluPreference;
import com.qilu.ec.main.option.OptionDelegate;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class UserDelegate extends BottomItemDelegate implements View.OnClickListener {
    private Context context;
    private IconTextView optionImage;

    @SuppressLint("ValidFragment")
    public UserDelegate(Context context) {
        this.context = context;
    }

    @Override
    public Object setLayout() {
//        return R.layout.delegate_user;
        return R.layout.me;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        optionImage = rootView.findViewById(R.id.option);
        optionImage.setOnClickListener(this);
        RestClient.builder()
                .url("/account")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(),"请求失败",Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(getContext(),"请求失败，"+code+"，"+msg,Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .getNoParamsWithToken();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.option) {
            changeToOption();
        }
    }

    private void changeToOption() {
        getParentDelegate().getSupportDelegate().start(new OptionDelegate(context));
    }
}
