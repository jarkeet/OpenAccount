package com.anychat.aiselfrecordcompontsdemo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @describe: sd卡文件操作工具类
 * @author: yyh
 * @createTime: 2018/5/17 10:38
 * @className: FileUtils
 */
public class FileUtils {

    /**
     * 判断sdcrad是否已经安装
     */
    public static boolean isSDCardMounted() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 根据文件路径判断文件是否存在
     *
     * @param context
     * @param filePath
     * @return
     */
    public static boolean isFileExists(Context context, String filePath) {
        if (EmptyUtils.isNullOrEmpty(filePath)) {
            return false;
        }
        File file = new File(filePath);
        if (!file.exists()) {
            UIUtils.makeToast(context, "文件不存在或者已损坏", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 获取sdcard路径
     */
    public static String getSDCardRoot() {
        if (isSDCardMounted()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return "";
    }

    /**
     * 获取缓存地址
     */
    public static String getDiskCacheDir(Context context) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }

    /**
     * 保存bitmap到本地
     */
    public static String saveBitmap(String savePath, String name, Bitmap bitmap) {
        if (EmptyUtils.isNullOrEmpty(savePath)) {
            savePath = getSDCardRoot();
        }
        File file = new File(savePath, "AnyChat");
        try {
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (EmptyUtils.isNullOrEmpty(name)) {
            name = String.valueOf(System.currentTimeMillis());
        }
        File filePic = new File(file, name + ".png");
        if (filePic.exists()) {
            return filePic.getAbsolutePath();
        }
        try {
            if (!filePic.exists()) {
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return filePic.getAbsolutePath();
    }
}