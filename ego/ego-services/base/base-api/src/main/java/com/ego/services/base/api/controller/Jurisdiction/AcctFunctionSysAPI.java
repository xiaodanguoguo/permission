package com.ego.services.base.api.controller.jurisdiction;

import java.util.List;

import com.ebase.core.page.PageInfo;
import com.ego.services.base.api.vo.jurisdiction.AcctFunctionSysVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;

/**
 * api :AcctFunctionSys
 * @author 
 * @date 2018-10-10
 */
 
@FeignClient(value = "${ser.name.base}") // 这个是服务名
public interface AcctFunctionSysAPI {
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/acctFunctionSys/save")
	public ServiceResponse<Integer> save(@RequestBody JsonRequest<AcctFunctionSysVO> jsonRequest);
	
	/**
	 * 更新
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/acctFunctionSys/update")
	public ServiceResponse<Integer> update(@RequestBody JsonRequest<AcctFunctionSysVO> jsonRequest);
	
	/**
	 * 删除
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/acctFunctionSys/delete")
	public ServiceResponse<Integer> delete(@RequestBody JsonRequest<AcctFunctionSysVO> jsonRequest);
	
	/**
	 * 查询单条记录
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/acctFunctionSys/queryDetails")
	public ServiceResponse<AcctFunctionSysVO> queryDetails(@RequestBody JsonRequest<AcctFunctionSysVO> jsonRequest);
	
	/**
	 * 分页查询
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/acctFunctionSys/queryPagedResult")
	public ServiceResponse<PageInfo<AcctFunctionSysVO>> queryPagedResult(@RequestBody JsonRequest<AcctFunctionSysVO> jsonRequest);
	
	/**
	 * 批量 保存、修改、删除
	 * 参数：opt insert、update、delete
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/acctFunctionSys/keep")
	public ServiceResponse<Integer> keep(@RequestBody JsonRequest<List<AcctFunctionSysVO>> jsonRequest);
    
    
}