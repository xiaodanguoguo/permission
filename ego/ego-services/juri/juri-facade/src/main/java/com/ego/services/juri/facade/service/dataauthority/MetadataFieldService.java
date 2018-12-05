package com.ego.services.juri.facade.service.dataauthority;

import java.util.List;
import java.util.Set;

import com.ebase.core.page.PageInfo;
import com.ego.services.juri.api.vo.dataauthority.MetadataFieldVO;

/**
 * dal Interface:MetadataField
 * @author Mrli
 * @date 2018-10-24
 */
public interface MetadataFieldService {

	/**
	 * 查询全部
	 * 
	 * @param
	 * @return List
	 */
    public List<MetadataFieldVO> findAll();

	/**
	 * 条件查询
	 * 
	 * @param record
	 * @return List
	 */
    public PageInfo<MetadataFieldVO> findSelective(MetadataFieldVO record);

	/**
	 * 查询一条
	 * 
	 * @param key
	 * @return VO
	 */
    public MetadataFieldVO getByPrimaryKey(Long key);

	/**
	 * 增加
	 * 
	 * @param record
	 * @return Integer
	 */
    public Integer insert(MetadataFieldVO record);
    
    /**
	 * 非空增加
	 * 
	 * @param record
	 * @return Integer
	 */
    public Integer insertSelective(MetadataFieldVO record);
	
	/**
	 * 根据主键更新
	 * 
	 * @param record
	 * @return Integer
	 */
    public Integer updateByPrimaryKey(MetadataFieldVO record);
    
    /**
	 * 根据主键非空更新
	 * 
	 * @param record
	 * @return Integer
	 */
    public Integer updateByPrimaryKeySelective(MetadataFieldVO record);

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
    public Integer keep(List<MetadataFieldVO> metadataFieldVOs);

}