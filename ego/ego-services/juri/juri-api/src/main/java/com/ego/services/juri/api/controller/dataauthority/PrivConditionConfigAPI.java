package com.ego.services.juri.api.controller.dataauthority;

import java.util.List;

import com.ego.services.juri.api.vo.dataauthority.PrivConditionConfigVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ebase.core.page.PageDTO;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;

/**
 * api :PrivConditionConfig
 * @author Mrli
 * @date 2018-11-1
 */
 
@FeignClient(value = "${ser.name.juri}") // 这个是服务名
public interface PrivConditionConfigAPI {
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/privConditionConfig/save")
	public ServiceResponse<Integer> save(@RequestBody JsonRequest<PrivConditionConfigVO> jsonRequest);
	
	/**
	 * 更新
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/privConditionConfig/update")
	public ServiceResponse<Integer> update(@RequestBody JsonRequest<PrivConditionConfigVO> jsonRequest);
	
	/**
	 * 删除
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/privConditionConfig/delete")
	public ServiceResponse<Integer> delete(@RequestBody JsonRequest<PrivConditionConfigVO> jsonRequest);
	
	/**
	 * 查询单条记录
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/privConditionConfig/getdetails")
	public ServiceResponse<PrivConditionConfigVO> getDetails(@RequestBody JsonRequest<PrivConditionConfigVO> jsonRequest);
	
	/**
	 * 分页查询
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/privConditionConfig/findpageresult")
	public ServiceResponse<PageDTO<PrivConditionConfigVO>> findPageResult(@RequestBody JsonRequest<PrivConditionConfigVO> jsonRequest);
	
	/**
	 * 批量 保存、修改、删除
	 * 参数：opt insert、update、delete
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/privConditionConfig/keep")
	public ServiceResponse<Integer> keep(@RequestBody JsonRequest<List<PrivConditionConfigVO>> jsonRequest);
    
    
}