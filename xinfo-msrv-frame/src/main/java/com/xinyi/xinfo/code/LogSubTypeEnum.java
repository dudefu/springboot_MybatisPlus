package com.xinyi.xinfo.code;

/**
 * @Author: longfei.ding
 * @Date: 2019/7/12 0012 上午 11:16
 * @Description 子类型枚举类
 * type定义规则如下: 公用类型:Gxxxx 特有类型:Txxxx
 * xxxx指的是流水号 不可重复 如0001已被占用则不管新扩展的是 公用类型/特有类型 都不可再使用此编号
 *  新的type编号应定义为:G0002/T0002
 */
public enum LogSubTypeEnum {

    UNKONWN("G0001", "未知操作"),
    LOGIN_IN("G0002", "登入"),
    LOGIN_OUT("G0003", "退出"),
    UPDATE_PASSWORD("G0004", "修改密码"),

    A_REALTIMEVIDEOSTART("T0005", "开始实时视频"),
    A_REALTIMEVIDEOSTOP("T0006", "停止实时视频"),
    A_CAPTUREREALTIMEVIDEO("T0007", "停止实时视频"),
    A_PTZ_LEFT("T0008", "云台左"),
    A_PTZ_TOP("T0009", "云台上"),
    A_PTZ_RIGHT("T0010", "云台右"),

    A_PTZ_BOTTOM("T0011", "云台下"),
    A_PTZ_LEFTTOP("T0012", "云台左上"),
    A_PTZ_RIGHTTOP("T0013", "云台右上"),
    A_PTZ_LEFTBOTTOM("T0014", "云台左下"),
    A_PTZ_RIGHTBOTTOM("T0015", "云台右下"),

    A_PTZ_FAR("T0016", "焦距远"),
    A_PTZ_NEAR("T0017", "焦距近"),
    A_PTZ_APERTUREBIGGER("T0018", "光圈加大"),
    A_PTZ_APERTURESMALLER("T0019", "光圈减小"),
    A_PTZ_WIPERON("T0020", "雨刷开"),

    A_PTZ_WIPEROFF("T0021", "雨刷开"),
    A_PTZ_LOCK("T0022", "云台锁定"),
    A_PTZ_UNLOCK("T0023", "云台解锁"),
    A_PTZ_PRESET("T0024", "预置位操作"),
    A_PTZ_SPEED("T0025", "云台速度操作"),

    A_PTZ_3DBIGGER("T0026", "3D放大"),
    A_PTZ_SELFROTATE("T0027", "云台自旋"),
    A_VIEWRECORDVIDEO("T0028", "查看历史视频"),
    A_CAPTURERECORDVIDEO("T0029", "历史视频抓拍"),
    A_DOWNLOADRECORDVIDEO("T0030", "下载历史视频"),

    A_MAPOPERATE("T0031", "地图操作类"),
    A_ALARMOPERATE("T0032", "预警操作"),
    A_LINKPLAN("T0033", "联动预案"),
    A_CARNUMQUERY("T0034", "过车查询"),
    A_FACEQUERY("T0035", "过人查询"),

    A_TAGQUERY("T0036", "标签查询操作"),
    PASSWORD_RECORD("G0037", "密码记录"),
    ;

    //TODO 待补充

    private String type;

    private String typeName;

    LogSubTypeEnum(String type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public String getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }
}
