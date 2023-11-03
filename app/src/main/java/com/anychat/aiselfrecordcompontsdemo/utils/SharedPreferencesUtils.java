package com.anychat.aiselfrecordcompontsdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @describe: 共享参数存储工具类
 * @author: yyh
 * @createTime: 2018/8/20 14:49
 * @className: SharedPreferencesUtils
 */
public class SharedPreferencesUtils {

    private static final String FILE_NAME = "share_data";

    public static final String LOGIN_USER_ACCOUNT = "login_user_account";//登录账户

    public static final String LOGIN_APPID_INPUT = "login_appid_input";

    public static final String LOGIN_MERCHANT_ID = "merchant_id";//登录商户简称

    public static final String LOGIN_MERCHANT_APPID = "merchant_appId";//由商户编码获取的appId

    public static final String ANYCHAT_LOGIN_APPID = "anychat_login_appid";//登录AnyChat 应用id

    public static final String LOGIN_CUSTOMER_INFO = "customer_info";//客户信息

    public static final String LOGIN_CUSTOMER_RESERVATE_INFO = "customer_reservate_info";//客户预约信息

    public static final String LOGIN_ANYCHAT_IP = "login_def_anychat_ip";//anychat服务器ip

    public static final String LOGIN_ANYCHAT_PORT = "login_def_anychat_port";//anychat服务器端口

    public static final String LOGIN_JAVA_IP = "login_def_java_ip";//java服务器ip

    public static final String LOGIN_JAVA_PORT = "login_def_java_port";//java服务器端口

    public static final String LOGIN_MODE = "login_mode";//登录模式

    public static final String LOGIN_ENVIRONMENT = "login_def_environment";//默认环境配置

    public static final String GET_CUSTOM_LOGIN_CONFIG = "get_custom_login_config";

    public static final String GET_AI_CONFIG = "get_ai_config";//是否开启人脸在框
    public static final String GET_REMOTE_STREAM = "get_remote_stream";

    public static final String GET_IS_OPEN_FACE_LIVING_CONFIG = "get_is_open_face_living_config";//静默活体是否开启

    public static final String GET_IS_OPEN_FACE_COMPOSITE_CONFIG = "get_is_open_face_composite_config";//静默活体是否开启

    public static final String GET_IS_OPEN_FACE_DETECT_CONFIG = "get_is_open_face_detect_config";//是否开启人脸在框

    public static final String GET_IS_OPEN_FACE_COMPARE_CONFIG = "get_is_open_face_compare_config";//是否开启人脸比对

    public static final String GET_IS_OPEN_ANSWER_VERIFY_CONFIG = "get_is_open_answer_verify_config";//是否开启回答验证

    public static final String GET_IS_OPEN_TRACE_DATA_CONFIG = "get_is_open_trace_data_config";//标签数据是否开启

    public static final String GET_IS_CLIENT_MODE_CONFIG = "get_is_client_mode_config";//是否是客户端模式

    public static final String GET_SYSTEM_THEME_CONFIG = "get_system_theme_config";//获取系统主题色

    public static final String GET_DIALOG_TIME_OUT_CONFIG = "get_dialog_time_out_config";//获取对话框倒计时配置

    public static final String GET_FACE_DETECT_TIME_CONFIG = "get_face_detect_time_config";//获取在框检测时间间隔
    public static final String GET_COMPARE_PASS_SCORE_CONFIG = "get_compare_pass_score_config";//获取人脸比对通过分数
    public static final String GET_MAX_FACE_OUT_COUNT_CONFIG = "get_max_face_out_count_config";//获取最大出框次数
    public static final String GET_ASR_TIME_CONFIG = "get_faceDetectTime_config";//获取客户回答时长

    public static final String GET_IS_AI_RECORD_MODE = "get_is_ai_record_mode";//是否是双录类型

    public static final String GET_IS_OPEN_DIGITAL_HUMAN_CONFIG = "get_is_open_digital_human_config";//是否开启数字人

    public static final String GET_IS_OPEN_COMPLETE_PROCESS_CONFIG = "get_is_open_complete_process_config";//是否开户完整流程

    public static final String GET_UI_CONFIG = "get_ui_config";//获取UI样式配置
    public static final String GET_DIALOG_CONFIG = "get_dialog_config";//获取对话框样式配置

    public static final String GET_APP_IS_FIRST_START = "get_app_is_first_start";//应用是否首次启动

    public static final String LOGIN_EXPER_ACCOUNT = "login_exper_account";//体验码

    public static final String H5_URL = "h5_url";//h5 url地址

    public static final String OPEN_ACCOUNT_SERVER_URL = "open_account_server_url";//统一智能审核地址

    public static final String SERVER = "server";//后管服务

    public static final String CUST_ID = "cust_id";//
    public static final String BUSINESS_CODE = "business_code";//
    public static final String ITEM_CODE = "item_code";//

    /**
     * 保存数据
     */
    public static void save(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }
    /**
     * 保存数据
     */
    public static void save(Context context, String key, int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * 保存数据
     */
    public static void save(Context context, String key, boolean value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * 获取数据
     */
    public static String get(Context context, String key) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(key, "");
    }

    /**
     * 获取数据
     */
    public static String get(Context context, String key, String defValue) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(key, defValue);
    }

    /**
     * 获取数据
     */
    public static boolean get(Context context, String key, boolean defValue) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getBoolean(key, defValue);
    }
    /**
     * 获取数据
     */
    public static int get(Context context, String key, int defValue) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getInt(key, defValue);
    }
}