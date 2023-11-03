package com.anychat.aiselfrecordcompontsdemo.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsoluteLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.anychat.aiselfrecordcompontsdemo.R;
import com.anychat.aiselfrecordcompontsdemo.utils.AnyChatJSBridge;
import com.bairuitech.anychat.util.json.JSONObject;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class BRBusinessWebView extends WebView {
    private ProgressBar progressbar;  //进度条
    private int progressHeight = 5;  //进度条的高度，默认10px;
    private dealData event;
    private boolean isShowProgressbar = true;//是否显示进度条
    String cacheDirPath = getContext().getFilesDir().getAbsolutePath() + "cache/";
    ValueCallback<Uri[]> mFilePathCallback;
    ValueCallback<Uri> mFileValueCallBack;

    public BRBusinessWebView(@NonNull Context context) {
        super(context);
    }

    public BRBusinessWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BRBusinessWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setEvent(dealData event) {
        this.event = event;
    }

    public void setChoseFile(Uri[] uris) {
        if (mFilePathCallback != null) {
            mFilePathCallback.onReceiveValue(uris);
            mFilePathCallback = null;
        }
        if (mFileValueCallBack != null) {
            mFileValueCallBack.onReceiveValue(uris[0]);
            mFileValueCallBack = null;
        }
    }

    public void initConfig() {
        //创建进度条
        progressbar = new ProgressBar(getContext(), null,
                android.R.attr.progressBarStyleHorizontal);
        //设置加载进度条的高度
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, progressHeight, 0, 0));

        Drawable drawable = getContext().getResources().getDrawable(R.drawable.cfrecordpad_speak_voice_progress);
        progressbar.setProgressDrawable(drawable);
        addView(progressbar);
        //开启js脚本支持
        WebSettings settings = getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            settings.setMediaPlaybackRequiresUserGesture(false);
        }
        //优先使用缓存：
        //settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //不使用缓存：
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setJavaScriptEnabled(true); // 启用javascript
        settings.setDomStorageEnabled(true); // 支持HTML5中的一些控件标签
        settings.setBuiltInZoomControls(false); // 自选，非必要
        //处理http和https混合的问题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                settings.setMixedContentMode(WebSettings.LOAD_NORMAL);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 允许javascript出错
            try {
                Method method = Class.forName("android.webkit.WebView").
                        getMethod("setWebContentsDebuggingEnabled", Boolean.TYPE);
                if (method != null) {
                    method.setAccessible(true);
                    method.invoke(null, true);
                }
            } catch (Exception e) {
                // do nothing
            }
        }
        setFocusable(true); // 自选，非必要
        setDrawingCacheEnabled(true); // 自选，非必要
        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); // 自选，非必要
        setWebChromeClient(mWebChromeClient);
        //适配手机大小
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setGeolocationEnabled(true);
        String dir = getContext().getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        settings.setGeolocationDatabasePath(dir);
        Log.e("settings", "setGeolocationDatabasePath:" + dir);

        settings.setDomStorageEnabled(true);
        //设置 缓存模式
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//        settings.setAppCacheMaxSize(1024 * 1024 * 15);// 设置缓冲大小15M
//        Log.e("settings", "cacheDirPath:" + cacheDirPath);
//        // 开启 Application Caches 功能
//        settings.setAppCacheEnabled(true);
//        // 设置 Application Caches 缓存目录
//        settings.setAppCachePath(cacheDirPath);
        settings.setAllowFileAccess(true);
        setWebViewClient(mWebViewClient);

    }

    WebChromeClient mWebChromeClient=new WebChromeClient() {
        @Override
        public void onPermissionRequest(PermissionRequest request) {
            String[] grants = new String[0];
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                grants = request.getResources();
            }
            for (int i = 0; i < grants.length; i++) {
                Log.e("onPermissionRequest", "request.getResources():" + grants[i]);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Log.e("onPermissionRequest", "request.getResources():" + Arrays.toString(request.getResources()));
            }
            //允许权限申请
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                request.grant(request.getResources());
            }
        }

        @Override
        public void onPermissionRequestCanceled(PermissionRequest request) {
            //拒绝权限申请
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (isShowProgressbar) {
                if (newProgress == 100) {
                    progressbar.setVisibility(GONE);
                } else {
                    if (progressbar.getVisibility() == GONE)
                        progressbar.setVisibility(VISIBLE);
                    progressbar.setProgress(newProgress);
                }
            } else {
                progressbar.setVisibility(GONE);
            }
            super.onProgressChanged(view, newProgress);

        }

        @Nullable
        @Override
        public View getVideoLoadingProgressView() {
            return super.getVideoLoadingProgressView();
        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            Log.e("onShowCustomView", "onShowCustomView");
            super.onShowCustomView(view, callback);
        }

        @Override
        public void onHideCustomView() {
            Log.e("onHideCustomView", "onHideCustomView");
            super.onHideCustomView();
        }

//        // For Android < 3.0
//        public void openFileChooser(ValueCallback<Uri> valueCallback) {
//            Log.e("openFileChooser", "openFileChooser");
//            mFileValueCallBack = valueCallback;
//            if (event != null) {
//                event.onShowFileChooser();
//            }
//        }
//
//        // For Android  >= 3.0
//        public void openFileChooser(ValueCallback valueCallback, String acceptType) {
//            Log.e("openFileChooser", "openFileChooser");
//            mFileValueCallBack = valueCallback;
//            if (event != null) {
//                event.onShowFileChooser();
//            }
//        }
//
//        //For Android  >= 4.1
//        public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {
//            Log.e("openFileChooser", "openFileChooser");
//            mFileValueCallBack = valueCallback;
//            if (event != null) {
//                event.onShowFileChooser();
//            }
//        }
//
//        @Override
//        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
//            Log.e("onShowFileChooser", "onShowFileChooser");
//            mFilePathCallback = filePathCallback;
//            if (event != null) {
//                event.onShowFileChooser();
//            }
//            return true;
//        }
    };
    WebViewClient mWebViewClient=new WebViewClient() {
        @Nullable
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Log.e("UrlLoading", "shouldInterceptRequest:" +  request.getUrl());
            }
            return super.shouldInterceptRequest(view, request);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            Log.e("UrlLoading", "shouldOverrideUrlLoading:" + url);
            if (url.startsWith("http://") || url.startsWith("https://")) { // 4.0以上必须要加
                webView.loadUrl(url);
                return true;
            }
            return super.shouldOverrideUrlLoading(webView, url);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//            super.onReceivedSslError(view, handler, error);
            Log.e("UrlLoading", "onReceivedSslError:" +error.getPrimaryError());
            switch (error.getPrimaryError()) {
                case SslError.SSL_INVALID: // 校验过程遇到了bug
                case SslError.SSL_UNTRUSTED: // 证书有问题
                    handler.proceed();
                default:
                    handler.cancel();
                    if (event!=null) {
                        event.onError();
                    }
            }
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.e("UrlLoading", "onPageStarted:" + url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if(progressbar!=null) {
                progressbar.setVisibility(GONE);
            }
            Log.e("UrlLoading", "onPageFinished:" + url);
        }
    };

    public interface dealData {
        void onError();
    }
//    public void onCancel() {
//        if (mFilePathCallback != null) {
//            mFilePathCallback.onReceiveValue(null);
//            mFilePathCallback = null;
//        }
//        if (mFileValueCallBack != null) {
//            mFileValueCallBack.onReceiveValue(null);
//            mFileValueCallBack = null;
//        }
//    }
}
