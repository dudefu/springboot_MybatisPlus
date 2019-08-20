package com.xinyi.xinfo.constants;

/**
 * 统一定义接口返回的code值和对应的详细信息
 *
 * @author chencan
 */
public class CodeMsg
{
    //2开头 （请求成功）表示成功处理了请求的状态代码。
    public static final int C200 = 200;
    public static final String C200_MSG = "请求成功";
    
    //3开头 （请求被重定向）表示要完成请求，需要进一步操作。 通常，这些状态代码用来重定向。
    
    //4开头 （请求错误）这些状态代码表示请求可能出错，妨碍了服务器的处理。
    public static final int C400 = 400;
    public static final String C400_MSG = "请求无效";
    public static final int C401 = 401;
    public static final String C401_MSG = "token验证失败";
    public static final int C404 = 404;
    public static final String C404_MSG = "服务器无回应";
    public static final int C405 = 405;
    public static final String C405_MSG = "请求方法不正确";    
    public static final int C408 = 408;
    public static final String C408_MSG = "请求超时";
    
    //5开头（服务器错误）这些状态代码表示服务器在尝试处理请求时发生内部错误。 这些错误可能是服务器本身的错误，而不是请求出错。
    public static final int C500 = 500;
    public static final String C500_MSG = "操作失败，程序发生异常";
    
    //自定义错误码，请使用6开头或者7开头
    public static final int C600 = 600;
    public static final String C600_MSG = "请求参数必须是字符串形式";
    public static final int C601 = 601;
    public static final String C601_MSG = "不是有效的JSON数据";
    public static final int C602 = 602;
    public static final String C602_MSG = "请求参数param不能为空";
    public static final int C603 = 603;
    public static final String C603_MSG = "账号或密码错误";
    public static final int C604 = 604;
    public static final String C604_MSG = "操作成功，请求参数有误";
    public static final int C605 = 605;
    public static final String C605_MSG = "不是有效的URL请求地址";
    public static final int C606 = 606;
    public static final String C606_MSG = "loginId或token不能为空";
    public static final int C607 = 607;
    public static final String C607_MSG = "form请求数据不能为空";
    public static final int C608 = 608;
    public static final String C608_MSG = "新增失败，请重试";
    public static final int C609 = 609;
    public static final String C609_MSG = "修改失败，请重试";
    public static final int C610 = 610;
    public static final String C610_MSG = "删除失败，请重试";
    public static final int C611 = 611;
    public static final String C611_MSG = "本单位暂未上线";
    public static final int C612 = 612;
    public static final String C612_MSG = "本用户无权访问";
    public static final int C613 = 613;
    public static final String C613_MSG = "已进入审批流程，不允许修改";
    public static final int C614 = 614;
    public static final String C614_MSG = "已进入审批流程，不允许删除";
    public static final int C615 = 615;
    public static final String C615_MSG = "无权限访问";
    public static final int C616 = 616;
    public static final String C616_MSG = "该数据已经存在";
    public static final int C617 = 617;
    public static final String C617_MSG = "请先删除子节点数据";
    public static final int C618 = 618;
    public static final String C618_MSG = "账号已被禁用";    
    public static final int C701 = 701;
    public static final String C701_MSG = "当前选择文件过大，无法上传";
    public static final int C700 = 700;
    public static final String C700_MSG = "上传成功";
    public static final int C702 = 702;
    public static final String C702_MSG = "当前文件为空";
    public static final int C703 = 703;
    public static final String C703_MSG = "当前terminalId为空,请上送公安网ip或警务云终端硬件序列号";
    public static final int C800 = 800;
    public static final String C800_MSG = "任务已被领取";
}
