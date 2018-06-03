package springmvc.liqiang.controller.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * @author liqiang
 * 时间 2018-05-27 11:24
 * 描述 session监听器
 */
public class ShiroSessionListener implements SessionListener {
    @Override
    public void onStart(Session session) {
        System.out.println("session开始" + session.getStartTimestamp());
        System.out.println("session开始" + session.getTimeout());
        System.out.println("session开始" + session.getHost());
        System.out.println("session开始" + session.getId());
    }

    @Override
    public void onStop(Session session) {
        System.out.println("session停止" + session.getLastAccessTime());
        System.out.println("session停止" + session.getTimeout());
        System.out.println("session停止" + session.getHost());
        System.out.println("session停止" + session.getId());

    }

    @Override
    public void onExpiration(Session session) {
        System.out.println("session过期" + session.getId());

    }
}
