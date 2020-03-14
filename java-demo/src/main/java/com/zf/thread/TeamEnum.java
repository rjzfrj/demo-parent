package com.zf.thread;

/**
 * @auther: zf
 * @date: 2020-03-06 21:43
 * @description:
 */
public enum TeamEnum{

    ONE(1,"关羽"),
    TWO(2,"张飞"),
    THREE(3,"马超"),
    FOUR(4,"黄忠"),
    FIVE(5,"赵云");

    private int code;
    private String msg;

    TeamEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static TeamEnum getTeamEnumMsg(int code) {

        for (TeamEnum teamEnum :TeamEnum.values()) {
            if (code == teamEnum.getCode()) {
                return teamEnum;
            }
        }
        return null;
    }
}
