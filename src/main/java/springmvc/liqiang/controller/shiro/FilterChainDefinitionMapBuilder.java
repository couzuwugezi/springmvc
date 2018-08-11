package springmvc.liqiang.controller.shiro;

import java.util.LinkedHashMap;

/**
 * @author liqiang
 * 时间 2018-05-27 10:34
 * 描述 shiro的权限map
 * 配置哪些页面需要受保护.
 * 以及访问这些页面需要的权限.
 * 1). anon 可以被匿名访问
 * 2). authc 必须认证(即登录)后才可能访问的页面.
 * 3). logout 登出.
 * 4). roles 角色过滤器
 */
public class FilterChainDefinitionMapBuilder {

    public LinkedHashMap<String, String> buildFilterChainDefinitionMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        map.put("/register", "anon");
        map.put("/login", "anon");
        map.put("/logout", "logout");
        map.put("/checkInfo", "anon");
        map.put("/static/**/*.js", "anon");
        map.put("/static/**/*.ttf", "anon");
        map.put("/static/**/*.woff", "anon");
        map.put("/static/**/*.map", "anon");
        map.put("/static/**/*.css", "anon");
        map.put("/**", "authc");

        return map;
    }
}
