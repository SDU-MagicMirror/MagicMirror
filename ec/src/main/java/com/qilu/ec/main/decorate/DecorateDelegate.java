package com.qilu.ec.main.decorate;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.qilu.core.delegates.bottom.BottomItemDelegate;
import com.qilu.core.ec.R;
import com.qilu.core.net.RestClient;
import com.qilu.core.net.callback.IError;
import com.qilu.core.net.callback.IFailure;
import com.qilu.core.net.callback.ISuccess;
import com.qilu.core.util.callback.CallbackManager;
import com.qilu.core.util.callback.CallbackType;
import com.qilu.core.util.callback.IGlobalCallback;
import com.qilu.core.util.storage.QiluPreference;
import com.qilu.ec.sign.ISignListener;
import com.qilu.ui.image.GlideTools;
import hearsilent.discreteslider.DiscreteSlider;

@SuppressLint("ValidFragment")
public class DecorateDelegate extends BottomItemDelegate implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private Context context;
//    private int position;

//    private final int POSITION_ONE = 10001;
//    private final int POSITION_TWO = 10002;


    private IconTextView button_1;
    private IconTextView button_2;
    private ImageView img;
    private ImageView img_2;
    private DiscreteSlider value;
    private RadioGroup radio_group;
    private RadioButton radioButton;
    private RadioButton radioButton_2;
    private int radio_1;
    private int radio_2;
    private CheckBox check_1;
    private CheckBox check_2;
    private CheckBox check_3;
    private boolean checkAll;
    private Button submit;

    //结果区域
    private LinearLayoutCompat resultLayout;
    private IconTextView button_result;
    private ImageView img_result;

    //保存待上传图片路径的字符串
    private String img_1_path;
    private String img_2_path;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }


    @SuppressLint("ValidFragment")
    public DecorateDelegate(Context context) {
        this.context = context;
    }

    @Override
    public Object setLayout() {
        return R.layout.decorate;
    }

    @SuppressLint("HandlerLeak")
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        View view = rootView;
        button_1 = view.findViewById(R.id.button_1);
        button_2 = view.findViewById(R.id.button_2);
        img = view.findViewById(R.id.img);
        img_2 = view.findViewById(R.id.img_2);
        value = view.findViewById(R.id.value);
        radio_group = view.findViewById(R.id.radio_group);
        radio_1 = R.id.radio_1;
        radio_2 = R.id.radio_2;
        radioButton = view.findViewById(radio_1);
        radioButton_2 = view.findViewById(radio_2);
        radioButton.setChecked(true);
        check_1 = view.findViewById(R.id.check_1);
        check_2 = view.findViewById(R.id.check_2);
        check_3 = view.findViewById(R.id.check_3);
        checkAll = true;
        checkAll();
        submit = view.findViewById(R.id.submit);

        resultLayout = view.findViewById(R.id.resultLayout);
        button_result = view.findViewById(R.id.button_result);
        img_result = view.findViewById(R.id.img_result);
        resultLayout.setVisibility(View.INVISIBLE);

        listenerRegister();
    }

    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Log.i("isChecked", String.valueOf(isChecked));
            if (checkAll) {
                buttonView.setChecked(true);
            } else {
                if (!isChecked) {
                    //false时，只有当为最后一个选中时不可执行
                    if (buttonView.getId()==check_1.getId()){
                        if (!check_2.isChecked()&&!check_3.isChecked())
                            buttonView.setChecked(true);
                    }else if (buttonView.getId()==check_2.getId()){
                        if (!check_1.isChecked()&&!check_3.isChecked())
                            buttonView.setChecked(true);
                    }else if (buttonView.getId()==check_3.getId()){
                        if (!check_1.isChecked()&&!check_2.isChecked())
                            buttonView.setChecked(true);
                    }
                } else {
                    buttonView.setChecked(true);
                }
            }
        }
    };

    private void listenerRegister() {
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        submit.setOnClickListener(this);
        radio_group.setOnCheckedChangeListener(this);
        check_1.setOnCheckedChangeListener(onCheckedChangeListener);
        check_2.setOnCheckedChangeListener(onCheckedChangeListener);
        check_3.setOnCheckedChangeListener(onCheckedChangeListener);
        submit.setOnClickListener(this);
        button_result.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.button_1) {
//            position = POSITION_ONE;
            // 设置拍照后的动作
            DecorateDelegate temp = this;
            CallbackManager.getInstance().addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                @Override
                public void executeCallback(@Nullable Uri args) {
                    // args是照片保存在硬盘上的地址
                    if (args != null) {
                        img_1_path = args.getPath();
                        GlideTools.showImagewithGlide(temp, img_1_path, (ImageView) $(R.id.img));
                    }
                }
            });
            startCameraWithCheck();

        } else if (id == R.id.button_2) {
//            position = POSITION_TWO;

            // 设置拍照后的动作
            DecorateDelegate temp = this;
            CallbackManager.getInstance().addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                @Override
                public void executeCallback(@Nullable Uri args) {
                    // args是照片保存在硬盘上的地址
                    if (args != null) {
                        img_2_path = args.getPath();
                        GlideTools.showImagewithGlide(temp, img_2_path, (ImageView) $(R.id.img_2));

                    }
                }
            });
            startCameraWithCheck();

        } else if (id == R.id.submit) {
            submitImage();
        } else if (id == R.id.button_result) {

        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == radio_1) {
            checkAll = true;
            checkAll();
        } else if (checkedId == radio_2) {
            checkAll = false;
            setCheckDefault();
        }
    }

    private void checkAll() {
        check_1.setChecked(true);
        check_2.setChecked(true);
        check_3.setChecked(true);
    }

    private void setCheckDefault() {
        check_3.setChecked(false);
        check_2.setChecked(false);
        check_1.setChecked(true);
    }

    private void submitImage() {
        sendRequest(img_1_path, img_2_path, value.getProgress(), getType());
    }

    /**
     * 结果图显示到屏幕上
     */
    private void showResultImage(@Nullable String image) {
        if(image!=null&&(!image.isEmpty())){
            byte[] bytes = Base64.decode(image, Base64.DEFAULT);
            Bitmap bitmap =  BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            img_result.setImageBitmap(bitmap);

            //自动缩放
            img_result.setScaleType(ImageView.ScaleType.FIT_XY);
            img_result.setAdjustViewBounds(true);
            resultLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 发送网络请求端,返回类型不确定，可能是图片相关类型
     */
    private void sendRequest(String image1Path, String image2Path, int levelDegree, String[] paramsValues) {
        // levelDegree: [0, 99]
        levelDegree+=1;
        float shade = ((float)levelDegree)/100.0f;
        String[] paramsNames = {"local_flag", "eye_flag", "lip_flag", "face_flag"};

        RestClient.builder()
                .url("/generate_makeup")
                .file("example_image", image1Path)
                .file("user_image", image2Path)
                .params("shade_alpha",String.valueOf(shade))
                .params(paramsNames[0],paramsValues[0])
                .params(paramsNames[1], paramsValues[1])
                .params(paramsNames[2], paramsValues[2])
                .params(paramsNames[3], paramsValues[3])
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        showResultImage(response);
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
                        Toast.makeText(getContext(),"请求出错，Msg："+msg+"\n"+"Code："+code,Toast.LENGTH_LONG).show();
                    }
                })
                .loader(getContext())
                .build()
                .postWithFiles();
    }

    private String[] getType() {
        String[] paramsValues = {"0","0","0","0"};
        if (radioButton.isChecked())
            return paramsValues;
        else if (radioButton_2.isChecked())
        {
            paramsValues[0] = "1";
            if (check_1.isChecked()){
                paramsValues[1] = "1";
            }
            if (check_2.isChecked())
                paramsValues[2] = "1";
            if (check_3.isChecked())
                paramsValues[3] = "1";
            for (String paramsValue : paramsValues) {
                if (!paramsValue.equals("1")) {
                    return paramsValues;
                }
            }
            paramsValues[0] = "0";
        }
        return paramsValues;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
