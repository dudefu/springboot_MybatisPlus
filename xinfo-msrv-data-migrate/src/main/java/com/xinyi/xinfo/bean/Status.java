package com.xinyi.xinfo.bean;

public class Status {

    private String msg;
    private String state;

    /**
     * 构造函数
     * @param msg
     * @param state
     */
    public Status(String state,String msg){
        this.state = state;
        this.msg = msg;
    }

    public Status() { }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public enum stateEnmu{
        SUCCESS("10200","OK"),
        FAILURE("10500","服务器内部异常");

        public String code;
        public String msg;

        stateEnmu(String code){
            this.code = code;
        }

        stateEnmu(String code,String msg){
            this.code = code;
            this.msg = msg;
        }
    }

}
