package com.ego.services.juri.api.controller.jurisdiction;

import java.util.List;

import com.ebase.core.page.PageInfo;
import com.ebase.core.web.json.JsonRequest;
import com.ego.services.juri.api.vo.jurisdiction.SysInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ebase.core.service.ServiceResponse;

/**
 * api :SysInfo
 * @author 
 * @date 2018-10-10
 */
 
@FeignClient(value = "${ser.name.juri}") // 这个是服务名
public interface SysInfoAPI {
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/sysInfo/save")
	public ServiceResponse<SysInfoVO> save(@RequestBody SysInfoVO jsonRequest);
	
	/**
	 * 更新
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/sysInfo/update")
	public ServiceResponse<SysInfoVO> update(@RequestBody SysInfoVO jsonRequest);

	/**
	 * 删除 验证是否被组织使用
	 *
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/sysInfo/verSysInfo")
	public ServiceResponse<String> verSysInfo(@RequestBody SysInfoVO jsonRequest);

	/**
	 * 删除 验证是否被组织使用
	 *
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/sysInfo/verInsertSysInfo")
	public ServiceResponse<String> verInsertSysInfo(@RequestBody SysInfoVO jsonRequest);

	/**
	 * 删除
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/sysInfo/delete")
	public ServiceResponse<SysInfoVO> delete(@RequestBody JsonRequest<SysInfoVO> jsonRequest);
	
	/**
	 * 查询单条记录
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/sysInfo/queryDetails")
	public ServiceResponse<SysInfoVO> queryDetails(@RequestBody JsonRequest<SysInfoVO> jsonRequest);
	
	/**
	 * 分页查询
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/sysInfo/queryPagedResult")
	public ServiceResponse<PageInfo<SysInfoVO>> queryPagedResult(@RequestBody JsonRequest<SysInfoVO> jsonRequest);

	/**
	 * 引用查询
	 *
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/sysInfo/selectSysInfoOrg")
	public ServiceResponse<List<SysInfoVO>> selectSysInfoOrg(@RequestBody SysInfoVO jsonRequest);

	/**
	 * 根据组织查询创建系统
	 *
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/sysInfo/selectSysEstablish")
	public ServiceResponse<List<SysInfoVO>> selectSysEstablish(@RequestBody SysInfoVO jsonRequest);

	/**
	 * 引用查询
	 *
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/sysInfo/selectSysInfoOrgCreate")
	public ServiceResponse<List<SysInfoVO>> selectSysInfoOrgCreate(@RequestBody SysInfoVO jsonRequest);

	/**
	 * 查看查询
	 *
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/sysInfo/selectSysInfoOrgSee")
	public ServiceResponse<List<SysInfoVO>> selectSysInfoOrgSee(@RequestBody SysInfoVO jsonRequest);


	/**
	 * 批量 保存、修改、删除
	 * 参数：opt insert、update、delete
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/sysInfo/keep")
	public ServiceResponse<SysInfoVO> keep(@RequestBody JsonRequest<List<SysInfoVO>> jsonRequest);
    
    
}