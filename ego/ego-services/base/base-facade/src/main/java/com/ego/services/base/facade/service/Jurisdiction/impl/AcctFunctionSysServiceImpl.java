package com.ego.services.base.facade.service.jurisdiction.impl;

import java.util.List;
import java.util.Set;

import com.ebase.core.page.PageInfo;
import com.ego.services.base.api.vo.jurisdiction.AcctFunctionSysVO;
import com.ego.services.base.facade.dao.jurisdiction.AcctFunctionSysMapper;
import com.ego.services.base.facade.model.jurisdiction.AcctFunctionSys;
import com.ego.services.base.facade.service.jurisdiction.AcctFunctionSysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebase.utils.ParamType;
import com.ebase.utils.BeanCopyUtil;

/**
 * dal Interface:AcctFunctionSys
 * @author 
 * @date 2018-10-10
 */
@Service
@Transactional
public class AcctFunctionSysServiceImpl implements AcctFunctionSysService {

    @Autowired
    private AcctFunctionSysMapper acctFunctionSysMapper;

	@Override
    public List<AcctFunctionSysVO> selectAll() {
        //List<AcctFunctionSys> acctFunctionSyss = acctFunctionSysMapper.selectAll();
        //List<AcctFunctionSysVO> acctFunctionSysVOs = BeanCopyUtil.copyList(acctFunctionSyss, AcctFunctionSysVO.class);
        //return acctFunctionSysVOs;
		return null;
    }

	@Override
    public PageInfo<AcctFunctionSysVO> select(AcctFunctionSysVO record){
		
		com.ego.services.base.facade.model.jurisdiction.AcctFunctionSys model = BeanCopyUtil.copy(record, AcctFunctionSys.class);
		List<AcctFunctionSys> acctFunctionSyss = acctFunctionSysMapper.select(model);
		List<AcctFunctionSysVO> acctFunctionSysVOs = BeanCopyUtil.copyList(acctFunctionSyss, AcctFunctionSysVO.class);
		PageInfo<AcctFunctionSysVO> pageInfo=new PageInfo<AcctFunctionSysVO>();
		return pageInfo;
    }

	@Override
    public AcctFunctionSysVO selectByPrimaryKey(Long key){
    	AcctFunctionSys acctFunctionSys = acctFunctionSysMapper.selectByPrimaryKey(key);
        return BeanCopyUtil.copy(acctFunctionSys, AcctFunctionSysVO.class);
    }

	@Override
    public Integer insert(AcctFunctionSysVO record){
    	AcctFunctionSys acctFunctionSys = BeanCopyUtil.copy(record, AcctFunctionSys.class);
        return acctFunctionSysMapper.insert(acctFunctionSys);

    }

	@Override
    public Integer insertSelective(AcctFunctionSysVO record){
        AcctFunctionSys acctFunctionSys = BeanCopyUtil.copy(record, AcctFunctionSys.class);
        return acctFunctionSysMapper.insertSelective(acctFunctionSys);
    }
    
    @Override
    public Integer updateByPrimaryKey(AcctFunctionSysVO record){
    	AcctFunctionSys acctFunctionSys = BeanCopyUtil.copy(record, AcctFunctionSys.class);
        return acctFunctionSysMapper.updateByPrimaryKey(acctFunctionSys);
    }

	@Override
    public Integer updateByPrimaryKeySelective(AcctFunctionSysVO record){
        AcctFunctionSys acctFunctionSys = BeanCopyUtil.copy(record, AcctFunctionSys.class);
        return acctFunctionSysMapper.updateByPrimaryKeySelective(acctFunctionSys);
    }

	@Override
    public Integer deleteByPrimaryKey(Long key){
        return acctFunctionSysMapper.deleteByPrimaryKey(key);
    }
    
    /**
	 *  IN
	 *	<foreach collection="keys" open="(" close=")" item="key" separator=",">
	 *		{key}
	 *	</foreach>
	 */
    @Override
    public Integer deleteByPrimaryKeys(Set<Long> keys){
    	//return acctFunctionSysMapper.deleteByPrimaryKeys(keys);
        return null;
    }
    
    @Override
	public Integer keep(List<AcctFunctionSysVO> acctFunctionSysVOs) {
		int result = 0;
//		Set<Long> keys = new HashSet<>();
		for (AcctFunctionSysVO acctFunctionSysVO : acctFunctionSysVOs) {
			String opt = acctFunctionSysVO.getOpt();
			if (ParamType.DELETE.getMsg().equals(opt)) {
//				keys.add(acctFunctionSysVO.getId());
				int i = deleteByPrimaryKey(acctFunctionSysVO.getRelaId());
				if (i > 0) {
					result++;
				}
			} else if (ParamType.UPDATE.getMsg().equals(opt)) {
				int i = updateByPrimaryKeySelective(acctFunctionSysVO);
				if (i > 0) {
					result++;
				}
			} else if (ParamType.INSERT.getMsg().equals(opt)) {
				int i = insertSelective(acctFunctionSysVO);
				if (i > 0) {
					result++;
				}
			}
		}
//		if(!keys.isEmpty())
//			result = result + deleteByPrimaryKeys(keys);
		return result;
	}
}