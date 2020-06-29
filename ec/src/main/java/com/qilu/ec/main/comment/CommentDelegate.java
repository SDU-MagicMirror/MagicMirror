package com.qilu.ec.main.comment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;
import com.qilu.core.delegates.QiluDelegate;
import com.qilu.core.ec.R;
import com.qilu.ec.main.sample.CommentItem;
import com.zyyoona7.popup.EasyPopup;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressLint("ValidFragment")
public class CommentDelegate extends QiluDelegate implements View.OnClickListener {
    private View parentView;
    private View thisView;
    private EasyPopup mCirclePop;
    private Context context;
    private EditText myComment;
    private Button button;
    private String editString;

    private FlexboxLayout flexboxLayout;

    @SuppressLint("ValidFragment")
    public CommentDelegate(View parentView, Context context) {
        this.parentView = parentView;
        this.context = context;

        WindowManager wm = (WindowManager) ((FragmentActivity) context)
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        mCirclePop = EasyPopup.create()
                .setContentView(context, R.layout.comment_popup)
                .setWidth(width)
                //.setAnimationStyle(R.style.RightPopAnim)
                //是否允许点击PopupWindow之外的地方消失
                .setAnchorView(parentView)
                .setFocusAndOutsideEnable(true)
                .apply();
        myComment = mCirclePop.findViewById(R.id.editText);

        button = mCirclePop.findViewById(R.id.editButton);
        editString = null;
    }

    @Override
    public Object setLayout() {
        return R.layout.news_infomation;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        Context context = rootView.getContext();
        RecyclerView recyclerView = rootView.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new CommentAdapter(getActivity(), generateDatas()));

        thisView = rootView;
        flexboxLayout = rootView.findViewById(R.id.comment);
        flexboxLayout.setOnClickListener(this);
    }

    private List<CommentItem> generateDatas() {
        Resources resources = getContext().getResources();
        Drawable user_example = resources.getDrawable(R.drawable.user_example);
        Drawable female = resources.getDrawable(R.drawable.female);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        List<CommentItem> commentItems = new ArrayList<CommentItem>();
        for (int i = 0; i < 10; i++) {
            commentItems.add(new CommentItem(String.valueOf(i),user_example, "name" + i, "contentTestcontentTestcontentTestcontentTestcontentTestcontentTest" + i, simpleDateFormat.format(date)));
        }
        return commentItems;
    }

    private void showComment() {
        //mCirclePop.showAtAnchorView(parentView, YGravity.ALIGN_BOTTOM, XGravity.CENTER);
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(parentView, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        mCirclePop.showAtAnchorView(thisView, YGravity.ALIGN_BOTTOM, XGravity.CENTER);
        myComment.setSelected(true);
        myComment.setFocusable(true);
        myComment.setFocusableInTouchMode(true);
        myComment.requestFocus();
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.comment) {
            showComment();
        } else if (id == R.id.editButton) {
            Toast.makeText(context, "SubmitTest", Toast.LENGTH_SHORT).show();
        }
    }
}
