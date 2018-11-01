package com.ego.services.base.api.controller.jurisdiction;

import java.util.List;

import com.ebase.core.page.PageInfo;
import com.ego.services.base.api.vo.jurisdiction.SysInfoVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;
import com.ebase.core.web.json.JsonResponse;
import com.ego.services.base.api.vo.jurisdiction.AcctInfoVO;
import com.ego.services.base.api.vo.jurisdiction.OrgInfoVO;


/**
 * 
 * @author zhangx
 *
 */
@FeignClient(value = "${ser.name.base}")
public interface OrgInfoServiceAPI {
	
	/**
	 * 添加组织机构
	 * @param orgInfoVO 参数（orgCode:机构代码,orgName:机构名称,parentId:上级机构,remark:备注,status:0:停用;1:启用,createdBy:创建人）
	 * @return 失败0 或 成功1
	 */
	@RequestMapping(value = "/addOrgInfo",method = RequestMethod.POST)
	ServiceResponse<String> addOrgInfo(OrgInfoVO orgInfoVO);
	
	 /**
	  * 组织名称唯一性校验
	  */
	@RequestMapping(value = "/getParityOrgName",method = RequestMethod.POST)
	public ServiceResponse<Boolean> getParityOrgName(@RequestBody OrgInfoVO orgInfoVO);
	
	/**
	 * 删除组织机构
	 * @param orgInfoVO 参数（id：主键）将会级联删除父类下的所有子类
	 * @return 可以删除返回删除个数（如果绑定用户返回不可删除）
	 */
	@RequestMapping(value = "/removeOrgInfo",method = RequestMethod.POST)
	ServiceResponse<Integer> removeOrgInfo(OrgInfoVO orgInfoVO);
	
	/**
	 * 修改组织机构
	 * @param orgInfoVO 根据主键id 参数（orgCode:机构代码,orgName:机构名称,parentId:上级机构,remark:备注,status:0:停用;1:启用,createdBy:创建人,updatedBy:修改人）
	 * @return 失败0 或 成功1
	 */
	@RequestMapping(value = "/saveOrgInfo",method = RequestMethod.POST)
	ServiceResponse<Integer> saveOrgInfo(OrgInfoVO orgInfoVO);
	
	/**
	 * 查询组织机构
	 * @param orgInfoVO 根据主键id
	 * @return 参数（id:主键,orgCode:机构代码,orgName:机构名称,parentId:上级机构,remark:备注,status:0:停用;1:启用,createdBy:创建人,createdTime:创建时间,updatedBy:修改人,updatedTime:修改时间）
	 */
	@RequestMapping(value = "/getOrgInfo",method = RequestMethod.POST)
	ServiceResponse<OrgInfoVO> getOrgInfo(OrgInfoVO orgInfoVO);
	
	/**
	 * 查询组织机构分页
	 * @param orgInfoVO
	 * @return 参数（id:主键,orgCode:机构代码,orgName:机构名称,parentId:上级机构,remark:备注,status:0:停用;1:启用,createdBy:创建人,createdTime:创建时间,updatedBy:修改人,updatedTime:修改时间）
	 */
	@RequestMapping(value = "/getListOrgInfo",method = RequestMethod.POST)
	JsonResponse<PageInfo<OrgInfoVO>> getListOrgInfo(OrgInfoVO orgInfoVO);

	/**
	 * 角色未引用并且可以引用的组织
	 * @param orgInfoVO
	 */
	@RequestMapping(value = "/selectRoleYesQuote",method = RequestMethod.POST)
	JsonResponse<PageInfo<OrgInfoVO>> selectRoleYesQuote(OrgInfoVO orgInfoVO);
	
	/**
	 * 组织表查询（父类查出子类信息）
	 * @param orgInfoVO 父类id
	 * @return 子类（id:主键,orgCode:机构代码,orgName:机构名称,parentId:上级机构,remark:备注,status:0:停用;1:启用,createdBy:创建人,createdTime:创建时间,updatedBy:修改人,updatedTime:修改时间）
	 */
	@RequestMapping(value = "/getListTreeOrgInfo",method = RequestMethod.POST)
	ServiceResponse<List<OrgInfoVO>> getListTreeOrgInfo(OrgInfoVO orgInfoVO);
	
	/**
	 * 组织表递归查询结构树
	 * @param jsonRequest 传入上级机构 parentId 
	 * @return 返回结构树
	 */
	@RequestMapping(value = "/getChildTreeOrgInfo",method = RequestMethod.POST)
	ServiceResponse<OrgInfoVO> getChildTreeOrgInfo(JsonRequest<OrgInfoVO> jsonRequest);
	
	 /**
	  * 根据当前用户的组织id，查询出当前用户及当前用户一下的组织
	  * 物料综合查询用
	  * @return
	  */
	@RequestMapping(value = "/getMaterielOrginfo",method = RequestMethod.POST)
	public ServiceResponse<List<OrgInfoVO>> getMaterielOrginfo(@RequestBody AcctInfoVO reqBody);

	/**
	 * 根据系统查询关联组织信息
	 *
	 * @return
	 */
	@RequestMapping(value = "/selectSysQuoteOrgInof",method = RequestMethod.POST)
	public ServiceResponse<List<OrgInfoVO>> selectSysQuoteOrgInof(@RequestBody SysInfoVO reqBody);

	/**
	 * 角色引用多个组织 角色已引用组织
	 *
	 * @return
	 */
	@RequestMapping(value = "/selectRoleQuoteOrg",method = RequestMethod.POST)
	public ServiceResponse<List<OrgInfoVO>> selectRoleQuoteOrg(@RequestBody OrgInfoVO orgInfoVO);
	
	/**
	 * 组织表查询结构树（内存拼接）
	 * @param jsonRequest
	 * @return 返回结构树
	 *//*
	@RequestMapping(value = "/getListRecursionOrgInfo",method = RequestMethod.POST)
	JsonResponse getListRecursionOrgInfo(JsonRequest<OrgInfoVO> jsonRequest);*/
	
	
	
	

}
