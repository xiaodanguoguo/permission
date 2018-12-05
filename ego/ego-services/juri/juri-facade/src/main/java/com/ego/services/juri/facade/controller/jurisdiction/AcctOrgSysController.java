package com.ego.services.juri.facade.controller.jurisdiction;

import java.util.List;

import com.ego.services.juri.api.vo.jurisdiction.AcctOrgSysVO;
import com.ego.services.juri.facade.service.jurisdiction.AcctOrgSysService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.util.CollectionUtils;
import com.ebase.core.page.constant.Pagination;
import com.ebase.core.exception.BusinessException;
import com.ebase.core.page.PageDTO;
import com.ebase.core.page.PageDTOUtil;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;
import com.ebase.utils.JsonUtil;

/**
 * controller :AcctOrgSys
 * @author 
 * @date 2018-10-10
 */
 
@RestController
@RequestMapping("/acctOrgSys")
public class AcctOrgSysController {
	private static Logger logger = LoggerFactory.getLogger(AcctOrgSysController.class);

	@Autowired
    private AcctOrgSysService acctOrgSysService;
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/save")
	public ServiceResponse<Integer> save(@RequestBody JsonRequest<AcctOrgSysVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("save 参数 = {}", JsonUtil.toJson(jsonRequest));
			AcctOrgSysVO vo = jsonRequest.getReqBody();
			Integer result = acctOrgSysService.insertSelective(vo);
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
	public ServiceResponse<Integer> saveOrgInfo(@RequestBody JsonRequest<AcctOrgSysVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("save 参数 = {}", JsonUtil.toJson(jsonRequest));
			AcctOrgSysVO vo = jsonRequest.getReqBody();
			Integer result = acctOrgSysService.saveOrgInfo(vo);
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
	public ServiceResponse<Integer> update(@RequestBody JsonRequest<AcctOrgSysVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("update 参数 = {}", JsonUtil.toJson(jsonRequest));
			AcctOrgSysVO vo = jsonRequest.getReqBody();
			Integer result = acctOrgSysService.updateByPrimaryKeySelective(vo);
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
	 * 删除
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/delete")
	public ServiceResponse<Integer> delete(@RequestBody JsonRequest<AcctOrgSysVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("delete 参数 = {}", JsonUtil.toJson(jsonRequest));
			AcctOrgSysVO vo = jsonRequest.getReqBody();
			Integer result = acctOrgSysService.deleteByPrimaryKey(vo.getRelaId());
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
	 * 查询单条记录
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/queryDetails")
	public ServiceResponse<AcctOrgSysVO> queryDetails(@RequestBody JsonRequest<AcctOrgSysVO> jsonRequest) {
		ServiceResponse<AcctOrgSysVO> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("queryDetails 参数 = {}", JsonUtil.toJson(jsonRequest));
			AcctOrgSysVO vo = jsonRequest.getReqBody();
			AcctOrgSysVO acctOrgSysVO = acctOrgSysService.selectByPrimaryKey(vo.getRelaId());
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
	public ServiceResponse<PageDTO<AcctOrgSysVO>> queryPagedResult(@RequestBody JsonRequest<AcctOrgSysVO> jsonRequest) {
		ServiceResponse<PageDTO<AcctOrgSysVO>> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("queryPagedResult 参数 = {}", JsonUtil.toJson(jsonRequest));
			AcctOrgSysVO vo = jsonRequest.getReqBody();
			PageDTOUtil.startPage(Pagination.PAGENUM.getValue(),Pagination.PAGESIZE.getValue());
			List<AcctOrgSysVO> acctOrgSysVOs = acctOrgSysService.select(vo);
			PageDTO<AcctOrgSysVO> pages = PageDTOUtil.transform(acctOrgSysVOs);
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
	public ServiceResponse<Integer> keep(@RequestBody JsonRequest<List<AcctOrgSysVO>> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		
		try {
			logger.info("keep 参数 = {}", JsonUtil.toJson(jsonRequest));
			List<AcctOrgSysVO> acctOrgSysVOs = jsonRequest.getReqBody();
			if (CollectionUtils.isEmpty(acctOrgSysVOs)) {
				serviceResponse.setRetCode("");
				return serviceResponse;
			}
			Integer result =  acctOrgSysService.keep(acctOrgSysVOs);
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
    
    
}