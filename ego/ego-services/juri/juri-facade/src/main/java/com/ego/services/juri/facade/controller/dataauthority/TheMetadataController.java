package com.ego.services.juri.facade.controller.dataauthority;

import java.util.List;

import com.ebase.core.page.PageInfo;
import com.ego.services.juri.api.vo.dataauthority.TheMetadataVO;
import com.ego.services.juri.facade.service.dataauthority.TheMetadataService;
import com.ego.services.juri.facade.service.dataauthority.TheMetadataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.util.CollectionUtils;

import com.ebase.core.exception.BusinessException;
import com.ebase.core.page.PageDTOUtil;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.web.json.JsonRequest;
import com.ebase.utils.JsonUtil;

/**
 * controller :TheMetadata
 * @author Mrli
 * @date 2018-10-24
 */
 
@RestController
@RequestMapping("/theMetadata")
public class TheMetadataController {
	private static Logger logger = LoggerFactory.getLogger(TheMetadataController.class);

	@Autowired
    private TheMetadataService theMetadataService;
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/save")
	public ServiceResponse<Integer> save(@RequestBody JsonRequest<TheMetadataVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("save 参数 = {}", JsonUtil.toJson(jsonRequest));
		TheMetadataVO vo = jsonRequest.getReqBody();
		Integer result = theMetadataService.insertSelective(vo);
		serviceResponse.setRetContent(result);
		if(result==-1){
			serviceResponse.setRetCode("0001001");
			serviceResponse.setRetMessage("名称重复");
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
	public ServiceResponse<Integer> update(@RequestBody JsonRequest<TheMetadataVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("update 参数 = {}", JsonUtil.toJson(jsonRequest));
		TheMetadataVO vo = jsonRequest.getReqBody();
		Integer result = theMetadataService.updateByPrimaryKeySelective(vo);
		serviceResponse.setRetContent(result);
		if(result==-1){
			serviceResponse.setRetCode("0001001");
			serviceResponse.setRetMessage("名称重复");
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
	public ServiceResponse<Integer> delete(@RequestBody JsonRequest<TheMetadataVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("delete 参数 = {}", JsonUtil.toJson(jsonRequest));
		TheMetadataVO vo = jsonRequest.getReqBody();
		Integer result = theMetadataService.deleteByPrimaryKey(vo.getId());
		serviceResponse.setRetContent(result);
		return serviceResponse;
	}
	
	/**
	 * 查询单条记录
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/getdetails")
	public ServiceResponse<TheMetadataVO> getDetails(@RequestBody JsonRequest<TheMetadataVO> jsonRequest) {
		ServiceResponse<TheMetadataVO> serviceResponse = new ServiceResponse<>();
		logger.info("queryDetails 参数 = {}", JsonUtil.toJson(jsonRequest));
		TheMetadataVO vo = jsonRequest.getReqBody();
		TheMetadataVO theMetadataVO = theMetadataService.getByPrimaryKey(vo.getId());
		serviceResponse.setRetContent(theMetadataVO);
		return serviceResponse;
	}
	
	/**
	 * 分页查询
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/findpageresult")
	public ServiceResponse<PageInfo<TheMetadataVO>> findPageResult(@RequestBody JsonRequest<TheMetadataVO> jsonRequest) {
		ServiceResponse<PageInfo<TheMetadataVO>> serviceResponse = new ServiceResponse<>();
		try {
			//jsonRequest.getReqHeader().setAcctId(AssertContext.getAcctId());
			logger.info("queryPagedResult 参数 = {}", JsonUtil.toJson(jsonRequest));
			TheMetadataVO vo = jsonRequest.getReqBody();
			PageInfo<TheMetadataVO> pages = theMetadataService.findSelective(vo);
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
	public ServiceResponse<Integer> keep(@RequestBody JsonRequest<List<TheMetadataVO>> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("keep 参数 = {}", JsonUtil.toJson(jsonRequest));
		List<TheMetadataVO> theMetadataVOs = jsonRequest.getReqBody();
		if (CollectionUtils.isEmpty(theMetadataVOs)) {
			serviceResponse.setRetCode("");
			return serviceResponse;
		}
		Integer result =  theMetadataService.keep(theMetadataVOs);
		if(result > 0)
			serviceResponse.setRetContent(result);
		else
			throw new BusinessException("204");
		return serviceResponse;
	}
    
}