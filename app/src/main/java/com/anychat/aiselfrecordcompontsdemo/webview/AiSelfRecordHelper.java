package com.anychat.aiselfrecordcompontsdemo.webview;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.anychat.aiselfrecordcompontsdemo.R;
import com.anychat.aiselfrecordcompontsdemo.logic.ApiManager;
import com.anychat.aiselfrecordcompontsdemo.utils.EmptyUtils;
import com.anychat.aiselfrecordcompontsdemo.utils.SaveBusinessDialogInfo;
import com.anychat.aiselfrecordcompontsdemo.utils.SharedPreferencesUtils;
import com.anychat.aiselfrecordcompontsdemo.utils.UIUtils;
import com.anychat.aiselfrecordcompontsdemo.utils.net.OkHttpUtils;
import com.anychat.aiselfrecordsdk.component.BRAiSelfRecordSDK;
import com.anychat.aiselfrecordsdk.component.interf.VideoRecordEvent;
import com.anychat.aiselfrecordsdk.component.model.BusinessResult;
import com.anychat.aiselfrecordsdk.component.model.ComponentField;
import com.anychat.aiselfrecordsdk.component.model.CustomerErrorCode;
import com.anychat.aiselfrecordsdk.component.model.TargetModel;
import com.anychat.aiselfrecordsdk.config.BusinessDialogMessageType;
import com.anychat.aiselfrecordsdk.eventtrack.EventTrackManager;
import com.anychat.aiselfrecordsdk.util.OkHttpNetUtils;
import com.anychat.aiselfrecordsdk.util.SDKLogUtils;
import com.bairuitech.anychat.util.json.JSONArray;
import com.bairuitech.anychat.util.json.JSONObject;
import com.bairuitech.anychat.videobanksdk.AnyChatClientEvent;
import com.bairuitech.anychat.videobanksdk.AnyChatClientSDK;
import com.bairuitech.anychat.videobanksdk.errormodel.BRResultMode;
import com.bairuitech.anychat.videobanksdk.routing.brcontext.AnyChatClientModel;
import com.google.gson.Gson;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AiSelfRecordHelper {

    private Context context;

    private int selectModel = TargetModel.TargetModelDefault.targetModel;

    private JSONObject componentJsonObject;

    public AiSelfRecordHelper(Context context) {
        this.context = context;
    }

    private void checkInput(String custName, String qualityRuleJsonStr) {
        boolean isClientMode = true;


        componentJsonObject = new JSONObject();
        //AnyChat 连接地址
        componentJsonObject.put(ComponentField.LOGIN_IP, SharedPreferencesUtils.get(context, SharedPreferencesUtils.LOGIN_ANYCHAT_IP, ApiManager.TESTDATA.ANYCHAT_LOGIN_IP));
        componentJsonObject.put(ComponentField.LOGIN_PORT, SharedPreferencesUtils.get(context, SharedPreferencesUtils.LOGIN_ANYCHAT_PORT, ApiManager.TESTDATA.ANYCHAT_LOGIN_PORT));
        componentJsonObject.put(ComponentField.LOGIN_APP_ID, SharedPreferencesUtils.get(context, SharedPreferencesUtils.ANYCHAT_LOGIN_APPID, ApiManager.TESTDATA.ANYCHAT_APPID));//

        componentJsonObject.put(ComponentField.NICK_NAME, custName);//
//        componentJsonObject.put("custID", "22273627");//客户号

        //系统集成（必传）
//        componentJsonObject.put("busType", businessCodeValue);//双录业务类别//业务编码
//        componentJsonObject.put(ComponentField.BUS_TYPE_NAME, businessNameValue);//双录业务类别名称
//        componentJsonObject.put(ComponentField.BUS_SERIAL_NUMBER, thirdTradeNumber);//业务流水号
//        componentJsonObject.put("prodCode", productNumberValue);//产品编号
//        componentJsonObject.put(ComponentField.PROD_TYPE, productTypeValue);//产品类型
//        componentJsonObject.put("prodName", productNameValue);//产品名称
//        componentJsonObject.put("sysID", integratorCodeValue);//渠道编码
//        componentJsonObject.put("sysIDName", integratorNameValue);//渠道名称
//        String key = (String) spinnerTargetModeView.getSelectedItem();
        componentJsonObject.put(ComponentField.CUST_NAME, custName);//客户名称
        componentJsonObject.put(ComponentField.ITEM_CODE, SharedPreferencesUtils.get(context, SharedPreferencesUtils.ITEM_CODE));//itemCode
        //业务流程
        componentJsonObject.put(ComponentField.TARGET_MODEL, selectModel);
        //集成方法（true:业务集成 false:系统集成）
        componentJsonObject.put(ComponentField.PROCESS_BOOL, isClientMode);

        boolean digitalHumanIsOpen = false;
        if (digitalHumanIsOpen) {
            componentJsonObject.put(ComponentField.OPEN_DIGITAL_HUMAN, 1);//是否开启数字人
        } else {
            componentJsonObject.put(ComponentField.OPEN_DIGITAL_HUMAN, 0);//是否开启数字人
        }


        JSONArray aiConfig = new JSONArray();

        JSONObject json1 = new JSONObject();
        json1.put("configName", "paramFaceInFrame");
        json1.put("enable", 1);
        json1.put("configDec", "在框检测");
        JSONObject content1 = new JSONObject();
        content1.put("rate", "5");
        content1.put("faceNum", "1");
        content1.put("allowNoPass", "2");
        json1.put("configContent", content1);

        JSONObject json2 = new JSONObject();
        json2.put("configName", "paramFaceCompare");
        json2.put("enable", 1);
        json2.put("configDec", "人脸比对");
        JSONObject content2 = new JSONObject();
        content2.put("pass", "60");
        content2.put("allowNoPass", "1");
        json2.put("configContent", content2);

        JSONObject json3 = new JSONObject();
        json3.put("configName", "paramFaceCover");
        json3.put("enable", 1);
        json3.put("configDec", "人脸遮挡");
        JSONObject content3 = new JSONObject();
        content3.put("rate", "5");
        content3.put("allowNoPass", "9");
        json3.put("configContent", content3);

        JSONObject json4 = new JSONObject();
        json4.put("configName", "paramSilentLive");
        json4.put("enable", 1);
        json4.put("configDec", "静默活体");
        JSONObject content4 = new JSONObject();
        content4.put("rate", "5");
        content4.put("pass", "60");
        content4.put("allowNoPass", "9");
        json4.put("configContent", content4);

        JSONObject json5 = new JSONObject();
        json5.put("configName", "paramLightCheck");
        json5.put("enable", 1);
        json5.put("configDec", "光线检测");
        JSONObject content5 = new JSONObject();
        content5.put("allowDuration", "10");
        content5.put("highest", "80");
        content5.put("lowest", "10");
        content5.put("rate", "5");
        json5.put("configContent", content5);

        JSONObject json6 = new JSONObject();
        json6.put("configName", "paramNoiseCheck");
        json6.put("enable", 1);
        json6.put("configDec", "噪音检测");
        JSONObject content6 = new JSONObject();
        content6.put("allowDuration", "10");
        content6.put("pass", "80");
        content6.put("rate", "5");
        json6.put("configContent", content6);

        JSONObject json7 = new JSONObject();
        json7.put("configName", "paramFaceDistance");
        json7.put("enable", 1);
        json7.put("configDec", "人脸距离检测");
        JSONObject content7 = new JSONObject();
//        content7.put("allowDuration" , "60");
//        content7.put("rate" , "2");
        json7.put("configContent", content7);

        JSONObject json8 = new JSONObject();
        json8.put("configName", "paramComposeCheck");
        json8.put("enable", 1);
        json8.put("configDec", "合成检测");
        JSONObject content8 = new JSONObject();
        content8.put("allowNoPass", "10");
        content8.put("rate", "5");
        content8.put("pass", "60");
        json8.put("configContent", content8);

        JSONObject json9 = new JSONObject();
        json9.put("configName", "paramScriptConfig");
        json9.put("enable", 1);
        json9.put("configDec", "话术匹配");
        JSONObject content9 = new JSONObject();
        content9.put("allowNoPass", "3");
        json9.put("configContent", content9);

        aiConfig.put(json1);
        aiConfig.put(json2);
//        aiConfig.put(json3);
//        aiConfig.put(json4);
//        aiConfig.put(json5);
//        aiConfig.put(json6);
        aiConfig.put(json7);
//        aiConfig.put(json8);

        //体验码配置的
        String aiConfigStr = SharedPreferencesUtils.get(context, SharedPreferencesUtils.GET_AI_CONFIG, "");
//        JSONObject aiConfig = new JSONObject(aiConfigStr);
        componentJsonObject.put(ComponentField.AI_CONFIG, aiConfig);
        componentJsonObject.put(ComponentField.ONLINE_VERIFY, 1);
        componentJsonObject.put(ComponentField.SHOW_VIDEO_TAG, 1);


//        if (isClientMode) {
//
//            //第三方流水号
//            String thirdTradeNumber = String.valueOf(new Date().getTime());
//            String question = "请问您是本人吗？请回答是或否。";
//            String answer = "是,是的";
//            String readQuestion = "请您确认无误后，大声朗读以下内容：";
//            String readAnswer = "我已知晓证券市场风险，并已阅读且充分理解开户协议条款。";
//            String questionEnd = "您的开户环节已完毕，感谢您的配合和支持，再见。";
//
//            if (EmptyUtils.isNullOrEmpty(question)) {
//                UIUtils.makeToast(context, R.string.input_question, Toast.LENGTH_SHORT).show();
//                return;
//            }
//            if (EmptyUtils.isNullOrEmpty(answer)) {
//                UIUtils.makeToast(context, R.string.input_question_answer, Toast.LENGTH_SHORT).show();
//                return;
//            }
//            if (EmptyUtils.isNullOrEmpty(readQuestion)) {
//                UIUtils.makeToast(context, R.string.input_read_notice, Toast.LENGTH_SHORT).show();
//                return;
//            }
//            if (EmptyUtils.isNullOrEmpty(readAnswer)) {
//                UIUtils.makeToast(context, R.string.input_read_data, Toast.LENGTH_SHORT).show();
//                return;
//            }
//            if (EmptyUtils.isNullOrEmpty(questionEnd)) {
//                UIUtils.makeToast(context, R.string.input_end_data, Toast.LENGTH_SHORT).show();
//                return;
//            }
//            //业务集成 质检话术JSON数组(必传)
//            JSONObject jsonObject8 = new JSONObject();
//            jsonObject8.put("checkQuestion", "尊敬的投资者您好！没有只涨不跌的市场，也没有包赚不赔的投资。在您进入证券市场之前， 为了保护您的权益，下面我们将向您揭示客户账户开立须知及风险事项，并全程进行录音录像，请您仔细聆听并配合面对摄像镜头，谢谢！");
//            jsonObject8.put("type", 0);
//            JSONArray qualityRuleJsonArray = new JSONArray();
//            JSONObject jsonObject = new JSONObject();
////            jsonObject.put("checkQuestion", idCardFrontTip);
//            jsonObject.put("type", 5);
//            JSONObject jsonObject1 = new JSONObject();
////            jsonObject1.put("checkQuestion", idCardBackTip);
//            jsonObject1.put("type", 6);
//            //话术一（风险播报）
////            com.bairuitech.anychat.util.json.JSONObject jsonObject1 = new com.bairuitech.anychat.util.json.JSONObject();
////            jsonObject1.put("checkQuestion", "尊敬的投资者您好！没有只涨不跌的市场，也没有包赚不赔的投资。在您进入证券市场之前， 为了保护您的权益，下面我们将向您揭示客户账户开立须知及风险事项，并全程进行录音录像，请您仔细聆听并配合面对摄像镜头，谢谢！");
////            jsonObject1.put("type", 0);
//            //话术二（问答播报）
//            JSONObject jsonObject2 = new JSONObject();
//            jsonObject2.put("checkQuestion", question);
//            jsonObject2.put("expectAnswer", answer);
////            jsonObject2.put("answerWaitingTime", 3);//设置等待回答时间,单位秒
////            jsonObject2.put("answerTimeOutTime", 3);//设置等待结果超时时间,单位秒
//            jsonObject2.put("type", 1);
//            //话术三（朗读播报）
//            JSONObject jsonObject3 = new JSONObject();
//            jsonObject3.put("checkQuestion", readQuestion);
//            jsonObject3.put("expectAnswer", readAnswer);
//            jsonObject3.put("type", 2);
//            JSONArray expectAnswers = new JSONArray();
//            JSONArray answers1 = new JSONArray();
//            answers1.put("我");
//            answers1.put("窝");
//            JSONArray answers2 = new JSONArray();
//            answers2.put("知晓");
//            answers2.put("滞销");
//            JSONArray answers3 = new JSONArray();
//            answers3.put("证券市场");
//            answers3.put("政权时长");
//            JSONArray answers4 = new JSONArray();
//            answers4.put("风险");
//            answers4.put("奉献");
//            JSONArray answers5 = new JSONArray();
//            answers5.put("阅读");
//            answers5.put("月度");
//            JSONArray answers6 = new JSONArray();
//            answers6.put("充分理解");
//            answers6.put("重分李姐");
//            JSONArray answers7 = new JSONArray();
//            answers7.put("协议条款");
//            answers7.put("协议条款");
////            expectAnswers.put(answers1);
////            expectAnswers.put(answers2);
////            expectAnswers.put(answers3);
////            expectAnswers.put(answers4);
//            expectAnswers.put(answers5);
////            expectAnswers.put(answers6);
////            expectAnswers.put(answers7);
//            jsonObject3.put("keywordList", expectAnswers);
//
//            JSONArray errorAnswers = new JSONArray();
//            errorAnswers.put("不理解");
//            errorAnswers.put("没有");
//            errorAnswers.put("不知晓");
//            jsonObject3.put("unExpectAnswer", errorAnswers);
//
//            //话术四（结束话术）
//            JSONObject jsonObject4 = new JSONObject();
//            jsonObject4.put("checkQuestion", questionEnd);
//            jsonObject4.put("type", 0);
//            JSONObject jsonObject0 = new JSONObject();
//            jsonObject0.put("checkQuestion", "请问您是刘景城本人吗？请回答“是”或“否”");
//            jsonObject0.put("type", 1);
//            jsonObject0.put("expectAnswer", answer);
////            话术五
//            JSONObject jsonObject5 = new JSONObject();
////            jsonObject5.put("checkQuestion", fileDetectTip);
//            jsonObject5.put("type", 7);
//
//            //话术五（PPT）
//            JSONObject jsonObject6 = new JSONObject();
//            jsonObject6.put("fileId", "20170518");
//            jsonObject6.put("fileUrl", "http://vip.anychat.cn/irecord2/2.zip");
//            jsonObject6.put("fileMd5", "b349819bca1d1a4dc85df9a31829e936");
//            jsonObject6.put("fileType", "1");
//            jsonObject6.put("type", 3);
//
//            //话术六（MP4）
//            JSONObject jsonObject7 = new JSONObject();
//            jsonObject7.put("fileId", "20170514");
//            jsonObject7.put("fileUrl", "https://huaxin-demo-1252725607.cos.ap-guangzhou.myqcloud.com/chat/chat1.mp4");
//            jsonObject7.put("fileMd5", "b349819bca1d1a4dc85df9a31829e934");
//            jsonObject7.put("fileType", "2");
//            jsonObject7.put("type", 4);
////            qualityRuleJsownArray.put(jsonObject6);
////            qualityRuleJsonArray.put(jsonObject7);
////            qualityRuleJsonArray.put(jsonObject0);
//            qualityRuleJsonArray.put(jsonObject2);
//            qualityRuleJsonArray.put(jsonObject3);
//            qualityRuleJsonArray.put(jsonObject4);
//            String qualityRuleJsonStr = qualityRuleJsonArray.toString();
//
//            componentJsonObject.put("qualityRuleJsonStr", qualityRuleJsonStr);
//        }
        //服务端配置的话术
        componentJsonObject.put("qualityRuleJsonStr", qualityRuleJsonStr);
        //配置对话框时间
        int dialogTimeOutValue = 30;
        componentJsonObject.put("dialogTimeOut", dialogTimeOutValue);//对话框超时时间,单位秒

        String settingAlldetectDialogCongigJsonStr = SaveBusinessDialogInfo.getLatestDialogInfoData();
        if (settingAlldetectDialogCongigJsonStr.length() != 0) {
            JSONArray alldetectDialogJSONArray = new JSONArray(settingAlldetectDialogCongigJsonStr);
            int listLength = alldetectDialogJSONArray.length();
            for (int i = 0; i < listLength; ++i) {
                JSONObject object = (JSONObject) alldetectDialogJSONArray.get(i);
                String message = object.optString(BusinessDialogMessageType.BUSINESS_DIALOG_MESSAGE);
                String title = object.optString(BusinessDialogMessageType.BUSINESS_DIALOG_TITLE);
                String type = object.optString(BusinessDialogMessageType.BUSINESS_DIALOG_TYPE);
                boolean isShow = object.optBoolean(BusinessDialogMessageType.BUSINESS_DIALOG_IS_SHOW);
                int showStytle = object.optInt(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_STYLE);
                String toastMessage = object.optString(BusinessDialogMessageType.BUSINESS_DIALOG_TOAST_MESSAGE);
                int showMode = object.optInt(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_MODE);
                String showType = object.optString(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_TYPE);

                JSONObject jsonObjectDialogType = new JSONObject();
                jsonObjectDialogType.put("title", title);
                jsonObjectDialogType.put("message", message);
                jsonObjectDialogType.put("isShow", isShow);
                if (!type.equals(BusinessDialogMessageType.RECORDING_SESSION_KEEP) && !type.equals(BusinessDialogMessageType.RECORDING_LINK_CLOSE) && !type.equals(BusinessDialogMessageType.COMPLETE_RECORD_LINK_CLOSE)) {
                    jsonObjectDialogType.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_TYPE, showType);
                }
                if (type.equals(BusinessDialogMessageType.FACE_DETECT_OUT)) {
                    jsonObjectDialogType.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_MODE, showMode);
                    jsonObjectDialogType.put(BusinessDialogMessageType.BUSINESS_DIALOG_TOAST_MESSAGE, toastMessage);
                }

            }
        }

        Log.e("BusinessActivity", "model" + componentJsonObject.toString());

    }


    /**
     * 活体检测 -单录
     *
     * @param openAccountId
     * @param khToken
     * @param custName
     * @param targetModel
     * @param callBack
     */
    public void start(String openAccountId, String khToken, String custName, int targetModel, CallBack callBack) {

        switch (targetModel) {
            case 1:
            case 2:
                selectModel = TargetModel.TargetModelFc.targetModel;

                startSingleRecordWithFace(openAccountId, khToken, targetModel, custName, callBack);

                break;
            case 3:
                selectModel = TargetModel.TargetModelRecord.targetModel;
                startVideoBankSDK("1", "1071", custName, openAccountId, "1");

                break;
            default:
                break;
        }

    }

    private void startSingleRecordWithFace(String openAccountId, String khToken, int targetModel, String custName, CallBack callBack) {

        getQualityRuleJson(khToken, targetModel, new QualityRuleCallBack() {

            @Override
            public void onSuccess(String json) {

                checkInput(custName, json);

                SDKLogUtils.log("model", componentJsonObject.toString());
                EventTrackManager.getInstance().startEventTrac();
                //AI自助双录组件开始调用接口
                BRAiSelfRecordSDK.getInstance().start(context, componentJsonObject.toString(), new VideoRecordEvent() {
                    @Override
                    public void onSelfError(BusinessResult result) {
//                Toast.makeText(getApplicationContext(), result.getAiStatus() + " : "
//                        + result.getAiMsg(), Toast.LENGTH_SHORT).show();
                        callBack.onSelfError(result);
                        Log.e("testLog", "AiSerialNo:" + result.getAiSerialNo() + " AiStatus:" + result.getAiStatus() + " AiMsg:" + result.getAiMsg());
                        if (result.getAiStatus() == CustomerErrorCode.AC_ERROR_TO_AGENT) {
                            Toast.makeText(context, "人工服务", Toast.LENGTH_SHORT).show();
                            Log.e("testLog", "转人工服务");
//                            String url = "https://192.168.15.168:8090/gatewayService/iaccount/AnyChatCallback/getAreaAndQueue";
//                            JSONObject param = new JSONObject();
//                            param.put("appId", SharedPreferencesUtils.get(context, SharedPreferencesUtils.ANYCHAT_LOGIN_APPID));
//                            OkHttpNetUtils.getInstance().post(url, param.toString(), new OkHttpNetUtils.BaseCallback<String>() {
//
//                                @Override
//                                public void onSuccess(String s) {
//                                    Log.e("===BusinessActivity", "onSuccess:" + s);
//                                    try {
//                                        JSONObject jsonObject = new JSONObject(s);
//                                        if (jsonObject.optInt("errorcode", -1) == 0) {
//
//                                            JSONObject content = jsonObject.optJSONObject("content");
//                                            int areaId = content.optInt("areaId");
//                                            int queueId = content.optInt("queueId");
//                                            String areaName = content.optString("areaName");
//                                            String queueName = content.optString("queueName");
//
////                                    toAgent(areaId, queueId, areaName, queueName);
//                                        }
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Exception e) {
//
//                                }
//                            });
                        }
                    }

                    @Override
                    public void onSelfRecordCompleted(BusinessResult result) {
                        callBack.onSelfRecordCompleted(result);
//                boolean traceDataIsOpen = SharedPreferencesUtils.get(context.getApplicationContext(), SharedPreferencesUtils.GET_IS_OPEN_TRACE_DATA_CONFIG, false);
//
//                if (traceDataIsOpen) {
//                    Toast.makeText(context, "办理完成",Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(context, "下一步",Toast.LENGTH_SHORT).show();
//                }

                        String tagsFilePath = result.getTagsFilePath();
                        Log.e("testLog", "AiSerialNo:" + result.getAiSerialNo() + " AiStatus:" + result.getAiStatus() + " AiMsg:" + result.getAiMsg() + " videoFilePath:" + result.getVideoFilePath());
                    }

                    @Override
                    public void onSelfFaceCaptureCompleted(BusinessResult result) {
                        Log.e("testLog", "AiSerialNo:" + result.getAiSerialNo() + " AiStatus:" + result.getAiStatus() + " AiMsg:" + result.getAiMsg() + " facePicture:" + result.getFacePicture());
                        callBack.onSelfFaceCaptureCompleted(result);
//                Bitmap bitmap = Base64BitmapUtil.base64ToBitmap(result.getFacePicture());
                        checkIDCardSecurityWithFacePic(result, openAccountId, khToken);
//                ((Activity)context).getWindow().getDecorView().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        BRAiSelfRecordSDK.getInstance().notifyOnlineVerifyResult(true, "");
//                    }
//                }, 5000);

                    }
                });
            }

            @Override
            public void onFailed(String errorMsg) {
                Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();

            }
        });


    }

    /**
     * 公安校验
     *
     * @param result
     * @param openAccountId
     * @param khToken
     */
    private static void checkIDCardSecurityWithFacePic(BusinessResult result, String openAccountId, String khToken) {
        //公安联网校验
        String url = "http://192.168.5.127:9500/kh/api/openAccount/faceVerify";
        JSONObject param = new JSONObject();
        param.put("image", result.getFacePicture());
        param.put("openAccountId", openAccountId);
        Map<String, String> headMap = new HashMap<>();
        headMap.put("khToken", khToken);
        OkHttpUtils.getInstance().post(url, headMap, param.toString(), new OkHttpUtils.BaseCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Log.e("testLog", "onSuccess:" + s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    if (jsonObject.optBoolean("success")) {
                        BRAiSelfRecordSDK.getInstance().notifyOnlineVerifyResult(true, "");
                    } else {
                        BRAiSelfRecordSDK.getInstance().notifyOnlineVerifyResult(false, "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("testLog", "onFailure:" + e.getMessage());
                BRAiSelfRecordSDK.getInstance().notifyOnlineVerifyResult(false, "");
            }
        });
    }

    private static void getQualityRuleJson(String khToken, int targetModel, QualityRuleCallBack callBack) {
        //公安联网校验
        String url = "http://192.168.5.127:9500/kh/api/anychat/scriptList";
        Map<String, String> headMap = new HashMap<>();
        headMap.put("khToken", khToken);
        JSONObject param = new JSONObject();
        param.put("scene", targetModel);
        OkHttpUtils.getInstance().post(url, headMap, param.toString(), new OkHttpUtils.BaseCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Log.e("testLog", "onSuccess:" + s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    if (jsonObject.optBoolean("success")) {
                        callBack.onSuccess(jsonObject.getJSONArray("data").toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("testLog", "onFailure:" + e.getMessage());
                callBack.onFailed(e.getMessage());
            }
        });
    }


    /**
     * 双录
     *
     * @param areaId
     * @param queueId
     * @param userName
     * @param userID
     * @param bussCode
     */
    private void startVideoBankSDK(String areaId, String queueId, String userName, String userID, String bussCode) {

        final AnyChatClientModel transferModel = new AnyChatClientModel();
        /**********************登录参数设置*************************/
        transferModel.setNeedLogin(true);//是否需要登录(默认需要)
        transferModel.setUserName(userName);//登录音视频能力平台用户姓名(需要登录时必须)
        transferModel.setUserId(userID);//登录音视频能力平台用户唯一ID(需要登录时必须)
        transferModel.setLoginIp(ApiManager.TESTDATA.ANYCHAT_LOGIN_IP);//接入音视频能力平台IP地址(需要登录时必须)
        transferModel.setLoginPort(ApiManager.TESTDATA.ANYCHAT_LOGIN_PORT);//接入音视频能力平台端口(需要登录时必须)
        transferModel.setLoginAppId(ApiManager.TESTDATA.ANYCHAT_APPID);//接入音视频能力平台应用ID(需要登录时必须)

        transferModel.setAreaId(areaId);//接入音视频能力平台营业厅ID(需要智能排队时必须)
        transferModel.setQueueId(queueId);//接入音视频能力平台队列ID(需要智能排队时必须)

        /**********************其他参数设置*************************/
        //业务编码，音视频呼叫时坐席端将根据此值加载不同的话术信息、风险播报信息
        transferModel.setBusinessCode(bussCode);
        JSONObject extra = new JSONObject();
        extra.put("custId", userID);
        extra.put("from", "Android");
        transferModel.setBusinessExtra(extra.toString());
        Log.e("test", "transferModel:" + transferModel.toJson());


        //业务开始调用接口
        AnyChatClientSDK.getInstance().start(context, transferModel, new AnyChatClientEvent() {
            @Override
            public void onAnyChatClientError(BRResultMode result) {
                Toast.makeText(context, result.errCode + " : "
                                + result.errMsg, Toast.LENGTH_SHORT).show();
                Log.e("===startVideoBankSDK", " AiStatus:" + result.errCode + " AiMsg:" + result.errMsg);
                Log.e("===startVideoBankSDK", "new Gson().toJson(result): " + new Gson().toJson(result));
            }

            @Override
            public void onAnyChatClientCompleted(BRResultMode result) {
//                        Toast.makeText(mActivity.getApplicationContext(), result.errMsg, Toast.LENGTH_SHORT).show();

                Log.e("===startVideoBankSDK", " AiStatus:" + result.errCode + " AiMsg:" + result.errMsg);
            }
        });
    }

    interface CallBack {

        default void onSelfError(BusinessResult result) {
        }

        default void onSelfRecordCompleted(BusinessResult result) {
        }

        default void onSelfFaceCaptureCompleted(BusinessResult result) {
        }
    }

    interface QualityRuleCallBack {

        void onSuccess(String json);

        void onFailed(String errMsg);

    }

}
