package com.ebase.ego.webapps.op.controller.dataauthority;

import java.util.List;

import com.ebase.core.AssertContext;
import com.ebase.core.page.PageInfo;
import com.ego.services.base.api.controller.dataauthority.PowerExpressionAPI;
import com.ego.services.base.api.vo.dataauthority.PowerExpressionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebase.core.page.PageDTO;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;
import com.ebase.core.web.json.JsonResponse;

/**
 * controller :PowerExpression
 * @author Mrli
 * @date 2018-11-1
 */
 
@RestController
@RequestMapping("/powerExpression")
public class PowerExpressionController {

	@Autowired
	public PowerExpressionAPI powerExpressionAPI;
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/save")
	public JsonResponse<Integer> save(@RequestBody JsonRequest<PowerExpressionVO> jsonRequest) {
		//jsonRequest.getReqHeader().setAcctId(AssertContext.getAcctId());
		JsonResponse<Integer> jsonResponse = new JsonResponse<>();
		// 根据service层返回的编码做不同的操作
		PowerExpressionVO powerExpressionVO=jsonRequest.getReqBody();
		powerExpressionVO.setOrgId(AssertContext.getOrgId());
		powerExpressionVO.setCreatedBy(AssertContext.getAcctTitle());
		jsonRequest.setReqBody(powerExpressionVO);
		ServiceResponse<Integer> response = powerExpressionAPI.save(jsonRequest);
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
	public JsonResponse<Integer> update(@RequestBody JsonRequest<PowerExpressionVO> jsonRequest) {
		JsonResponse<Integer> jsonResponse = new JsonResponse<>();
		// 根据service层返回的编码做不同的操作
		ServiceResponse<Integer> response = powerExpressionAPI.update(jsonRequest);
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
	public JsonResponse<Integer> delete(@RequestBody JsonRequest<PowerExpressionVO> jsonRequest) {
		JsonResponse<Integer> jsonResponse = new JsonResponse<>();
		// 根据service层返回的编码做不同的操作
		ServiceResponse<Integer> response = powerExpressionAPI.delete(jsonRequest);
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
	public JsonResponse<PowerExpressionVO> getDetails(@RequestBody JsonRequest<PowerExpressionVO> jsonRequest) {
		JsonResponse<PowerExpressionVO> jsonResponse = new JsonResponse<>();
		// 根据service层返回的编码做不同的操作
		ServiceResponse<PowerExpressionVO> response = powerExpressionAPI.getDetails(jsonRequest);
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
	public JsonResponse<PageInfo<PowerExpressionVO>> findPageResult(@RequestBody JsonRequest<PowerExpressionVO> jsonRequest) {
		JsonResponse<PageInfo<PowerExpressionVO>> jsonResponse = new JsonResponse<>();
		// 根据service层返回的编码做不同的操作
		PowerExpressionVO powerExpressionVO=jsonRequest.getReqBody();
		powerExpressionVO.setOrgId(AssertContext.getOrgId());
		jsonRequest.setReqBody(powerExpressionVO);
		ServiceResponse<PageInfo<PowerExpressionVO>> response = powerExpressionAPI.findPageResult(jsonRequest);
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
	public JsonResponse<Integer> keep(@RequestBody JsonRequest<List<PowerExpressionVO>> jsonRequest) {
		JsonResponse<Integer> jsonResponse = new JsonResponse<>();
		// 根据service层返回的编码做不同的操作
		ServiceResponse<Integer> response = powerExpressionAPI.keep(jsonRequest);
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