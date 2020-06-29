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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delegate_example_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ExampleItem exampleItem = mValues.get(position);
        holder.textView.setText(exampleItem.getItemNumber());
        holder.imageView.setImageDrawable(exampleItem.getImage());
        holder.isSaved = exampleItem.getSaved();
        if (holder.isSaved){
            holder.buttonView.setText(R.string.starPlused);
        }
        else{
            holder.buttonView.setText(R.string.starToPlus);
        }
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
        // TODO 将id,图和文字三个数据放到一个数据库，对象可以沿用sample.ExampleItem类（将其中的Drawable改为String即可）

        // 示例
//        UserCollectionHelper userCollectionHelper = new UserCollectionHelper(this.context);
//        SQLiteDatabase db = userCollectionHelper.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(UserCollectionHelper.ID,"某id");
//        values.put(UserCollectionHelper.CONTENT,"测试内容");
//        values.put(UserCollectionHelper.IMAGE,"image");
//
//        db.insert(UserCollectionHelper.TABLE_NAME,null,values);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public IconTextView buttonView;
        public TextView textView;
        public ImageView imageView;
        public Boolean isSaved;

        public ViewHolder(View view) {
            super(view);
            this.view=view;
            buttonView = view.findViewById(R.id.button);
            textView = (TextView) view.findViewById(R.id.text_content);
            imageView = (ImageView) view.findViewById(R.id.image);
        }
    }
}