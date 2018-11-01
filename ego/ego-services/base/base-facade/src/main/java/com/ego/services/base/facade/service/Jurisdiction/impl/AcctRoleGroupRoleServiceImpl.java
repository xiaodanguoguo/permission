package com.ego.services.base.facade.service.jurisdiction.impl;


import com.ebase.utils.BeanCopyUtil;
import com.ego.services.base.api.vo.jurisdiction.AcctRoleGroupRoleVO;
import com.ego.services.base.facade.dao.jurisdiction.AcctRoleGroupRoleMapper;
import com.ego.services.base.facade.model.jurisdiction.AcctRoleGroupRole;
import com.ego.services.base.facade.service.jurisdiction.AcctRoleGroupRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: zhaoyuhang
 */
@Service
public class AcctRoleGroupRoleServiceImpl implements AcctRoleGroupRoleService {

    private static Logger LOG = LoggerFactory.getLogger(AcctRoleGroupRoleServiceImpl.class);

    @Autowired
    private AcctRoleGroupRoleMapper acctRoleGroupRoleMapper;

    @Override
    public Integer delAcctRoleGroupRole(AcctRoleGroupRoleVO jsonRequest) {

        AcctRoleGroupRole reqBody = new AcctRoleGroupRole();
        BeanCopyUtil.copy(jsonRequest, reqBody);

        //删除此角色下的功能关联
        Integer num=acctRoleGroupRoleMapper.deleteRoleGroupId(reqBody.getRoleGroupId());

        return num;
    }

    @Override
    public Integer addAcctRoleGroupRole(AcctRoleGroupRoleVO acctRoleGroupRoleVO) {
        AcctRoleGroupRole reqBody = new AcctRoleGroupRole();
        BeanCopyUtil.copy(acctRoleGroupRoleVO, reqBody);

        //先删除此角色下的功能关联
        Integer num=acctRoleGroupRoleMapper.deleteRoleGroupId(reqBody.getRoleGroupId());
        String[]  strs=reqBody.getRoleIds().split(",");
        for(int i=0;i<strs.length;i++) {
            reqBody.setRoleId(Long.parseLong(strs[i]));
            //添加角色与功能的关联
            acctRoleGroupRoleMapper.insertSelective(reqBody);
        }
        return num;
    }

}
