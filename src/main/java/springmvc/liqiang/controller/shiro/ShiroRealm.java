package springmvc.liqiang.controller.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import springmvc.liqiang.dao.mapper.SysUserInfoMapper;
import springmvc.liqiang.entity.SysUserInfoExample;
import springmvc.liqiang.entity.SysUserInfoPO;
import springmvc.liqiang.utils.CommonUtil;

import java.util.List;

/**
 * @author liqiang
 * 时间 2018-05-18 22:33
 * 描述 shiro认证域
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserInfoMapper sysUserInfoMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String loginname = CommonUtil.toString(token.getPrincipal());
        String password = new String((char[]) token.getCredentials());
        SysUserInfoExample example = new SysUserInfoExample();
        SysUserInfoExample.Criteria criteria = example.createCriteria();

        //先通过用户名查找
        criteria.andLoginNameEqualTo(loginname).andUserStatusEqualTo("1");
        List<SysUserInfoPO> checkNamw = sysUserInfoMapper.selectByExample(example);
        if (checkNamw.size() == 0) {
            throw new UnknownAccountException("用户不存在");
        }
        // 再加上密码匹配
        criteria.andLoginPasswordEqualTo(password);
        List<SysUserInfoPO> checkPwdAndName = sysUserInfoMapper.selectByExample(example);
        if (checkPwdAndName.size() == 0) {
            throw new IncorrectCredentialsException("密码错误");
        }
        if ("0".equals(checkPwdAndName.get(0).getIsStart())) {
            throw new LockedAccountException("用户被锁定");
        }
        //  当前 realm 对象的 name. 调用父类的 getName() 方法即可
        String realmName = getName();
        ByteSource credentialsSalt = ByteSource.Util.bytes(loginname);
        SimpleAuthenticationInfo info;
        info = new SimpleAuthenticationInfo(loginname, password, credentialsSalt, realmName);

        return info;
    }
}
