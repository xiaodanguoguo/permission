package com.ego.services.base.facade.controller.jurisdiction;

import com.ebase.core.exception.BusinessException;
import com.ebase.core.page.PageDTO;
import com.ebase.core.page.PageDTOUtil;
import com.ebase.core.page.constant.Pagination;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;
import com.ebase.utils.JsonUtil;
import com.ego.services.base.api.vo.jurisdiction.AcctRoleOrgVO;
import com.ego.services.base.facade.service.jurisdiction.AcctRoleOrgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * controller :AcctOrgSys
 * @author 
 * @date 2018-10-10
 */
 
@RestController
@RequestMapping("/acctRoleOrg")
public class AcctRoleOrgController {
	private static Logger logger = LoggerFactory.getLogger(AcctRoleOrgController.class);

	@Autowired
    private AcctRoleOrgService acctRoleOrgService;
    
    /**
	 * 保存
	 * 一个角色引用多个组织
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/save")
	public ServiceResponse<Integer> save(@RequestBody JsonRequest<AcctRoleOrgVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("save 参数 = {}", JsonUtil.toJson(jsonRequest));
			AcctRoleOrgVO vo = jsonRequest.getReqBody();
			Integer result = acctRoleOrgService.insertSelective(vo);
			serviceResponse.setRetContent(result);
		} catch (Exception e) {
			e.printStackTrace();
			serviceResponse.setException(new BusinessException("500", new Object[] {}));
			serviceResponse.setRetCode("500");
			logger.error(e.getMessage());
			return serviceResponse;
		}

		return serviceResponse;

	}


	/**
	 * 保存
	 *
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/saveOrgInfo")
	public ServiceResponse<Integer> saveOrgInfo(@RequestBody JsonRequest<AcctRoleOrgVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("save 参数 = {}", JsonUtil.toJson(jsonRequest));
			AcctRoleOrgVO vo = jsonRequest.getReqBody();
			Integer result = acctRoleOrgService.saveOrgInfo(vo);
			serviceResponse.setRetContent(result);
		} catch (Exception e) {
			e.printStackTrace();
			serviceResponse.setException(new BusinessException("500", new Object[] {}));
			serviceResponse.setRetCode("500");
			logger.error(e.getMessage());
			return serviceResponse;
		}

		return serviceResponse;

	}


	/**
	 * 更新
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/update")
	public ServiceResponse<Integer> update(@RequestBody JsonRequest<AcctRoleOrgVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("update 参数 = {}", JsonUtil.toJson(jsonRequest));
			AcctRoleOrgVO vo = jsonRequest.getReqBody();
			Integer result = acctRoleOrgService.updateByPrimaryKeySelective(vo);
			if (result > 0) {
				serviceResponse.setRetContent(result);
			}else{
				serviceResponse.setRetCode("");
			}
		} catch (Exception e) {
			e.printStackTrace();
			serviceResponse.setException(new BusinessException("500", new Object[] {}));
			serviceResponse.setRetCode("500");
			logger.error(e.getMessage());
			return serviceResponse;
		}

		return serviceResponse;

	}
	
	/**
	 * 删除
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/delete")
	public ServiceResponse<Integer> delete(@RequestBody JsonRequest<AcctRoleOrgVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("delete 参数 = {}", JsonUtil.toJson(jsonRequest));
			AcctRoleOrgVO vo = jsonRequest.getReqBody();
			Integer result = acctRoleOrgService.deleteByPrimaryKey(vo.getRelaId());
			if (result > 0) {
				serviceResponse.setRetContent(result);
			}else{
				serviceResponse.setRetCode("");
			}
		} catch (Exception e) {
			e.printStackTrace();
			serviceResponse.setException(new BusinessException("500", new Object[] {}));
			serviceResponse.setRetCode("500");
			logger.error(e.getMessage());
			return serviceResponse;
		}

		return serviceResponse;

	}
	
	/**
	 * 查询单条记录
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/queryDetails")
	public ServiceResponse<AcctRoleOrgVO> queryDetails(@RequestBody JsonRequest<AcctRoleOrgVO> jsonRequest) {
		ServiceResponse<AcctRoleOrgVO> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("queryDetails 参数 = {}", JsonUtil.toJson(jsonRequest));
			AcctRoleOrgVO vo = jsonRequest.getReqBody();
			AcctRoleOrgVO acctOrgSysVO = acctRoleOrgService.selectByPrimaryKey(vo.getRelaId());
			serviceResponse.setRetContent(acctOrgSysVO);
		} catch (Exception e) {
			e.printStackTrace();
			serviceResponse.setException(new BusinessException("500", new Object[] {}));
			serviceResponse.setRetCode("500");
			logger.error(e.getMessage());
			return serviceResponse;
		}

		return serviceResponse;

	}
	
	/**
	 * 分页查询
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/queryPagedResult")
	public ServiceResponse<PageDTO<AcctRoleOrgVO>> queryPagedResult(@RequestBody JsonRequest<AcctRoleOrgVO> jsonRequest) {
		ServiceResponse<PageDTO<AcctRoleOrgVO>> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("queryPagedResult 参数 = {}", JsonUtil.toJson(jsonRequest));
			AcctRoleOrgVO vo = jsonRequest.getReqBody();
			PageDTOUtil.startPage(Pagination.PAGENUM.getValue(),Pagination.PAGESIZE.getValue());
			List<AcctRoleOrgVO> acctOrgSysVOs = acctRoleOrgService.select(vo);
			PageDTO<AcctRoleOrgVO> pages = PageDTOUtil.transform(acctOrgSysVOs);
			serviceResponse.setRetContent(pages);
		} catch (Exception e) {
			e.printStackTrace();
			serviceResponse.setException(new BusinessException("500", new Object[] {}));
			serviceResponse.setRetCode("500");
			logger.error(e.getMessage());
			return serviceResponse;
		} finally {
			PageDTOUtil.endPage();
		}
		return serviceResponse;
	}
	
	/**
	 * 批量 保存、修改、删除
	 * 参数：opt insert、update、delete
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/keep")
	public ServiceResponse<Integer> keep(@RequestBody JsonRequest<List<AcctRoleOrgVO>> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		
		try {
			logger.info("keep 参数 = {}", JsonUtil.toJson(jsonRequest));
			List<AcctRoleOrgVO> acctOrgSysVOs = jsonRequest.getReqBody();
			if (CollectionUtils.isEmpty(acctOrgSysVOs)) {
				serviceResponse.setRetCode("");
				return serviceResponse;
			}
			Integer result =  acctRoleOrgService.keep(acctOrgSysVOs);
			if (result > 0) {
				serviceResponse.setRetContent(result);
			}else{
				serviceResponse.setRetCode("");
			}
		} catch (Exception e) {
			e.printStackTrace();
			serviceResponse.setException(new BusinessException("500", new Object[] {}));
			serviceResponse.setRetCode("500");
			logger.error(e.getMessage());
			return serviceResponse;
		}
		return serviceResponse;
	}
    
    
}