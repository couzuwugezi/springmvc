package springmvc.liqiang.dao.model;

/**
 * @author liqiang
 * 时间 2018-05-17 14:34
 * 描述 后台返回操作结果载体
 */
public class OperaResult {
    private String msg;
    private String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
