package springmvc.liqiang.service.impl.sys;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import springmvc.liqiang.dao.mapper.SysUserInfoMapper;
import springmvc.liqiang.dao.model.OperaResult;
import springmvc.liqiang.entity.SysUserInfoPO;
import springmvc.liqiang.service.sys.UserService;
import springmvc.liqiang.utils.CommonUtil;

import java.util.Date;

/**
 * @author liqiang
 * 时间 2018-05-20 17:08
 * 描述
 */
@Transactional(rollbackFor = Exception.class)
@Service("UserService")
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private SysUserInfoMapper sysUserInfoMapper;

    /**
     * 注册用户
     *
     * @param record 注册保存的数据
     * @return 返回保存结果
     */
    @Override
    public OperaResult register(SysUserInfoPO record) {
        OperaResult result = new OperaResult();
        int insertRes;
        try {
            record.setIsStart("1");
            record.setUserStatus("1");
            record.setCreatTime(new Date());

            // shiro 密码加密
            String hashAlgorithmName = "MD5";
            int hashIterations = 1024;
            ByteSource credentialsSalt = ByteSource.Util.bytes(record.getLoginName());
            Object password = new SimpleHash(hashAlgorithmName, record.getLoginPassword(), credentialsSalt, hashIterations);

            record.setLoginPassword(CommonUtil.toString(password));
            insertRes = sysUserInfoMapper.insert(record);
            if (insertRes == 1) {
                result.setMsg("操作成功!");
                result.setCode("1");
            } else {
                result.setMsg("操作失败!");
                result.setCode("0");
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("method UserServiceImpl.register has an exception: ", e);
            result.setCode("-1");
            result.setMsg("系统异常请联系管理员");
        }
        return result;
    }
}
