package com.ego.services.juri.facade.controller.dataauthority;

import java.util.List;

import com.ego.services.juri.api.vo.dataauthority.AccountPrivRelationVO;
import com.ego.services.juri.facade.service.dataauthority.AccountPrivRelationService;
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
 * controller :AccountPrivRelation
 * @author Mrli
 * @date 2018-10-24
 */
 
@RestController
@RequestMapping("/accountPrivRelation")
public class AccountPrivRelationController {
	private static Logger logger = LoggerFactory.getLogger(AccountPrivRelationController.class);

	@Autowired
    private AccountPrivRelationService accountPrivRelationService;
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/save")
	public ServiceResponse<Integer> save(@RequestBody JsonRequest<AccountPrivRelationVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("save 参数 = {}", JsonUtil.toJson(jsonRequest));
		AccountPrivRelationVO vo = jsonRequest.getReqBody();
		Integer result = accountPrivRelationService.insertSelective(vo);
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
	public ServiceResponse<Integer> update(@RequestBody JsonRequest<AccountPrivRelationVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("update 参数 = {}", JsonUtil.toJson(jsonRequest));
		AccountPrivRelationVO vo = jsonRequest.getReqBody();
		Integer result = accountPrivRelationService.updateByPrimaryKeySelective(vo);
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
	public ServiceResponse<Integer> delete(@RequestBody JsonRequest<AccountPrivRelationVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("delete 参数 = {}", JsonUtil.toJson(jsonRequest));
		AccountPrivRelationVO vo = jsonRequest.getReqBody();
		Integer result = accountPrivRelationService.deleteByPrimaryKey(vo.getId());
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
	public ServiceResponse<AccountPrivRelationVO> getDetails(@RequestBody JsonRequest<AccountPrivRelationVO> jsonRequest) {
		ServiceResponse<AccountPrivRelationVO> serviceResponse = new ServiceResponse<>();
		logger.info("queryDetails 参数 = {}", JsonUtil.toJson(jsonRequest));
		AccountPrivRelationVO vo = jsonRequest.getReqBody();
		AccountPrivRelationVO accountPrivRelationVO = accountPrivRelationService.getByPrimaryKey(vo.getId());
		serviceResponse.setRetContent(accountPrivRelationVO);
		return serviceResponse;
	}
	
	/**
	 * 分页查询
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/findpageresult")
	public ServiceResponse<PageDTO<AccountPrivRelationVO>> findPageResult(@RequestBody JsonRequest<AccountPrivRelationVO> jsonRequest) {
		ServiceResponse<PageDTO<AccountPrivRelationVO>> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("queryPagedResult 参数 = {}", JsonUtil.toJson(jsonRequest));
			AccountPrivRelationVO vo = jsonRequest.getReqBody();
			/*if(vo.getPageNum() != null && vo.getPageSize() != null){
				PageDTOUtil.startPage(vo.getPageNum(),vo.getPageSize());
			}else{
				PageDTOUtil.startPage(Pagination.PAGENUM.getValue(),Pagination.PAGESIZE.getValue());
			}*/
			PageDTOUtil.startPage(Pagination.PAGENUM.getValue(),Pagination.PAGESIZE.getValue());
			List<AccountPrivRelationVO> accountPrivRelationVOs = accountPrivRelationService.findSelective(vo);
			PageDTO<AccountPrivRelationVO> pages = PageDTOUtil.transform(accountPrivRelationVOs);
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
	public ServiceResponse<Integer> keep(@RequestBody JsonRequest<List<AccountPrivRelationVO>> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("keep 参数 = {}", JsonUtil.toJson(jsonRequest));
		List<AccountPrivRelationVO> accountPrivRelationVOs = jsonRequest.getReqBody();
		if (CollectionUtils.isEmpty(accountPrivRelationVOs)) {
			serviceResponse.setRetCode("");
			return serviceResponse;
		}
		Integer result =  accountPrivRelationService.keep(accountPrivRelationVOs);
		if(result > 0)
			serviceResponse.setRetContent(result);
		else
			throw new BusinessException("204");
		return serviceResponse;
	}
    
}