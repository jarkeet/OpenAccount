package com.anychat.aiselfrecordcompontsdemo.utils;

import android.app.Activity;
import android.os.Looper;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.anychat.aiselfrecordcompontsdemo.ui.view.BRBusinessWebView;
import com.anychat.aiselfrecordsdk.component.BRAiSelfRecordSDK;
import com.anychat.aiselfrecordsdk.component.interf.VideoRecordEvent;
import com.anychat.aiselfrecordsdk.component.model.BusinessResult;
import com.anychat.aiselfrecordsdk.component.model.CustomerErrorCode;
import com.bairuitech.anychat.util.json.JSONObject;
import com.bairuitech.anychat.videobanksdk.AnyChatClientEvent;
import com.bairuitech.anychat.videobanksdk.AnyChatClientSDK;
import com.bairuitech.anychat.videobanksdk.errormodel.BRResultMode;
import com.bairuitech.anychat.videobanksdk.routing.brcontext.AnyChatClientModel;
import com.google.gson.Gson;

public class AnyChatJSBridge {
    private static final String TAG = "AnyChatJSBridge";
    private Activity mActivity;
    private BRBusinessWebView mWebView = null;
    private OcrPictureTypeOnClick event;

    public AnyChatJSBridge(Activity mActivity, BRBusinessWebView mWebView) {
        this.mActivity = mActivity;
        this.mWebView = mWebView;
        this.mWebView.addJavascriptInterface(this, "AnyChatJSBridge");
        isMainThread();
    }

    public void setEvent(OcrPictureTypeOnClick event) {
        this.event = event;
    }

    private boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    @JavascriptInterface
    public void loginAgentAction(final String paramString) {
        //{"areaId":1,"queueId":1071,"businessCode":"1","loginAppId":"00A4B0B4-ECBF-5B4A-A65E-91FC22F59BD1","loginIp":"dev.bairuitech.cn","loginPort":"16004","userId":"1684890594741","userName":"韩呈祥"}
        Log.e(TAG, "loginAgentAction paramString:" + paramString);

        try {
            JSONObject modelJsonObject = new JSONObject(paramString);
            String areaId = modelJsonObject.optString("areaId", "");
            String queueId = modelJsonObject.optString("queueId", "");
            String businessCode = modelJsonObject.optString("businessCode", "");
            String loginAppId = modelJsonObject.optString("loginAppId", "");
            String loginIp = modelJsonObject.optString("loginIp", "");
            String loginPort = modelJsonObject.optString("loginPort", "");
            String userId = modelJsonObject.optString("userId", "");
            String userName = modelJsonObject.optString("userName", "");
            String custId = modelJsonObject.optString("custId", "");

            startVideoBankSDK(areaId,queueId, userName,userId,businessCode, custId,loginIp,loginPort,loginAppId);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * 开始组件调用
     *
     * @param areaId   营业厅id
     * @param queueId  队列id
     * @param userName 用户姓名
     * @param userID   登录音视频用户唯一ID
     * @param bussCode
     */
    private void startVideoBankSDK(String areaId, String queueId, String userName, String userID, String bussCode,
                                   String custId, String loginIp, String loginPort, String loginAppId) {

        final AnyChatClientModel transferModel = new AnyChatClientModel();
        /**********************登录参数设置*************************/
        transferModel.setNeedLogin(true);//是否需要登录(默认需要)
        transferModel.setUserName(userName);//登录音视频能力平台用户姓名(需要登录时必须)
        transferModel.setUserId(userID);//登录音视频能力平台用户唯一ID(需要登录时必须)
        transferModel.setLoginIp(loginIp);//接入音视频能力平台IP地址(需要登录时必须)
        transferModel.setLoginPort(loginPort);//接入音视频能力平台端口(需要登录时必须)
        transferModel.setLoginAppId(loginAppId);//接入音视频能力平台应用ID(需要登录时必须)

        transferModel.setAreaId(areaId);//接入音视频能力平台营业厅ID(需要智能排队时必须)
        transferModel.setQueueId(queueId);//接入音视频能力平台队列ID(需要智能排队时必须)

        /**********************其他参数设置*************************/
        //业务编码，音视频呼叫时坐席端将根据此值加载不同的话术信息、风险播报信息
        transferModel.setBusinessCode(bussCode);
        JSONObject extra = new JSONObject();
        extra.put("custId", custId);
        extra.put("from", "Android");
        transferModel.setBusinessExtra(extra.toString());
        Log.e("test","transferModel:"+transferModel.toJson());


        //业务开始调用接口
        this.mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AnyChatClientSDK.getInstance().start(mActivity, transferModel, new AnyChatClientEvent() {
                    @Override
                    public void onAnyChatClientError(BRResultMode result) {
//                        Toast.makeText(mActivity.getApplicationContext(), result.errCode + " : "
//                                + result.errMsg, Toast.LENGTH_SHORT).show();
                        Log.e("===startVideoBankSDK", " AiStatus:" + result.errCode + " AiMsg:" + result.errMsg);
                        Log.e(TAG, "new Gson().toJson(result): " + new Gson().toJson(result));
                        JSONObject resultJson = new JSONObject();
                        resultJson.put("errorCode", result.errCode);
                        resultJson.put("errorMsg", result.errMsg);
                        mWebView.loadUrl("javascript:receiveAgentResult('" + resultJson.toString() + "')");

                    }

                    @Override
                    public void onAnyChatClientCompleted(BRResultMode result) {
//                        Toast.makeText(mActivity.getApplicationContext(), result.errMsg, Toast.LENGTH_SHORT).show();

                        Log.e("===startVideoBankSDK", " AiStatus:" + result.errCode + " AiMsg:" + result.errMsg );
                        JSONObject resultJson = new JSONObject();
                        resultJson.put("errorCode", result.errCode);
                        resultJson.put("errorMsg", result.errMsg);
                        mWebView.loadUrl("javascript:receiveAgentResult('" + resultJson.toString() + "')");
                    }
                });
            }
        });
    }


    @JavascriptInterface
    public void loginAction(final String paramString) {
        Log.e(TAG, "loginAction paramString:" + paramString);
        if (isMainThread()) {
            Log.e(TAG, "isMainThread success");
        } else {
            Log.e(TAG, "isMainThread fail fail ");
        }

        this.mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                    BRAiSelfRecordSDK.getInstance().start(mActivity, paramString, new VideoRecordEvent() {
                        @Override
                        public void onSelfError(BusinessResult result) {
//                            Toast.makeText(mActivity.getApplicationContext(), result.getAiStatus() + " : "
//                                    + result.getAiMsg(), Toast.LENGTH_SHORT).show();
                            Log.e("===BusinessActivity", "AiSerialNo:" + result.getAiSerialNo()
                                    + " AiStatus:" + result.getAiStatus() + " AiMsg:" + result.getAiMsg());
                            if(result.getAiStatus()== CustomerErrorCode.AC_ERROR_TO_AGENT){
//                                Toast.makeText(mActivity.getApplicationContext(), "人工服务", Toast.LENGTH_SHORT).show();
                                Log.e("===startOpenAccount", "人工服务");
                            }
                            Log.e(TAG, "new Gson().toJson(result): "+new Gson().toJson(result));
                            mWebView.loadUrl("javascript:receiveResult('" + new Gson().toJson(result) + "')");
                        }

                        @Override
                        public void onSelfRecordCompleted(BusinessResult result) {
//                            Toast.makeText(mActivity.getApplicationContext(), result.getAiMsg(), Toast.LENGTH_SHORT).show();

                            Log.e("===startOpenAccount", "AiSerialNo:" + result.getAiSerialNo()
                                    + " AiStatus:" + result.getAiStatus() + " AiMsg:" + result.getAiMsg() + " videoFilePath:" + result.getVideoFilePath());

                            mWebView.loadUrl("javascript:receiveResult('" + new Gson().toJson(result) + "')");
                        }

                        @Override
                        public void onSelfFaceCaptureCompleted(BusinessResult businessResult) {

                        }
                    });

            }
        });
    }

    public void receiveOcrPictureInfo(String message){
        Log.e(TAG, "receiveOcrPictureInfo message:" + message);
        mWebView.loadUrl("javascript:receiveOcrPictureInfo('" + message + "')");
    }
    @JavascriptInterface
    public void selectOcrPicture(final String paramString) {
        Log.e(TAG, "selectOcrPicture paramString:" + paramString);
        JSONObject selectOcrPictureJsonObject = new JSONObject(paramString);
        String selectOcrPictureType  = selectOcrPictureJsonObject.getString("type");
        ///face 正面  back 反面
        if (selectOcrPictureType.equals("face")) {
            if (event != null) {
                event.onSelectImageFront();
            }
        } else if(selectOcrPictureType.equals("back")) {
            if (event != null) {
                event.onSelectImageBack();
            }
        } else if(selectOcrPictureType.equals("bankCard")) {
            if (event != null) {
                event.onSelectImageBankCard();
            }
        }
    }
    @JavascriptInterface
    public void back() {
        Log.e(TAG, "back:" );
        if (event != null) {
            event.back();
        }

    }

    public void release(){
        Log.e(TAG, "AnyChatJSBridge release()" );
        event = null;
        mActivity = null;
        mWebView.removeJavascriptInterface("AnyChatJSBridge");
        mWebView = null;

    }

    public interface OcrPictureTypeOnClick {
        void onSelectImageFront();
        void onSelectImageBack();
        void onSelectImageBankCard();
        void back();
    }


}