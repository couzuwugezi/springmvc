package springmvc.liqiang.controller.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
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
        SysUserInfoExample example = new SysUserInfoExample();
        SysUserInfoExample.Criteria criteria = example.createCriteria();

        //先通过用户名查找
        criteria.andLoginNameEqualTo(loginname).andUserStatusEqualTo("1");
        List<SysUserInfoPO> checkName = sysUserInfoMapper.selectByExample(example);
        if (checkName.size() == 0) {
            throw new UnknownAccountException("用户不存在");
        }

        //  当前 realm 对象的 name. 调用父类的 getName() 方法即可
        String realmName = getName();
        // 获取当前用户的密码
        String checkPassword = checkName.get(0).getLoginPassword();
        // 加密盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(loginname);

        SimpleAuthenticationInfo info;
        info = new SimpleAuthenticationInfo(loginname, checkPassword, credentialsSalt, realmName);

        return info;
    }

    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        Object credentials = "111111";
        int hashIterations = 1024;
        ByteSource credentialsSalt = ByteSource.Util.bytes("Bruce");
        Object result = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
        System.out.println(result);
    }
}
