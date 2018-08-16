package springmvc.liqiang.service.sys;

import springmvc.liqiang.dao.model.OperaResult;
import springmvc.liqiang.entity.SysUserInfoPO;

/**
 * @author liqiang
 * 时间 2018-05-20 17:07
 * 描述 用户相关service
 */
public interface UserService {
    /**
     * 注册用户
     *
     * @param record 注册保存的数据
     * @return 返回保存结果
     */
    OperaResult register(SysUserInfoPO record) throws Exception;
}
