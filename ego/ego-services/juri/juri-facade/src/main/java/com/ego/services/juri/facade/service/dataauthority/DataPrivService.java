package com.ego.services.juri.facade.service.dataauthority;

import java.util.List;
import java.util.Set;
import com.ego.services.juri.api.vo.dataauthority.DataPrivVO;

/**
 * dal Interface:DataPriv
 * @author Mrli
 * @date 2018-11-1
 */
public interface DataPrivService {

	/**
	 * 查询全部
	 * 
	 * @param
	 * @return List
	 */
    public List<DataPrivVO> findAll();

	/**
	 * 条件查询
	 * 
	 * @param record
	 * @return List
	 */
    public List<DataPrivVO> findSelective(DataPrivVO record);

	/**
	 * 查询一条
	 * 
	 * @param key
	 * @return VO
	 */
    public DataPrivVO getByPrimaryKey(Long key);

	/**
	 * 增加
	 * 
	 * @param record
	 * @return Integer
	 */
    public Integer insert(DataPrivVO record);
    
    /**
	 * 非空增加
	 * 
	 * @param record
	 * @return Integer
	 */
    public Integer insertSelective(DataPrivVO record);
	
	/**
	 * 根据主键更新
	 * 
	 * @param record
	 * @return Integer
	 */
    public Integer updateByPrimaryKey(DataPrivVO record);
    
    /**
	 * 根据主键非空更新
	 * 
	 * @param record
	 * @return Integer
	 */
    public Integer updateByPrimaryKeySelective(DataPrivVO record);

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
    public Integer keep(List<DataPrivVO> dataPrivVOs);

}