package com.gx.cloud.admin.server.constants;

public class ResultDao {

    //业务处理是否成功
    private boolean success=true;
    //错误码,0默认成功
    private int resultCode;
    //错误描述
    private String resultDes;
    //返回结果
    private Object result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
        this.resultDes = ResultCode.getMessage(resultCode);
    }

    public String getResultDes() {
        return resultDes;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
