package com.ego.services.juri.facade.controller.dataauthority;

import java.util.List;

import com.ebase.core.page.PageInfo;
import com.ego.services.juri.api.vo.dataauthority.PowerExpressionVO;
import com.ego.services.juri.facade.service.dataauthority.PowerExpressionService;
import com.ego.services.juri.facade.service.dataauthority.PowerExpressionService;
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
 * controller :PowerExpression
 * @author Mrli
 * @date 2018-11-1
 */
 
@RestController
@RequestMapping("/powerExpression")
public class PowerExpressionController {
	private static Logger logger = LoggerFactory.getLogger(PowerExpressionController.class);

	@Autowired
    private PowerExpressionService powerExpressionService;
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/save")
	public ServiceResponse<Integer> save(@RequestBody JsonRequest<PowerExpressionVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("save 参数 = {}", JsonUtil.toJson(jsonRequest));
		Integer result = powerExpressionService.insertSelective(jsonRequest.getReqBody());
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
	public ServiceResponse<Integer> update(@RequestBody JsonRequest<PowerExpressionVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("update 参数 = {}", JsonUtil.toJson(jsonRequest));
		PowerExpressionVO vo = jsonRequest.getReqBody();
		Integer result = powerExpressionService.updateByPrimaryKeySelective(vo);
		serviceResponse.setRetContent(result);
		return serviceResponse;
	}
	
	/**
	 * 删除
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/delete")
	public ServiceResponse<Integer> delete(@RequestBody JsonRequest<PowerExpressionVO> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("delete 参数 = {}", JsonUtil.toJson(jsonRequest));
		PowerExpressionVO vo = jsonRequest.getReqBody();
		Integer result = powerExpressionService.deleteByPrimaryKey(vo.getPowerExpressionId());
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
	public ServiceResponse<PowerExpressionVO> getDetails(@RequestBody JsonRequest<PowerExpressionVO> jsonRequest) {
		ServiceResponse<PowerExpressionVO> serviceResponse = new ServiceResponse<>();
		logger.info("queryDetails 参数 = {}", JsonUtil.toJson(jsonRequest));
		PowerExpressionVO vo = jsonRequest.getReqBody();
		PowerExpressionVO powerExpressionVO = powerExpressionService.getByPrimaryKey(vo.getPowerExpressionId());
		serviceResponse.setRetContent(powerExpressionVO);
		return serviceResponse;
	}
	
	/**
	 * 分页查询
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/findpageresult")
	public ServiceResponse<PageInfo<PowerExpressionVO>> findPageResult(@RequestBody JsonRequest<PowerExpressionVO> jsonRequest) {
		ServiceResponse<PageInfo<PowerExpressionVO>> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("queryPagedResult 参数 = {}", JsonUtil.toJson(jsonRequest));
			PowerExpressionVO vo = jsonRequest.getReqBody();
			/*if(vo.getPageNum() != null && vo.getPageSize() != null){
				PageDTOUtil.startPage(vo.getPageNum(),vo.getPageSize());
			}else{
				PageDTOUtil.startPage(Pagination.PAGENUM.getValue(),Pagination.PAGESIZE.getValue());
			}*/
			PageInfo<PowerExpressionVO> pages = powerExpressionService.findSelective(vo);
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
	public ServiceResponse<Integer> keep(@RequestBody JsonRequest<List<PowerExpressionVO>> jsonRequest) {
		ServiceResponse<Integer> serviceResponse = new ServiceResponse<>();
		logger.info("keep 参数 = {}", JsonUtil.toJson(jsonRequest));
		List<PowerExpressionVO> powerExpressionVOs = jsonRequest.getReqBody();
		if (CollectionUtils.isEmpty(powerExpressionVOs)) {
			serviceResponse.setRetCode("");
			return serviceResponse;
		}
		Integer result =  powerExpressionService.keep(powerExpressionVOs);
		if(result > 0)
			serviceResponse.setRetContent(result);
		else
			throw new BusinessException("204");
		return serviceResponse;
	}



	/**
	 * 用户查询数据权限
	 *
	 * @param acctId
	 * @return
	 */
	@RequestMapping("/selectAcctConfig")
	public ServiceResponse<PowerExpressionVO> selectAcctConfig(@RequestBody Long acctId) {
		ServiceResponse<PowerExpressionVO> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("queryPagedResult 参数 = {}", JsonUtil.toJson(acctId));
			PowerExpressionVO pages = powerExpressionService.selectAcctConfig(acctId);
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
}