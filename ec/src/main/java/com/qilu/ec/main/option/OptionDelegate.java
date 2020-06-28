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

@SuppressLint("ValidFragment")
public class OptionDelegate extends QiluDelegate implements View.OnClickListener {
    private Context context;

    private TextView name;
    private TextView passWord;
    private TextView honor;
    private TextView about;

    @SuppressLint("ValidFragment")
    public OptionDelegate(Context context) {
        this.context = context;
    }

    @Override
    public Object setLayout() {
        return R.layout.option_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        name = rootView.findViewById(R.id.name);
        passWord = rootView.findViewById(R.id.passWord);
        honor = rootView.findViewById(R.id.honor);
        about = rootView.findViewById(R.id.about);
        listenerRegister();
    }

    private void listenerRegister() {
        name.setOnClickListener(this);
        passWord.setOnClickListener(this);
        honor.setOnClickListener(this);
        about.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.name) {
            showNameDialog();
        }
    }


    private void showNameDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.name_dialog, null, false);
        dialog.setView(view);
        EditText name = view.findViewById(R.id.editTextName);
        //对话框标题设置
        dialog.setTitle("修改名称");
        //对话框设置不可以用Back键退出
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
            dialog1.dismiss();
        });
        dialog.setNegativeButton("取消", (dialog12, which) -> {
            //关闭对话框
            dialog12.dismiss();
        });
        dialog.show();
    }
}
