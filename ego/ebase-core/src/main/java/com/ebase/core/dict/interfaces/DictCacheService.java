package com.ebase.core.dict.interfaces;

import java.util.List;
import java.util.Map;

import com.ebase.core.dict.model.DictTreeDTO;
import com.ebase.core.dict.model.DictTreeDTOResult;
import com.ebase.core.dict.model.DictValueDTO;

public interface DictCacheService {
	
	/**
	 * 根据类型获取树
	 * @param dictTreeType
	 * @return
	 */
	@Deprecated
	public List<DictTreeDTO> getAllDictTreeByType(String dictTreeType);
	
	/**
	 * 根据类型清空树缓存
	 * @param dictTreeType
	 */
	@Deprecated
	public void removeAllDictTreeByType(String dictTreeType);
	
	/**
	 * 根据父id查询供应商类型
	 * @param parentId
	 * @return
	 */
	public List<DictTreeDTOResult> getSupplierTypeByParentId(String parentId);
	
	/**
	 * 根据父id查询物料类型
	 * @param parentId
	 * @return
	 */
	public List<DictTreeDTOResult> getMaterielTypeByParentId(String parentId);
	
	/**
	 * 根据父id查询区域字典类型
	 * @param parentId
	 * @return
	 */
	public List<DictTreeDTOResult> getDictRegionByParentId(String parentId);
	
	/**
	 * 根据父id删除供应商类型
	 * @param parentId
	 * @return
	 */
	public void removeSupplierTypeByParentId();
	
	/**
	 * 根据父id删除物料类型
	 * @param parentId
	 * @return
	 */
	public void removeMaterielTypeByParentId();
	
	/**
	 * 根据父id删除区域字典类型
	 * @param parentId
	 * @return
	 */
	public void removeDictRegionByParentId();
	
	/**start 字典**/
	
	/**
	 * 通过type拿到字典列表
	 * @param codeType
	 * @return
	 */
	public List<DictValueDTO> findDictValueByType(String codeType) throws Exception;
	
	/**
	 * 通过type删掉字典数据
	 * @param dictValueDTO
	 */
	public void removeDictValueByCode(DictValueDTO dictValueDTO);
	
	/************************************************************************************************/
	
	/**
	 * 通过PCode拿到子节点列表
	 * @param parentCode
	 * @return
	 */
	@Deprecated
	public List<DictTreeDTOResult> findDictTreeValueByParentCode(String dictTreeType, String parentCode) throws Exception;
	
	/**
	 * 
	 * 通过dictTreeCode拿到单个节点
	 * @return
	 */
	public DictTreeDTOResult getDictTreeValueByCode(String dictTreeType, String dictTreeCode);
	
	/**
	 * 通过dictTreeCode拿到父节点
	 * @param dictTreeType
	 * @param dictTreeCode
	 * @return
	 */
	public DictTreeDTOResult getDictTreeParentValueByCode(String dictTreeType, String dictTreeCode);
	
	/**
	 * 通过type和code值拿到单个字典
	 * @param codeType
	 * @param codeValue
	 * @return
	 */
	public DictValueDTO getDictValueByCode(String codeType, String codeValue);
	
	/**
	 * 删除区域缓存
	 * @param regionCode
	 * @return
	 */
	public void removeDictTreeCache(String dictTreeType, DictTreeDTO dictTreeDTO);
	
	/**
	 * 获取所有的缓存
	 * @return
	 */
	@Deprecated
	public Map<String, String> getAllNativeCache();
	
	/**
	 * 获取字典缓存
	 * @return
	 */
	@Deprecated
	public Map<String, String> getDictNativeCache();
	
}
