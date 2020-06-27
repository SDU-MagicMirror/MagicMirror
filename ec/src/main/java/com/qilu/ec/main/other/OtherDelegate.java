package com.qilu.ec.main.other;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.qilu.core.delegates.QiluDelegate;
import com.qilu.core.ec.R;
import com.qilu.ec.main.sample.OtherNewsItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OtherDelegate extends QiluDelegate{

    @Override
    public Object setLayout() {
        return R.layout.other_news_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        View view = rootView;
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new OtherAdapter(getActivity(), generateDatas(), false));
    }

    private List<OtherNewsItem> generateDatas() {
        Resources resources = getContext().getResources();
        Drawable user_example = resources.getDrawable(R.drawable.user_example);
        Drawable female = resources.getDrawable(R.drawable.female);

        List<OtherNewsItem> otherNewsItems = new ArrayList<OtherNewsItem>();
        for (int i = 0; i < 4; i++) {
            otherNewsItems.add(new OtherNewsItem(String.valueOf(i),
                    String.valueOf(new Date().getTime()),
                    user_example,
                    "content" + i,
                    female,
                    20, 21, 22));
        }
        return otherNewsItems;
    }
}
