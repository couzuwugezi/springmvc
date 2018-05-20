package springmvc.liqiang.controller.sys;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springmvc.liqiang.utils.MD5Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liqiang
 * 时间 2018-05-13 19:25
 * 描述 首页
 */
@Controller
public class IndexController {

    private Logger log = Logger.getLogger(IndexController.class);

    /**
     * 测试画点功能
     *
     * @return 返回页面
     */
    @RequestMapping(value = "/test/drawPoint", method = RequestMethod.POST)
    public String drawPoint() {
        return "html/test/drawPoint";
    }

    /**
     * 跳转登录页面
     *
     * @return 返回页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return "html/sys/index";
        }
        return "html/sys/login";
    }

    /**
     * 跳转主页
     *
     * @return 返回主页
     */
    @RequestMapping(value = {"/index", "/", ""}, method = {RequestMethod.POST, RequestMethod.GET})
    public String index() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return "html/sys/index";
        }
        return "redirect:/login";
    }

    /**
     * 跳转未授权提示页面
     *
     * @return 返回页面
     */
    @RequestMapping(value = "/unauthorized", method = RequestMethod.POST)
    public String unauthorized() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return "html/sys/index";
        }
        return "html/sys/405";
    }

    /**
     * 验证登录
     */
    @RequestMapping(value = "/checkInfo", method = RequestMethod.POST)
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        String loginname = request.getParameter("loginname");
        JSONObject obj = new JSONObject();
        try {
            String password = MD5Util.encryptMD5(request.getParameter("password"));
            Subject subject = SecurityUtils.getSubject();
            if (!subject.isAuthenticated()) {
                UsernamePasswordToken token = new UsernamePasswordToken(loginname, password);
                token.setRememberMe(true);
                subject.login(token);
            }
            obj.put("code", "1");
            response.getWriter().write(obj.toJSONString());
        } catch (UnknownAccountException uae) {
            log.error("----> There is no user with username of " + loginname, uae);
            obj.put("msg", uae.getMessage());
            obj.put("code", "-1");
            response.getWriter().write(obj.toJSONString());
        } catch (IncorrectCredentialsException ice) {
            log.error("----> Password for account " + loginname + " was incorrect!", ice);
            obj.put("msg", ice.getMessage());
            obj.put("code", "-2");
            response.getWriter().write(obj.toJSONString());
        } catch (LockedAccountException lae) {
            log.error("The account for username " + loginname + " is locked.  ", lae);
            obj.put("msg", lae.getMessage());
            obj.put("code", "-1");
            response.getWriter().write(obj.toJSONString());
        } catch (Exception ae) {
            log.error("----> system error ", ae);
            obj.put("msg", "系统异常");
            obj.put("code", "-1");
            response.getWriter().write(obj.toJSONString());
        }

    }
}
