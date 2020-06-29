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
import com.qilu.ec.main.sample.ExampleItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ExampleItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class StarRecyclerViewAdapter extends RecyclerView.Adapter<StarRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<ExampleItem> mValues;

    public StarRecyclerViewAdapter(Context context, List<ExampleItem> items) {
        this.context = context;
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
        holder.textView.setText(exampleItem.getItemNumber());
        holder.imageView.setImageDrawable(exampleItem.getImage());
        holder.isSaved = exampleItem.getSaved();
        // TODO 取消收藏功能（暂时不做）
        /*if (holder.isSaved){
            holder.buttonView.setText(R.string.starPlused);
        }
        else{
            holder.buttonView.setText(R.string.starToPlus);
        }*/
        holder.buttonView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        if (mValues != null)
            return mValues.size();
        else
            return 0;
    }

    @Override
    public void onClick(View v) {
        // TODO 转到美妆界面（调用带有图片的构造方法）

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