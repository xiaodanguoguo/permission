package com.ego.services.juri.facade.service.jurisdiction.impl;


import com.ebase.utils.BeanCopyUtil;
import com.ego.services.juri.api.vo.jurisdiction.AcctOperPrivRelaVO;
import com.ego.services.juri.facade.dao.jurisdiction.AcctOperPrivRelaMapper;
import com.ego.services.juri.facade.model.jurisdiction.AcctOperPrivRela;
import com.ego.services.juri.facade.service.jurisdiction.AcctOperPrivRelaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: zhaoyuhang
 */
@Service
public class AcctOperPrivRelaServiceImpl implements AcctOperPrivRelaService {

    private static Logger LOG = LoggerFactory.getLogger(AcctOperPrivRelaServiceImpl.class);

    @Autowired
    private AcctOperPrivRelaMapper acctOperPrivRelaMapper;

    @Override
    public Integer delAcctOperPrivRela(AcctOperPrivRelaVO jsonRequest) {

        AcctOperPrivRela reqBody = new AcctOperPrivRela();
        BeanCopyUtil.copy(jsonRequest,reqBody );
        //删除此角色下的功能关联
        Integer num=acctOperPrivRelaMapper.deleteRoleId(reqBody.getRoleId());
        return num;
    }

    @Override
    public Integer addAcctOperPrivRela(AcctOperPrivRelaVO jsonRequest) {
        AcctOperPrivRela acctOperPrivRela=new AcctOperPrivRela();
        BeanCopyUtil.copy(jsonRequest,acctOperPrivRela);

        //先删除此角色下的功能关联
        Integer num=acctOperPrivRelaMapper.deleteRoleId(acctOperPrivRela.getRoleId());
        String[]  strs=acctOperPrivRela.getFunctionIds().split(",");
        for(int i=0;i<strs.length;i++) {
            acctOperPrivRela.setFunctionId(Long.parseLong(strs[i]));
            //添加角色与功能的关联
            acctOperPrivRelaMapper.insertSelective(acctOperPrivRela);
        }

        return num;
    }

}
