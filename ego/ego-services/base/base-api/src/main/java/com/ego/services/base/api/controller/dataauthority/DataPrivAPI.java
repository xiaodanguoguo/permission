package com.ego.services.base.api.controller.dataauthority;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ebase.core.page.PageDTO;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;
import com.ego.services.base.api.vo.dataauthority.DataPrivVO;

/**
 * api :DataPriv
 * @author Mrli
 * @date 2018-11-1
 */
 
@FeignClient(value = "${ser.name.base}") // 这个是服务名
public interface DataPrivAPI {
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/dataPriv/save")
	public ServiceResponse<Integer> save(@RequestBody JsonRequest<DataPrivVO> jsonRequest);
	
	/**
	 * 更新
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/dataPriv/update")
	public ServiceResponse<Integer> update(@RequestBody JsonRequest<DataPrivVO> jsonRequest);
	
	/**
	 * 删除
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/dataPriv/delete")
	public ServiceResponse<Integer> delete(@RequestBody JsonRequest<DataPrivVO> jsonRequest);
	
	/**
	 * 查询单条记录
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/dataPriv/getdetails")
	public ServiceResponse<DataPrivVO> getDetails(@RequestBody JsonRequest<DataPrivVO> jsonRequest);
	
	/**
	 * 分页查询
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/dataPriv/findpageresult")
	public ServiceResponse<PageDTO<DataPrivVO>> findPageResult(@RequestBody JsonRequest<DataPrivVO> jsonRequest);
	
	/**
	 * 批量 保存、修改、删除
	 * 参数：opt insert、update、delete
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/dataPriv/keep")
	public ServiceResponse<Integer> keep(@RequestBody JsonRequest<List<DataPrivVO>> jsonRequest);
    
    
}