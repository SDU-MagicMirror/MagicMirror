package com.qilu.ec.main.user;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.qilu.core.delegates.QiluDelegate;
import com.qilu.core.ec.R;
import com.qilu.core.util.callback.CallbackManager;
import com.qilu.core.util.callback.CallbackType;
import com.qilu.core.util.callback.IGlobalCallback;
import com.qilu.ui.image.GlideTools;

public class NewsCreateDelegate extends QiluDelegate implements View.OnClickListener {
    private IconTextView button_1;  //添加图片按钮
    private Button button;          //发表按钮
    private EditText editText;      //文字输入框
    private ImageView imageView;    //图片区域

    private String text;//文本内容
    private String img_1_path;//图片路径

    @Override
    public Object setLayout() {
        return R.layout.news_create;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        button_1 = rootView.findViewById(R.id.button_1);
        button_1.setOnClickListener(this);
        button = rootView.findViewById(R.id.button);
        button.setOnClickListener(this);
        editText = rootView.findViewById(R.id.text);
        imageView = rootView.findViewById(R.id.only_img);
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_1) {
            //拍照
            NewsCreateDelegate temp = this;
            CallbackManager.getInstance().addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                @Override
                public void executeCallback(@Nullable Uri args) {
                    // args是照片保存在硬盘上的地址
                    if (args != null) {
                        img_1_path = args.getPath();
                        GlideTools.showImagewithGlide(temp, img_1_path, (ImageView) $(R.id.only_img));
                    }
                }
            });
            startCameraWithCheck();
        } else if (v.getId() == R.id.button) {
            text = String.valueOf(editText.getText());
            if (img_1_path != null && !img_1_path.equals("")) { //图片路径不为空
                //TODO 图片处理
            }
            Toast.makeText(getContext(), "假装发表", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.only_img) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(null);
            builder.setMessage("确定移除？");
            builder.setPositiveButton("是", (dialog, which) -> {
                img_1_path = null;
                //GlideTools.showImagewithGlide(getContext(), null, (ImageView) $(R.id.only_img));
                imageView.setImageDrawable(null);
            });
            builder.setNegativeButton("否", (dialog, which) -> {
            });
            builder.show();
        }
    }
}
