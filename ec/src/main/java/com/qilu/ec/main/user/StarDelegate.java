package com.qilu.ec.main.user;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.qilu.core.delegates.QiluDelegate;
import com.qilu.core.ec.R;
import com.qilu.ec.main.example.MyExampleRecyclerViewAdapter;
import com.qilu.ec.main.sample.ExampleItem;

import java.util.ArrayList;
import java.util.List;

public class StarDelegate extends QiluDelegate {
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    @Override
    public Object setLayout() {
        return R.layout.delegate_star;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        View view = rootView;
        RecyclerView recyclerView = view.findViewById(R.id.list);
        // TODO 获取存储的收藏记录,代替generateDatas()
        if (recyclerView != null) {
            Context context = view.getContext();
//            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new StarRecyclerViewAdapter(getActivity(), generateDatas()));
        }
    }


    //测试数据
    private List<ExampleItem> generateDatas() {
        Resources resources = getContext().getResources();
        Drawable user_example = resources.getDrawable(R.drawable.user_example);
        Drawable female = resources.getDrawable(R.drawable.female);

        List<ExampleItem> exampleItems = new ArrayList<ExampleItem>();
        for (int i = 0; i < 4; i++) {
            ExampleItem exampleItem = new ExampleItem(String.valueOf(i), "内容" + (i + 1), female, true);
            exampleItems.add(exampleItem);
        }
        return exampleItems;
    }
}
