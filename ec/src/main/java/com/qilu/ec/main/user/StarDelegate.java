package com.qilu.ec.main.user;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.qilu.core.delegates.QiluDelegate;
import com.qilu.core.ec.R;
import com.qilu.core.util.storage.UserCollectionHelper;
import com.qilu.ec.main.example.MyExampleRecyclerViewAdapter;
import com.qilu.ec.main.sample.ExampleItem;
import com.qilu.ec.main.util.Image;

import java.util.ArrayList;
import java.util.List;

public class StarDelegate extends QiluDelegate {
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    RecyclerView recyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_star;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        View view = rootView;
        recyclerView = view.findViewById(R.id.list);
        if (recyclerView != null) {
            Context context = view.getContext();
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
        UserCollectionHelper userCollectionHelper = new UserCollectionHelper(getContext());
        SQLiteDatabase db = userCollectionHelper.getWritableDatabase();
        ArrayList<ExampleItem> exampleItems = new ArrayList<ExampleItem>();
        //查找
        Cursor cursor = db.query(UserCollectionHelper.TABLE_NAME, null, null, null, null, null, null);
        Log.i("本地数据库条数", String.valueOf(cursor.getCount()));
        if (cursor.getCount() > 0) {
            //生成加入adapter中的数据
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                Log.i("加入", String.valueOf(i));
                exampleItems.add(
                        new ExampleItem(
                                cursor.getString(cursor.getColumnIndex(UserCollectionHelper.ID)),
                                cursor.getString(cursor.getColumnIndex(UserCollectionHelper.CONTENT)),
                                cursor.getString(cursor.getColumnIndex(UserCollectionHelper.IMAGE)),
                                true    //收藏的一定都是true
                        )); //取出记录中的id
                cursor.moveToNext();
            }
            Log.i("size大小", String.valueOf(exampleItems.size()));
            recyclerView.setAdapter(new StarRecyclerViewAdapter(this, /*generateDatas()*/exampleItems));
        }
    }
}
