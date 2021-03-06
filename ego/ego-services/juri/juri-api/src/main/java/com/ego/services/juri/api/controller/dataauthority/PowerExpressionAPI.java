package com.ego.services.juri.api.controller.dataauthority;

import java.util.List;

import com.ebase.core.page.PageInfo;
import com.ego.services.juri.api.vo.dataauthority.PowerExpressionVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;

/**
 * api :PowerExpression
 * @author Mrli
 * @date 2018-11-1
 */
 
@FeignClient(value = "${ser.name.juri}") // 这个是服务名
public interface PowerExpressionAPI {
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/powerExpression/save")
	public ServiceResponse<Integer> save(@RequestBody JsonRequest<PowerExpressionVO> jsonRequest);
	
	/**
	 * 更新
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/powerExpression/update")
	public ServiceResponse<Integer> update(@RequestBody JsonRequest<PowerExpressionVO> jsonRequest);
	
	/**
	 * 删除
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/powerExpression/delete")
	public ServiceResponse<Integer> delete(@RequestBody JsonRequest<PowerExpressionVO> jsonRequest);
	
	/**
	 * 查询单条记录
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/powerExpression/getdetails")
	public ServiceResponse<PowerExpressionVO> getDetails(@RequestBody JsonRequest<PowerExpressionVO> jsonRequest);
	
	/**
	 * 分页查询
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/powerExpression/findpageresult")
	public ServiceResponse<PageInfo<PowerExpressionVO>> findPageResult(@RequestBody JsonRequest<PowerExpressionVO> jsonRequest);
	
	/**
	 * 批量 保存、修改、删除
	 * 参数：opt insert、update、delete
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/powerExpression/keep")
	public ServiceResponse<Integer> keep(@RequestBody JsonRequest<List<PowerExpressionVO>> jsonRequest);

	/**
	 * 用户有多少数据权限
	 *
	 * @param acctId
	 * @return
	 */
	@RequestMapping("/powerExpression/selectAcctConfig")
	public ServiceResponse<PowerExpressionVO> selectAcctConfig(@RequestBody Long acctId);

}