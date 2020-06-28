package com.qilu.ec.main.example;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qilu.core.ec.R;
import com.qilu.ec.main.sample.ExampleItem;

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
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_example, parent, false);
        return new ViewHolder(view/*, context*/);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ExampleItem exampleItem = mValues.get(position);
        holder.textView.setText(exampleItem.getItemNumber());
        holder.honorView.setText(exampleItem.getItemNumber2());
        holder.imageView.setImageDrawable(exampleItem.getImage());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View v) {
                Toast.makeText(context, holder.textView.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //public final View mView;
        public TextView textView;
        public TextView honorView;
        public ImageView imageView;
        //public ExampleItem mItem;

        public ViewHolder(View view/*, Context context*/) {
            super(view);
            //mView = view;
            textView = (TextView) view.findViewById(R.id.item_number);
            honorView = (TextView) view.findViewById(R.id.item_number2);
            imageView = (ImageView) view.findViewById(R.id.image);
        }
    }
}