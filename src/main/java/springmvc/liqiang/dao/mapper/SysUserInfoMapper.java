package springmvc.liqiang.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import springmvc.liqiang.entity.SysUserInfoExample;
import springmvc.liqiang.entity.SysUserInfoPO;

public interface SysUserInfoMapper {
    int countByExample(SysUserInfoExample example);

    int deleteByExample(SysUserInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysUserInfoPO record);

    int insertSelective(SysUserInfoPO record);

    List<SysUserInfoPO> selectByExample(SysUserInfoExample example);

    SysUserInfoPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysUserInfoPO record, @Param("example") SysUserInfoExample example);

    int updateByExample(@Param("record") SysUserInfoPO record, @Param("example") SysUserInfoExample example);

    int updateByPrimaryKeySelective(SysUserInfoPO record);

    int updateByPrimaryKey(SysUserInfoPO record);
}