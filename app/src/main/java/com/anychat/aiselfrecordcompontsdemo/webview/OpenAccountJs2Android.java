package com.anychat.aiselfrecordcompontsdemo.webview;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.anychat.aiselfrecordcompontsdemo.utils.PermissonUtils;
import com.anychat.aiselfrecordsdk.component.model.BusinessResult;
import com.anychat.aiselfrecordsdk.util.Base64BitmapUtil;
import com.anychat.aiselfrecordsdk.util.FileUtils;
import com.baidu.ocr.demo.FileUtil;
import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.BankCardParams;
import com.baidu.ocr.sdk.model.BankCardResult;
import com.baidu.ocr.sdk.model.IDCardParams;
import com.baidu.ocr.sdk.model.IDCardResult;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.bairuitech.anychat.util.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;


/**
 * Copyright: Copyright (c) 2021
 * Date: 2021/8/4 10:15
 */
public class OpenAccountJs2Android implements  PermissonUtils.PermissionCallback{
    private static final int REQUEST_CODE_CAMERA = 102;
    private static final int REQUEST_CODE_BANKCARD = 111;
    private WeakReference<Activity> mContext;
    private WebView mWebView;
    private Handler mHandler;

    private String[] mPermissions;
    private AlertDialog mPermissionDialog;

    public OpenAccountJs2Android(Activity activity) {
        this.mContext = new WeakReference<>(activity);
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public OpenAccountJs2Android(Activity activity, WebView webView) {
        this.mContext = new WeakReference<Activity>(activity);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mWebView = webView;
    }


    boolean checkNullContext() {
        return mContext == null || mContext.get() == null;
    }

    private Activity getContext() {
        return mContext.get();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (checkNullContext()) {
            return;
        }

        // 识别成功回调，身份证识别
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
                String filePath = FileUtil.getSaveFile(getContext()).getAbsolutePath();
                if (!TextUtils.isEmpty(contentType)) {
                    if (CameraActivity.CONTENT_TYPE_ID_CARD_FRONT.equals(contentType)) {
                        recIDCard(IDCardParams.ID_CARD_SIDE_FRONT, filePath);
                    } else if (CameraActivity.CONTENT_TYPE_ID_CARD_BACK.equals(contentType)) {
                        recIDCard(IDCardParams.ID_CARD_SIDE_BACK, filePath);
                    }
                }
            }
        }

        // 识别成功回调，银行卡识别
        if (requestCode == REQUEST_CODE_BANKCARD && resultCode == Activity.RESULT_OK) {

            recBankCard(getContext(), FileUtil.getSaveFile(getContext()).getAbsolutePath());

        }

    }

    PermissionsGrantedCallBack permissionsGrantedCallBack;
    public void requestPermission(PermissionsGrantedCallBack callBack) {
        permissionsGrantedCallBack = callBack;

        mPermissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA};
        PermissonUtils.getInstance().setPermissionCallback(this);
        PermissonUtils.getInstance().needToFinsh(true).requestPermission(getContext(), mPermissions);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (perms != null && perms.size() == mPermissions.length) {
            permissionsGrantedCallBack.onPermissionGranted();
        }
    }

    /**
     * 权限被拒绝
     */
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        mPermissionDialog = PermissonUtils.getInstance().showGoSettingDialog(getContext());
        permissionsGrantedCallBack.onPermissionDenied();
    }


    // 身份证正面拍照
    @JavascriptInterface
    public void getFrontIDCardInfo() {
        if(checkNullContext()) {
            return;
        }

        requestPermission(new PermissionsGrantedCallBack() {
            @Override
            public void onPermissionGranted( ) {
                Intent intent = new Intent(getContext(), CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtil.getSaveFile(getContext()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
                getContext().startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }

        });


    }

    // 身份证正面拍照
    @JavascriptInterface
    public void getBackIDCardInfo() {
        if(checkNullContext()) {
            return;
        }
        requestPermission(new PermissionsGrantedCallBack() {
            @Override
            public void onPermissionGranted() {
                Intent intent = new Intent(getContext(), CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtil.getSaveFile(getContext()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_BACK);
                getContext().startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });

    }

    // 银行卡识别
    @JavascriptInterface
    public void getBankCardInfo() {
        if(checkNullContext()) {
            return;
        }

        requestPermission(new PermissionsGrantedCallBack() {
            @Override
            public void onPermissionGranted() {
                Intent intent = new Intent(getContext(), CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtil.getSaveFile(getContext()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_BANK_CARD);
                getContext().startActivityForResult(intent, REQUEST_CODE_BANKCARD);
            }
        });

    }

    @JavascriptInterface
    public void startAiSelfRecord(String jsonParam) {
        if(checkNullContext()) {
            return;
        }
        JSONObject jsonObject = new JSONObject(jsonParam);

        requestPermission(new PermissionsGrantedCallBack() {
            @Override
            public void onPermissionGranted() {
                getContext().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new AiSelfRecordHelper(getContext()).start(jsonObject.optString("openAccountId"),
                                jsonObject.optString("khToken"), jsonObject.optString("userName"),
                                jsonObject.optInt("targetModel"), new AiSelfRecordHelper.CallBack() {
                                    @Override
                                    public void onSelfRecordCompleted(BusinessResult result) {
                                        JSONObject resultJson = new JSONObject();
                                        resultJson.put("videoFilePath", result.getVideoFilePath());
                                        //通知h5单录视频地址
                                        mWebView.loadUrl("javascript:onCallBackSingleWayauthentication('" + resultJson.toString() + "')");
                                    }
                                });
                    }
                });

            }
        });


    }

    private void recIDCard(String idCardSide, String filePath) {
        IDCardParams param = new IDCardParams();
        param.setImageFile(new File(filePath));
        // 设置身份证正反面
        param.setIdCardSide(idCardSide);
        // 设置方向检测
        param.setDetectDirection(true);
        // 设置图像参数压缩质量0-100, 越大图像质量越好但是请求时间越长。 不设置则默认值为20
        param.setImageQuality(20);

        param.setDetectRisk(true);

        OCR.getInstance(getContext().getApplication()).recognizeIDCard(param, new OnResultListener<IDCardResult>() {
            @Override
            public void onResult(IDCardResult result) {
                if (result != null) {
                    Log.d("testLog", result.toString());

                    Bitmap bitmap = FileUtils.fileToBitmap(filePath);

                    if (IDCardParams.ID_CARD_SIDE_FRONT.equals(idCardSide)) {
                        JSONObject resultJson = new JSONObject();
                        resultJson.put("name", result.getName());
                        resultJson.put("dateOfBirth", result.getBirthday());
                        resultJson.put("IDCardNumber", result.getIdNumber());
                        resultJson.put("address", result.getAddress());
                        resultJson.put("nation", result.getEthnic());
                        resultJson.put("frontIDCardImg", Base64BitmapUtil.bitmapToBase64(bitmap));

                        mWebView.loadUrl("javascript:onCallFrontIDCardInfo('" + resultJson.toString() + "')");
                    } else if (IDCardParams.ID_CARD_SIDE_BACK.equals(idCardSide)) {
                        JSONObject resultJson = new JSONObject();
                        resultJson.put("issuingAuthority", result.getIssueAuthority());
                        resultJson.put("dateOfIssuing", result.getSignDate());
                        resultJson.put("expirationDate", result.getExpiryDate());
                        resultJson.put("backIDCardImg", Base64BitmapUtil.bitmapToBase64(bitmap));
                        mWebView.loadUrl("javascript:onCallBackIDCardInfo('" + resultJson.toString() + "')");
                    }


                }
            }

            @Override
            public void onError(OCRError error) {
                Log.d("testLog", error.toString());
                JSONObject resultJson = getErrorJson(error);
                if (IDCardParams.ID_CARD_SIDE_FRONT.equals(idCardSide)) {
                    mWebView.post(new Runnable() {
                        @Override
                        public void run() {
                            mWebView.loadUrl("javascript:onCallFrontIDCardFail ('" + resultJson.toString() + "')");
                        }
                    });
                } else if (IDCardParams.ID_CARD_SIDE_BACK.equals(idCardSide)) {
                    mWebView.post(new Runnable() {
                        @Override
                        public void run() {
                            mWebView.loadUrl("javascript:onCallBackIDCardFail  ('" + resultJson.toString() + "')");
                        }
                    });
                }

            }
        });
    }



    private void recBankCard(Context ctx, String filePath) {
        BankCardParams param = new BankCardParams();
        param.setImageFile(new File(filePath));
        OCR.getInstance(ctx).recognizeBankCard(param, new OnResultListener<BankCardResult>() {
            @Override
            public void onResult(BankCardResult result) {

                Bitmap bitmap = FileUtils.fileToBitmap(filePath);
                JSONObject resultJson = new JSONObject();
                resultJson.put("bankCardNumber", result.getBankCardNumber());
                resultJson.put("bankName", result.getBankName());
                resultJson.put("bankCardType", result.getBankCardType().ordinal());
                resultJson.put("validDate", result.getValidDate());
                resultJson.put("holderName", result.getHolderHame());
                resultJson.put("bankImage", Base64BitmapUtil.bitmapToBase64(bitmap));
                mWebView.loadUrl("javascript:onCallBankCardInfo('" + resultJson.toString() + "')");
            }

            @Override
            public void onError(OCRError error) {
                JSONObject resultJson = getErrorJson(error);
                mWebView.post(new Runnable() {
                    @Override
                    public void run() {
                        mWebView.loadUrl("javascript:onCallBankCardInfonFail  ('" + resultJson.toString() + "')");

                    }
                });
            }
        });
    }

    private static JSONObject getErrorJson(OCRError error) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("errorCode", error.getErrorCode());
        resultJson.put("errorMsg", error.getMessage());
        return resultJson;
    }

    @JavascriptInterface
    public void goBack() {
        if (checkNullContext()) {
            return;
        }

        getContext().finish();
    }

    interface PermissionsGrantedCallBack {
      void onPermissionGranted();

      default void onPermissionDenied() {}
    }




}

