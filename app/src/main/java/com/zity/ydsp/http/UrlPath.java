package com.zity.ydsp.http;

/**
 * Created by luochao on 2017/6/14.
 * 接口地址
 */

public class UrlPath {
    private static String BaseUrl="http://121.42.186.190:9157";
    //个人注册
    public static String PERSION_REGISTER=BaseUrl+"/person/savePersonByApp.do?";
    //个人登录
    public static String PERSION_LOGIN=BaseUrl+"/person/loginFront.do?";
    //个人办事主题 与法人办事
    public static String PERSION_THEM=BaseUrl+"/reporting/titlenameGrByApp.do?";
    // 个人更多办事主题
    public static String PERSION_MORE=BaseUrl+"/reporting/titlenameGrAllByApp.do?";
    //法人 更多办事主题  http://121.42.186.190:9157flag=0
    public static String CORPORATION_MORE=BaseUrl+"/reporting/titlenameFrAllByApp.do?";
    //个人  法人 部门办事
    public static String CORPORATION_CLASS=BaseUrl+"/reporting/orgnameByApp.do";
    //我的意见
    public static String MYSUGGEST=BaseUrl+"/supervision/getByPersonuuidApp.do?";
    //建议详情
    public static String SUGGESTXQ=BaseUrl+"/supervision/getSupervisionByIdApp.do?";

    //填写建议
    public static String SUBMIT_SUGGEST=BaseUrl+"/supervision/saveSupervision.do?";
    //修改密码
    public static String CHANG_PASSWORD=BaseUrl+"/person/changePassByApp.do?";

    //修改手机号码
    public static String CHANG_PHONE=BaseUrl+"/person/changePhoneByApp.do?";
    //获取个人信息
    public static String PERSIONAL_INFO=BaseUrl+"/person/getPersonByIdApp.do?";

}
