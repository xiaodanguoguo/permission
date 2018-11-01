package com.ebase.ego.webapps.op.controller.dataauthority;

import java.util.List;

import com.ebase.core.page.PageInfo;
import com.ego.services.base.api.controller.dataauthority.TheMetadataAPI;
import com.ego.services.base.api.vo.dataauthority.TheMetadataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebase.core.page.PageDTO;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;
import com.ebase.core.web.json.JsonResponse;

/**
 * controller :TheMetadata
 * @author Mrli
 * @date 2018-10-24
 */
 
@RestController
@RequestMapping("/theMetadata")
public class TheMetadataController {

	@Autowired
	public TheMetadataAPI theMetadataAPI;
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/save")
	public JsonResponse<Integer> save(@RequestBody JsonRequest<TheMetadataVO> jsonRequest) {
		JsonResponse<Integer> jsonResponse = new JsonResponse<>();
		// 根据service层返回的编码做不同的操作
		ServiceResponse<Integer> response = theMetadataAPI.save(jsonRequest);
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
	public JsonResponse<Integer> update(@RequestBody JsonRequest<TheMetadataVO> jsonRequest) {
		JsonResponse<Integer> jsonResponse = new JsonResponse<>();
		// 根据service层返回的编码做不同的操作
		ServiceResponse<Integer> response = theMetadataAPI.update(jsonRequest);
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
	public JsonResponse<Integer> delete(@RequestBody JsonRequest<TheMetadataVO> jsonRequest) {
		JsonResponse<Integer> jsonResponse = new JsonResponse<>();
		// 根据service层返回的编码做不同的操作
		ServiceResponse<Integer> response = theMetadataAPI.delete(jsonRequest);
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
	public JsonResponse<TheMetadataVO> getDetails(@RequestBody JsonRequest<TheMetadataVO> jsonRequest) {
		JsonResponse<TheMetadataVO> jsonResponse = new JsonResponse<>();
		// 根据service层返回的编码做不同的操作
		ServiceResponse<TheMetadataVO> response = theMetadataAPI.getDetails(jsonRequest);
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
	public JsonResponse<PageInfo<TheMetadataVO>> findPageResult(@RequestBody JsonRequest<TheMetadataVO> jsonRequest) {
		JsonResponse<PageInfo<TheMetadataVO>> jsonResponse = new JsonResponse<>();
		// 根据service层返回的编码做不同的操作
		ServiceResponse<PageInfo<TheMetadataVO>> response = theMetadataAPI.findPageResult(jsonRequest);
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
	public JsonResponse<Integer> keep(@RequestBody JsonRequest<List<TheMetadataVO>> jsonRequest) {
		JsonResponse<Integer> jsonResponse = new JsonResponse<>();
		// 根据service层返回的编码做不同的操作
		ServiceResponse<Integer> response = theMetadataAPI.keep(jsonRequest);
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