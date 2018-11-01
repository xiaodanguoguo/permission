package com.ego.services.base.facade.service.dataauthority.impl;

import java.util.List;
import java.util.Set;

import com.ego.services.base.api.vo.dataauthority.AccountPrivRelationVO;
import com.ego.services.base.facade.dao.dataauthority.AccountPrivRelationMapper;
import com.ego.services.base.facade.model.dataauthority.AccountPrivRelation;
import com.ego.services.base.facade.service.dataauthority.AccountPrivRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebase.utils.ParamType;
import com.ebase.utils.BeanCopyUtil;

/**
 * dal Interface:AccountPrivRelation
 * @author Mrli
 * @date 2018-11-1
 */
@Service
@Transactional
public class AccountPrivRelationServiceImpl implements AccountPrivRelationService {

    @Autowired
    private AccountPrivRelationMapper accountPrivRelationMapper;

	@Override
    public List<AccountPrivRelationVO> findAll() {
        //List<AccountPrivRelation> accountPrivRelations = accountPrivRelationMapper.selectAll();
        //List<AccountPrivRelationVO> accountPrivRelationVOs = BeanCopyUtil.copyList(accountPrivRelations, AccountPrivRelationVO.class);
        //return accountPrivRelationVOs;
        return null;
    }

	@Override
    public List<AccountPrivRelationVO> findSelective(AccountPrivRelationVO record){
		AccountPrivRelation model = BeanCopyUtil.copy(record, AccountPrivRelation.class);
		List<AccountPrivRelation> accountPrivRelations = accountPrivRelationMapper.select(model);
		List<AccountPrivRelationVO> accountPrivRelationVOs = BeanCopyUtil.copyList(accountPrivRelations, AccountPrivRelationVO.class);
		return accountPrivRelationVOs;
    }

	@Override
    public AccountPrivRelationVO getByPrimaryKey(Long key){
    	AccountPrivRelation accountPrivRelation = accountPrivRelationMapper.selectByPrimaryKey(key);
        return BeanCopyUtil.copy(accountPrivRelation, AccountPrivRelationVO.class);
    }

	@Override
    public Integer insert(AccountPrivRelationVO record){
    	AccountPrivRelation accountPrivRelation = BeanCopyUtil.copy(record, AccountPrivRelation.class);
        return accountPrivRelationMapper.insert(accountPrivRelation);

    }

	@Override
    public Integer insertSelective(AccountPrivRelationVO record){
        AccountPrivRelation accountPrivRelation = BeanCopyUtil.copy(record, AccountPrivRelation.class);
        return accountPrivRelationMapper.insertSelective(accountPrivRelation);
    }
    
    @Override
    public Integer updateByPrimaryKey(AccountPrivRelationVO record){
    	AccountPrivRelation accountPrivRelation = BeanCopyUtil.copy(record, AccountPrivRelation.class);
        return accountPrivRelationMapper.updateByPrimaryKey(accountPrivRelation);
    }

	@Override
    public Integer updateByPrimaryKeySelective(AccountPrivRelationVO record){
        AccountPrivRelation accountPrivRelation = BeanCopyUtil.copy(record, AccountPrivRelation.class);
        return accountPrivRelationMapper.updateByPrimaryKeySelective(accountPrivRelation);
    }

	@Override
    public Integer deleteByPrimaryKey(Long key){
        return accountPrivRelationMapper.deleteByPrimaryKey(key);
    }
    
    /**
	 *  IN
	 *	<foreach collection="keys" open="(" close=")" item="key" separator=",">
	 *		{key}
	 *	</foreach>
	 */
    @Override
    public Integer deleteByPrimaryKeys(Set<Long> keys){
    	//return accountPrivRelationMapper.deleteByPrimaryKeys(keys);
        return null;
    }
    
    @Override
	public Integer keep(List<AccountPrivRelationVO> accountPrivRelationVOs) {
		int result = 0;
//		Set<Long> keys = new HashSet<>();
		for (AccountPrivRelationVO accountPrivRelationVO : accountPrivRelationVOs) {
			String opt = accountPrivRelationVO.getOpt();
			if (ParamType.DELETE.getMsg().equals(opt)) {
//				keys.add(accountPrivRelationVO.getId());
				int i = deleteByPrimaryKey(accountPrivRelationVO.getId());
				if (i > 0) {
					result++;
				}
			} else if (ParamType.UPDATE.getMsg().equals(opt)) {
				int i = updateByPrimaryKeySelective(accountPrivRelationVO);
				if (i > 0) {
					result++;
				}
			} else if (ParamType.INSERT.getMsg().equals(opt)) {
				int i = insertSelective(accountPrivRelationVO);
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