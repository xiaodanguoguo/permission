package com.ego.services.juri.facade.controller.jurisdiction;


import java.util.ArrayList;
import java.util.List;

import com.ebase.core.AssertContext;
import com.ebase.core.page.PageInfo;
import com.ego.services.juri.api.vo.jurisdiction.SysInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebase.core.exception.BusinessException;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;
import com.ebase.core.web.json.JsonResponse;
import com.ebase.utils.BeanCopyUtil;
import com.ego.services.juri.api.vo.jurisdiction.AcctInfoVO;
import com.ego.services.juri.api.vo.jurisdiction.OrgInfoVO;
import com.ego.services.juri.facade.model.jurisdiction.AcctInfo;
import com.ego.services.juri.facade.model.jurisdiction.OrgInfo;
import com.ego.services.juri.facade.service.jurisdiction.OrgInfoService;




/**
 *
 * @author zhangx
 *
 */
@RestController
public class OrgInfoController {

	private static Logger logger = LoggerFactory.getLogger(OrgInfoController.class);

	@Autowired
	private OrgInfoService orgInfoService;

	/**
	 * 添加   组织机构信息
	 * @param record
	 * @return
	 */
	@RequestMapping("/addOrgInfo")
	public ServiceResponse<String> addOrgInfo(@RequestBody OrgInfoVO orgInfoVO) {
		ServiceResponse<String> response = new ServiceResponse<String>();

		OrgInfo orgInfo = new OrgInfo();
		BeanCopyUtil.copy(orgInfoVO, orgInfo);
		try {
			Long i = orgInfoService.addOrgInfo(orgInfo);

			response.setRetContent(orgInfo.getId());

		} catch(BusinessException e) {
			response.setException(new BusinessException("异常编码", new Object[]{"参数"}));
			logger.error(e.getMessage());
		}
		return response;
	}

	/**
	 * 组织名称唯一性校验
	 * 未发
	 */
	@RequestMapping("/getParityOrgName")
	public ServiceResponse<Boolean> getParityOrgName(@RequestBody OrgInfoVO orgInfoVO) {
		ServiceResponse<Boolean> response = new ServiceResponse<Boolean>();

		OrgInfo orgInfo = new OrgInfo();
		BeanCopyUtil.copy(orgInfoVO, orgInfo);
		try {

			Boolean condition = orgInfoService.getParityOrgName(orgInfo);

			response.setRetContent(condition);
		} catch (BusinessException e) {
			response.setException(new BusinessException("异常编码", new Object[]{"参数"}));
			logger.error(e.getMessage());
		}
		return response;
	}

	/**
	 * 刪除 组织机构信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/removeOrgInfo")
	public ServiceResponse<Integer> removeOrgInfo(@RequestBody OrgInfoVO orgInfoVO) {
		ServiceResponse<Integer> response = new ServiceResponse<Integer>();

		OrgInfo orgInfo = new OrgInfo();
		BeanCopyUtil.copy(orgInfoVO, orgInfo);
		try {
			Integer i = orgInfoService.removeOrgInfo(orgInfo);
			response.setRetContent(i);
		}catch(BusinessException e) {
			response.setException(new BusinessException("异常编码", new Object[]{"参数"}));
			logger.error(e.getMessage());
		}
		return response;
	}

	/**
	 * 组织机构信息父查子
	 * @param id
	 * @return
	 */
	@RequestMapping("/getListTreeOrgInfo")
	public ServiceResponse<List<OrgInfoVO>> getListTreeOrgInfo(@RequestBody OrgInfoVO orgInfoVO) {
		ServiceResponse<List<OrgInfoVO>> response = new ServiceResponse<List<OrgInfoVO>>();

		OrgInfo orgInfo = new OrgInfo();
		BeanCopyUtil.copy(orgInfoVO, orgInfo);
		try {
			List<OrgInfo> listTreeOrgInfo = orgInfoService.getListTreeOrgInfo(orgInfo);
			List<OrgInfoVO> retContentVO  = BeanCopyUtil.copyList(listTreeOrgInfo, OrgInfoVO.class);
			response.setRetContent(retContentVO);
		} catch (BusinessException e) {
			response.setException(new BusinessException("异常编码", new Object[]{"参数"}));
			logger.error(e.getMessage());
		}
		return response;
	}

	/**
	 * 组织机构信息树查詢
	 * @param id
	 * @return
	 */
	@RequestMapping("/getChildTreeOrgInfo")
	public ServiceResponse<OrgInfoVO> getChildTreeOrgInfo(@RequestBody JsonRequest<OrgInfo> jsonRequest) {
		ServiceResponse<OrgInfoVO> response = new ServiceResponse<OrgInfoVO>();

		OrgInfo reqBody = jsonRequest.getReqBody();
		OrgInfo orgInfo = new OrgInfo();
		BeanCopyUtil.copy(reqBody, orgInfo);
		try {
			OrgInfo childTreeOrgInfo = orgInfoService.getChildTreeOrgInfo(orgInfo);
			OrgInfoVO copy = BeanCopyUtil.copy(childTreeOrgInfo, OrgInfoVO.class);
			response.setRetContent(copy);
		} catch (BusinessException e) {
			response.setException(new BusinessException("异常编码", new Object[]{"参数"}));
			logger.error(e.getMessage());
		}
		return response;
	}

	/**
	 * 修改  组织机构信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/saveOrgInfo")
	public ServiceResponse<Integer> saveOrgInfo(@RequestBody OrgInfoVO orgInfoVO) {
		ServiceResponse<Integer> response = new ServiceResponse<Integer>();

		OrgInfo orgInfo = new OrgInfo();
		BeanCopyUtil.copy(orgInfoVO, orgInfo);
		try {
			Integer i = orgInfoService.saveOrgInfo(orgInfo);
			response.setRetContent(i);
		} catch (BusinessException e) {
			response.setException(new BusinessException("异常编码", new Object[]{"参数"}));
			logger.error(e.getMessage());
		}
		return response;
	}

	/**
	 * 组织机构信息查詢
	 * @param id
	 * @return
	 */
	@RequestMapping("/getOrgInfo")
	public ServiceResponse<OrgInfoVO> getOrgInfo(@RequestBody OrgInfoVO orgInfoVO) {
		ServiceResponse<OrgInfoVO> response = new ServiceResponse<OrgInfoVO>();

		OrgInfo orgInfo = new OrgInfo();
		BeanCopyUtil.copy(orgInfoVO, orgInfo);
		try {
			OrgInfo orgInfo2 = orgInfoService.getOrgInfo(orgInfo);
			OrgInfoVO copy = BeanCopyUtil.copy(orgInfo2, OrgInfoVO.class);
			response.setRetContent(copy);
		} catch (BusinessException e) {
			response.setException(new BusinessException("异常编码", new Object[]{"参数"}));
			logger.error(e.getMessage());
		}
		return response;
	}


	/**
	 * 角色未引用并且可以引用的组织
	 * @param orgInfoVO
	 * @return
	 */
	@RequestMapping("/selectRoleYesQuote")
	public JsonResponse<PageInfo<OrgInfoVO>> selectRoleYesQuote(@RequestBody OrgInfoVO orgInfoVO) {
		JsonResponse<PageInfo<OrgInfoVO>> jsonResponse = new JsonResponse<PageInfo<OrgInfoVO>>();

		try {
			PageInfo<OrgInfoVO> page = orgInfoService.selectRoleYesQuote(orgInfoVO);
			jsonResponse.setRspBody(page);
		} catch (BusinessException e) {
			jsonResponse.setRetCode(e.getErrorCode());
			jsonResponse.setRetDesc(e.getMessage());
		}
		return jsonResponse;
	}

	/**
	 * 组织机构信息查詢分页
	 * @param id
	 * @return
	 */
	@RequestMapping("/getListOrgInfo")
	public JsonResponse<PageInfo<OrgInfoVO>> getListOrgInfo(@RequestBody OrgInfoVO orgInfoVO) {
		JsonResponse<PageInfo<OrgInfoVO>> jsonResponse = new JsonResponse<PageInfo<OrgInfoVO>>();

		try {
			PageInfo<OrgInfoVO> page = orgInfoService.getListOrgInfo(orgInfoVO);
			jsonResponse.setRspBody(page);
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
	@RequestMapping("/getMaterielOrginfo")
	public ServiceResponse<List<OrgInfoVO>> getMaterielOrginfo(@RequestBody AcctInfoVO reqBody){
		ServiceResponse<List<OrgInfoVO>> response = new ServiceResponse<List<OrgInfoVO>>();

		AcctInfo acctInfo = new AcctInfo();
		acctInfo.setAcctId(reqBody.getAcctId());
		List<OrgInfoVO> list=new ArrayList<>();
		try {
			List<OrgInfo> listOrgInfo= orgInfoService.getMaterielOrginfo(acctInfo);
			if(!CollectionUtils.isEmpty(listOrgInfo)){
				list = BeanCopyUtil.copyList(listOrgInfo, OrgInfoVO.class);
			}
			response.setRetContent(list);
		} catch (BusinessException e) {
			response.setException(new BusinessException("异常编码", new Object[]{"参数"}));
			logger.error(e.getMessage());
		}
		return response;
	}


	/**
	 * 根据当前用户的组织id，查询出当前用户及当前用户一下的组织
	 * 物料综合查询用
	 * @return
	 */
	@RequestMapping("/selectSysQuoteOrgInof")
	public ServiceResponse<List<OrgInfoVO>> selectSysQuoteOrgInof(@RequestBody SysInfoVO reqBody){
		ServiceResponse<List<OrgInfoVO>> response = new ServiceResponse<List<OrgInfoVO>>();

		try {
			List<OrgInfoVO> listOrgInfo= orgInfoService.selectSysQuoteOrgInof(reqBody);
			response.setRetContent(listOrgInfo);
		} catch (BusinessException e) {
			response.setException(new BusinessException("异常编码", new Object[]{"参数"}));
			logger.error(e.getMessage());
		}
		return response;
	}


	/**
	 * 角色引用多个组织 角色已引用组织
	 * @return
	 */
	@RequestMapping("/selectRoleQuoteOrg")
	public ServiceResponse<List<OrgInfoVO>> selectRoleQuoteOrg(@RequestBody OrgInfoVO reqBody){
		ServiceResponse<List<OrgInfoVO>> response = new ServiceResponse<List<OrgInfoVO>>();
		try {
			List<OrgInfoVO> listOrgInfo= orgInfoService.selectRoleQuoteOrg(reqBody);
			response.setRetContent(listOrgInfo);
		} catch (BusinessException e) {
			response.setException(new BusinessException("异常编码", new Object[]{"参数"}));
			logger.error(e.getMessage());
		}
		return response;
	}



	/**
	 * 组织机构信息树查詢
	 * @param id
	 * @return
	 */
	@RequestMapping("/getPwerTreeOrgInfo")
	public ServiceResponse<OrgInfoVO> getPwerTreeOrgInfo(@RequestBody JsonRequest<OrgInfo> jsonRequest) {
		ServiceResponse<OrgInfoVO> response = new ServiceResponse<OrgInfoVO>();

		OrgInfo reqBody = jsonRequest.getReqBody();
		OrgInfo orgInfo = new OrgInfo();
		BeanCopyUtil.copy(reqBody, orgInfo);
		try {
			OrgInfo childTreeOrgInfo = orgInfoService.getPwerTreeOrgInfo(orgInfo);
			OrgInfoVO copy = BeanCopyUtil.copy(childTreeOrgInfo, OrgInfoVO.class);
			response.setRetContent(copy);
		} catch (BusinessException e) {
			response.setException(new BusinessException("异常编码", new Object[]{"参数"}));
			logger.error(e.getMessage());
		}
		return response;
	}

    /**
     * 组织机构信息树查詢
     * @param id
     * @return
     */
    @RequestMapping("/getPwerTreeRoleInfo")
    public ServiceResponse<OrgInfoVO> getPwerTreeRoleInfo(@RequestBody JsonRequest<OrgInfo> jsonRequest) {
        ServiceResponse<OrgInfoVO> response = new ServiceResponse<OrgInfoVO>();

        OrgInfo reqBody = jsonRequest.getReqBody();
        OrgInfo orgInfo = new OrgInfo();
        BeanCopyUtil.copy(reqBody, orgInfo);
        try {
            OrgInfo childTreeOrgInfo = orgInfoService.getPwerTreeRoleInfo(orgInfo);
            OrgInfoVO copy = BeanCopyUtil.copy(childTreeOrgInfo, OrgInfoVO.class);
            response.setRetContent(copy);
        } catch (BusinessException e) {
            response.setException(new BusinessException("异常编码", new Object[]{"参数"}));
            logger.error(e.getMessage());
        }
        return response;
    }

    /**
     * 组织机构信息树查詢
     * @param id
     * @return
     */
    @RequestMapping("/getPwerTreeAcctInfo")
    public ServiceResponse<OrgInfoVO> getPwerTreeAcctInfo(@RequestBody JsonRequest<OrgInfo> jsonRequest) {
        ServiceResponse<OrgInfoVO> response = new ServiceResponse<OrgInfoVO>();

        OrgInfo reqBody = jsonRequest.getReqBody();
        OrgInfo orgInfo = new OrgInfo();
        BeanCopyUtil.copy(reqBody, orgInfo);
        try {
            OrgInfo childTreeOrgInfo = orgInfoService.getPwerTreeAcctInfo(orgInfo);
            OrgInfoVO copy = BeanCopyUtil.copy(childTreeOrgInfo, OrgInfoVO.class);
            response.setRetContent(copy);
        } catch (BusinessException e) {
            response.setException(new BusinessException("异常编码", new Object[]{"参数"}));
            logger.error(e.getMessage());
        }
        return response;
    }
}
