package com.ebase.ego.webapps.op.controller.jurisdiction;

import java.util.List;
import com.ebase.core.AssertContext;
import com.ebase.core.page.PageInfo;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;
import com.ego.services.juri.api.controller.jurisdiction.SysInfoAPI;
import com.ego.services.juri.api.vo.jurisdiction.SysInfoVO;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ebase.core.web.json.JsonResponse;

/**
 * controller :SysInfo
 * @author 
 * @date 2018-10-10
 */
 
@RestController
@RequestMapping("/sysInfo")
public class SysInfoController {
	private static Logger logger = LoggerFactory.getLogger(SysInfoAPI.class);

	@Autowired
	public SysInfoAPI sysInfoAPI;
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/save")
	public JsonResponse<SysInfoVO> save(@RequestBody JsonRequest<SysInfoVO> jsonRequest) {
		JsonResponse<SysInfoVO> jsonResponse = new JsonResponse<>();
		try {
			// 根据service层返回的编码做不同的操作
			SysInfoVO sysInfoVO=jsonRequest.getReqBody();
			sysInfoVO.setOrgId(AssertContext.getOrgId());
			sysInfoVO.setCreatedBy(AssertContext.getAcctTitle());
			ServiceResponse<SysInfoVO> response = sysInfoAPI.save(sysInfoVO);
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
	public JsonResponse<SysInfoVO> update(@RequestBody JsonRequest<SysInfoVO> jsonRequest) {
		JsonResponse<SysInfoVO> jsonResponse = new JsonResponse<>();
		try {
			// 根据service层返回的编码做不同的操作
			SysInfoVO sysInfoVO=jsonRequest.getReqBody();
			sysInfoVO.setUpdatedBy(AssertContext.getAcctTitle());
			ServiceResponse<SysInfoVO> response = sysInfoAPI.update(sysInfoVO);
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
	 * 删除 验证是否被组织关联
	 *
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/verSysInfo")
	public JsonResponse<String> verSysInfo(@RequestBody JsonRequest<SysInfoVO> jsonRequest) {
		JsonResponse<String> jsonResponse = new JsonResponse<>();
		try {
			// 根据service层返回的编码做不同的操作
			ServiceResponse<String> response = sysInfoAPI.verSysInfo(jsonRequest.getReqBody());
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
	 * 删除 验证是否被组织关联
	 *
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/verInsertSysInfo")
	public JsonResponse<String> verInsertSysInfo(@RequestBody JsonRequest<SysInfoVO> jsonRequest) {
		JsonResponse<String> jsonResponse = new JsonResponse<>();
		try {
			// 根据service层返回的编码做不同的操作
			ServiceResponse<String> response = sysInfoAPI.verInsertSysInfo(jsonRequest.getReqBody());
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
	public JsonResponse<SysInfoVO> delete(@RequestBody JsonRequest<SysInfoVO> jsonRequest) {
		JsonResponse<SysInfoVO> jsonResponse = new JsonResponse<>();
		try {
			// 根据service层返回的编码做不同的操作
			ServiceResponse<SysInfoVO> response = sysInfoAPI.delete(jsonRequest);
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
	public JsonResponse<SysInfoVO> queryDetails(@RequestBody JsonRequest<SysInfoVO> jsonRequest) {
		JsonResponse<SysInfoVO> jsonResponse = new JsonResponse<>();
		try {
			// 根据service层返回的编码做不同的操作
			ServiceResponse<SysInfoVO> response = sysInfoAPI.queryDetails(jsonRequest);
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
	public JsonResponse<PageInfo<SysInfoVO>> queryPagedResult(@RequestBody JsonRequest<SysInfoVO> jsonRequest) {
		JsonResponse<PageInfo<SysInfoVO>> jsonResponse = new JsonResponse<>();
		try {
			// 根据service层返回的编码做不同的操作
			ServiceResponse<PageInfo<SysInfoVO>> response = sysInfoAPI.queryPagedResult(jsonRequest);
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
	public JsonResponse<SysInfoVO> keep(@RequestBody JsonRequest<List<SysInfoVO>> jsonRequest) {
		JsonResponse<SysInfoVO> jsonResponse = new JsonResponse<>();
		
		try {
			// 根据service层返回的编码做不同的操作
			ServiceResponse<SysInfoVO> response = sysInfoAPI.keep(jsonRequest);
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
	 * 引用系统
	 *
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/selectSysInfoOrg")
	public JsonResponse<List<SysInfoVO>> selectSysInfoOrg(@RequestBody JsonRequest<SysInfoVO> jsonRequest) {
		JsonResponse<List<SysInfoVO>> jsonResponse = new JsonResponse<>();
		try {
			// 根据service层返回的编码做不同的操作
			SysInfoVO sysInfoVO=jsonRequest.getReqBody();
			sysInfoVO.setOrgId(AssertContext.getOrgId());
			sysInfoVO.setAcctId(Long.parseLong(AssertContext.getAcctId()));
			sysInfoVO.setAcctType(AssertContext.getAcctType());
			ServiceResponse<List<SysInfoVO>> response = sysInfoAPI.selectSysInfoOrg(sysInfoVO);
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
	 * 根据组织查询创建系统
	 *
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/selectSysEstablish")
	public JsonResponse<List<SysInfoVO>> selectSysEstablish(@RequestBody JsonRequest<SysInfoVO> jsonRequest) {
		JsonResponse<List<SysInfoVO>> jsonResponse = new JsonResponse<>();
		try {
			// 根据service层返回的编码做不同的操作
			SysInfoVO sysInfoVO=jsonRequest.getReqBody();
//			sysInfoVO.setOrgId(AssertContext.getOrgId());
//			sysInfoVO.setAcctId(Long.parseLong(AssertContext.getAcctId()));
//			sysInfoVO.setAcctType(AssertContext.getAcctType());
			ServiceResponse<List<SysInfoVO>> response = sysInfoAPI.selectSysEstablish(sysInfoVO);
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
	 * 组织创建的系统
	 *
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/selectSysInfoOrgCreate")
	public JsonResponse<List<SysInfoVO>> selectSysInfoOrgCreate(@RequestBody JsonRequest<SysInfoVO> jsonRequest) {
		JsonResponse<List<SysInfoVO>> jsonResponse = new JsonResponse<>();
		try {
			// 根据service层返回的编码做不同的操作
			SysInfoVO sysInfoVO=jsonRequest.getReqBody();
			sysInfoVO.setOrgId(AssertContext.getOrgId());
			sysInfoVO.setAcctId(Long.parseLong(AssertContext.getAcctId()));
			sysInfoVO.setAcctType(AssertContext.getAcctType());
			ServiceResponse<List<SysInfoVO>> response = sysInfoAPI.selectSysInfoOrgCreate(sysInfoVO);
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
	 * 可以查看的系统
	 *
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/selectSysInfoOrgSee")
	public JsonResponse<List<SysInfoVO>> selectSysInfoOrgSee(@RequestBody JsonRequest<SysInfoVO> jsonRequest) {
		JsonResponse<List<SysInfoVO>> jsonResponse = new JsonResponse<>();
		try {
			// 根据service层返回的编码做不同的操作
			SysInfoVO sysInfoVO=jsonRequest.getReqBody();

			if(StringUtils.isEmpty(sysInfoVO.getOrgId())){
				sysInfoVO.setOrgId(AssertContext.getOrgId());
			}

			sysInfoVO.setAcctId(Long.parseLong(AssertContext.getAcctId()));
			sysInfoVO.setAcctType(AssertContext.getAcctType());
			ServiceResponse<List<SysInfoVO>> response = sysInfoAPI.selectSysInfoOrgSee(sysInfoVO);
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
	 * 角色选择组织可以查看的系统
	 *
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/selectSysInfoRoleSee")
	public JsonResponse<List<SysInfoVO>> selectSysInfoRoleSee(@RequestBody JsonRequest<SysInfoVO> jsonRequest) {
		JsonResponse<List<SysInfoVO>> jsonResponse = new JsonResponse<>();
		try {
			// 根据service层返回的编码做不同的操作
			SysInfoVO sysInfoVO=jsonRequest.getReqBody();

			if(StringUtils.isEmpty(sysInfoVO.getOrgId())){
				sysInfoVO.setOrgId(AssertContext.getOrgId());
			}
			sysInfoVO.setAcctType(Long.parseLong("1"));
			ServiceResponse<List<SysInfoVO>> response = sysInfoAPI.selectSysInfoOrgSee(sysInfoVO);
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