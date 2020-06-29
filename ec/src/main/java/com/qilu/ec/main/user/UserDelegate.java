package com.qilu.ec.main.user;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.qilu.core.delegates.bottom.BottomItemDelegate;
import com.qilu.core.ec.R;
import com.qilu.core.net.RestClient;
import com.qilu.core.net.callback.IError;
import com.qilu.core.net.callback.IFailure;
import com.qilu.core.net.callback.ISuccess;
import com.qilu.ec.main.option.OptionDelegate;
import com.google.gson.Gson;
import com.qilu.ec.main.sample.user_profile.UserProfile;
import com.qilu.ec.main.sample.user_profile.UserProfile_Data_Data;
import com.qilu.ec.main.util.Image;
import com.qilu.ec.sign.ISignListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

@SuppressLint("ValidFragment")
public class UserDelegate extends BottomItemDelegate implements View.OnClickListener {
    private Context context;
    private IconTextView optionImage;
    private UserProfile_Data_Data data;

    private CircleImageView user_img;
    private TextView text_name;

    private RelativeLayout star;
    private RelativeLayout history;
    private RelativeLayout upload;
    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @SuppressLint("ValidFragment")
    public UserDelegate(Context context) {
        this.context = context;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_me;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        optionImage = rootView.findViewById(R.id.option);
        optionImage.setOnClickListener(this);
        user_img = rootView.findViewById(R.id.user_img);
        text_name = rootView.findViewById(R.id.text_name);
        star = rootView.findViewById(R.id.star);
        history = rootView.findViewById(R.id.history);
        upload = rootView.findViewById(R.id.upload);
        // TODO 点击star跳转到示例收藏
        star.setOnClickListener(this);
        // TODO 点击history跳转到美妆历史
        history.setOnClickListener(this);
        // TODO 点击upload跳转到上传示例
        upload.setOnClickListener(this);
        RestClient.builder()
                .url("/account")
                .loader(getContext())
                .success(response -> {
                    Gson gson = new Gson();
                    UserProfile userProfile = gson.fromJson(response, UserProfile.class);
                    if (userProfile.getCode() == 401) {
                        mISignListener.onTokenExpired();
                    } else {
                        data = userProfile.getData().getData();
                        if (data != null) {
                            Image.showResultImage(data.getAvatar(), user_img);
                            text_name.setText(data.getUserName());
                        } else {
                            Log.e("data", "用户信息为空！");
                            Toast.makeText(getContext(), "用户信息获取失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .failure(() -> Toast.makeText(getContext(), "请求失败", Toast.LENGTH_LONG).show())
                .error((code, msg) -> {
                    if (code == 401) {
                        //Token失效
                        mISignListener.onTokenExpired();
                    }
                    Toast.makeText(getContext(), "请求失败，" + code + "，" + msg, Toast.LENGTH_LONG).show();
                })
                .build()
                .getNoParamsWithToken();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.option) {
            changeToOption();
        } else if (v.getId() == R.id.star) {
            //跳转到示例收藏
            getParentDelegate().getSupportDelegate().start(new StarDelegate());
        } else if (v.getId() == R.id.history) {
            //跳转到美妆历史
            getParentDelegate().getSupportDelegate().start(new HistoryDelegate(getImageHistoryList()));
        } else if (v.getId() == R.id.upload) {
            //跳转到上传示例
            getParentDelegate().getSupportDelegate().start(new ExampleCreateDelegate());
        }
    }

    private void changeToOption() {
        getParentDelegate().getSupportDelegate().start(new OptionDelegate(context, data, this));
    }

    private List<String> getImageHistoryList() {
        // TODO 从本地获取图片和时间列表，传入adapter中
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        RestClient.builder()
                .url("/account")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                        Gson gson = new Gson();
                        UserProfile userProfile = gson.fromJson(response, UserProfile.class);
                        if (userProfile.getCode() == 401) {
                            mISignListener.onTokenExpired();
                        } else {
                            data = userProfile.getData().getData();
                            if (data != null) {
                                Image.showResultImage(data.getAvatar(), user_img);
                                text_name.setText(data.getUserName());
                            } else {
                                Log.e("data", "用户信息为空！");
                                Toast.makeText(getContext(), "用户信息获取失败！", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                })
                .failure(() -> Toast.makeText(getContext(), "请求失败", Toast.LENGTH_LONG).show())
                .error((code, msg) -> {
                    if (code == 401) {
                        //Token失效
                        mISignListener.onTokenExpired();
                    } else {
                        Toast.makeText(getContext(), "请求失败，" + code + "，" + msg, Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .getNoParamsWithToken();
    }
}
