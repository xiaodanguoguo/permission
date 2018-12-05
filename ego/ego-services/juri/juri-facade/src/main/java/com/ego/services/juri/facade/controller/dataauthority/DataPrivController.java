package com.ego.services.juri.facade.controller.dataauthority;

import java.util.List;

import com.ego.services.juri.api.vo.dataauthority.DataPrivVO;
import com.ego.services.juri.facade.service.dataauthority.DataPrivService;
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
 * controller :DataPriv
 * @author Mrli
 * @date 2018-10-24
 */
 
@RestController
@RequestMapping("/dataPriv")
public class DataPrivController {
	private static Logger logger = LoggerFactory.getLogger(DataPrivController.class);

	@Autowired
    private DataPrivService dataPrivService;
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/save")
	public ServiceResponse<Integer> save(@RequestBody JsonRequest<DataPrivVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("save 参数 = {}", JsonUtil.toJson(jsonRequest));
		DataPrivVO vo = jsonRequest.getReqBody();
		Integer result = dataPrivService.insertSelective(vo);
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
	public ServiceResponse<Integer> update(@RequestBody JsonRequest<DataPrivVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("update 参数 = {}", JsonUtil.toJson(jsonRequest));
		DataPrivVO vo = jsonRequest.getReqBody();
		Integer result = dataPrivService.updateByPrimaryKeySelective(vo);
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
	public ServiceResponse<Integer> delete(@RequestBody JsonRequest<DataPrivVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("delete 参数 = {}", JsonUtil.toJson(jsonRequest));
		DataPrivVO vo = jsonRequest.getReqBody();
		Integer result = dataPrivService.deleteByPrimaryKey(vo.getPrivId());
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
	public ServiceResponse<DataPrivVO> getDetails(@RequestBody JsonRequest<DataPrivVO> jsonRequest) {
		ServiceResponse<DataPrivVO> serviceResponse = new ServiceResponse<>();
		logger.info("queryDetails 参数 = {}", JsonUtil.toJson(jsonRequest));
		DataPrivVO vo = jsonRequest.getReqBody();
		DataPrivVO dataPrivVO = dataPrivService.getByPrimaryKey(vo.getPrivId());
		serviceResponse.setRetContent(dataPrivVO);
		return serviceResponse;
	}
	
	/**
	 * 分页查询
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/findpageresult")
	public ServiceResponse<PageDTO<DataPrivVO>> findPageResult(@RequestBody JsonRequest<DataPrivVO> jsonRequest) {
		ServiceResponse<PageDTO<DataPrivVO>> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("queryPagedResult 参数 = {}", JsonUtil.toJson(jsonRequest));
			DataPrivVO vo = jsonRequest.getReqBody();
			/*if(vo.getPageNum() != null && vo.getPageSize() != null){
				PageDTOUtil.startPage(vo.getPageNum(),vo.getPageSize());
			}else{
				PageDTOUtil.startPage(Pagination.PAGENUM.getValue(),Pagination.PAGESIZE.getValue());
			}*/
			PageDTOUtil.startPage(Pagination.PAGENUM.getValue(),Pagination.PAGESIZE.getValue());
			List<DataPrivVO> dataPrivVOs = dataPrivService.findSelective(vo);
			PageDTO<DataPrivVO> pages = PageDTOUtil.transform(dataPrivVOs);
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
	public ServiceResponse<Integer> keep(@RequestBody JsonRequest<List<DataPrivVO>> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("keep 参数 = {}", JsonUtil.toJson(jsonRequest));
		List<DataPrivVO> dataPrivVOs = jsonRequest.getReqBody();
		if (CollectionUtils.isEmpty(dataPrivVOs)) {
			serviceResponse.setRetCode("");
			return serviceResponse;
		}
		Integer result =  dataPrivService.keep(dataPrivVOs);
		if(result > 0)
			serviceResponse.setRetContent(result);
		else
			throw new BusinessException("204");
		return serviceResponse;
	}
    
}