package cn.hot.hotdog.util;

public class AjaxResult {
    private boolean success=true;
    private String msg="操作成功";
    private Object object;
    public static AjaxResult me(){
        return new AjaxResult();
    }

    public boolean isSuccess() {
        return success;
    }

    public AjaxResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public AjaxResult setMsg(String mes) {
        this.msg = mes;
        return this;
    }

    public Object getObject() {
        return object;
    }

    public AjaxResult setObject(Object object) {
        this.object = object;
        return this;

    }

    @Override
    public String toString() {
        return "AjaxResult{" +
                "success=" + success +
                ", mes='" + msg+ '\'' +
                ", object=" + object +
                '}';
    }
}
