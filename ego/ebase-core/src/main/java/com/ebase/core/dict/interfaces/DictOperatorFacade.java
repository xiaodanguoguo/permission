package com.ebase.core.dict.interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ebase.core.dict.model.DictTreeDTO;
import com.ebase.core.dict.model.DictValueDTO;
import com.ebase.core.web.json.JsonRequest;

/**
 * 内部接口, 做缓存用的
 * @author MrLi on 2018年8月15日
 *
 */
@FeignClient(value = "${ser.name.base}") //这个是服务名
public interface DictOperatorFacade {

	 /**
     * 根据类型获取所有树列表
     * @param busiLineType 
     * @return
     */
	@RequestMapping("/cache/dict/findAllDictTreeByType")
    public List<DictTreeDTO> findAllDictTreeByType(@RequestParam("dictTreeType") String dictTreeType);
	
	/**
     * 根据类型获取所有字典列表
     * @param busiLineType 
     * @return
     */
	@RequestMapping("/cache/dict/findAllDictValueByType")
	public List<DictValueDTO> findAllDictValueByType(@RequestParam("dictValueType") String dictValueType);
	
	 /**
     * 拿到所有的字典(初始化缓存的时候用)
     * 
     * @return
     */
	@RequestMapping("/cache/dict/findAllDicts")
    public List<DictValueDTO> findAllDicts();
	
	/**
	 * test
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/getString")
	public Map<String, String> getString(@RequestBody JsonRequest<Map<String, String>> jsonRequest);
	
	/****************************************************************/
	
	/**
	 * 通过dictTreeType、parentCode拿到子节点列表
	 * @param dictTreeType
	 * @param parentCode
	 * @return
	 */
	@RequestMapping("/cache/dict/findDictTreeByParentCode")
    public Map<String, List<DictTreeDTO>> findDictTreeByParentCode(@RequestParam("dictTreeType") String dictTreeType, @RequestParam("parentCode") String parentCode);
    
    /**
     * 通过type和code值拿到单个字典
     * 
     * @param codeType
     * @param codeValue
     * @return
     */
	@RequestMapping("/cache/dict/getDictValueByCode")
    public DictValueDTO getDictValueByCode(@RequestParam("codeType") String codeType, @RequestParam("codeValue") String codeValue);
    
    /**
     * 通过dictTreeCode拿到单个节点
     * 
     * @param
     * @return
     */
	@RequestMapping("/cache/dict/getDictTreeByCode")
    public DictTreeDTO getDictTreeByCode(@RequestParam("dictTreeType") String dictTreeType, @RequestParam("dictTreeCode") String dictTreeCode);
    
}
