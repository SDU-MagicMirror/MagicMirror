package com.qilu.ec.main.example;

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
public class MyExampleRecyclerViewAdapter extends RecyclerView.Adapter<MyExampleRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<ExampleItem> mValues;

    public MyExampleRecyclerViewAdapter(Context context, List<ExampleItem> items) {
        this.context = context;
        mValues = items;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_example, parent, false);
        return new ViewHolder(view/*, context*/);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ExampleItem exampleItem = mValues.get(position);
        holder.textView.setText(exampleItem.getItemNumber());
        holder.imageView.setImageDrawable(exampleItem.getImage());

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
        //只有一个控件监听，不用设if判断
        // TODO 将id,图和文字三个数据放到一个数据对象json存到本地缓存文件中，json对象可以沿用sample.ExampleItem类（将其中的Drawable改为String即可）
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        //public final View mView;
        public IconTextView buttonView;
        public TextView textView;
        public ImageView imageView;
        //public ExampleItem mItem;

        public ViewHolder(View view/*, Context context*/) {
            super(view);
            //mView = view;
            buttonView = view.findViewById(R.id.button);
            textView = (TextView) view.findViewById(R.id.text_content);
            imageView = (ImageView) view.findViewById(R.id.image);
        }
    }
}