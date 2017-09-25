package com.zity.ydsp.http;

/**
 * Created by luochao on 2017/6/14.
 * 接口地址
 */

public class UrlPath {
    private static String BaseUrl = "http://121.42.186.190:9157";
    //个人注册
    public static String PERSION_REGISTER = BaseUrl + "/person/savePersonByApp.do?";
    //个人登录
    public static String PERSION_LOGIN = BaseUrl + "/person/loginFront.do?";
    //个人办事主题 与法人办事
    public static String PERSION_THEM = BaseUrl + "/reporting/titlenameGrByApp.do?";
    // 个人更多办事主题
    public static String PERSION_MORE = BaseUrl + "/reporting/titlenameGrAllByApp.do?";
    //法人 更多办事主题  http://121.42.186.190:9157flag=0
    public static String CORPORATION_MORE = BaseUrl + "/reporting/titlenameFrAllByApp.do?";
    //个人  法人 部门办事
    public static String CORPORATION_CLASS = BaseUrl + "/reporting/orgnameByApp.do";
    //我的意见
    public static String MYSUGGEST = BaseUrl + "/supervision/getByPersonuuidApp.do?";
    //建议详情
    public static String SUGGESTXQ = BaseUrl + "/supervision/getSupervisionByIdApp.do?";

    //填写建议
    public static String SUBMIT_SUGGEST = BaseUrl + "/supervision/saveSupervision.do?";
    //修改密码
    public static String CHANG_PASSWORD = BaseUrl + "/person/changePassByApp.do?";

    //修改手机号码
    public static String CHANG_PHONE = BaseUrl + "/person/changePhoneByApp.do?";
    //获取个人信息
    public static String PERSIONAL_INFO = BaseUrl + "/person/getPersonByIdApp.do?";


    //民生互动模块

    public static String BaseUrl1 = "http://211.151.183.170:9081";
    //登录
    public static String LoginUrl = BaseUrl1 + "/interface/login.do?";
    //获取验证码
    public static String getSecurityCode = BaseUrl1 + "/interface/sendCode.do?";
    //注册
    public static String SendRegister = BaseUrl1 + "/interface/register.do?";
    //修改密码
    public static String CHANGE_PASSWORD = BaseUrl1 + "/interface/savepersonpassword.do?";
    //修改个人信息
    public static String CHANGE_USERINFO = BaseUrl1 + "/interface/editInfo.do?";
    //获取图片
    public static String BANNER_IMAGE = BaseUrl1 + "/interface/queryHomeImg.do";
    //获取进度列表
    public static String PROGRESS_LIST = BaseUrl1 + "/interface/getMyAll.do?";
    //获取医疗列表
    public static String YILIAO = BaseUrl1 + "/interface/publicQuery.do?";
    //公共服务详情
    public static String GGFUXQ = BaseUrl1 + "/interface/getPublicById.do?";
    //提诉求
    public static String LEFT_APPEAL = BaseUrl1 + "/interface/save.do?";
    //进度详情
    public static String PROGRESS_XQ = BaseUrl1 + "/interface/getById.do?";
    //提交品论
    public static String MAKE_COMMENT = BaseUrl1 + "/interface/evaluate.do?";
    //办事指南
    public static String GUIDE = BaseUrl1 + "/interface/queryGuide.do";
    //政策法规
    public static String POLICY = BaseUrl1 + "/interface/queryPolicy.do";
    //通知公告
    public static String NOTICE = BaseUrl1 + "/interface/queryAnnouncement.do";

    //民生网用户id
    public static String MSHD=BaseUrl1+"/interface/getByUserId.do?";
}
