package com.qilu.ec.main.user;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;
import com.qilu.core.ec.R;
import com.qilu.ec.main.util.Image;

import org.w3c.dom.Text;

import java.util.List;

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<String> mValues;

    public HistoryRecyclerViewAdapter(Context context, List<String> mValues) {
        this.context = context;
        this.mValues = mValues;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.delegate_decorate_history_item, parent, false);
        return new HistoryRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String str = mValues.get(position);
        Image.showResultImage(str, holder.imageView);
        holder.button.setOnClickListener(this);
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
        // TODO 将结果图片（重新）下到本地，之前用过这个方法，复制粘贴即可
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public IconTextView button;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image);
            button = view.findViewById(R.id.button);
        }
    }
}
