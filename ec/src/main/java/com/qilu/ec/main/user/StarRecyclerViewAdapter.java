package com.qilu.ec.main.user;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.qilu.core.ec.R;
import com.qilu.ec.main.decorate.DecorateDelegate;
import com.qilu.ec.main.sample.ExampleItem;
import com.qilu.ec.main.util.Image;

import java.util.List;

public class StarRecyclerViewAdapter extends RecyclerView.Adapter<StarRecyclerViewAdapter.ViewHolder> {
    private StarDelegate starDelegate;
    private List<ExampleItem> mValues;

    public StarRecyclerViewAdapter(StarDelegate starDelegate, List<ExampleItem> items) {
        this.starDelegate = starDelegate;
        mValues = items;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delegate_star_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ExampleItem exampleItem = mValues.get(position);
        holder.textView.setText(exampleItem.getContent());
//        holder.imageView.setImageDrawable(exampleItem.getImage());
        Image.showResultImage(exampleItem.getImage(), holder.imageView);
        holder.isSaved = exampleItem.getSaved();
        // TODO 取消收藏功能（暂时未做）
        /*if (holder.isSaved){
            holder.buttonView.setText(R.string.starPlused);
        }
        else{
            holder.buttonView.setText(R.string.starToPlus);
        }*/
        holder.buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 转入界面后无法退到原fragment，未解决
                starDelegate.getSupportDelegate().start(new DecorateDelegate(exampleItem.getImage()));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mValues != null)
            return mValues.size();
        else
            return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public IconTextView buttonView;
        public TextView textView;
        public ImageView imageView;
        public Boolean isSaved;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            buttonView = view.findViewById(R.id.button);
            textView = view.findViewById(R.id.text_content);
            imageView = view.findViewById(R.id.image);
        }
    }
}