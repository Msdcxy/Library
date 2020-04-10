package com.common.lib.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;

/**
 * Created by dengdayi
 * Date: 2019/03/20 14：16
 * Desc:Bitmap工具类
 */
public class BitmapUtils {
    public static Bitmap zoomImg(Bitmap bm, int newWidth, int newHeight) {// 获得图片的宽高   
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例   
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数   
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片   www.2cto.com
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }

    /**
     * 通过文件名字获取图片资源
     *
     * @param context
     * @param name
     * @return
     */
    public static int getMapmipRes(Context context, String name) {
        String packageName = context.getPackageName();
        return context.getResources().getIdentifier(name, "mipmap", packageName);
    }

    /**
     * 格式化文件大小单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "B";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }

    /**
     * 把Bitmap转Byte
     */
    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }
}
