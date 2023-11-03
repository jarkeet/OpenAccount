package com.anychat.aiselfrecordcompontsdemo.utils;

import android.app.Service;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * @describe: UI常用方法封装
 * @author: yyh
 * @createTime: 2018/8/3 16:47
 * @className: UIUtils
 */
public class UIUtils {

    public static Toast makeToast(Context context, int resId, int duration) {
        Toast toast = Toast.makeText(context, null, duration);
        toast.setText(resId);
        return toast;
    }

    public static Toast makeToast(Context context, CharSequence text, int duration) {
        Toast toast = Toast.makeText(context, null, duration);
        toast.setText(text);
        return toast;
    }

    /**
     * 隐藏输入法
     */
    public static void hideSoftKeyboard(Context context, View view) {
        if (context == null) {
            return;
        }
        if (view == null) {
            return;
        }
        View rootView = view.getRootView();
        if (rootView == null) {
            return;
        }
        View focusedView = rootView.findFocus();
        if (focusedView == null) {
            return;
        }
        InputMethodManager inputMgr = (InputMethodManager) context.getSystemService(Service.INPUT_METHOD_SERVICE);
        if (inputMgr != null) {
            inputMgr.hideSoftInputFromWindow(focusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 获取app版本号
     */
    public static String getAppVersionCode(Context context) {
        float versioncode = 0;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versioncode = pi.versionCode;
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        if (versioncode == 0) {
            return "";
        }
        return "" + versioncode;
    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = null;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        if (versionName == null) {
            return "";
        }
        return "App v" + versionName;
    }
}