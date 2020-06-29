package com.qilu.ec.main.example;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.qilu.core.ec.R;
import com.qilu.core.util.storage.UserCollectionHelper;
import com.qilu.ec.main.sample.ExampleItem;
import com.qilu.ec.main.util.Image;

import java.util.Base64;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ExampleItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyExampleRecyclerViewAdapter extends RecyclerView.Adapter<MyExampleRecyclerViewAdapter.ViewHolder> {
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
        if (holder.isSaved) {
            holder.buttonView.setText(R.string.starPlused);
        } else {
            holder.buttonView.setText(R.string.starToPlus);
        }
        holder.buttonView.setOnClickListener(v -> {
            UserCollectionHelper userCollectionHelper = new UserCollectionHelper(context);
            SQLiteDatabase db = userCollectionHelper.getWritableDatabase();
            String id = exampleItem.getId();
            String content = exampleItem.getItemNumber();
            //只有buttonView监听click
            if (exampleItem.getSaved()) {
                //已收藏
                // TODO 取消收藏
                long result = db.delete(UserCollectionHelper.TABLE_NAME,"id = ?",new String[]{String.valueOf(id)});
                if(result != -1){
                    ((IconTextView)v).setText(R.string.starToPlus);
                    Toast.makeText(context, "取消收藏成功", Toast.LENGTH_SHORT).show();
                    exampleItem.setSaved(false);
                }
                else{
                    Toast.makeText(context, "取消收藏失败", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                //未收藏

                //假设接口的图片是Base64字符串
                String img_base64;

                //如果get返回的是Drawable
                img_base64 = Image.BitmapToStrByBase64(Image.drawableToBitmap(exampleItem.getImage()));

                //如果get返回的是Base64的String
                //img_base64=exampleItem.getImage();

                // TODO 将id,图和文字三个数据放到一个数据库，对象可以沿用sample.ExampleItem类（将其中的Drawable改为String即可）

                ContentValues values = new ContentValues();
                values.put(UserCollectionHelper.ID, id);
                values.put(UserCollectionHelper.CONTENT, content);
                values.put(UserCollectionHelper.IMAGE, img_base64);

                long result = db.insert(UserCollectionHelper.TABLE_NAME, null, values);
                if (result != -1) {
                    exampleItem.setSaved(true);
                    //更新成功
                    Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
                    ((IconTextView)v).setText(R.string.starPlused);
                }
                else{
                    Toast.makeText(context, "收藏失败", Toast.LENGTH_SHORT).show();
                }
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
        View view;
        IconTextView buttonView;
        TextView textView;
        ImageView imageView;
        Boolean isSaved;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            buttonView = view.findViewById(R.id.button);
            textView = (TextView) view.findViewById(R.id.text_content);
            imageView = (ImageView) view.findViewById(R.id.image);
        }
    }
}