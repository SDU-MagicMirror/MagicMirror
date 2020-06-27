package com.qilu.ec.main.option;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qilu.core.delegates.QiluDelegate;
import com.qilu.core.delegates.bottom.BottomItemDelegate;
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
            Toast.makeText(context, "name", Toast.LENGTH_SHORT).show();
        }
    }
}
