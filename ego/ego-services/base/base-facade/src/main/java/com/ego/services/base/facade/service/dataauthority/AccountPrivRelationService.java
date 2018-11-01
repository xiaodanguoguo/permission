package com.ego.services.base.facade.service.dataauthority;

import com.ego.services.base.api.vo.dataauthority.AccountPrivRelationVO;

import java.util.List;
import java.util.Set;

/**
 * dal Interface:AccountPrivRelation
 * @author Mrli
 * @date 2018-11-1
 */
public interface AccountPrivRelationService {

	/**
	 * 查询全部
	 * 
	 * @param
	 * @return List
	 */
    public List<AccountPrivRelationVO> findAll();

	/**
	 * 条件查询
	 * 
	 * @param record
	 * @return List
	 */
    public List<AccountPrivRelationVO> findSelective(AccountPrivRelationVO record);

	/**
	 * 查询一条
	 * 
	 * @param key
	 * @return VO
	 */
    public AccountPrivRelationVO getByPrimaryKey(Long key);

	/**
	 * 增加
	 * 
	 * @param record
	 * @return Integer
	 */
    public Integer insert(AccountPrivRelationVO record);
    
    /**
	 * 非空增加
	 * 
	 * @param record
	 * @return Integer
	 */
    public Integer insertSelective(AccountPrivRelationVO record);
	
	/**
	 * 根据主键更新
	 * 
	 * @param record
	 * @return Integer
	 */
    public Integer updateByPrimaryKey(AccountPrivRelationVO record);
    
    /**
	 * 根据主键非空更新
	 * 
	 * @param record
	 * @return Integer
	 */
    public Integer updateByPrimaryKeySelective(AccountPrivRelationVO record);

	/**
	 * 根据主键删除
	 * 
	 * @param key
	 * @return Integer
	 */
    public Integer deleteByPrimaryKey(Long key);
	
	/**
	 * 根据主键批量删除
	 * 
	 * @param keys
	 * @return Integer
	 */
    public Integer deleteByPrimaryKeys(Set<Long> keys);
    
    /**
     * 批量 保存、修改、删除
     * @param meMaterielTypeVOs
     * @return
     */
    public Integer keep(List<AccountPrivRelationVO> accountPrivRelationVOs);

}