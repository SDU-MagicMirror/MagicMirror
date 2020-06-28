package com.qilu.ec.main.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qilu.core.delegates.QiluDelegate;
import com.qilu.core.ec.R;
import com.qilu.ec.main.example.MyExampleRecyclerViewAdapter;

import java.util.List;

@SuppressLint("ValidFragment")
public class HistoryDelegate extends QiluDelegate {
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private List<String> strList;

    @SuppressLint("ValidFragment")
    public HistoryDelegate(List<String> strList) {
        this.strList = strList;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_decorate_history;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        RecyclerView recyclerView = rootView.findViewById(R.id.list);
        if (recyclerView != null) {
            Context context = rootView.getContext();
//            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new HistoryRecyclerViewAdapter(getContext(), strList));
        }
    }


}
