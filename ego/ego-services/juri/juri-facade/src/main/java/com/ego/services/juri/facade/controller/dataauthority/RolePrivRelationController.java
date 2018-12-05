package com.ego.services.juri.facade.controller.dataauthority;

import java.util.List;

import com.ego.services.juri.api.vo.dataauthority.RolePrivRelationVO;
import com.ego.services.juri.facade.service.dataauthority.RolePrivRelationService;
import com.ego.services.juri.facade.service.dataauthority.RolePrivRelationService;
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
 * controller :RolePrivRelation
 * @author Mrli
 * @date 2018-10-24
 */
 
@RestController
@RequestMapping("/rolePrivRelation")
public class RolePrivRelationController {
	private static Logger logger = LoggerFactory.getLogger(RolePrivRelationController.class);

	@Autowired
    private RolePrivRelationService rolePrivRelationService;
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/save")
	public ServiceResponse<Integer> save(@RequestBody JsonRequest<RolePrivRelationVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("save 参数 = {}", JsonUtil.toJson(jsonRequest));
		RolePrivRelationVO vo = jsonRequest.getReqBody();
		Integer result = rolePrivRelationService.insertSelective(vo);
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
	public ServiceResponse<Integer> update(@RequestBody JsonRequest<RolePrivRelationVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("update 参数 = {}", JsonUtil.toJson(jsonRequest));
		RolePrivRelationVO vo = jsonRequest.getReqBody();
		Integer result = rolePrivRelationService.updateByPrimaryKeySelective(vo);
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
	public ServiceResponse<Integer> delete(@RequestBody JsonRequest<RolePrivRelationVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("delete 参数 = {}", JsonUtil.toJson(jsonRequest));
		RolePrivRelationVO vo = jsonRequest.getReqBody();
		Integer result = rolePrivRelationService.deleteByPrimaryKey(vo.getId());
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
	public ServiceResponse<RolePrivRelationVO> getDetails(@RequestBody JsonRequest<RolePrivRelationVO> jsonRequest) {
		ServiceResponse<RolePrivRelationVO> serviceResponse = new ServiceResponse<>();
		logger.info("queryDetails 参数 = {}", JsonUtil.toJson(jsonRequest));
		RolePrivRelationVO vo = jsonRequest.getReqBody();
		RolePrivRelationVO rolePrivRelationVO = rolePrivRelationService.getByPrimaryKey(vo.getId());
		serviceResponse.setRetContent(rolePrivRelationVO);
		return serviceResponse;
	}
	
	/**
	 * 分页查询
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/findpageresult")
	public ServiceResponse<PageDTO<RolePrivRelationVO>> findPageResult(@RequestBody JsonRequest<RolePrivRelationVO> jsonRequest) {
		ServiceResponse<PageDTO<RolePrivRelationVO>> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("queryPagedResult 参数 = {}", JsonUtil.toJson(jsonRequest));
			RolePrivRelationVO vo = jsonRequest.getReqBody();
			/*if(vo.getPageNum() != null && vo.getPageSize() != null){
				PageDTOUtil.startPage(vo.getPageNum(),vo.getPageSize());
			}else{
				PageDTOUtil.startPage(Pagination.PAGENUM.getValue(),Pagination.PAGESIZE.getValue());
			}*/
			PageDTOUtil.startPage(Pagination.PAGENUM.getValue(),Pagination.PAGESIZE.getValue());
			List<RolePrivRelationVO> rolePrivRelationVOs = rolePrivRelationService.findSelective(vo);
			PageDTO<RolePrivRelationVO> pages = PageDTOUtil.transform(rolePrivRelationVOs);
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
	public ServiceResponse<Integer> keep(@RequestBody JsonRequest<List<RolePrivRelationVO>> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("keep 参数 = {}", JsonUtil.toJson(jsonRequest));
		List<RolePrivRelationVO> rolePrivRelationVOs = jsonRequest.getReqBody();
		if (CollectionUtils.isEmpty(rolePrivRelationVOs)) {
			serviceResponse.setRetCode("");
			return serviceResponse;
		}
		Integer result =  rolePrivRelationService.keep(rolePrivRelationVOs);
		if(result > 0)
			serviceResponse.setRetContent(result);
		else
			throw new BusinessException("204");
		return serviceResponse;
	}
    
}