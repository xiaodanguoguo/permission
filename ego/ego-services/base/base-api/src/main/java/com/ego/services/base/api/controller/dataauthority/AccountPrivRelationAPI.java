package com.ego.services.base.api.controller.dataauthority;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ebase.core.page.PageDTO;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;
import com.ego.services.base.api.vo.dataauthority.AccountPrivRelationVO;

/**
 * api :AccountPrivRelation
 * @author Mrli
 * @date 2018-11-1
 */
 
@FeignClient(value = "${ser.name.order}") // 这个是服务名
public interface AccountPrivRelationAPI {
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/accountPrivRelation/save")
	public ServiceResponse<Integer> save(@RequestBody JsonRequest<AccountPrivRelationVO> jsonRequest);
	
	/**
	 * 更新
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/accountPrivRelation/update")
	public ServiceResponse<Integer> update(@RequestBody JsonRequest<AccountPrivRelationVO> jsonRequest);
	
	/**
	 * 删除
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/accountPrivRelation/delete")
	public ServiceResponse<Integer> delete(@RequestBody JsonRequest<AccountPrivRelationVO> jsonRequest);
	
	/**
	 * 查询单条记录
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/accountPrivRelation/getdetails")
	public ServiceResponse<AccountPrivRelationVO> getDetails(@RequestBody JsonRequest<AccountPrivRelationVO> jsonRequest);
	
	/**
	 * 分页查询
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/accountPrivRelation/findpageresult")
	public ServiceResponse<PageDTO<AccountPrivRelationVO>> findPageResult(@RequestBody JsonRequest<AccountPrivRelationVO> jsonRequest);
	
	/**
	 * 批量 保存、修改、删除
	 * 参数：opt insert、update、delete
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/accountPrivRelation/keep")
	public ServiceResponse<Integer> keep(@RequestBody JsonRequest<List<AccountPrivRelationVO>> jsonRequest);
    
    
}