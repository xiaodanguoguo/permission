package com.ego.services.juri.api.controller.dataauthority;

import java.util.List;

import com.ebase.core.page.PageInfo;
import com.ego.services.juri.api.vo.dataauthority.MetadataFieldVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;

/**
 * api :MetadataField
 * @author Mrli
 * @date 2018-10-24
 */
 
@FeignClient(value = "${ser.name.juri}") // 这个是服务名
public interface MetadataFieldAPI {
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/metadataField/save")
	public ServiceResponse<Integer> save(@RequestBody JsonRequest<MetadataFieldVO> jsonRequest);
	
	/**
	 * 更新
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/metadataField/update")
	public ServiceResponse<Integer> update(@RequestBody JsonRequest<MetadataFieldVO> jsonRequest);
	
	/**
	 * 删除
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/metadataField/delete")
	public ServiceResponse<Integer> delete(@RequestBody JsonRequest<MetadataFieldVO> jsonRequest);
	
	/**
	 * 查询单条记录
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/metadataField/getdetails")
	public ServiceResponse<MetadataFieldVO> getDetails(@RequestBody JsonRequest<MetadataFieldVO> jsonRequest);
	
	/**
	 * 分页查询
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/metadataField/findpageresult")
	public ServiceResponse<PageInfo<MetadataFieldVO>> findPageResult(@RequestBody JsonRequest<MetadataFieldVO> jsonRequest);
	
	/**
	 * 批量 保存、修改、删除
	 * 参数：opt insert、update、delete
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/metadataField/keep")
	public ServiceResponse<Integer> keep(@RequestBody JsonRequest<List<MetadataFieldVO>> jsonRequest);
    
    
}