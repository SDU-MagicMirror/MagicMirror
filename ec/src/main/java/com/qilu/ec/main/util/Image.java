package com.qilu.ec.main.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
     *
     * @param bit 传入的bitmap
     * @return
     */
    public static String BitmapToStrByBase64(Bitmap bit) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 100, bos);//参数100表示不压缩
        byte[] bytes = bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    /**
     * Drawable 转  bitmap
     */
    public static Bitmap drawableToBitmap(Drawable img) {
        BitmapDrawable bd = (BitmapDrawable) img;
        Bitmap bitmap = bd.getBitmap();
        return bitmap;
    }

    /**
     * 文件转base64字符串
     *
     * @param file
     * @return
     */
    public static String fileToBase64(File file) {
        String base64 = null;
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            byte[] bytes = new byte[in.available()];
            int length = in.read(bytes);
            base64 = Base64.encodeToString(bytes, 0, length, Base64.DEFAULT);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return base64;
    }

    /**
     * base64字符串转文件
     *
     * @param base64
     * @return
     */
    public static File base64ToFile(String base64) {
        File file = null;
        String fileName = "testFile.jpg";
        FileOutputStream out = null;
        try {
            // 解码，然后将字节转换为文件
            Log.i("存储目录", String.valueOf(Environment.getExternalStorageDirectory()));
            file = new File(Environment.getExternalStorageDirectory(), fileName);
            if (!file.exists())
                file.createNewFile();
            else {
                Log.i("已经存在文件", "将其删除");
                file.delete();
                Log.i("文件是否被删除", String.valueOf(!file.exists()));
                Log.i("新建文件", "");
                file.createNewFile();
                Log.i("文件是否被新建", String.valueOf(file.exists()));
            }
            byte[] bytes = Base64.decode(base64, Base64.DEFAULT);// 将字符串转换为byte数组
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            byte[] buffer = new byte[1024];
            out = new FileOutputStream(file);
            int bytesum = 0;
            int byteread = 0;
            while ((byteread = in.read(buffer)) != -1) {
                bytesum += byteread;
                out.write(buffer, 0, byteread); // 文件写操作
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return file;
    }
}
