package com.qilu.ec.main;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.qilu.core.delegates.bottom.BaseBottomDelegate;
import com.qilu.core.delegates.bottom.BottomItemDelegate;
import com.qilu.core.delegates.bottom.BottomTabBean;
import com.qilu.core.delegates.bottom.ItemBuilder;
import com.qilu.ec.main.home.HomeDelegate;
import com.qilu.ec.main.example.ExampleDelegate;
import com.qilu.ec.main.decorate.DecorateDelegate;
import com.qilu.ec.main.user.UserDelegate;
import com.qilu.ec.sign.ISignListener;

import java.util.LinkedHashMap;

public class EcBottomDelegate extends BaseBottomDelegate {

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-flag-o}", "示例榜"),new ExampleDelegate());
        items.put(new BottomTabBean("{fa-magic}", "上妆"), new DecorateDelegate(getProxyActivity()));
        items.put(new BottomTabBean("{fa-user}","我"),new UserDelegate(getProxyActivity()));
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#1E90FF");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
