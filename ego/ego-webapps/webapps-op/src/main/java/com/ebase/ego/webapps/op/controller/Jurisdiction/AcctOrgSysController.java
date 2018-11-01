package com.ebase.ego.webapps.op.controller.jurisdiction;

import java.util.List;

import com.ebase.core.page.PageInfo;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;
import com.ego.services.base.api.controller.jurisdiction.AcctOrgSysAPI;
import com.ego.services.base.api.vo.jurisdiction.AcctOrgSysVO;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ebase.core.web.json.JsonResponse;
/**
 * controller :AcctOrgSys
 * @author 
 * @date 2018-10-10
 */
 
@RestController
@RequestMapping("/acctOrgSys")
public class AcctOrgSysController {
	private static Logger logger = LoggerFactory.getLogger(AcctOrgSysAPI.class);

	@Autowired
	public AcctOrgSysAPI acctOrgSysAPI;
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/save")
	public JsonResponse<Integer> save(@RequestBody JsonRequest<AcctOrgSysVO> jsonRequest) {
		JsonResponse<Integer> jsonResponse = new JsonResponse<>();
		try {
			// 根据service层返回的编码做不同的操作
			ServiceResponse<Integer> response = acctOrgSysAPI.save(jsonRequest);
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
		} catch (FeignException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
			return jsonResponse;
		}

		return jsonResponse;

	}

	/**
	 * 保存
	 *
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/saveOrgInfo")
	public JsonResponse<Integer> saveOrgInfo(@RequestBody JsonRequest<AcctOrgSysVO> jsonRequest) {
		JsonResponse<Integer> jsonResponse = new JsonResponse<>();
		try {
			// 根据service层返回的编码做不同的操作
			ServiceResponse<Integer> response = acctOrgSysAPI.saveOrgInfo(jsonRequest);
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
		} catch (FeignException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
			return jsonResponse;
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
	public JsonResponse<Integer> update(@RequestBody JsonRequest<AcctOrgSysVO> jsonRequest) {
		JsonResponse<Integer> jsonResponse = new JsonResponse<>();
		try {
			// 根据service层返回的编码做不同的操作
			ServiceResponse<Integer> response = acctOrgSysAPI.update(jsonRequest);
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
		} catch (FeignException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
			return jsonResponse;
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
	public JsonResponse<Integer> delete(@RequestBody JsonRequest<AcctOrgSysVO> jsonRequest) {
		JsonResponse<Integer> jsonResponse = new JsonResponse<>();
		try {
			// 根据service层返回的编码做不同的操作
			ServiceResponse<Integer> response = acctOrgSysAPI.delete(jsonRequest);
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
		} catch (FeignException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
			return jsonResponse;
		}

		return jsonResponse;

	}
	
	/**
	 * 查询单条记录
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/queryDetails")
	public JsonResponse<AcctOrgSysVO> queryDetails(@RequestBody JsonRequest<AcctOrgSysVO> jsonRequest) {
		JsonResponse<AcctOrgSysVO> jsonResponse = new JsonResponse<>();
		try {
			// 根据service层返回的编码做不同的操作
			ServiceResponse<AcctOrgSysVO> response = acctOrgSysAPI.queryDetails(jsonRequest);
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
		} catch (FeignException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
			return jsonResponse;
		}

		return jsonResponse;

	}
	
	/**
	 * 分页查询
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/queryPagedResult")
	public JsonResponse<PageInfo<AcctOrgSysVO>> queryPagedResult(@RequestBody JsonRequest<AcctOrgSysVO> jsonRequest) {
		JsonResponse<PageInfo<AcctOrgSysVO>> jsonResponse = new JsonResponse<>();
		try {
			// 根据service层返回的编码做不同的操作
			ServiceResponse<PageInfo<AcctOrgSysVO>> response = acctOrgSysAPI.queryPagedResult(jsonRequest);
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
		} catch (FeignException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
			return jsonResponse;
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
	public JsonResponse<Integer> keep(@RequestBody JsonRequest<List<AcctOrgSysVO>> jsonRequest) {
		JsonResponse<Integer> jsonResponse = new JsonResponse<>();
		
		try {
			// 根据service层返回的编码做不同的操作
			ServiceResponse<Integer> response = acctOrgSysAPI.keep(jsonRequest);
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
		} catch (FeignException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
			return jsonResponse;
		}
		return jsonResponse;
	}
    
    
}