package com.ego.services.juri.api.controller.dataauthority;

import java.util.List;

import com.ego.services.juri.api.vo.dataauthority.RolePrivRelationVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ebase.core.page.PageDTO;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;

/**
 * api :RolePrivRelation
 * @author Mrli
 * @date 2018-11-1
 */
 
@FeignClient(value = "${ser.name.juri}") // 这个是服务名
public interface RolePrivRelationAPI {
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/rolePrivRelation/save")
	public ServiceResponse<Integer> save(@RequestBody JsonRequest<RolePrivRelationVO> jsonRequest);
	
	/**
	 * 更新
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/rolePrivRelation/update")
	public ServiceResponse<Integer> update(@RequestBody JsonRequest<RolePrivRelationVO> jsonRequest);
	
	/**
	 * 删除
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/rolePrivRelation/delete")
	public ServiceResponse<Integer> delete(@RequestBody JsonRequest<RolePrivRelationVO> jsonRequest);
	
	/**
	 * 查询单条记录
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/rolePrivRelation/getdetails")
	public ServiceResponse<RolePrivRelationVO> getDetails(@RequestBody JsonRequest<RolePrivRelationVO> jsonRequest);
	
	/**
	 * 分页查询
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/rolePrivRelation/findpageresult")
	public ServiceResponse<PageDTO<RolePrivRelationVO>> findPageResult(@RequestBody JsonRequest<RolePrivRelationVO> jsonRequest);
	
	/**
	 * 批量 保存、修改、删除
	 * 参数：opt insert、update、delete
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/rolePrivRelation/keep")
	public ServiceResponse<Integer> keep(@RequestBody JsonRequest<List<RolePrivRelationVO>> jsonRequest);
    
    
}