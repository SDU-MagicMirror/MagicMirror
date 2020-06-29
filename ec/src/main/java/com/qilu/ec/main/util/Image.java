package com.qilu.ec.main.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

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


    /**
     * 将bitmap转为base64格式的字符串
     * @param bit 传入的bitmap
     * @return
     */
    public static String BitmapToStrByBase64(Bitmap bit){
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 100, bos);//参数100表示不压缩
        byte[] bytes=bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    /**
     * Drawable 转  bitmap
     */
    public static Bitmap drawableToBitmap(Drawable img){
        BitmapDrawable bd = (BitmapDrawable) img;
        Bitmap bitmap = bd.getBitmap();
        return bitmap;
    }
}
