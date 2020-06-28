package com.qilu.ec.main.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Image {
    /**
     * 结果图显示到屏幕上
     */
    public static void showResultImage(@Nullable String image, ImageView imageView) {
        if (image != null && (!image.isEmpty())) {
            byte[] bytes = Base64.decode(image, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            imageView.setImageBitmap(bitmap);

            //自动缩放
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setAdjustViewBounds(true);
        }
    }

    public static void showResultImage(@Nullable String image, CircleImageView circleImageView) {
        if (image != null && (!image.isEmpty())) {
            byte[] bytes = Base64.decode(image, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            circleImageView.setImageBitmap(bitmap);

            //自动缩放
//            circleImageView.setScaleType(CircleImageView.ScaleType.FIT_XY);
//            circleImageView.setAdjustViewBounds(true);
        }
    }

}
