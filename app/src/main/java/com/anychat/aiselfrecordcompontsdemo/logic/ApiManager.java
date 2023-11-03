package com.anychat.aiselfrecordcompontsdemo.logic;

/**
 * @describe: api工具类
 * @author: yyh
 * @createTime: 2019/5/23 18:23
 * @className: ApiManager
 */
public class ApiManager {

    //腾讯Bugly SDK初始化全局参数定义
    public static String BuglyAppID = "8b5aea78a4";
    public static String BuglyAppKey = "2e212ee7-c84b-427f-ae90-9ee2bebf608e";


    /**
     * 环境配置  1：测试环境，2：开发环境，3：自定义环境
     */
    public interface EnvironmentConfigType {
        String ENVIRONMENT_TEST = "1"; //测试环境
        String ENVIRONMENT_DEVELOP = "2"; //开发环境
        String ENVIRONMENT_CUSTOM = "3"; //自定义环境
    }
    /**
     * 自定义环境数据配置
     */
    public interface CUSTOMDATA {
        //AnyChat登录ip
        String ANYCHAT_LOGIN_IP = "dev.bairuitech.cn";
        //AnyChat登录端口
        String ANYCHAT_LOGIN_PORT = "18603";

        String ANYCHAT_APPID = "00A4B0B4-ECBF-5B4A-A65E-91FC22F59BD1";


    }
    /**
     * 测试环境
     */
    public interface TESTDATA {
//        //AnyChat登录ip
//        String ANYCHAT_LOGIN_IP = "t.bairuitech.cn";
//        //AnyChat登录端口
//        String ANYCHAT_LOGIN_PORT = "18406";
//
//        String ANYCHAT_APPID = "F8B1AD3F-00DD-A342-9A79-7E00A64F1C65";

//        //AnyChat登录ip
//        String ANYCHAT_LOGIN_IP = "cloud-test.bairuitech.cn";
//        //AnyChat登录端口
//        String ANYCHAT_LOGIN_PORT = "9929";
//
//        String ANYCHAT_APPID = "4B0A25E5-7A51-49FE-B471-22D4ACCB751D";

        String ANYCHAT_LOGIN_IP = "cloud-test.bairuitech.cn";
        //AnyChat登录端口
        String ANYCHAT_LOGIN_PORT = "9929";

        String ANYCHAT_APPID = "4B0A25E5-7A51-49FE-B471-22D4ACCB751D";
        /**
         * 应用ID：4B0A25E5-7A51-49FE-B471-22D4ACCB751D
         * 服务地址：cloud-test.bairuitech.cn
         * 普通接入：9929
         * 业务服务接口: http://cloud-test.bairuitech.cn:9000
         */
    }

    /**
     * 开发环境
     */
    public interface DEVELOPDATA {
        //AnyChat登录ip
//        String ANYCHAT_LOGIN_IP = "dev.bairuitech.cn";
//        //AnyChat登录端口
//        String ANYCHAT_LOGIN_PORT = "18605";
//
//        String ANYCHAT_APPID = "00A4B0B4-ECBF-5B4A-A65E-91FC22F59BD1";
        String ANYCHAT_LOGIN_IP = "cloud-test.bairuitech.cn";
        //AnyChat登录端口
        String ANYCHAT_LOGIN_PORT = "9929";

        String ANYCHAT_APPID = "4B0A25E5-7A51-49FE-B471-22D4ACCB751D";


        /**
         * 应用ID：4B0A25E5-7A51-49FE-B471-22D4ACCB751D
         * 服务地址：cloud-test.bairuitech.cn
         * 普通接入：9929
         * 业务服务接口: http://cloud-test.bairuitech.cn:9000
         */

    }

    //根据商户编码获取登录appid
    private final static String GET_APP_ID_URL = "/AnyChatPlatform/adminAppconfig/getAppIdByCode";

}