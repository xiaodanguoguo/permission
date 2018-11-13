package com.ego.services.base.facade.controller.dataauthority;

import java.util.List;

import com.ebase.core.page.PageInfo;
import com.ego.services.base.api.vo.dataauthority.MetadataFieldVO;
import com.ego.services.base.facade.service.dataauthority.MetadataFieldService;
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
 * controller :MetadataField
 * @author Mrli
 * @date 2018-10-24
 */
 
@RestController
@RequestMapping("/metadataField")
public class MetadataFieldController {
	private static Logger logger = LoggerFactory.getLogger(MetadataFieldController.class);

	@Autowired
    private MetadataFieldService metadataFieldService;
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/save")
	public ServiceResponse<Integer> save(@RequestBody JsonRequest<MetadataFieldVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("save 参数 = {}", JsonUtil.toJson(jsonRequest));
		MetadataFieldVO vo = jsonRequest.getReqBody();
		Integer result = metadataFieldService.insertSelective(vo);
		serviceResponse.setRetContent(result);
		return serviceResponse;
	}
	
	/**
	 * 更新
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/update")
	public ServiceResponse<Integer> update(@RequestBody JsonRequest<MetadataFieldVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("update 参数 = {}", JsonUtil.toJson(jsonRequest));
		MetadataFieldVO vo = jsonRequest.getReqBody();
		Integer result = metadataFieldService.updateByPrimaryKeySelective(vo);
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
	public ServiceResponse<Integer> delete(@RequestBody JsonRequest<MetadataFieldVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("delete 参数 = {}", JsonUtil.toJson(jsonRequest));
		MetadataFieldVO vo = jsonRequest.getReqBody();
		Integer result = metadataFieldService.deleteByPrimaryKey(vo.getFieldId());
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
	public ServiceResponse<MetadataFieldVO> getDetails(@RequestBody JsonRequest<MetadataFieldVO> jsonRequest) {
		ServiceResponse<MetadataFieldVO> serviceResponse = new ServiceResponse<>();
		logger.info("queryDetails 参数 = {}", JsonUtil.toJson(jsonRequest));
		MetadataFieldVO vo = jsonRequest.getReqBody();
		MetadataFieldVO metadataFieldVO = metadataFieldService.getByPrimaryKey(vo.getFieldId());
		serviceResponse.setRetContent(metadataFieldVO);
		return serviceResponse;
	}
	
	/**
	 * 分页查询
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/findpageresult")
	public ServiceResponse<PageInfo<MetadataFieldVO>> findPageResult(@RequestBody JsonRequest<MetadataFieldVO> jsonRequest) {
		ServiceResponse<PageInfo<MetadataFieldVO>> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("queryPagedResult 参数 = {}", JsonUtil.toJson(jsonRequest));
			MetadataFieldVO vo = jsonRequest.getReqBody();
			PageInfo<MetadataFieldVO> pages = metadataFieldService.findSelective(vo);
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
	public ServiceResponse<Integer> keep(@RequestBody JsonRequest<List<MetadataFieldVO>> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("keep 参数 = {}", JsonUtil.toJson(jsonRequest));
		List<MetadataFieldVO> metadataFieldVOs = jsonRequest.getReqBody();
		if (CollectionUtils.isEmpty(metadataFieldVOs)) {
			serviceResponse.setRetCode("");
			return serviceResponse;
		}
		Integer result =  metadataFieldService.keep(metadataFieldVOs);
		if(result > 0)
			serviceResponse.setRetContent(result);
		else
			throw new BusinessException("204");
		return serviceResponse;
	}
    
}