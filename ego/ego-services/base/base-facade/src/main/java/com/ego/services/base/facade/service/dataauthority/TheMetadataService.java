package com.ego.services.base.facade.service.dataauthority;

import java.util.List;
import java.util.Set;

import com.ebase.core.page.PageInfo;
import com.ego.services.base.api.vo.dataauthority.TheMetadataVO;

/**
 * dal Interface:TheMetadata
 * @author Mrli
 * @date 2018-10-24
 */
public interface TheMetadataService {

	/**
	 * 查询全部
	 * 
	 * @param
	 * @return List
	 */
    public List<TheMetadataVO> findAll();

	/**
	 * 条件查询
	 * 
	 * @param record
	 * @return List
	 */
    public PageInfo<TheMetadataVO> findSelective(TheMetadataVO record);

	/**
	 * 查询一条
	 * 
	 * @param key
	 * @return VO
	 */
    public TheMetadataVO getByPrimaryKey(Long key);

	/**
	 * 增加
	 * 
	 * @param record
	 * @return Integer
	 */
    public Integer insert(TheMetadataVO record);
    
    /**
	 * 非空增加
	 * 
	 * @param record
	 * @return Integer
	 */
    public Integer insertSelective(TheMetadataVO record);
	
	/**
	 * 根据主键更新
	 * 
	 * @param record
	 * @return Integer
	 */
    public Integer updateByPrimaryKey(TheMetadataVO record);
    
    /**
	 * 根据主键非空更新
	 * 
	 * @param record
	 * @return Integer
	 */
    public Integer updateByPrimaryKeySelective(TheMetadataVO record);

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
    public Integer keep(List<TheMetadataVO> theMetadataVOs);

}