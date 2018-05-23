package springmvc.liqiang.controller.sys;

import springmvc.liqiang.utils.ReturnFormat;

/**
 * @author liqiang
 * 时间 2018-05-23 22:43
 * 描述 exception handler
 */
public abstract class BaseController {
    protected String retContent(int status, Object data) {
        return ReturnFormat.retParam(status, data);
    }
}
