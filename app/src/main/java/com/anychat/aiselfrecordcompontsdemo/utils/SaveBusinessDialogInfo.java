package com.anychat.aiselfrecordcompontsdemo.utils;

import com.anychat.aiselfrecordsdk.config.AnswerDialogType;
import com.anychat.aiselfrecordsdk.config.BusinessDialogBtnEventType;
import com.anychat.aiselfrecordsdk.config.BusinessDialogBtnNum;
import com.anychat.aiselfrecordsdk.config.BusinessDialogMessageType;
import com.anychat.aiselfrecordsdk.config.BusinessDialogShowModel;
import com.anychat.aiselfrecordsdk.config.FaceCompareDialogType;
import com.anychat.aiselfrecordsdk.config.FaceDetectDialogType;
import com.anychat.aiselfrecordsdk.config.NetWorkStateDialogType;
import com.anychat.aiselfrecordsdk.model.business.BusinessResponseField;
import com.bairuitech.anychat.util.json.JSONArray;
import com.bairuitech.anychat.util.json.JSONObject;

public class SaveBusinessDialogInfo {

    public static FaceCompareDialogType faceCompareDialogType = new FaceCompareDialogType();
    public static AnswerDialogType answerDialogType = new AnswerDialogType();
    public static FaceDetectDialogType faceDetectDialogType = new FaceDetectDialogType();
    public static NetWorkStateDialogType netWorkStateDialogType = new NetWorkStateDialogType();


    public static void getDialogInfoFromString(String dialogConfigValue){
        if(!dialogConfigValue.trim().equals("")){
            JSONArray alldetectDialogJSONArray = new JSONArray(dialogConfigValue);
            int listLength = alldetectDialogJSONArray.length();
            for (int i = 0; i < listLength; ++i) {
                JSONObject object = (JSONObject) alldetectDialogJSONArray.get(i);
                String message = object.optString(BusinessDialogMessageType.BUSINESS_DIALOG_MESSAGE);
                String title = object.optString(BusinessDialogMessageType.BUSINESS_DIALOG_TITLE);
                String type = object.optString(BusinessDialogMessageType.BUSINESS_DIALOG_TYPE);
                boolean isShow = object.optBoolean(BusinessDialogMessageType.BUSINESS_DIALOG_IS_SHOW);
                int showStytle = object.optInt(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_STYLE);
                String showType = object.optString(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_TYPE);
                String[] showTypeStrArray = showType.split(BusinessResponseField.EXPECT_ANSWER_SEPARATOR);
                if (type.equals(BusinessDialogMessageType.FACE_DETECT_OUT)) {

                    faceDetectDialogType.setFaceDetectOutDefaultBtnNum(BusinessDialogBtnNum.getBusinessDialogBtnNum(showStytle));
                    faceDetectDialogType.setFaceDetectOutDefaultLeftBtnType(BusinessDialogBtnEventType.getBusinessDialogBtnEventType(Integer.valueOf(showTypeStrArray[0])));
                    if (showStytle == BusinessDialogBtnNum.DoubleBtn.ordinal()) {
                        faceDetectDialogType.setFaceDetectOutDefaultRightBtnType(BusinessDialogBtnEventType.getBusinessDialogBtnEventType(Integer.valueOf(showTypeStrArray[1])));
                    }
                    faceDetectDialogType.setFaceDetectOutDefaultShow(isShow);
                    faceDetectDialogType.setFaceDetectOutDefaultTitle(title);
                    faceDetectDialogType.setFaceDetectOutDefaultMessage(message);

                }
                if (type.equals(BusinessDialogMessageType.FACE_DETECT_MORE_FACE)) {

                    faceDetectDialogType.setFaceDetectMoreFaceDefaultBtnNum(BusinessDialogBtnNum.getBusinessDialogBtnNum(showStytle));
                    faceDetectDialogType.setFaceDetectMoreFaceDefaultLeftBtnType(BusinessDialogBtnEventType.getBusinessDialogBtnEventType(Integer.valueOf(showTypeStrArray[0])));
                    if (showStytle == BusinessDialogBtnNum.DoubleBtn.ordinal()) {
                        faceDetectDialogType.setFaceDetectMoreFaceDefaultRightBtnType(BusinessDialogBtnEventType.getBusinessDialogBtnEventType(Integer.valueOf(showTypeStrArray[1])));
                    }
                    faceDetectDialogType.setFaceDetectMoreFaceDefaultShow(isShow);
                    faceDetectDialogType.setFaceDetectMoreFaceDefaultTitle(title);
                    faceDetectDialogType.setFaceDetectMoreFaceDefaultMessage(message);
                }
                if (type.equals(BusinessDialogMessageType.FACE_DETECT_ERROR)) {

                    faceDetectDialogType.setFaceDetectErrorDefaultBtnNum(BusinessDialogBtnNum.getBusinessDialogBtnNum(showStytle));
                    faceDetectDialogType.setFaceDetectErrorDefaultLeftBtnType(BusinessDialogBtnEventType.getBusinessDialogBtnEventType(Integer.valueOf(showTypeStrArray[0])));
                    if (showStytle == BusinessDialogBtnNum.DoubleBtn.ordinal()) {
                        faceDetectDialogType.setFaceDetectErrorDefaultRightBtnType(BusinessDialogBtnEventType.getBusinessDialogBtnEventType(Integer.valueOf(showTypeStrArray[1])));
                    }
                    faceDetectDialogType.setFaceDetectErrorDefaultShow(isShow);
                    faceDetectDialogType.setFaceDetectErrorDefaultTitle(title);
                    faceDetectDialogType.setFaceDetectErrorDefaultMessage(message);
                }
                if (type.equals(BusinessDialogMessageType.FACE_DETECT_OUT_MAX_COUNT)) {

                    faceDetectDialogType.setFaceDetectOutMaxCountDefaultBtnNum(BusinessDialogBtnNum.getBusinessDialogBtnNum(showStytle));
                    faceDetectDialogType.setFaceDetectOutMaxCountDefaultLeftBtnType(BusinessDialogBtnEventType.getBusinessDialogBtnEventType(Integer.valueOf(showTypeStrArray[0])));
                    if (showStytle == BusinessDialogBtnNum.DoubleBtn.ordinal()) {
                        faceDetectDialogType.setFaceDetectOutMaxCountDefaultRightBtnType(BusinessDialogBtnEventType.getBusinessDialogBtnEventType(Integer.valueOf(showTypeStrArray[1])));
                    }
                    faceDetectDialogType.setFaceDetectOutMaxCountDefaultShow(isShow);
                    faceDetectDialogType.setFaceDetectOutMaxCountDefaultTitle(title);
                    faceDetectDialogType.setFaceDetectOutMaxCountDefaultMessage(message);
                }
                if (type.equals(BusinessDialogMessageType.FACE_COMPARE_NO_PASS)) {

                    faceCompareDialogType.setFaceCompareNoPassDefaultBtnNum(BusinessDialogBtnNum.getBusinessDialogBtnNum(showStytle));
                    faceCompareDialogType.setFaceCompareNoPassDefaultLeftBtnType(BusinessDialogBtnEventType.getBusinessDialogBtnEventType(Integer.valueOf(showTypeStrArray[0])));
                    if (showStytle == BusinessDialogBtnNum.DoubleBtn.ordinal()) {
                        faceCompareDialogType.setFaceCompareNoPassDefaultRightBtnType(BusinessDialogBtnEventType.getBusinessDialogBtnEventType(Integer.valueOf(showTypeStrArray[1])));
                    }
                    faceCompareDialogType.setFaceCompareNoPassDefaultShow(isShow);
                    faceCompareDialogType.setFaceCompareNoPassDefaultTitle(title);
                    faceCompareDialogType.setFaceCompareNoPassDefaultMessage(message);
                }
                if (type.equals(BusinessDialogMessageType.FACE_COMPARE_ERROR)) {

                    faceCompareDialogType.setFaceCompareErrorDefaultBtnNum(BusinessDialogBtnNum.getBusinessDialogBtnNum(showStytle));
                    faceCompareDialogType.setFaceCompareErrorDefaultLeftBtnType(BusinessDialogBtnEventType.getBusinessDialogBtnEventType(Integer.valueOf(showTypeStrArray[0])));
                    if(showStytle == BusinessDialogBtnNum.DoubleBtn.ordinal()) {
                        faceCompareDialogType.setFaceCompareErrorDefaultRightBtnType(BusinessDialogBtnEventType.getBusinessDialogBtnEventType(Integer.valueOf(showTypeStrArray[1])));
                    }
                    faceCompareDialogType.setFaceCompareErrorDefaultShow(isShow);
                    faceCompareDialogType.setFaceCompareErrorDefaultTitle(title);
                    faceCompareDialogType.setFaceCompareErrorDefaultMessage(message);
                }
                if (type.equals(BusinessDialogMessageType.ANSWER_NO_PASS)) {
                    answerDialogType.setAnswerNoPassDefaultBtnNum(BusinessDialogBtnNum.getBusinessDialogBtnNum(showStytle));
                    answerDialogType.setAnswerNoPassDefaultLeftBtnType(BusinessDialogBtnEventType.getBusinessDialogBtnEventType(Integer.valueOf(showTypeStrArray[0])));
                    if(showStytle == BusinessDialogBtnNum.DoubleBtn.ordinal()) {
                        answerDialogType.setAnswerNoPassDefaultRightBtnType(BusinessDialogBtnEventType.getBusinessDialogBtnEventType(Integer.valueOf(showTypeStrArray[1])));
                    }
                    answerDialogType.setAnswerNoPassDefaultShow(isShow);
                    answerDialogType.setAnswerNoPassDefaultTitle(title);
                    answerDialogType.setAnswerNoPassDefaultMessage(message);
                }
                if (type.equals(BusinessDialogMessageType.ANSWER_TIMEOUT)) {

                    answerDialogType.setAnswerTimeoutDefaultBtnNum(BusinessDialogBtnNum.getBusinessDialogBtnNum(showStytle));
                    answerDialogType.setAnswerTimeoutDefaultLeftBtnType(BusinessDialogBtnEventType.getBusinessDialogBtnEventType(Integer.valueOf(showTypeStrArray[0])));
                    if(showStytle == BusinessDialogBtnNum.DoubleBtn.ordinal()) {
                        answerDialogType.setAnswerTimeoutDefaultRightBtnType(BusinessDialogBtnEventType.getBusinessDialogBtnEventType(Integer.valueOf(showTypeStrArray[1])));
                    }
                    answerDialogType.setAnswerTimeoutDefaultShow(isShow);
                    answerDialogType.setAnswerTimeoutDefaultTitle(title);
                    answerDialogType.setAnswerTimeoutDefaultMessage(message);
                }
                if (type.equals(BusinessDialogMessageType.RECORDING_SESSION_KEEP)) {

                    netWorkStateDialogType.setRecordingSessionKeepDefaultTitle(title);
                    netWorkStateDialogType.setRecordingSessionKeepDefaultMessage(message);
                }
                if (type.equals(BusinessDialogMessageType.RECORDING_LINK_CLOSE)) {

                    netWorkStateDialogType.setRecordingLinkCloseDefaultTitle(title);
                    netWorkStateDialogType.setRecordingLinkCloseDefaultMessage(message);
                }
                if (type.equals(BusinessDialogMessageType.COMPLETE_RECORD_LINK_CLOSE)) {

                    netWorkStateDialogType.setCompleteRecordLinkCloseDefaultTitle(title);
                    netWorkStateDialogType.setCompleteRecordLinkCloseDefaultMessage(message);
                }
            }
        }
    }
    public static String getLatestNetWorkDialogInfoData(){
        NetWorkStateDialogType netWorkStateDialogType = SaveBusinessDialogInfo.netWorkStateDialogType;

        JSONArray alldetectDialogCongigArray = new JSONArray();
        com.bairuitech.anychat.util.json.JSONObject recordingSessionKeepJsonObject = new com.bairuitech.anychat.util.json.JSONObject();
        recordingSessionKeepJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TYPE,BusinessDialogMessageType.RECORDING_SESSION_KEEP);
        recordingSessionKeepJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TITLE, netWorkStateDialogType.getRecordingSessionKeepDefaultTitle());
        recordingSessionKeepJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_MESSAGE, netWorkStateDialogType.getRecordingSessionKeepDefaultMessage());

        com.bairuitech.anychat.util.json.JSONObject recordingLinkCloseJsonObject = new com.bairuitech.anychat.util.json.JSONObject();
        recordingLinkCloseJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TYPE,BusinessDialogMessageType.RECORDING_LINK_CLOSE);
        recordingLinkCloseJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TITLE, netWorkStateDialogType.getRecordingLinkCloseDefaultTitle());
        recordingLinkCloseJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_MESSAGE, netWorkStateDialogType.getRecordingLinkCloseDefaultMessage());

        com.bairuitech.anychat.util.json.JSONObject completeRecordLinkCloseJsonObject = new com.bairuitech.anychat.util.json.JSONObject();
        completeRecordLinkCloseJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TYPE,BusinessDialogMessageType.COMPLETE_RECORD_LINK_CLOSE);
        completeRecordLinkCloseJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TITLE, netWorkStateDialogType.getCompleteRecordLinkCloseDefaultTitle());
        completeRecordLinkCloseJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_MESSAGE, netWorkStateDialogType.getCompleteRecordLinkCloseDefaultMessage());

        alldetectDialogCongigArray.put(recordingSessionKeepJsonObject);
        alldetectDialogCongigArray.put(recordingLinkCloseJsonObject);
        alldetectDialogCongigArray.put(completeRecordLinkCloseJsonObject);
        String dialogCongigJsonStr = alldetectDialogCongigArray.toString();
        return dialogCongigJsonStr;

    }
    public static String getLatestDialogInfoData(){

        FaceDetectDialogType faceDetectDialogType = SaveBusinessDialogInfo.faceDetectDialogType;
        FaceCompareDialogType faceCompareDialogType = SaveBusinessDialogInfo.faceCompareDialogType;
        AnswerDialogType answerDialogType = SaveBusinessDialogInfo.answerDialogType;
        NetWorkStateDialogType netWorkStateDialogType = SaveBusinessDialogInfo.netWorkStateDialogType;

        JSONArray alldetectDialogCongigArray = new JSONArray();

        com.bairuitech.anychat.util.json.JSONObject faceDetectOutJsonObject = new com.bairuitech.anychat.util.json.JSONObject();
        faceDetectOutJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TYPE,BusinessDialogMessageType.FACE_DETECT_OUT);
        faceDetectOutJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TITLE, faceDetectDialogType.getFaceDetectOutDefaultTitle());
        faceDetectOutJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_MESSAGE, faceDetectDialogType.getFaceDetectOutDefaultMessage());
        faceDetectOutJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_IS_SHOW, faceDetectDialogType.isFaceDetectOutDefaultShow());
        faceDetectOutJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_STYLE, faceDetectDialogType.getFaceDetectOutDefaultBtnNum().ordinal());
        if (faceDetectDialogType.getFaceDetectOutDefaultBtnNum() == BusinessDialogBtnNum.DoubleBtn) {
            faceDetectOutJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_TYPE,faceDetectDialogType.getFaceDetectOutDefaultLeftBtnType().ordinal() + "," +faceDetectDialogType.getFaceDetectOutDefaultRightBtnType().ordinal());
        }else {
            faceDetectOutJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_TYPE,faceDetectDialogType.getFaceDetectOutDefaultLeftBtnType().ordinal() + "");
        }
        faceDetectOutJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_MODE,faceDetectDialogType.getFaceDetectOutDefaultShowModel().ordinal());
        if (faceDetectDialogType.getFaceDetectOutDefaultShowModel() == BusinessDialogShowModel.ShowToast) {
            faceDetectOutJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TOAST_MESSAGE,faceDetectDialogType.getFaceDetectOutDefaultToastMessage());
        }


        com.bairuitech.anychat.util.json.JSONObject faceDetectMoreFaceJsonObject = new com.bairuitech.anychat.util.json.JSONObject();
        faceDetectMoreFaceJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TYPE,BusinessDialogMessageType.FACE_DETECT_MORE_FACE);
        faceDetectMoreFaceJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TITLE, faceDetectDialogType.getFaceDetectMoreFaceDefaultTitle());
        faceDetectMoreFaceJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_MESSAGE, faceDetectDialogType.getFaceDetectMoreFaceDefaultMessage());
        faceDetectMoreFaceJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_IS_SHOW, faceDetectDialogType.isFaceDetectMoreFaceDefaultShow());
        faceDetectMoreFaceJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_STYLE, faceDetectDialogType.getFaceDetectMoreFaceDefaultBtnNum().ordinal());
        if (faceDetectDialogType.getFaceDetectMoreFaceDefaultBtnNum() == BusinessDialogBtnNum.DoubleBtn) {
            faceDetectMoreFaceJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_TYPE,faceDetectDialogType.getFaceDetectMoreFaceDefaultLeftBtnType().ordinal() + "," +faceDetectDialogType.getFaceDetectMoreFaceDefaultRightBtnType());
        }else {
            faceDetectMoreFaceJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_TYPE,faceDetectDialogType.getFaceDetectMoreFaceDefaultLeftBtnType().ordinal() + "" );
        }

        com.bairuitech.anychat.util.json.JSONObject faceDetectErrorJsonObject = new com.bairuitech.anychat.util.json.JSONObject();
        faceDetectErrorJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TYPE,BusinessDialogMessageType.FACE_DETECT_ERROR);
        faceDetectErrorJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TITLE, faceDetectDialogType.getFaceDetectErrorDefaultTitle());
        faceDetectErrorJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_MESSAGE, faceDetectDialogType.getFaceDetectErrorDefaultMessage());
        faceDetectErrorJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_IS_SHOW, faceDetectDialogType.isFaceDetectErrorDefaultShow());
        faceDetectErrorJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_STYLE, faceDetectDialogType.getFaceDetectErrorDefaultBtnNum().ordinal());
        if (faceDetectDialogType.getFaceDetectErrorDefaultBtnNum() == BusinessDialogBtnNum.DoubleBtn) {
            faceDetectErrorJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_TYPE,faceDetectDialogType.getFaceDetectErrorDefaultLeftBtnType().ordinal() + "," +faceDetectDialogType.getFaceDetectErrorDefaultRightBtnType().ordinal());
        }else {
            faceDetectErrorJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_TYPE,faceDetectDialogType.getFaceDetectErrorDefaultLeftBtnType().ordinal() + "");
        }

        com.bairuitech.anychat.util.json.JSONObject faceDetectOutMaxCountJsonObject = new com.bairuitech.anychat.util.json.JSONObject();
        faceDetectOutMaxCountJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TYPE,BusinessDialogMessageType.FACE_DETECT_OUT_MAX_COUNT);
        faceDetectOutMaxCountJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TITLE, faceDetectDialogType.getFaceDetectOutMaxCountDefaultTitle());
        faceDetectOutMaxCountJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_MESSAGE, faceDetectDialogType.getFaceDetectOutMaxCountDefaultMessage());
        faceDetectOutMaxCountJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_IS_SHOW, faceDetectDialogType.isFaceDetectOutMaxCountDefaultShow());
        faceDetectOutMaxCountJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_STYLE, faceDetectDialogType.getFaceDetectOutMaxCountDefaultBtnNum().ordinal());
        if (faceDetectDialogType.getFaceDetectOutMaxCountDefaultBtnNum() == BusinessDialogBtnNum.DoubleBtn) {
            faceDetectOutMaxCountJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_TYPE,faceDetectDialogType.getFaceDetectOutMaxCountDefaultLeftBtnType().ordinal() + "," +faceDetectDialogType.getFaceDetectOutMaxCountDefaultRightBtnType().ordinal());
        }else {
            faceDetectOutMaxCountJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_TYPE,faceDetectDialogType.getFaceDetectOutMaxCountDefaultLeftBtnType().ordinal() + "");
        }

        com.bairuitech.anychat.util.json.JSONObject faceCompareNoPassJsonObject = new com.bairuitech.anychat.util.json.JSONObject();
        faceCompareNoPassJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TYPE,BusinessDialogMessageType.FACE_COMPARE_NO_PASS);
        faceCompareNoPassJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TITLE, faceCompareDialogType.getFaceCompareNoPassDefaultTitle());
        faceCompareNoPassJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_MESSAGE, faceCompareDialogType.getFaceCompareNoPassDefaultMessage());
        faceCompareNoPassJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_IS_SHOW, faceCompareDialogType.isFaceCompareNoPassDefaultShow());
        faceCompareNoPassJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_STYLE, faceCompareDialogType.getFaceCompareNoPassDefaultBtnNum().ordinal());
        if (faceCompareDialogType.getFaceCompareNoPassDefaultBtnNum() == BusinessDialogBtnNum.DoubleBtn) {
            faceCompareNoPassJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_TYPE,faceCompareDialogType.getFaceCompareNoPassDefaultLeftBtnType().ordinal() + "," +faceCompareDialogType.getFaceCompareNoPassDefaultRightBtnType().ordinal());
        }else {
            faceCompareNoPassJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_TYPE,faceCompareDialogType.getFaceCompareNoPassDefaultLeftBtnType().ordinal() + "");
        }

        com.bairuitech.anychat.util.json.JSONObject faceCompareErrorJsonObject = new com.bairuitech.anychat.util.json.JSONObject();
        faceCompareErrorJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TYPE,BusinessDialogMessageType.FACE_COMPARE_ERROR);
        faceCompareErrorJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TITLE, faceCompareDialogType.getFaceCompareErrorDefaultTitle());
        faceCompareErrorJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_MESSAGE, faceCompareDialogType.getFaceCompareErrorDefaultMessage());
        faceCompareErrorJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_IS_SHOW, faceCompareDialogType.isFaceCompareErrorDefaultShow());
        faceCompareErrorJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_STYLE, faceCompareDialogType.getFaceCompareErrorDefaultBtnNum().ordinal());
        if (faceCompareDialogType.getFaceCompareErrorDefaultBtnNum() == BusinessDialogBtnNum.DoubleBtn) {
            faceCompareErrorJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_TYPE,faceCompareDialogType.getFaceCompareErrorDefaultLeftBtnType().ordinal() + "," +faceCompareDialogType.getFaceCompareErrorDefaultRightBtnType().ordinal());
        }else {
            faceCompareErrorJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_TYPE,faceCompareDialogType.getFaceCompareErrorDefaultLeftBtnType().ordinal() + "");
        }

        com.bairuitech.anychat.util.json.JSONObject answerNoPassJsonObject = new com.bairuitech.anychat.util.json.JSONObject();
        answerNoPassJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TYPE,BusinessDialogMessageType.ANSWER_NO_PASS);
        answerNoPassJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TITLE, answerDialogType.getAnswerNoPassDefaultTitle());
        answerNoPassJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_MESSAGE, answerDialogType.getAnswerNoPassDefaultMessage());
        answerNoPassJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_IS_SHOW, answerDialogType.isAnswerNoPassDefaultShow());
        answerNoPassJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_STYLE, answerDialogType.getAnswerNoPassDefaultBtnNum().ordinal());
        if (answerDialogType.getAnswerNoPassDefaultBtnNum() == BusinessDialogBtnNum.DoubleBtn) {
            answerNoPassJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_TYPE,answerDialogType.getAnswerNoPassDefaultLeftBtnType().ordinal() + "," +answerDialogType.getAnswerNoPassDefaultRightBtnType().ordinal());
        }else {
            answerNoPassJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_TYPE,answerDialogType.getAnswerNoPassDefaultLeftBtnType().ordinal() + "");
        }


        com.bairuitech.anychat.util.json.JSONObject answerTimeOutJsonObject = new com.bairuitech.anychat.util.json.JSONObject();
        answerTimeOutJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TYPE,BusinessDialogMessageType.ANSWER_TIMEOUT);
        answerTimeOutJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TITLE, answerDialogType.getAnswerTimeoutDefaultTitle());
        answerTimeOutJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_MESSAGE, answerDialogType.getAnswerTimeoutDefaultMessage());
        answerTimeOutJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_IS_SHOW, answerDialogType.isAnswerTimeoutDefaultShow());
        answerTimeOutJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_STYLE, answerDialogType.getAnswerTimeoutDefaultBtnNum().ordinal());
        if (answerDialogType.getAnswerTimeoutDefaultBtnNum() == BusinessDialogBtnNum.DoubleBtn) {
            answerTimeOutJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_TYPE,answerDialogType.getAnswerTimeoutDefaultLeftBtnType().ordinal() + "," +answerDialogType.getAnswerTimeoutDefaultRightBtnType().ordinal());
        }else {
            answerTimeOutJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_SHOW_TYPE,answerDialogType.getAnswerTimeoutDefaultLeftBtnType().ordinal() + "");
        }

        com.bairuitech.anychat.util.json.JSONObject recordingSessionKeepJsonObject = new com.bairuitech.anychat.util.json.JSONObject();
        recordingSessionKeepJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TYPE,BusinessDialogMessageType.RECORDING_SESSION_KEEP);
        recordingSessionKeepJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TITLE, netWorkStateDialogType.getRecordingSessionKeepDefaultTitle());
        recordingSessionKeepJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_MESSAGE, netWorkStateDialogType.getRecordingSessionKeepDefaultMessage());

        com.bairuitech.anychat.util.json.JSONObject recordingLinkCloseJsonObject = new com.bairuitech.anychat.util.json.JSONObject();
        recordingLinkCloseJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TYPE,BusinessDialogMessageType.RECORDING_LINK_CLOSE);
        recordingLinkCloseJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TITLE, netWorkStateDialogType.getRecordingLinkCloseDefaultTitle());
        recordingLinkCloseJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_MESSAGE, netWorkStateDialogType.getRecordingLinkCloseDefaultMessage());

        com.bairuitech.anychat.util.json.JSONObject completeRecordLinkCloseJsonObject = new com.bairuitech.anychat.util.json.JSONObject();
        completeRecordLinkCloseJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TYPE,BusinessDialogMessageType.COMPLETE_RECORD_LINK_CLOSE);
        completeRecordLinkCloseJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_TITLE, netWorkStateDialogType.getCompleteRecordLinkCloseDefaultTitle());
        completeRecordLinkCloseJsonObject.put(BusinessDialogMessageType.BUSINESS_DIALOG_MESSAGE, netWorkStateDialogType.getCompleteRecordLinkCloseDefaultMessage());


        alldetectDialogCongigArray.put(faceDetectOutJsonObject);
        alldetectDialogCongigArray.put(faceDetectMoreFaceJsonObject);
        alldetectDialogCongigArray.put(faceDetectErrorJsonObject);
        alldetectDialogCongigArray.put(faceDetectOutMaxCountJsonObject);
        alldetectDialogCongigArray.put(faceCompareNoPassJsonObject);
        alldetectDialogCongigArray.put(faceCompareErrorJsonObject);
        alldetectDialogCongigArray.put(answerNoPassJsonObject);
        alldetectDialogCongigArray.put(answerTimeOutJsonObject);
        alldetectDialogCongigArray.put(recordingSessionKeepJsonObject);
        alldetectDialogCongigArray.put(recordingLinkCloseJsonObject);
        alldetectDialogCongigArray.put(completeRecordLinkCloseJsonObject);
        String alldetectDialogCongigJsonStr = alldetectDialogCongigArray.toString();
        return alldetectDialogCongigJsonStr;
    }

    public static void resetFaceDetectDialogInfo(){
        faceDetectDialogType = null;
        faceDetectDialogType = new FaceDetectDialogType();
    }
    public static void resetFaceCompareDialogInfo(){
        faceCompareDialogType = null;
        faceCompareDialogType = new FaceCompareDialogType();
    }
    public static void resetAnswerDialogInfo(){
        answerDialogType = null;
        answerDialogType = new AnswerDialogType();
    }
}
