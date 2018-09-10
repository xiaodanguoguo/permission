package com.ego.services.base.facade.dao.Jurisdiction;

import com.ebase.core.page.Page;
import com.ego.services.base.facade.model.Jurisdiction.RoleInfo;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface RoleInfoMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(RoleInfo record);

    int insertSelective(RoleInfo record);

    RoleInfo selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(RoleInfo record);

    int updateByPrimaryKey(RoleInfo record);

    List<RoleInfo> find(RoleInfo roleInfo);

    List<RoleInfo> findTwo(RoleInfo roleInfo);

    List<RoleInfo> findAll(RoleInfo roleInfo);

    List<RoleInfo> findAllLike(RoleInfo roleInfo);

    List<RoleInfo> findRoleDetailed(RoleInfo roleInfo);

    List<RoleInfo> roleInfoTree(RoleInfo roleInfo);

    List<RoleInfo> roleRoleAcctInfo(RoleInfo roleInfo);

    List<RoleInfo> roleGroupTree(RoleInfo roleInfo);

    RoleInfo findGroupId(RoleInfo roleInfo);


    RoleInfo selectListByRoleId(Long roleId);

    //验证是否名称重复
    List<RoleInfo> verificationIsTtitle(RoleInfo roleInfo);

    List<RoleInfo> roleGroupParentTree(RoleInfo roleInfo);

    List<RoleInfo> verificationDeleteRoelInfo(RoleInfo roleInfo);

    RoleInfo selectInitializationRole(RoleInfo roleInfo);
}