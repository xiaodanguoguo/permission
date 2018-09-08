package com.ebase.core.dict.cache.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.ebase.core.conf.PropertyConfigurer;
import com.ebase.core.dict.constant.DictTreeConstant;
import com.ebase.core.dict.interfaces.DictCacheService;
import com.ebase.core.dict.interfaces.DictOperatorFacade;
import com.ebase.core.dict.model.DictTreeDTO;
import com.ebase.core.dict.model.DictTreeDTOResult;
import com.ebase.core.dict.model.DictValueDTO;
import com.ebase.core.service.ServiceResponse;
import com.ebase.utils.BeanCopyUtil;

@Component
public class DictCacheServiceImpl implements DictCacheService {

	@Autowired
	private DictOperatorFacade dictOperatorFacade;
	@Autowired
	private RestTemplate restTemplate;
	private String initURL = PropertyConfigurer.getStringProperty("init.url");
	/*
	 * public void setDictOperatorFacade(DictOperatorFacade dictOperatorFacade)
	 * { this.dictOperatorFacade = dictOperatorFacade; }
	 */

	// 字典缓存
	private Map<String, LinkedHashMap<String, DictValueDTO>> dictCache;
	// 区域类目缓存
	private Map<String, Map<String, DictTreeDTO>> dictTreeCache;

	/**
	 * 初始化集合里面的所有数据
	 */
//	@PostConstruct
	public void init() {
		initDictTree();
		initDictCache();
	}

	/**
	 * 初始化类目和区域树
	 */
	private void initDictTree() {
		this.dictTreeCache = new HashMap<String, Map<String, DictTreeDTO>>();
		initMaterielTypeTree();
		initSupplierTypeTree();
		initRegionTypeTree();
	}
	
	private void initMaterielTypeTree(){
		// 物料大中页类
		List<DictTreeDTO> dictTreeMaterielType = getDictTrees(DictTreeConstant.MATERIEL_TYPE);
		this.dictTreeCache.put(DictTreeConstant.MATERIEL_TYPE, new HashMap<String, DictTreeDTO>());
		createTree(DictTreeConstant.MATERIEL_TYPE, dictTreeMaterielType);
	}
	
	private void initSupplierTypeTree(){
		// 供应商类型
		List<DictTreeDTO> dictTreeSupplierType = getDictTrees(DictTreeConstant.SUPPLIER_TYPE);
		this.dictTreeCache.put(DictTreeConstant.SUPPLIER_TYPE, new HashMap<String, DictTreeDTO>());
		createTree(DictTreeConstant.SUPPLIER_TYPE, dictTreeSupplierType);
	}
	
	private void initRegionTypeTree(){
		// 区域字典
		List<DictTreeDTO> dictTreeDictRegionType = getDictTrees(DictTreeConstant.DICT_REGION);
		this.dictTreeCache.put(DictTreeConstant.DICT_REGION, new HashMap<String, DictTreeDTO>());
		createTree(DictTreeConstant.DICT_REGION, dictTreeDictRegionType);
	}
	
	/**
	 * 初始化系统字典
	 */
	private void initDictCache() {
		this.dictCache = new HashMap<String, LinkedHashMap<String, DictValueDTO>>();
		saveDict(dictOperatorFacade.findAllDicts());
	}

	/**
	 * 通过类型获取树
	 * 
	 * @param treeType
	 * @return
	 */
	private List<DictTreeDTO> getDictTrees(String treeType) {
		@SuppressWarnings("unchecked")
		ServiceResponse<List<Object>> result = restTemplate.postForObject(initURL + "方法名", "参数值", ServiceResponse.class);
		System.out.println(result);
		List<DictTreeDTO> list = dictOperatorFacade.findAllDictTreeByType(treeType);
		return list;
	}

	/**
	 * 构造树形结构
	 * 
	 * @param
	 */
	private void createTree(String dictTreeType, List<DictTreeDTO> dictTree) {
		Map<String, DictTreeDTO> dictTreeMap = this.dictTreeCache.get(dictTreeType);
		for (DictTreeDTO node : dictTree)
			dictTreeMap.put(node.getDictTreeCode(), node);

		DictTreeDTO root = new DictTreeDTO();
		for (DictTreeDTO node : dictTree) {
			DictTreeDTO pNode = null;
			if (isRoot(node.getDictTreeCode()))
				pNode = root;
			else
				pNode = dictTreeMap.get(node.getParentCode());

			if (!node.getParentCode().equals(node.getDictTreeCode()))
				node.setParentNode(pNode);

			pNode.getChildren().add(node);
		}
	}

	private boolean isRoot(String dictTreeCode) {
		return DictTreeConstant.DICT_TREE_ROOT_CATEGORY.equals(dictTreeCode);
	}

	// 保存Dict数据

	private void saveDict(List<DictValueDTO> dictValues) {
		Map<String, List<DictValueDTO>> dictValuesMap = dictValues.stream().collect(Collectors.groupingBy(DictValueDTO::getCodeType));
		dictValuesMap.forEach((k,v) -> {
			LinkedHashMap<String, DictValueDTO> dictMap = new LinkedHashMap<String, DictValueDTO>();
			v.forEach(dictValue -> {
				dictMap.put(dictValue.getCodeName(), dictValue);
			});
			this.dictCache.put(k, dictMap);
		});
		
		/*for (int i = 0; i < dictValues.size(); i++) {
			if (i < dictValues.size() - 1 && dictValues.get(i).getCodeType().equals(dictValues.get(i + 1).getCodeType()))
				dictMap.put(dictValues.get(i).getCodeName(), dictValues.get(i));

			else if (i < dictValues.size() - 1 && !dictValues.get(i).getCodeType().equals(dictValues.get(i + 1).getCodeType())) {
				dictMap.put(dictValues.get(i).getCodeName(), dictValues.get(i));
				this.dictCache.put(dictValues.get(i).getCodeType(), dictMap);
				dictMap = new LinkedHashMap<String, DictValueDTO>();

			} else {
				if (dictValues.get(i).getCodeType().equals(dictValues.get(i - 1).getCodeType())) {
					dictMap.put(dictValues.get(i).getCodeName(), dictValues.get(i));
					this.dictCache.put(dictValues.get(i).getCodeType(), dictMap);

				} else {
					dictMap = dictMap.size() == 0 ? dictMap : new LinkedHashMap<String, DictValueDTO>();
					dictMap.put(dictValues.get(i).getCodeName(), dictValues.get(i));
					this.dictCache.put(dictValues.get(i).getCodeType(), dictMap);
				}
			}
		}*/
	}

	private boolean isNull(Map<String, ? extends Object> map) {
		return map == null || map.size() < 1;
	}

	private boolean isNull(Collection<? extends Object> collection) {
		return collection == null || collection.size() < 1;
	}

	private boolean isNull(Object obj) {
		return obj == null;
	}

	/**
	 * 通过dictTreeCode拿到父节点
	 * 
	 * @param dictTreeType
	 * @param dictTreeCode
	 * @return
	 */
	@Override
	public DictTreeDTOResult getDictTreeParentValueByCode(String dictTreeType, String dictTreeCode) {
		if (isNull(this.dictTreeCache))
			initDictTree();
		DictTreeDTOResult result = new DictTreeDTOResult();
		DictTreeDTO parentNode = this.dictTreeCache.get(dictTreeType).get(dictTreeCode).getParentNode();
		if (!isNull(parentNode))
			BeanCopyUtil.copyProperties(parentNode, result);
		return result;
	}

	/**
	 * 删除字典缓存
	 */
	@Override
	public void removeDictTreeCache(String dictTreeType, DictTreeDTO dictTreeDTO) {
		Map<String, DictTreeDTO> dictTreeMap = dictTreeCache.get(dictTreeType);
		// 新增或修改类目
		// 从缓存里面获取父节点，然后清空子节点
		DictTreeDTO parentNode = dictTreeMap.get(dictTreeDTO.getParentCode());
		List<DictTreeDTO> children = null;
		if (!isNull(parentNode)) {
			children = parentNode.getChildren();
			removeDictTreeCache(dictTreeMap, children);
		} else {
			// 删除类目 说明缓存里面有，拿到当前节点的父节点把子节点
			parentNode = dictTreeMap.get(dictTreeDTO.getDictTreeCode());
			if (isNull(parentNode))
				return;
			children = parentNode.getParentNode().getChildren();
			removeDictTreeCache(dictTreeMap, children);
		}
	}

	private void removeDictTreeCache(Map<String, DictTreeDTO> dictTreeMap, List<DictTreeDTO> dictTrees) {
		if (!isNull(dictTrees))
			for (DictTreeDTO dictTree : dictTrees)
				dictTreeMap.remove(dictTree.getDictTreeCode());
		dictTrees.clear();
	}

	/**
	 * 通过code删掉字典数据
	 * 
	 * @return
	 */
	@Override
	public void removeDictValueByCode(DictValueDTO dictValueDTO) {
		dictCache.get(dictValueDTO.getCodeType()).clear();
		dictCache.remove(dictValueDTO.getCodeType());
	}

	/**
	 * 获取所有的缓存
	 * 
	 * @return
	 */
	public Map<String, String> getAllNativeCache() {
		Map<String, String> resultMap = new TreeMap<String, String>();
		resultMap.putAll(getDictNativeCache());
		return resultMap;
	}

	/**
	 * 获取树形结构
	 * 
	 * @param dictTreeType
	 * @return
	 */
	/*
	 * private Map<String, String> getDictTreeMap(String dictTreeType) {
	 * TreeMap<String, String> resultMap = new TreeMap<String, String>();
	 * Map<String, DictTreeDTO> category = this.dictTreeCache.get(dictTreeType);
	 * if (!isNull(category)) for (Map.Entry<String, DictTreeDTO> entry :
	 * category.entrySet()) { DictTreeDTO value = entry.getValue(); if
	 * (isRoot(value.getDictTreeCode())) continue; DictTreeDTOResult result =
	 * new DictTreeDTOResult(); result.setDictTreeCode(value.getDictTreeCode());
	 * result.setDictTreeName(value.getDictTreeName());
	 * result.setParentCode(value.getParentCode());
	 * result.setChildSize(value.getChildren().size());
	 * resultMap.put(entry.getKey(), JSON.toJSONString(result)); } return
	 * resultMap; }
	 */

	/**
	 * 获取字典缓存
	 * 
	 * @return
	 */
	public Map<String, String> getDictNativeCache() {
		TreeMap<String, String> resultMap = new TreeMap<String, String>();
		if (!isNull(dictCache))
			for (Map.Entry<String, LinkedHashMap<String, DictValueDTO>> entry : this.dictCache.entrySet())
				for (Map.Entry<String, DictValueDTO> dictEntry : entry.getValue().entrySet())
					resultMap.put(dictEntry.getKey(), JSON.toJSONString(dictEntry.getValue()));
		return resultMap;
	}

	@Override
	public List<DictTreeDTOResult> findDictTreeValueByParentCode(String dictTreeType, String parentCode)
			throws Exception {
		if (isNull(this.dictTreeCache))
			initDictTree();

		List<DictTreeDTO> result = this.dictTreeCache.get(dictTreeType).get(parentCode).getChildren();
		if (isNull(result)) {
			// 拿key和缓存的key做匹配
			result = dictOperatorFacade.findDictTreeByParentCode(dictTreeType, parentCode).get(dictTreeType);
			createTree(dictTreeType, result);
		}

		List<DictTreeDTOResult> dictTreeDTOResults = BeanCopyUtil.copyList(result, DictTreeDTOResult.class);
		// BeanUtils.copyPropertieses(result, new
		// ArrayList<DictTreeDTOResult>(result.size()),
		// DictTreeDTOResult.class);
		// 子类的数量
		for (int i = 0; i < result.size(); i++)
			dictTreeDTOResults.get(i).setChildSize(result.get(i).getChildren().size());

		return dictTreeDTOResults;
	}

	@Override
	public DictTreeDTOResult getDictTreeValueByCode(String dictTreeType, String dictTreeCode) {
		if (isNull(this.dictTreeCache))
			initDictTree();

		DictTreeDTO dictTreeDTO = this.dictTreeCache.get(dictTreeType).get(dictTreeCode);
		if (isNull(dictTreeDTO))
			dictTreeDTO = dictOperatorFacade.getDictTreeByCode(dictTreeType, dictTreeCode);
		// if (!isNull(dictTreeDTO) && !isNull(dictTreeDTO.getDictTreeCode())) {
		// List<DictTreeDTO> dictTrees = new ArrayList<DictTreeDTO>();
		// dictTrees.add(dictTreeDTO);
		// createTree(dictTreeType, dictTrees);
		// }
		DictTreeDTOResult dictRegionDTOResult = new DictTreeDTOResult();
		BeanCopyUtil.copyProperties(dictTreeDTO, dictRegionDTOResult);
		dictRegionDTOResult.setChildSize(dictTreeDTO.getChildren().size());
		return dictRegionDTOResult;
	}

	@Override
	public List<DictValueDTO> findDictValueByType(String codeType) throws Exception {
		if (isNull(this.dictCache))
			initDictCache();

		List<DictValueDTO> resultList = new ArrayList<DictValueDTO>();
		LinkedHashMap<String, DictValueDTO> dictValueMap = this.dictCache.get(codeType);
		if (isNull(dictValueMap)){
			saveDict(dictOperatorFacade.findAllDictValueByType(codeType));
			dictValueMap = this.dictCache.get(codeType);
		}
		if (isNull(dictValueMap))
			return new ArrayList<DictValueDTO>();
		Collection<DictValueDTO> values = dictValueMap.values();
		resultList.addAll(values);
		return resultList;
	}

	@Override
	public DictValueDTO getDictValueByCode(String codeType, String codeValue) {
		if (StringUtils.isBlank(codeType) || StringUtils.isBlank(codeValue))
			return null;
		if (isNull(this.dictCache))
			initDictCache();

		LinkedHashMap<String, DictValueDTO> dictValueMap = dictCache.get(codeType);
		DictValueDTO result = isNull(dictValueMap) ? null : dictValueMap.get(codeValue);

		if (isNull(result))
			result = dictOperatorFacade.getDictValueByCode(codeType, codeValue);
		if (StringUtils.isNotBlank(result.getCodeType()))
			this.dictCache.get(codeType).put(codeValue, result);
		return result;
	}

	@Override
	public List<DictTreeDTO> getAllDictTreeByType(String dictTreeType) {
		List<DictTreeDTO> list =  null;
		try {
			if (StringUtils.isBlank(dictTreeType))
				return null;
			if (isNull(this.dictCache))
				initDictCache();
			Map<String, DictTreeDTO> map = dictTreeCache.get(dictTreeType);
			if(isNull(map)){
				if(dictTreeType.equals(DictTreeConstant.MATERIEL_TYPE))
					initMaterielTypeTree();
				if(dictTreeType.equals(DictTreeConstant.SUPPLIER_TYPE))
					initSupplierTypeTree();
				if(dictTreeType.equals(DictTreeConstant.DICT_REGION))
					initRegionTypeTree();
				map = dictTreeCache.get(dictTreeType);
			}
				
			list =  map.get("0").getChildren();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	@Override
	public void removeAllDictTreeByType(String dictTreeType) {
		dictTreeCache.remove(dictTreeType);
	}

	@Override
	public List<DictTreeDTOResult> getSupplierTypeByParentId(String parentId) {
		if (isNull(this.dictTreeCache))
			initDictTree();

		Map<String, DictTreeDTO> map = this.dictTreeCache.get(DictTreeConstant.SUPPLIER_TYPE);
		if (isNull(map)) {
			// 拿key和缓存的key做匹配
			initSupplierTypeTree();
			map = this.dictTreeCache.get(DictTreeConstant.SUPPLIER_TYPE);
			/*result = dictOperatorFacade.findAllDictTreeByType(DictTreeConstant.SUPPLIER_TYPE);
			createTree(dictTreeType, result);*/
		}
		DictTreeDTO dictTreeDTO = map.get(parentId);
		if(isNull(dictTreeDTO))
			return new ArrayList<>();
		List<DictTreeDTO> result = dictTreeDTO.getChildren();
		List<DictTreeDTOResult> dictTreeDTOResults = BeanCopyUtil.copyList(result, DictTreeDTOResult.class);
		// BeanUtils.copyPropertieses(result, new
		// ArrayList<DictTreeDTOResult>(result.size()),
		// DictTreeDTOResult.class);
		// 子类的数量
		for (int i = 0; i < result.size(); i++)
			dictTreeDTOResults.get(i).setChildSize(result.get(i).getChildren().size());

		return dictTreeDTOResults;
	}
	
	@Override
	public List<DictTreeDTOResult> getMaterielTypeByParentId(String parentId) {
		if (isNull(this.dictTreeCache))
			initDictTree();

		Map<String, DictTreeDTO> map = this.dictTreeCache.get(DictTreeConstant.MATERIEL_TYPE);
		if (isNull(map)) {
			// 拿key和缓存的key做匹配
			initMaterielTypeTree();
			map = this.dictTreeCache.get(DictTreeConstant.MATERIEL_TYPE);
			/*result = dictOperatorFacade.findAllDictTreeByType(DictTreeConstant.SUPPLIER_TYPE);
			createTree(dictTreeType, result);*/
		}
		DictTreeDTO dictTreeDTO = map.get(parentId);
		if(isNull(dictTreeDTO))
			return new ArrayList<>();
		List<DictTreeDTO> result = dictTreeDTO.getChildren();
		List<DictTreeDTOResult> dictTreeDTOResults = BeanCopyUtil.copyList(result, DictTreeDTOResult.class);
		// BeanUtils.copyPropertieses(result, new
		// ArrayList<DictTreeDTOResult>(result.size()),
		// DictTreeDTOResult.class);
		// 子类的数量
		for (int i = 0; i < result.size(); i++)
			dictTreeDTOResults.get(i).setChildSize(result.get(i).getChildren().size());

		return dictTreeDTOResults;
	}

	@Override
	public List<DictTreeDTOResult> getDictRegionByParentId(String parentId) {
		if (isNull(this.dictTreeCache))
			initDictTree();

		Map<String, DictTreeDTO> map = this.dictTreeCache.get(DictTreeConstant.DICT_REGION);
		if (isNull(map)) {
			// 拿key和缓存的key做匹配
			initRegionTypeTree();
			map = this.dictTreeCache.get(DictTreeConstant.DICT_REGION);
			/*result = dictOperatorFacade.findAllDictTreeByType(DictTreeConstant.SUPPLIER_TYPE);
			createTree(dictTreeType, result);*/
		}
		DictTreeDTO dictTreeDTO = map.get(parentId);
		if(isNull(dictTreeDTO))
			return new ArrayList<>();
		List<DictTreeDTO> result = dictTreeDTO.getChildren();
		List<DictTreeDTOResult> dictTreeDTOResults = BeanCopyUtil.copyList(result, DictTreeDTOResult.class);
		// BeanUtils.copyPropertieses(result, new
		// ArrayList<DictTreeDTOResult>(result.size()),
		// DictTreeDTOResult.class);
		// 子类的数量
		for (int i = 0; i < result.size(); i++)
			dictTreeDTOResults.get(i).setChildSize(result.get(i).getChildren().size());

		return dictTreeDTOResults;
	}

	@Override
	public void removeSupplierTypeByParentId() {
		this.dictTreeCache.remove(DictTreeConstant.SUPPLIER_TYPE);
	}

	@Override
	public void removeMaterielTypeByParentId() {
		this.dictTreeCache.remove(DictTreeConstant.MATERIEL_TYPE);
	}

	@Override
	public void removeDictRegionByParentId() {
		this.dictTreeCache.remove(DictTreeConstant.DICT_REGION);
	}

}
