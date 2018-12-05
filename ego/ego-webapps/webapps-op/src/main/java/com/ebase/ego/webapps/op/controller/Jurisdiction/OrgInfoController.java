package com.ebase.ego.webapps.op.controller.jurisdiction;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ebase.core.page.PageInfo;
import com.ego.services.juri.api.vo.jurisdiction.SysInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ebase.core.AssertContext;
import com.ebase.core.exception.BusinessException;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;
import com.ebase.core.web.json.JsonResponse;
import com.ebase.utils.JsonUtil;
import com.ego.services.juri.api.controller.jurisdiction.OrgInfoServiceAPI;
import com.ego.services.juri.api.vo.jurisdiction.AcctInfoVO;
import com.ego.services.juri.api.vo.jurisdiction.OrgInfoVO;





/**
 * 
 * @author zhangx
 *
 */
@RestController
@RequestMapping("/orgInfo")
public class OrgInfoController {
	
	 private static Logger logger = LoggerFactory.getLogger(OrgInfoController.class);
 
	 @Autowired
	 private OrgInfoServiceAPI orgInfoServiceAPI;
	 
	 /**
	  * 组织机构信息添加
	  * @param record
	  * @return
	  */
	 @RequestMapping(value="/addOrgInfo", method = RequestMethod.POST)
	 public JsonResponse<String> addOrgInfo(@RequestBody JsonRequest<OrgInfoVO> jsonRequest) {
		 logger.info(" www 系统编码list 参数 = {}",JsonUtil.toJson(jsonRequest));
		JsonResponse<String> jsonResponse = new JsonResponse<String>();
		OrgInfoVO orgInfoVO = jsonRequest.getReqBody();
		try {
			ServiceResponse<String> addOrgInfo = orgInfoServiceAPI.addOrgInfo(orgInfoVO);
			String retContent = addOrgInfo.getRetContent();
			if (retContent != null) {
				jsonResponse.setRspBody(retContent);
			}else {
				jsonResponse.setRetCode("0301002");
			}
		} catch (BusinessException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
		}
		return jsonResponse;
	 }
	 
	 /**
	  * 组织名称唯一性校验
	  * 未发
	  * @param record
	  * @return
	  */
	 @RequestMapping(value="/getParityOrgName", method = RequestMethod.POST)
	 public JsonResponse<Boolean> getParityOrgName(@RequestBody JsonRequest<OrgInfoVO> jsonRequest) {
		logger.info(" www 系统编码list 参数 = {}",JsonUtil.toJson(jsonRequest));
		JsonResponse<Boolean> jsonResponse = new JsonResponse<Boolean>();
		OrgInfoVO orgInfoVO = jsonRequest.getReqBody();
		try {
			ServiceResponse<Boolean> parityOrgName = orgInfoServiceAPI.getParityOrgName(orgInfoVO);
			Boolean retContent = parityOrgName.getRetContent();
			jsonResponse.setRspBody(retContent);
		} catch (BusinessException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
		}
		return jsonResponse;
	 }
	 
	 
	 
	 /**
	  * 组织机构信息刪除
	  * @param id
	  * @return
	  */
	 @RequestMapping(value = "/removeOrgInfo", method = RequestMethod.POST)
	 public JsonResponse<Integer> removeOrgInfo(@RequestBody JsonRequest<OrgInfoVO> jsonRequest) {
		logger.info(" www 系统编码list 参数 = {}",JsonUtil.toJson(jsonRequest));
		JsonResponse<Integer> jsonResponse = new JsonResponse<Integer>();
		OrgInfoVO orgInfoVO = jsonRequest.getReqBody();
		try {
			ServiceResponse<Integer> removeOrgInfo = orgInfoServiceAPI.removeOrgInfo(orgInfoVO);
			Integer retContent = removeOrgInfo.getRetContent();
			if(retContent != 0) {
				jsonResponse.setRspBody(retContent);
			}else {
				jsonResponse.setRetCode("0301001");
				jsonResponse.setRspBody(retContent);
			}		
		} catch (BusinessException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			jsonResponse.setRetCode(JsonResponse.SYS_EXCEPTION);
		}
		return jsonResponse;
	 }
	 
	 /**
	  * 组织机构信息修改
	  * @param id
	  * @return
	  */
	 @RequestMapping(value = "/saveOrgInfo", method = RequestMethod.POST)
	 public JsonResponse<Integer> saveOrgInfo(@RequestBody JsonRequest<OrgInfoVO> jsonRequest) {
		 logger.info(" www 系统编码list 参数 = {}",JsonUtil.toJson(jsonRequest));
		 JsonResponse<Integer> jsonResponse = new JsonResponse<Integer>();
		 
		 OrgInfoVO orgInfoVO = jsonRequest.getReqBody();
		 try {
			ServiceResponse<Integer> saveOrgInfo = orgInfoServiceAPI.saveOrgInfo(orgInfoVO);
			Integer retContent = saveOrgInfo.getRetContent();
			if (retContent != 0) {
				jsonResponse.setRspBody(retContent);
			}else {
				jsonResponse.setRetCode("0301002");
			}
		} catch (BusinessException e) {
			jsonResponse.setRetCode(e.getErrorCode());
			jsonResponse.setRetDesc(e.getMessage());
		}
		return jsonResponse;
	 }
	 
	 /**
	  * 组织机构信息查詢
	  * @param id
	  * @return
	  */
	 @RequestMapping(value = "/getOrgInfo", method = RequestMethod.POST)
	 public JsonResponse<OrgInfoVO> getOrgInfo(@RequestBody JsonRequest<OrgInfoVO> jsonRequest) {
		 logger.info(" www 系统编码list 参数 = {}",JsonUtil.toJson(jsonRequest));
		 JsonResponse<OrgInfoVO> jsonResponse = new JsonResponse<OrgInfoVO>();
		 OrgInfoVO orgInfoVO = jsonRequest.getReqBody();
		 try {
			ServiceResponse<OrgInfoVO> orgInfo = orgInfoServiceAPI.getOrgInfo(orgInfoVO);
			OrgInfoVO retContent = orgInfo.getRetContent();
			jsonResponse.setRspBody(retContent);
		} catch (BusinessException e) {
			jsonResponse.setRetCode(e.getErrorCode());
			jsonResponse.setRetDesc(e.getMessage());
		}
		return jsonResponse;
	 }
	 
	  
	 
	 /**
	  * 组织机构信息查詢
	  * @param id
	  * @return
	  */
	 @RequestMapping(value = "/getListOrgInfo", method = RequestMethod.POST)
	 public JsonResponse<PageInfo<OrgInfoVO>> getListOrgInfo(@RequestBody JsonRequest<OrgInfoVO> jsonRequest) {
		 logger.info(" www 系统编码list 参数 = {}",JsonUtil.toJson(jsonRequest));
		 
		 OrgInfoVO orgInfoVO = jsonRequest.getReqBody();
		 JsonResponse<PageInfo<OrgInfoVO>> jsonResponse = new JsonResponse<PageInfo<OrgInfoVO>>();
		 try {
			 jsonResponse = orgInfoServiceAPI.getListOrgInfo(orgInfoVO);
		} catch (BusinessException e) {
			jsonResponse.setRetCode(e.getErrorCode());
			jsonResponse.setRetDesc(e.getMessage());
		}
		return jsonResponse;
	 }
	 
	 /**
	  * 组织机构信息树查詢
	  * @param id
	  * @return
	  */
	 @RequestMapping(value = "/getListTreeOrgInfo")
	 public JsonResponse<Map<String, List<OrgInfoVO>>> getListTreeOrgInfo(@RequestBody JsonRequest<OrgInfoVO> jsonRequest) {
		 logger.info(" www 系统编码list 参数 = {}",JsonUtil.toJson(jsonRequest));
		 JsonResponse<Map<String, List<OrgInfoVO>>> jsonResponse = new JsonResponse<Map<String, List<OrgInfoVO>>>();
		 
		 OrgInfoVO orgInfoVO = jsonRequest.getReqBody();
		 try {
			ServiceResponse<List<OrgInfoVO>> listTreeOrgInfo2 = orgInfoServiceAPI.getListTreeOrgInfo(orgInfoVO);
			List<OrgInfoVO> retContent = listTreeOrgInfo2.getRetContent();
			Map<String, List<OrgInfoVO>> map=new HashMap<String, List<OrgInfoVO>>();
			map.put("resultData",retContent);
			jsonResponse.setRspBody(map);
		} catch (BusinessException e) {
			jsonResponse.setRetCode(e.getErrorCode());
			jsonResponse.setRetDesc(e.getMessage());
		}
		return jsonResponse;
	 }
	 
	 /**
	  * 组织机构信息树查詢
	  * @param id
	  * @return
	  */
	 @RequestMapping(value = "/getChildTreeOrgInfo")
	 public JsonResponse<Map<String, List<OrgInfoVO>>> getChildTreeOrgInfo(@RequestBody JsonRequest<OrgInfoVO> jsonRequest) {
		 logger.info(" www 系统编码list 参数 = {}",JsonUtil.toJson(jsonRequest));
		 JsonResponse<Map<String, List<OrgInfoVO>>> jsonResponse = new JsonResponse<Map<String, List<OrgInfoVO>>>();
		 try {
			 OrgInfoVO orgInfoVO=jsonRequest.getReqBody();
			 orgInfoVO.setParentId(AssertContext.getOrgId());
			 jsonRequest.setReqBody(orgInfoVO);
			ServiceResponse<OrgInfoVO> childTreeOrgInfo = orgInfoServiceAPI.getChildTreeOrgInfo(jsonRequest);
			OrgInfoVO retContent = childTreeOrgInfo.getRetContent();
			Map<String, List<OrgInfoVO>> map=new HashMap<String, List<OrgInfoVO>>();
			map.put("children", Arrays.asList(retContent));
			jsonResponse.setRspBody(map);
		} catch (BusinessException e) {
			jsonResponse.setRetCode(e.getErrorCode());
			jsonResponse.setRetDesc(e.getMessage());
		}
		return jsonResponse;
	 }
	 
	/**
	  * 根据当前用户的组织id，查询出当前用户及当前用户一下的组织
	  * 物料综合查询用
	  * @return
	  */
	 @RequestMapping(value = "/getMaterielOrginfo")
	 public JsonResponse<List<OrgInfoVO>> getMaterielOrginfo(@RequestBody JsonRequest<AcctInfoVO> jsonRequest) {
		 logger.info(" www 系统编码list 参数 = {}",JsonUtil.toJson(jsonRequest));
		 JsonResponse<List<OrgInfoVO>> jsonResponse = new JsonResponse<List<OrgInfoVO>>();
		 try {
			AcctInfoVO reqBody = jsonRequest.getReqBody();
			String acctId = AssertContext.getAcctId();
			reqBody.setAcctId(
					Long.valueOf(acctId));
			ServiceResponse<List<OrgInfoVO>> materielOrginfo = orgInfoServiceAPI.getMaterielOrginfo(reqBody);
			List<OrgInfoVO> retContent = materielOrginfo.getRetContent();
			jsonResponse.setRspBody(retContent);
		} catch (BusinessException e) {
			jsonResponse.setRetCode(e.getErrorCode());
			jsonResponse.setRetDesc(e.getMessage());
		}
		return jsonResponse;
	 }









	/**
	 * 根据系统查询关联组织信息
	 * @return
	 */
	@RequestMapping(value = "/selectSysQuoteOrgInof")
	public JsonResponse<List<OrgInfoVO>> selectSysQuoteOrgInof(@RequestBody JsonRequest<SysInfoVO> jsonRequest) {
		logger.info(" www 系统编码list 参数 = {}",JsonUtil.toJson(jsonRequest));
		JsonResponse<List<OrgInfoVO>> jsonResponse = new JsonResponse<List<OrgInfoVO>>();
		try {
			ServiceResponse<List<OrgInfoVO>> materielOrginfo = orgInfoServiceAPI.selectSysQuoteOrgInof(jsonRequest.getReqBody());
			List<OrgInfoVO> retContent = materielOrginfo.getRetContent();
			jsonResponse.setRspBody(retContent);
		} catch (BusinessException e) {
			jsonResponse.setRetCode(e.getErrorCode());
			jsonResponse.setRetDesc(e.getMessage());
		}
		return jsonResponse;
	}




	/**
	 * 角色引用多个组织 角色已引用组织
	 * @return
	 */
	@RequestMapping(value = "/selectRoleQuoteOrg")
	public JsonResponse<HashMap<String,List<OrgInfoVO>>> selectRoleQuoteOrg(@RequestBody JsonRequest<OrgInfoVO> jsonRequest) {
		logger.info(" www 系统编码list 参数 = {}",JsonUtil.toJson(jsonRequest));
		JsonResponse<HashMap<String,List<OrgInfoVO>>> jsonResponse = new JsonResponse<HashMap<String,List<OrgInfoVO>>>();
		try {
			OrgInfoVO orgInfoVO=jsonRequest.getReqBody();
			orgInfoVO.setOrgId(AssertContext.getOrgId());
			ServiceResponse<List<OrgInfoVO>> materielOrginfo = orgInfoServiceAPI.selectRoleQuoteOrg(orgInfoVO);
			List<OrgInfoVO> retContent = materielOrginfo.getRetContent();
			HashMap map=new HashMap();
			map.put("resultData", retContent);
			jsonResponse.setRspBody(map);
		} catch (BusinessException e) {
			jsonResponse.setRetCode(e.getErrorCode());
			jsonResponse.setRetDesc(e.getMessage());
		}
		return jsonResponse;
	}


	/**
	 * 角色未引用并且可以引用的组织
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/selectRoleYesQuote", method = RequestMethod.POST)
	public JsonResponse<PageInfo<OrgInfoVO>> selectRoleYesQuote(@RequestBody JsonRequest<OrgInfoVO> jsonRequest) {
		logger.info(" www 系统编码list 参数 = {}",JsonUtil.toJson(jsonRequest));

		OrgInfoVO orgInfoVO = jsonRequest.getReqBody();
		JsonResponse<PageInfo<OrgInfoVO>> jsonResponse = new JsonResponse<PageInfo<OrgInfoVO>>();
		try {
			if(AssertContext.getAcctType().equals(1)){
				orgInfoVO.setOrgId(AssertContext.getOrgId());
			}
			if(AssertContext.getAcctType().equals(0)){
				orgInfoVO.setOrgId(AssertContext.getOrgId());
			}
			jsonResponse = orgInfoServiceAPI.selectRoleYesQuote(orgInfoVO);
		} catch (BusinessException e) {
			jsonResponse.setRetCode(e.getErrorCode());
			jsonResponse.setRetDesc(e.getMessage());
		}
		return jsonResponse;
	}





	/**
	 * 组织机构信息树查詢
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getPwerTreeOrgInfo")
	public JsonResponse<Map<String, List<OrgInfoVO>>> getPwerTreeOrgInfo(@RequestBody JsonRequest<OrgInfoVO> jsonRequest) {
		logger.info(" www 系统编码list 参数 = {}",JsonUtil.toJson(jsonRequest));
		JsonResponse<Map<String, List<OrgInfoVO>>> jsonResponse = new JsonResponse<Map<String, List<OrgInfoVO>>>();
		try {
			OrgInfoVO orgInfoVO=jsonRequest.getReqBody();
			orgInfoVO.setParentId(AssertContext.getOrgId());
			jsonRequest.setReqBody(orgInfoVO);
			ServiceResponse<OrgInfoVO> childTreeOrgInfo = orgInfoServiceAPI.getPwerTreeOrgInfo(jsonRequest);
			OrgInfoVO retContent = childTreeOrgInfo.getRetContent();
			Map<String, List<OrgInfoVO>> map=new HashMap<String, List<OrgInfoVO>>();
			map.put("children", Arrays.asList(retContent));
			jsonResponse.setRspBody(map);
		} catch (BusinessException e) {
			jsonResponse.setRetCode(e.getErrorCode());
			jsonResponse.setRetDesc(e.getMessage());
		}
		return jsonResponse;
	}



	/**
	 * 组织机构信息树查詢 角色
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getPwerTreeRoleInfo")
	public JsonResponse<Map<String, List<OrgInfoVO>>> getPwerTreeRoleInfo(@RequestBody JsonRequest<OrgInfoVO> jsonRequest) {
		logger.info(" www 系统编码list 参数 = {}",JsonUtil.toJson(jsonRequest));
		JsonResponse<Map<String, List<OrgInfoVO>>> jsonResponse = new JsonResponse<Map<String, List<OrgInfoVO>>>();
		try {
			OrgInfoVO orgInfoVO=jsonRequest.getReqBody();
			orgInfoVO.setParentId(AssertContext.getOrgId());
			jsonRequest.setReqBody(orgInfoVO);
			ServiceResponse<OrgInfoVO> childTreeOrgInfo = orgInfoServiceAPI.getPwerTreeRoleInfo(jsonRequest);
			OrgInfoVO retContent = childTreeOrgInfo.getRetContent();
			Map<String, List<OrgInfoVO>> map=new HashMap<String, List<OrgInfoVO>>();
			map.put("children", Arrays.asList(retContent));
			jsonResponse.setRspBody(map);
		} catch (BusinessException e) {
			jsonResponse.setRetCode(e.getErrorCode());
			jsonResponse.setRetDesc(e.getMessage());
		}
		return jsonResponse;
	}


	/**
	 * 组织机构信息树查詢 角色
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getPwerTreeAcctInfo")
	public JsonResponse<Map<String, List<OrgInfoVO>>> getPwerTreeAcctInfo(@RequestBody JsonRequest<OrgInfoVO> jsonRequest) {
		logger.info(" www 系统编码list 参数 = {}",JsonUtil.toJson(jsonRequest));
		JsonResponse<Map<String, List<OrgInfoVO>>> jsonResponse = new JsonResponse<Map<String, List<OrgInfoVO>>>();
		try {
			OrgInfoVO orgInfoVO=jsonRequest.getReqBody();
			orgInfoVO.setParentId(AssertContext.getOrgId());
			jsonRequest.setReqBody(orgInfoVO);
			ServiceResponse<OrgInfoVO> childTreeOrgInfo = orgInfoServiceAPI.getPwerTreeAcctInfo(jsonRequest);
			OrgInfoVO retContent = childTreeOrgInfo.getRetContent();
			Map<String, List<OrgInfoVO>> map=new HashMap<String, List<OrgInfoVO>>();
			map.put("children", Arrays.asList(retContent));
			jsonResponse.setRspBody(map);
		} catch (BusinessException e) {
			jsonResponse.setRetCode(e.getErrorCode());
			jsonResponse.setRetDesc(e.getMessage());
		}
		return jsonResponse;
	}

}
