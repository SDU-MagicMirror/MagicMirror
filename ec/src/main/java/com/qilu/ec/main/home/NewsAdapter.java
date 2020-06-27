package com.qilu.ec.main.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.flexbox.FlexboxLayout;
import com.qilu.core.ec.R;
import com.qilu.ec.main.comment.CommentDelegate;
import com.qilu.ec.main.other.OtherDelegate;
import com.qilu.ec.main.sample.NewsItem;

import java.util.List;

public class NewsAdapter extends BaseAdapter implements View.OnClickListener {
        private View parent;
    private List<NewsItem> newsItemList;
    private LayoutInflater inflater;
    private Context context;
    private boolean hasFocus;
    private HomeDelegate homeDelegate;

    public NewsAdapter(View view, List<NewsItem> newsItemList, Context context, boolean hasFocus, HomeDelegate homeDelegate) {
        super();
        this.parent = view;
        this.newsItemList = newsItemList;
        this.hasFocus = hasFocus;
        this.context = context;
        this.homeDelegate = homeDelegate;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return newsItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //创建ViewHolder的对象。
        ViewHolder viewHolder = null;
        //获得Item位置上的数据。
        NewsItem newsItem = newsItemList.get(position);
        //convertview 优化
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_item, null);
            viewHolder = new ViewHolder();
            viewHolder.user_img = (ImageView) convertView.findViewById(R.id.user_img);
            viewHolder.text_name = (TextView) convertView.findViewById(R.id.text_name);
            viewHolder.text_honor = (TextView) convertView.findViewById(R.id.text_honor);
            viewHolder.text_content = (TextView) convertView.findViewById(R.id.text_content);
            viewHolder.attached_img = (ImageView) convertView.findViewById(R.id.attached_img);
            viewHolder.button = convertView.findViewById(R.id.button);
            //加载操作区
            viewHolder.disLayout = convertView.findViewById(R.id.dis);
            viewHolder.commentLayout = convertView.findViewById(R.id.comment);
            viewHolder.likeLayout = convertView.findViewById(R.id.like);
            viewHolder.dis_text = convertView.findViewById(R.id.dis_text);
            viewHolder.comment_text = convertView.findViewById(R.id.comment_text);
            viewHolder.like_text = convertView.findViewById(R.id.like_text);

            viewHolder.linearLayout = convertView.findViewById(R.id.layout);
            //convertView为空时，ViewHolder将显示在ListView中的数据通过findViewById获取到。
            convertView.setTag(viewHolder);
        } else {
            //convertView不为空时，直接获取ViewHolder的Tag即可。
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.user_img.setImageDrawable(newsItem.getUser_img());
        viewHolder.text_name.setText(newsItem.getText_name());
        viewHolder.text_honor.setText(newsItem.getText_honor());
        viewHolder.text_content.setText(newsItem.getText_content());
        viewHolder.attached_img.setImageDrawable(newsItem.getAttached_img());
        //Test
        viewHolder.disLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Test", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.dis_text.setText(String.valueOf(newsItem.getDistributionNum()));
        viewHolder.comment_text.setText(String.valueOf(newsItem.getCommentNum()));
        viewHolder.like_text.setText(String.valueOf(newsItem.getLikeNum()));

        viewHolder.user_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeDelegate.getParentDelegate().getSupportDelegate().start(new OtherDelegate());
                //MainActivity.hideBottomBar();

//                fragmentTransaction.commit();
            }
        });
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Test", Toast.LENGTH_SHORT).show();
            }
        });
        if (hasFocus) {
            viewHolder.button.setVisibility(View.VISIBLE);
        } else {
            viewHolder.button.setVisibility(View.INVISIBLE);
        }

        viewHolder.linearLayout.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.layout) {
            //                FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
            homeDelegate.getParentDelegate().getSupportDelegate().start(new CommentDelegate(parent,context));
        }
    }

    static class ViewHolder {
        ImageView user_img;
        TextView text_name;
        TextView text_honor;
        TextView text_content;
        ImageView attached_img;
        Button button;
        TextView dis_text;
        TextView comment_text;
        TextView like_text;
        FlexboxLayout disLayout;
        FlexboxLayout commentLayout;
        FlexboxLayout likeLayout;

        LinearLayout linearLayout;
    }
}
