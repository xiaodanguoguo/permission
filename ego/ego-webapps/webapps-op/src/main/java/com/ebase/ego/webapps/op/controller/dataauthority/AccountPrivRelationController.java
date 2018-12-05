package com.ebase.ego.webapps.op.controller.dataauthority;

import java.util.List;

import com.ego.services.juri.api.controller.dataauthority.AccountPrivRelationAPI;
import com.ego.services.juri.api.vo.dataauthority.AccountPrivRelationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebase.core.page.PageDTO;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;
import com.ebase.core.web.json.JsonResponse;

/**
 * controller :AccountPrivRelation
 * @author Mrli
 * @date 2018-10-24
 */
 
@RestController
@RequestMapping("/accountPrivRelation")
public class AccountPrivRelationController {

	@Autowired
	public AccountPrivRelationAPI accountPrivRelationAPI;
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/save")
	public JsonResponse<Integer> save(@RequestBody JsonRequest<AccountPrivRelationVO> jsonRequest) {
		JsonResponse<Integer> jsonResponse = new JsonResponse<>();
		// 根据service层返回的编码做不同的操作
		ServiceResponse<Integer> response = accountPrivRelationAPI.save(jsonRequest);
		if (ServiceResponse.SUCCESS_CODE.equals(response.getRetCode()))
			jsonResponse.setRspBody(response.getRetContent());
		// 如果需要异常信息
		else if (response.isHasError())
			// 系统异常
			jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
		// 如果需要的话, 这个方法可以获取异常信息 response.getErrorMessage()
		else {
			// 根据业务的不同确定返回的业务信息是否正常,是否需要执行下一步操作
			jsonResponse.setRetCode(response.getRetCode());
			jsonResponse.setRetDesc(response.getRetMessage());
		}
		return jsonResponse;
	}
	
	/**
	 * 更新
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/update")
	public JsonResponse<Integer> update(@RequestBody JsonRequest<AccountPrivRelationVO> jsonRequest) {
		JsonResponse<Integer> jsonResponse = new JsonResponse<>();
		// 根据service层返回的编码做不同的操作
		ServiceResponse<Integer> response = accountPrivRelationAPI.update(jsonRequest);
		if (ServiceResponse.SUCCESS_CODE.equals(response.getRetCode()))
			jsonResponse.setRspBody(response.getRetContent());
		// 如果需要异常信息
		else if (response.isHasError())
			// 系统异常
			jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
		// 如果需要的话, 这个方法可以获取异常信息 response.getErrorMessage()
		else {
			// 根据业务的不同确定返回的业务信息是否正常,是否需要执行下一步操作
			jsonResponse.setRetCode(response.getRetCode());
			jsonResponse.setRetDesc(response.getRetMessage());
		}
		return jsonResponse;
	}
	
	/**
	 * 删除
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/delete")
	public JsonResponse<Integer> delete(@RequestBody JsonRequest<AccountPrivRelationVO> jsonRequest) {
		JsonResponse<Integer> jsonResponse = new JsonResponse<>();
		// 根据service层返回的编码做不同的操作
		ServiceResponse<Integer> response = accountPrivRelationAPI.delete(jsonRequest);
		if (ServiceResponse.SUCCESS_CODE.equals(response.getRetCode()))
			jsonResponse.setRspBody(response.getRetContent());
		// 如果需要异常信息
		else if (response.isHasError())
			// 系统异常
			jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
		// 如果需要的话, 这个方法可以获取异常信息 response.getErrorMessage()
		else {
			// 根据业务的不同确定返回的业务信息是否正常,是否需要执行下一步操作
			jsonResponse.setRetCode(response.getRetCode());
			jsonResponse.setRetDesc(response.getRetMessage());
		}
		return jsonResponse;
	}
	
	/**
	 * 查询单条记录
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/getdetails")
	public JsonResponse<AccountPrivRelationVO> getDetails(@RequestBody JsonRequest<AccountPrivRelationVO> jsonRequest) {
		JsonResponse<AccountPrivRelationVO> jsonResponse = new JsonResponse<>();
		// 根据service层返回的编码做不同的操作
		ServiceResponse<AccountPrivRelationVO> response = accountPrivRelationAPI.getDetails(jsonRequest);
		if (ServiceResponse.SUCCESS_CODE.equals(response.getRetCode()))
			jsonResponse.setRspBody(response.getRetContent());
		// 如果需要异常信息
		else if (response.isHasError())
			// 系统异常
			jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
		// 如果需要的话, 这个方法可以获取异常信息 response.getErrorMessage()
		else {
			// 根据业务的不同确定返回的业务信息是否正常,是否需要执行下一步操作
			jsonResponse.setRetCode(response.getRetCode());
			jsonResponse.setRetDesc(response.getRetMessage());
		}
		return jsonResponse;
	}
	
	/**
	 * 分页查询
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/findpageresult")
	public JsonResponse<PageDTO<AccountPrivRelationVO>> findPageResult(@RequestBody JsonRequest<AccountPrivRelationVO> jsonRequest) {
		JsonResponse<PageDTO<AccountPrivRelationVO>> jsonResponse = new JsonResponse<>();
		// 根据service层返回的编码做不同的操作
		ServiceResponse<PageDTO<AccountPrivRelationVO>> response = accountPrivRelationAPI.findPageResult(jsonRequest);
		if (ServiceResponse.SUCCESS_CODE.equals(response.getRetCode()))
			jsonResponse.setRspBody(response.getRetContent());
		// 如果需要异常信息
		else if (response.isHasError())
			// 系统异常
			jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
		// 如果需要的话, 这个方法可以获取异常信息 response.getErrorMessage()
		else {
			// 根据业务的不同确定返回的业务信息是否正常,是否需要执行下一步操作
			jsonResponse.setRetCode(response.getRetCode());
			jsonResponse.setRetDesc(response.getRetMessage());
		}
		return jsonResponse;
	}
	
	/**
	 * 批量 保存、修改、删除
	 * 参数：opt insert、update、delete
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/keep")
	public JsonResponse<Integer> keep(@RequestBody JsonRequest<List<AccountPrivRelationVO>> jsonRequest) {
		JsonResponse<Integer> jsonResponse = new JsonResponse<>();
		// 根据service层返回的编码做不同的操作
		ServiceResponse<Integer> response = accountPrivRelationAPI.keep(jsonRequest);
		if (ServiceResponse.SUCCESS_CODE.equals(response.getRetCode()))
			jsonResponse.setRspBody(response.getRetContent());
		// 如果需要异常信息
		else if (response.isHasError())
			// 系统异常
			jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
		// 如果需要的话, 这个方法可以获取异常信息 response.getErrorMessage()
		else {
			// 根据业务的不同确定返回的业务信息是否正常,是否需要执行下一步操作
			jsonResponse.setRetCode(response.getRetCode());
			jsonResponse.setRetDesc(response.getRetMessage());
		}
		return jsonResponse;
	}
    
    
}