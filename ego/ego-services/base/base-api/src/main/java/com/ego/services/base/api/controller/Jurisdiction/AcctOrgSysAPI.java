package com.ego.services.base.api.controller.jurisdiction;

import java.util.List;

import com.ebase.core.page.PageInfo;
import com.ego.services.base.api.vo.jurisdiction.AcctOrgSysVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;

/**
 * api :AcctOrgSys
 * @author 
 * @date 2018-10-10
 */
 
@FeignClient(value = "${ser.name.base}") // 这个是服务名
public interface AcctOrgSysAPI {
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/acctOrgSys/save")
	public ServiceResponse<Integer> save(@RequestBody JsonRequest<AcctOrgSysVO> jsonRequest);

	/**
	 * 保存
	 *
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/acctOrgSys/saveOrgInfo")
	public ServiceResponse<Integer> saveOrgInfo(@RequestBody JsonRequest<AcctOrgSysVO> jsonRequest);
	
	/**
	 * 更新
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/acctOrgSys/update")
	public ServiceResponse<Integer> update(@RequestBody JsonRequest<AcctOrgSysVO> jsonRequest);
	
	/**
	 * 删除
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/acctOrgSys/delete")
	public ServiceResponse<Integer> delete(@RequestBody JsonRequest<AcctOrgSysVO> jsonRequest);
	
	/**
	 * 查询单条记录
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/acctOrgSys/queryDetails")
	public ServiceResponse<AcctOrgSysVO> queryDetails(@RequestBody JsonRequest<AcctOrgSysVO> jsonRequest);
	
	/**
	 * 分页查询
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/acctOrgSys/queryPagedResult")
	public ServiceResponse<PageInfo<AcctOrgSysVO>> queryPagedResult(@RequestBody JsonRequest<AcctOrgSysVO> jsonRequest);
	
	/**
	 * 批量 保存、修改、删除
	 * 参数：opt insert、update、delete
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/acctOrgSys/keep")
	public ServiceResponse<Integer> keep(@RequestBody JsonRequest<List<AcctOrgSysVO>> jsonRequest);
    
    
}