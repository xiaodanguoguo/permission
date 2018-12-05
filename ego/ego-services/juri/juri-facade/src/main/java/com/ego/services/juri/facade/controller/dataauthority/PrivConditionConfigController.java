package com.ego.services.juri.facade.controller.dataauthority;

import java.util.List;

import com.ego.services.juri.api.vo.dataauthority.PrivConditionConfigVO;
import com.ego.services.juri.facade.service.dataauthority.PrivConditionConfigService;
import com.ego.services.juri.facade.service.dataauthority.PrivConditionConfigService;
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
 * controller :PrivConditionConfig
 * @author Mrli
 * @date 2018-10-24
 */
 
@RestController
@RequestMapping("/privConditionConfig")
public class PrivConditionConfigController {
	private static Logger logger = LoggerFactory.getLogger(PrivConditionConfigController.class);

	@Autowired
    private PrivConditionConfigService privConditionConfigService;
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/save")
	public ServiceResponse<Integer> save(@RequestBody JsonRequest<PrivConditionConfigVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("save 参数 = {}", JsonUtil.toJson(jsonRequest));
		PrivConditionConfigVO vo = jsonRequest.getReqBody();
		Integer result = privConditionConfigService.insertSelective(vo);
		if(result > 0)
			serviceResponse.setRetContent(result);
		else
			throw new BusinessException("204");
		return serviceResponse;
	}
	
	/**
	 * 更新
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/update")
	public ServiceResponse<Integer> update(@RequestBody JsonRequest<PrivConditionConfigVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("update 参数 = {}", JsonUtil.toJson(jsonRequest));
		PrivConditionConfigVO vo = jsonRequest.getReqBody();
		Integer result = privConditionConfigService.updateByPrimaryKeySelective(vo);
		if(result > 0)
			serviceResponse.setRetContent(result);
		else
			throw new BusinessException("204");
		return serviceResponse;
	}
	
	/**
	 * 删除
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/delete")
	public ServiceResponse<Integer> delete(@RequestBody JsonRequest<PrivConditionConfigVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("delete 参数 = {}", JsonUtil.toJson(jsonRequest));
		PrivConditionConfigVO vo = jsonRequest.getReqBody();
		Integer result = privConditionConfigService.deleteByPrimaryKey(vo.getId());
		if(result > 0)
			serviceResponse.setRetContent(result);
		else
			throw new BusinessException("204");
		return serviceResponse;
	}
	
	/**
	 * 查询单条记录
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/getdetails")
	public ServiceResponse<PrivConditionConfigVO> getDetails(@RequestBody JsonRequest<PrivConditionConfigVO> jsonRequest) {
		ServiceResponse<PrivConditionConfigVO> serviceResponse = new ServiceResponse<>();
		logger.info("queryDetails 参数 = {}", JsonUtil.toJson(jsonRequest));
		PrivConditionConfigVO vo = jsonRequest.getReqBody();
		PrivConditionConfigVO privConditionConfigVO = privConditionConfigService.getByPrimaryKey(vo.getId());
		serviceResponse.setRetContent(privConditionConfigVO);
		return serviceResponse;
	}
	
	/**
	 * 分页查询
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/findpageresult")
	public ServiceResponse<PageDTO<PrivConditionConfigVO>> findPageResult(@RequestBody JsonRequest<PrivConditionConfigVO> jsonRequest) {
		ServiceResponse<PageDTO<PrivConditionConfigVO>> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("queryPagedResult 参数 = {}", JsonUtil.toJson(jsonRequest));
			PrivConditionConfigVO vo = jsonRequest.getReqBody();
			/*if(vo.getPageNum() != null && vo.getPageSize() != null){
				PageDTOUtil.startPage(vo.getPageNum(),vo.getPageSize());
			}else{
				PageDTOUtil.startPage(Pagination.PAGENUM.getValue(),Pagination.PAGESIZE.getValue());
			}*/
			PageDTOUtil.startPage(Pagination.PAGENUM.getValue(),Pagination.PAGESIZE.getValue());
			List<PrivConditionConfigVO> privConditionConfigVOs = privConditionConfigService.findSelective(vo);
			PageDTO<PrivConditionConfigVO> pages = PageDTOUtil.transform(privConditionConfigVOs);
			serviceResponse.setRetContent(pages);
		} catch (Exception e) {
			e.printStackTrace();
			serviceResponse.setException(new BusinessException("500", new Object[] {}));
			serviceResponse.setRetCode("500");
			logger.error(e.getMessage());
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
	public ServiceResponse<Integer> keep(@RequestBody JsonRequest<List<PrivConditionConfigVO>> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("keep 参数 = {}", JsonUtil.toJson(jsonRequest));
		List<PrivConditionConfigVO> privConditionConfigVOs = jsonRequest.getReqBody();
		if (CollectionUtils.isEmpty(privConditionConfigVOs)) {
			serviceResponse.setRetCode("");
			return serviceResponse;
		}
		Integer result =  privConditionConfigService.keep(privConditionConfigVOs);
		if(result > 0)
			serviceResponse.setRetContent(result);
		else
			throw new BusinessException("204");
		return serviceResponse;
	}
    
}