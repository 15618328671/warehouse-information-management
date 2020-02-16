package com.zk.warehouse.information.management.commons.dto;

import java.io.Serializable;

/**
 * 自定义返回结果
 * @author zk
 * @date 2020/1/31-16:30
 */
public class BaseResult implements Serializable {
    private int status;
    private String message;

    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_FAIL = 500;

    public static BaseResult success(){
        return BaseResult.createBaseResult(STATUS_SUCCESS,"成功");
    }

    public static BaseResult success(String message){
        return BaseResult.createBaseResult(STATUS_SUCCESS,message);
    }

    public static BaseResult fail(){
        return BaseResult.createBaseResult(STATUS_FAIL,"失败");
    }

    public static BaseResult fail(String message){
        return BaseResult.createBaseResult(STATUS_FAIL,message);
    }

    public static BaseResult fail(int status,String message){
        return BaseResult.createBaseResult(status,message);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static BaseResult createBaseResult(int status,String message){
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        return baseResult;
    }
}
