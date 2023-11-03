package com.anychat.aiselfrecordcompontsdemo.webview;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;

import com.anychat.aiselfrecordcompontsdemo.R;
import com.anychat.aiselfrecordcompontsdemo.utils.PermissonUtils;
import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.bairuitech.anychat.main.AnyChatJournal;

public class OpenAccountH5Activity extends AppCompatActivity {



    public static final String URL_OPEN_ACCOUNT = "http://192.168.5.95:8081/#/openAccount/mobileVerify?showtype=3";
    public static final String URL_ID_CARD_VERIFY = "http://192.168.5.95:8081/#/openAccount/identify?showtype=3";
    public static final String URL_BANK_CARD_VERIFY = "http://192.168.5.95:8081/#/openAccount/bankInfo?showtype=3";
    public static final String URL_VIDEO_VERIFY = "http://192.168.5.95:8081/#/openAccount/videoCertification?showtype=3";

    public static final String URL = "url";

    private WebView webView;
    private String url = "http://192.168.5.95:8081/#/openAccount/mobileVerify?showtype=3";
    private OpenAccountJs2Android js2Android;
    private boolean hasGotToken = false;


    /**
     * 郭小赵(开发) 16:20:59
     * 身份证页面：
     * http://192.168.5.95:8081/#/openAccount/identify?showtype=3
     *
     * 银行卡页面：
     * http://192.168.5.95:8081/#/openAccount/bankInfo?showtype=3
     *
     * 视频见证
     * http://192.168.5.95:8081/#/openAccount/videoCertification?showtype=3
     *
     * 郭小赵(开发) 16:21:04
     * http://192.168.5.95:8081/#/openAccount/mobileVerify?showtype=3
     * @param context
     * @param url
     */


    public static void startActivity(Context context, String url) {
        Intent intent = new Intent(context, OpenAccountH5Activity.class);
        intent.putExtra(URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zs_layout_open_account_web_view);
//        url = getIntent().getStringExtra(URL);
        AnyChatJournal.enable(true);
        initView();
    }



    private void initView() {

        webView = (WebView)findViewById(R.id.web_view);
        WebUtils.setWebViewSetting(webView);
        js2Android = new OpenAccountJs2Android(this, webView);
        webView.addJavascriptInterface(js2Android, "zsqhYSAndroid");
        webView.loadUrl(url);

        webView.setOnKeyListener((v, keyCode, event) -> {
            //if user click back from phone self button
            if (event.getAction() == KeyEvent.ACTION_UP &&
                    keyCode == KeyEvent.KEYCODE_BACK) {
                if (webView.canGoBack()){
                    webView.goBack();
                } else {
                    finish();
                }

                return true;
            }
            return false;
        });

        initAccessTokenWithAkSk();

    }

    private void initAccessTokenWithAkSk() {
        OCR.getInstance(getApplicationContext()).initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken accessToken) {
                String token = accessToken.getAccessToken();
                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                Log.w("testLog","自定义文件路径licence方式获取token失败: "+ error.getMessage());
            }
        },  getApplicationContext(), "KYbICx7n0HZGHUbbOFWWVyVW", "Uu7LoiSPG3DxBsFNkbOS8W50dNe8y4mX");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        js2Android.onActivityResult(requestCode, resultCode, data);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissonUtils.getInstance().onRequestPermissionsResult(requestCode, permissions, grantResults);
    }








}
