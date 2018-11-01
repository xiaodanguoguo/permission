package com.ego.services.base.facade.controller.jurisdiction;

import java.util.List;

import com.ebase.core.page.PageInfo;
import com.ego.services.base.api.vo.jurisdiction.AcctFunctionSysVO;
import com.ego.services.base.facade.service.jurisdiction.AcctFunctionSysService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.util.CollectionUtils;

import com.ebase.core.page.constant.Pagination;
import com.ebase.core.exception.BusinessException;
import com.ebase.core.page.PageDTOUtil;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;
import com.ebase.utils.JsonUtil;

/**
 * controller :AcctFunctionSys
 * @author 
 * @date 2018-10-10
 */
 
@RestController
@RequestMapping("/acctFunctionSys")
public class AcctFunctionSysController {
	private static Logger logger = LoggerFactory.getLogger(AcctFunctionSysController.class);

	@Autowired
    private AcctFunctionSysService acctFunctionSysService;
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/save")
	public ServiceResponse<Integer> save(@RequestBody JsonRequest<AcctFunctionSysVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("save 参数 = {}", JsonUtil.toJson(jsonRequest));
			AcctFunctionSysVO vo = jsonRequest.getReqBody();
			Integer result = acctFunctionSysService.insertSelective(vo);
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
	 * 更新
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/update")
	public ServiceResponse<Integer> update(@RequestBody JsonRequest<AcctFunctionSysVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("update 参数 = {}", JsonUtil.toJson(jsonRequest));
			AcctFunctionSysVO vo = jsonRequest.getReqBody();
			Integer result = acctFunctionSysService.updateByPrimaryKeySelective(vo);
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
	public ServiceResponse<Integer> delete(@RequestBody JsonRequest<AcctFunctionSysVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("delete 参数 = {}", JsonUtil.toJson(jsonRequest));
			AcctFunctionSysVO vo = jsonRequest.getReqBody();
			Integer result = acctFunctionSysService.deleteByPrimaryKey(vo.getRelaId());
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
	public ServiceResponse<AcctFunctionSysVO> queryDetails(@RequestBody JsonRequest<AcctFunctionSysVO> jsonRequest) {
		ServiceResponse<AcctFunctionSysVO> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("queryDetails 参数 = {}", JsonUtil.toJson(jsonRequest));
			AcctFunctionSysVO vo = jsonRequest.getReqBody();
			AcctFunctionSysVO acctFunctionSysVO = acctFunctionSysService.selectByPrimaryKey(vo.getRelaId());
			serviceResponse.setRetContent(acctFunctionSysVO);
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
	public ServiceResponse<PageInfo<AcctFunctionSysVO>> queryPagedResult(@RequestBody JsonRequest<AcctFunctionSysVO> jsonRequest) {
		ServiceResponse<PageInfo<AcctFunctionSysVO>> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("queryPagedResult 参数 = {}", JsonUtil.toJson(jsonRequest));
			AcctFunctionSysVO vo = jsonRequest.getReqBody();
			PageDTOUtil.startPage(Pagination.PAGENUM.getValue(),Pagination.PAGESIZE.getValue());
			PageInfo<AcctFunctionSysVO> pageInfo = acctFunctionSysService.select(vo);
			serviceResponse.setRetContent(pageInfo);
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
	public ServiceResponse<Integer> keep(@RequestBody JsonRequest<List<AcctFunctionSysVO>> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		
		try {
			logger.info("keep 参数 = {}", JsonUtil.toJson(jsonRequest));
			List<AcctFunctionSysVO> acctFunctionSysVOs = jsonRequest.getReqBody();
			if (CollectionUtils.isEmpty(acctFunctionSysVOs)) {
				serviceResponse.setRetCode("");
				return serviceResponse;
			}
			Integer result =  acctFunctionSysService.keep(acctFunctionSysVOs);
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