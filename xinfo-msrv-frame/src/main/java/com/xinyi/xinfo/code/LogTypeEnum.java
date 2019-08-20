package com.xinyi.xinfo.code;

import java.util.Objects;

/**
 * @Author: longfei.ding
 * @Date: 2019/7/12 0012 上午 11:15
 * @Description 通用日志接口 类型划分
 * type定义规则如下: 底层服务:Axxxx BS相关:Bxxxx CS相关:Cxxxx
 * xxxx指的是流水号 不可重复 如0001已被A占用则不管新扩展的是 BS/CS相关 都不可再使用此编号
 * 新的type编号应定义为:B0002/C0002
 */
public enum LogTypeEnum {

    A_UNKONWN("A0001", "未知服务", ""),
    B_UNKONWN("B0002", "未知服务", ""),
    C_UNKONWN("C0003", "未知服务", ""),

    B_STANDARDIZED_ACCESS_MANAGEMENT("B0004", "联网管理中心", "xsweb"),
    B_VIDEO_RESOURCE_APPLICATION("B0005", "视频资源共享平台", "xshare"),
    B_SHARED_APPLICATION_SERVICE_PLATFORM("B0006", "智慧南山视频汇聚共享应用服务平台", "xmenu"),
    B_RESOURCE_SHARE_MANAGEMENT("B0007", "资源共享管理", "resourceAdmin"),
    B_API_SERVER("B0008", "api接口服务", "api"),

    A_CLIENT("A0009", "客户端", "client"),
	C_MEDIA_SERVER("C0010", "流媒体服务", null);

    //TODO 待补充

    private String type;

    private String typeName;

    private String adhibitionId;

    LogTypeEnum(String type, String typeName, String adhibitionId) {
        this.type = type;
        this.typeName = typeName;
        this.adhibitionId = adhibitionId;
    }

    public String getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getAdhibitionId() {
        return adhibitionId;
    }

    public static LogTypeEnum valueOfType(String type) throws Exception{
        LogTypeEnum[] logTypeEnums = LogTypeEnum.values();
        if(logTypeEnums != null){
            for(LogTypeEnum logTypeEnum : logTypeEnums){
                if(Objects.equals(type, logTypeEnum.getType())){
                    return logTypeEnum;
                }
            }
        }
        throw new Exception("不存在的日志类型~");
    }

    public static LogTypeEnum valueOfAdhibitionId(String adhibitionId) throws Exception{
        LogTypeEnum[] logTypeEnums = LogTypeEnum.values();
        if(logTypeEnums != null){
            for(LogTypeEnum logTypeEnum : logTypeEnums){
                if(Objects.equals(adhibitionId, logTypeEnum.getAdhibitionId())){
                    return logTypeEnum;
                }
            }
        }
        throw new Exception("不存在的日志类型~");
    }
}
