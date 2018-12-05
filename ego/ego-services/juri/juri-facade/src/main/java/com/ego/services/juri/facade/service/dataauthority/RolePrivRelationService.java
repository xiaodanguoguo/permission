package com.ego.services.juri.facade.service.dataauthority;

import com.ego.services.juri.api.vo.dataauthority.RolePrivRelationVO;

import java.util.List;
import java.util.Set;

/**
 * dal Interface:RolePrivRelation
 * @author Mrli
 * @date 2018-11-1
 */
public interface RolePrivRelationService {

	/**
	 * 查询全部
	 * 
	 * @param
	 * @return List
	 */
    public List<RolePrivRelationVO> findAll();

	/**
	 * 条件查询
	 * 
	 * @param record
	 * @return List
	 */
    public List<RolePrivRelationVO> findSelective(RolePrivRelationVO record);

	/**
	 * 查询一条
	 * 
	 * @param key
	 * @return VO
	 */
    public RolePrivRelationVO getByPrimaryKey(Long key);

	/**
	 * 增加
	 * 
	 * @param record
	 * @return Integer
	 */
    public Integer insert(RolePrivRelationVO record);
    
    /**
	 * 非空增加
	 * 
	 * @param record
	 * @return Integer
	 */
    public Integer insertSelective(RolePrivRelationVO record);
	
	/**
	 * 根据主键更新
	 * 
	 * @param record
	 * @return Integer
	 */
    public Integer updateByPrimaryKey(RolePrivRelationVO record);
    
    /**
	 * 根据主键非空更新
	 * 
	 * @param record
	 * @return Integer
	 */
    public Integer updateByPrimaryKeySelective(RolePrivRelationVO record);

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
    public Integer keep(List<RolePrivRelationVO> rolePrivRelationVOs);

}