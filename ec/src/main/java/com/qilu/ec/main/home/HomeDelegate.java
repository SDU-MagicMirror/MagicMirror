package com.qilu.ec.main.home;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.qilu.core.delegates.bottom.BottomItemDelegate;
import com.qilu.core.ec.R;
import com.qilu.core.util.storage.QiluPreference;
import com.qilu.ec.main.sample.NewsItem;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class HomeDelegate extends BottomItemDelegate implements View.OnClickListener {
    final String type = QiluPreference.getCustomAppProfile("type");

    private ArrayList<NewsItem> newsList;
    private ListView listView;
    private NewsAdapter newsAdapter;
    //    private boolean hasFocus;
    private View rootView;

    private Button button_1;
    private Button button_2;

    /*@SuppressLint("ValidFragment")
    public HomeDelegate(boolean hasFocus) {
        super();
        this.hasFocus = hasFocus;
    }*/

    @Override
    public Object setLayout() {
        return R.layout.fragment_item_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        this.rootView = rootView;
        listView = (ListView) rootView.findViewById(R.id.list);
        getListFunction();
        newsAdapter = new NewsAdapter(rootView, newsList, getActivity(), false, this);
        listView.setAdapter(newsAdapter);
        button_1 = rootView.findViewById(R.id.top_button1);
        button_2 = rootView.findViewById(R.id.top_button2);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        int color = getResources().getColor(R.color.peter_river);
        int fontColor = getResources().getColor(R.color.white);
        button_1.setBackgroundColor(color);
        button_1.setTextColor(fontColor);
    }

    private void getListFunction() {
        newsList = new ArrayList<NewsItem>();
        for (int i = 0; i < 2; i++) {
            Resources resources = getContext().getResources();
            Drawable user_example = resources.getDrawable(R.drawable.user_example);
            Drawable female = resources.getDrawable(R.drawable.female);
            newsList.add(new NewsItem("1",
                    user_example,
                    "text_name",
                    "text_honor",
                    "text_content",
                    female,
                    20, 21, 22));
        }
    }

    private void getListFunction2() {
        newsList = new ArrayList<NewsItem>();
        for (int i = 0; i < 2; i++) {
            Resources resources = getContext().getResources();
            Drawable user_example = resources.getDrawable(R.drawable.user_example);
            Drawable female = resources.getDrawable(R.drawable.female);
            newsList.add(new NewsItem("1",
                    user_example,
                    "text_name2",
                    "text_honor2",
                    "text_content2",
                    female,
                    20, 21, 22));
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        int color = getResources().getColor(R.color.peter_river);
        int color2 = getResources().getColor(R.color.white);
        int fontColor = getResources().getColor(R.color.white);
        int fontColor2 = getResources().getColor(R.color.peter_river);
        if (id == R.id.top_button1) {
            button_1.setBackgroundColor(color);
            button_1.setTextColor(fontColor);
            button_2.setBackgroundColor(color2);
            button_2.setTextColor(fontColor2);
            setLayout("focus");
        } else if (id == R.id.top_button2) {
            button_2.setBackgroundColor(color);
            button_2.setTextColor(fontColor);
            button_1.setBackgroundColor(color2);
            button_1.setTextColor(fontColor2);
            setLayout("recommend");
        }
    }

    private void setLayout(String type) {
        switch (type) {
            case "focus":
//                getParentDelegate().getSupportDelegate().startWithPop(new HomeDelegate(false));
                getListFunction();
                newsAdapter = new NewsAdapter(rootView, newsList, getActivity(), false, this);
                listView.setAdapter(newsAdapter);
                break;
            case "recommend":
//                getParentDelegate().getSupportDelegate().startWithPop(new HomeDelegate(true));
                getListFunction2();
                newsAdapter = new NewsAdapter(rootView, newsList, getActivity(), true, this);
                listView.setAdapter(newsAdapter);
                break;
            default:
                break;
        }
    }
}
