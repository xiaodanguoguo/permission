package com.ego.services.juri.api.controller.dataauthority;

import java.util.List;

import com.ebase.core.page.PageInfo;
import com.ego.services.juri.api.vo.dataauthority.TheMetadataVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;

/**
 * api :TheMetadata
 * @author Mrli
 * @date 2018-10-24
 */
 
@FeignClient(value = "${ser.name.juri}") // 这个是服务名
public interface TheMetadataAPI {
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/theMetadata/save")
	public ServiceResponse<Integer> save(@RequestBody JsonRequest<TheMetadataVO> jsonRequest);
	
	/**
	 * 更新
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/theMetadata/update")
	public ServiceResponse<Integer> update(@RequestBody JsonRequest<TheMetadataVO> jsonRequest);
	
	/**
	 * 删除
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/theMetadata/delete")
	public ServiceResponse<Integer> delete(@RequestBody JsonRequest<TheMetadataVO> jsonRequest);
	
	/**
	 * 查询单条记录
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/theMetadata/getdetails")
	public ServiceResponse<TheMetadataVO> getDetails(@RequestBody JsonRequest<TheMetadataVO> jsonRequest);
	
	/**
	 * 分页查询
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/theMetadata/findpageresult")
	public ServiceResponse<PageInfo<TheMetadataVO>> findPageResult(@RequestBody JsonRequest<TheMetadataVO> jsonRequest);
	
	/**
	 * 批量 保存、修改、删除
	 * 参数：opt insert、update、delete
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/theMetadata/keep")
	public ServiceResponse<Integer> keep(@RequestBody JsonRequest<List<TheMetadataVO>> jsonRequest);
    
    
}