package com.qilu.ec.main.example;

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

import com.qilu.core.delegates.bottom.BottomItemDelegate;
import com.qilu.core.ec.R;
import com.qilu.ec.main.sample.ExampleItem;

import java.util.ArrayList;
import java.util.List;

public class ExampleDelegate extends BottomItemDelegate {
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    @Override
    public Object setLayout() {
//        return R.layout.delegate_example;
        return R.layout.fragment_example_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        View view = rootView;
        RecyclerView recyclerView = view.findViewById(R.id.list);
        if (recyclerView instanceof RecyclerView) {
            Context context = view.getContext();
//            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyExampleRecyclerViewAdapter(getActivity(), generateDatas()));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //测试数据
    private List<ExampleItem> generateDatas() {
        Resources resources = getContext().getResources();
        Drawable user_example = resources.getDrawable(R.drawable.user_example);
        Drawable female = resources.getDrawable(R.drawable.female);

        List<ExampleItem> exampleItems = new ArrayList<ExampleItem>();
        for (int i = 0; i < 4; i++) {
            ExampleItem exampleItem = new ExampleItem(String.valueOf(i),"名字" + (i + 1), "头衔" + (i + 1), female);
            exampleItems.add(exampleItem);
        }
        return exampleItems;
    }
}
