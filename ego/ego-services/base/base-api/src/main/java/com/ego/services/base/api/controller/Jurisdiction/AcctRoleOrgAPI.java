package com.ego.services.base.api.controller.jurisdiction;

import com.ebase.core.page.PageInfo;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;
import com.ego.services.base.api.vo.jurisdiction.AcctRoleOrgVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * api :AcctOrgSys
 * @author 
 * @date 2018-10-10
 */
 
@FeignClient(value = "${ser.name.base}") // 这个是服务名
public interface AcctRoleOrgAPI {
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/acctRoleOrg/save")
	public ServiceResponse<Integer> save(@RequestBody JsonRequest<AcctRoleOrgVO> jsonRequest);

	/**
	 * 保存
	 *
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/acctRoleOrg/saveOrgInfo")
	public ServiceResponse<Integer> saveOrgInfo(@RequestBody JsonRequest<AcctRoleOrgVO> jsonRequest);
	
	/**
	 * 更新
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/acctRoleOrg/update")
	public ServiceResponse<Integer> update(@RequestBody JsonRequest<AcctRoleOrgVO> jsonRequest);
	
	/**
	 * 删除
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/acctRoleOrg/delete")
	public ServiceResponse<Integer> delete(@RequestBody JsonRequest<AcctRoleOrgVO> jsonRequest);
	
	/**
	 * 查询单条记录
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/acctRoleOrg/queryDetails")
	public ServiceResponse<AcctRoleOrgVO> queryDetails(@RequestBody JsonRequest<AcctRoleOrgVO> jsonRequest);
	
	/**
	 * 分页查询
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/acctRoleOrg/queryPagedResult")
	public ServiceResponse<PageInfo<AcctRoleOrgVO>> queryPagedResult(@RequestBody JsonRequest<AcctRoleOrgVO> jsonRequest);
	
	/**
	 * 批量 保存、修改、删除
	 * 参数：opt insert、update、delete
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/acctRoleOrg/keep")
	public ServiceResponse<Integer> keep(@RequestBody JsonRequest<List<AcctRoleOrgVO>> jsonRequest);
    
    
}