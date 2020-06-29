package com.qilu.ec.main.option;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qilu.core.delegates.QiluDelegate;
import com.qilu.core.ec.R;
import com.qilu.ec.main.sample.user_profile.UserProfile_Data_Data;

@SuppressLint("ValidFragment")
public class OptionDelegate extends QiluDelegate implements View.OnClickListener {
    private Context context;
    private UserProfile_Data_Data data; //用户当前信息

    private TextView name;
    private TextView passWord;
    private TextView user_img;
    private TextView honor;
    private TextView about;

    @SuppressLint("ValidFragment")
    public OptionDelegate(Context context, UserProfile_Data_Data data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public Object setLayout() {
        return R.layout.option_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        name = rootView.findViewById(R.id.name);
        passWord = rootView.findViewById(R.id.passWord);
        user_img = rootView.findViewById(R.id.user_img);
        honor = rootView.findViewById(R.id.edit_old_password);
        about = rootView.findViewById(R.id.about);
        listenerRegister();
    }

    private void listenerRegister() {
        name.setOnClickListener(this);
        passWord.setOnClickListener(this);
        user_img.setOnClickListener(this);
        honor.setOnClickListener(this);
        about.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.name) {
            showNameDialog();
        } else if (v.getId() == R.id.passWord) {
            showPasswordDialog();
        } else if (v.getId() == R.id.user_img) {
            // TODO 更改头像
            changeImg();
        } else if (v.getId() == R.id.edit_old_password) {
            showHonorDialog();
        } else if (v.getId() == R.id.about) {
            // TODO 关于（暂无页面）
            showAbout();
        }
    }

    private void showAbout() {

    }

    private void showHonorDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.honor_dialog, null, false);
        dialog.setView(view);
        EditText honor = view.findViewById(R.id.honor);
        if (data != null && data.getSignature() != null && !data.getSignature().trim().equals(""))
            honor.setText(data.getSignature());
        dialog.setTitle("更改头衔");
        dialog.setCancelable(true);
        dialog.setPositiveButton("确定", (dialog1, which) -> {
            String newHonor = String.valueOf(honor.getText());
            // TODO 提交修改头衔

            dialog1.dismiss();
        });
        dialog.setNegativeButton("取消", (dialog12, which) -> {
            //关闭对话框
            dialog12.dismiss();
        });
        dialog.show();
    }

    private void changeImg() {
        // TODO 更改头像操作
    }

    private void showPasswordDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.password_dialog, null, false);
        dialog.setView(view);
        EditText edit_old_password = view.findViewById(R.id.edit_old_password);
        EditText edit_new_password = view.findViewById(R.id.edit_new_password);
        EditText edit_sure_password = view.findViewById(R.id.edit_sure_password);
        dialog.setTitle("修改密码");
        dialog.setCancelable(true);
        dialog.setPositiveButton("确定", (dialog1, which) -> {
            String oldPassword = String.valueOf(edit_old_password.getText());
            String newPassword = String.valueOf(edit_new_password.getText());
            String surePassword = String.valueOf(edit_sure_password.getText());
            // TODO 提交修改密码

            dialog1.dismiss();
        });
        dialog.setNegativeButton("取消", (dialog12, which) -> {
            //关闭对话框
            dialog12.dismiss();
        });
        dialog.show();
    }


    private void showNameDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.name_dialog, null, false);
        dialog.setView(view);
        EditText name = view.findViewById(R.id.edit_old_password);
        //对话框标题设置
        dialog.setTitle("修改昵称");
        //对话框设置可以用Back键退出
        dialog.setCancelable(true);
        // dialog.clone()
                /*
                三种Button
                Positive Button  正面按键
                Negative Button  负面按键
                Neutral Button   中性按键
                 */
        dialog.setPositiveButton("确定", (dialog1, which) -> {
            //关闭对话框
            String newName = String.valueOf(name.getText());
            // TODO 提交修改昵称

            dialog1.dismiss();
        });
        dialog.setNegativeButton("取消", (dialog12, which) -> {
            //关闭对话框
            dialog12.dismiss();
        });
        dialog.show();
    }
}
