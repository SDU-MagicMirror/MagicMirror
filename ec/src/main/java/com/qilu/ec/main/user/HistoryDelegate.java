package com.qilu.ec.main.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.qilu.core.delegates.QiluDelegate;
import com.qilu.core.ec.R;
import com.qilu.core.util.storage.ImageHistoryHelper;
import com.qilu.core.util.storage.UserCollectionHelper;
import com.qilu.ec.main.example.MyExampleRecyclerViewAdapter;
import com.qilu.ec.main.sample.DecorateHistory;
import com.qilu.ec.main.sample.ExampleItem;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class HistoryDelegate extends QiluDelegate {
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    RecyclerView recyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_decorate_history;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        recyclerView = rootView.findViewById(R.id.list);
        if (recyclerView != null) {
            Context context = rootView.getContext();
//            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            requestDatas();
        }
    }

    // TODO 小Bug：加载条不显示
    private void requestDatas() {
        ImageHistoryHelper imageHistoryHelper = new ImageHistoryHelper(getContext());
        SQLiteDatabase db = imageHistoryHelper.getWritableDatabase();
        ArrayList<DecorateHistory> decorateHistories = new ArrayList<DecorateHistory>();
        //查找
        Cursor cursor = db.query(ImageHistoryHelper.TABLE_NAME, null, null, null, null, null, null);
        Log.i("本地数据库条数", String.valueOf(cursor.getCount()));
        if (cursor.getCount() > 0) {
            //生成加入adapter中的数据
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                Log.i("加入", String.valueOf(i));
                decorateHistories.add(
                        new DecorateHistory(
                                cursor.getString(cursor.getColumnIndex(ImageHistoryHelper.TIME)),
                                cursor.getString(cursor.getColumnIndex(ImageHistoryHelper.IMAGE))
                        ));
                cursor.moveToNext();
            }
            Log.i("size大小", String.valueOf(decorateHistories.size()));
            recyclerView.setAdapter(new HistoryRecyclerViewAdapter(getContext(), decorateHistories));
        }
    }
}
