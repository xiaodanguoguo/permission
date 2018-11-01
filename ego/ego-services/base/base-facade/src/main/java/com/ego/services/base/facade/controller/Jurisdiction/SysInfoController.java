package com.ego.services.base.facade.controller.jurisdiction;

import java.util.List;

import com.ebase.core.page.PageInfo;
import com.ego.services.base.api.vo.jurisdiction.SysInfoVO;
import com.ego.services.base.facade.service.jurisdiction.SysInfoService;
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
 * controller :SysInfo
 * @author 
 * @date 2018-10-10
 */
 
@RestController
@RequestMapping("/sysInfo")
public class SysInfoController {
	private static Logger logger = LoggerFactory.getLogger(SysInfoController.class);

	@Autowired
    private SysInfoService sysInfoService;
    
    /**
	 * 保存
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/save")
	public ServiceResponse<SysInfoVO> save(@RequestBody SysInfoVO jsonRequest) {
		ServiceResponse<SysInfoVO> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("save 参数 = {}", JsonUtil.toJson(jsonRequest));
            SysInfoVO result = sysInfoService.insertSelective(jsonRequest);
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
	public ServiceResponse<SysInfoVO> update(@RequestBody SysInfoVO jsonRequest) {
		ServiceResponse<SysInfoVO> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("update 参数 = {}", JsonUtil.toJson(jsonRequest));
            SysInfoVO result = sysInfoService.updateByPrimaryKeySelective(jsonRequest);
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
	public ServiceResponse<SysInfoVO> delete(@RequestBody JsonRequest<SysInfoVO> jsonRequest) {
		ServiceResponse<SysInfoVO> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("delete 参数 = {}", JsonUtil.toJson(jsonRequest));
			SysInfoVO vo = jsonRequest.getReqBody();
			SysInfoVO result = sysInfoService.deleteByPrimaryKey(vo.getSysId());
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
	 * 系统删除验证  是否被组织关联
	 * @param sysInfoVO
	 * @return
	 */
	@RequestMapping("/verSysInfo")
	public ServiceResponse<String> verSysInfo(@RequestBody SysInfoVO sysInfoVO) {
		ServiceResponse<String> jsonResponse = new ServiceResponse();
		try {
			logger.info("delete 参数 = {}", JsonUtil.toJson(sysInfoVO));
			String ver = sysInfoService.verSysInfo(sysInfoVO);
			jsonResponse.setRetContent(ver);
		} catch (Exception e) {
			jsonResponse.setException(new BusinessException("0102002", new Object[]{sysInfoVO}));
			logger.error(e.getMessage());
		}
		return jsonResponse;
	}

	/**
	 * 系统删除验证  是否被组织关联
	 * @param sysInfoVO
	 * @return
	 */
	@RequestMapping("/verInsertSysInfo")
	public ServiceResponse<String> verInsertSysInfo(@RequestBody SysInfoVO sysInfoVO) {
		ServiceResponse<String> jsonResponse = new ServiceResponse();
		try {
			logger.info("delete 参数 = {}", JsonUtil.toJson(sysInfoVO));
			String ver = sysInfoService.verInsertSysInfo(sysInfoVO);
			jsonResponse.setRetContent(ver);
		} catch (Exception e) {
			jsonResponse.setException(new BusinessException("0102002", new Object[]{sysInfoVO}));
			logger.error(e.getMessage());
		}
		return jsonResponse;
	}
	
	/**
	 * 查询单条记录
	 * 
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/queryDetails")
	public ServiceResponse<SysInfoVO> queryDetails(@RequestBody JsonRequest<SysInfoVO> jsonRequest) {
		ServiceResponse<SysInfoVO> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("queryDetails 参数 = {}", JsonUtil.toJson(jsonRequest));
			SysInfoVO vo = jsonRequest.getReqBody();
			SysInfoVO sysInfoVO = sysInfoService.selectByPrimaryKey(vo.getSysId());
			serviceResponse.setRetContent(sysInfoVO);
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
	public ServiceResponse<PageInfo<SysInfoVO>> queryPagedResult(@RequestBody JsonRequest<SysInfoVO> jsonRequest) {
		ServiceResponse<PageInfo<SysInfoVO>> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("queryPagedResult 参数 = {}", JsonUtil.toJson(jsonRequest));
			SysInfoVO vo = jsonRequest.getReqBody();
			PageInfo<SysInfoVO> pages = sysInfoService.select(vo);
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
	 * 组织管理员创建的系统
	 *
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/selectSysInfoOrgCreate")
	public ServiceResponse<List<SysInfoVO>> selectSysInfoOrgCreate(@RequestBody SysInfoVO jsonRequest) {
		ServiceResponse<List<SysInfoVO>> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("queryPagedResult 参数 = {}", JsonUtil.toJson(jsonRequest));
			List<SysInfoVO> pages = sysInfoService.selectSysInfoOrgCreate(jsonRequest);
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
	 * 引用查询
	 *
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/selectSysInfoOrg")
	public ServiceResponse<List<SysInfoVO>> selectSysInfoOrg(@RequestBody SysInfoVO jsonRequest) {
		ServiceResponse<List<SysInfoVO>> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("queryPagedResult 参数 = {}", JsonUtil.toJson(jsonRequest));
			List<SysInfoVO> pages = sysInfoService.selectSysInfoOrg(jsonRequest);
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
	 * 根据组织查询创建系统
	 *
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/selectSysEstablish")
	public ServiceResponse<List<SysInfoVO>> selectSysEstablish(@RequestBody SysInfoVO jsonRequest) {
		ServiceResponse<List<SysInfoVO>> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("queryPagedResult 参数 = {}", JsonUtil.toJson(jsonRequest));
			List<SysInfoVO> pages = sysInfoService.selectSysEstablish(jsonRequest);
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
	 * 查看查询
	 *
	 * @param jsonRequest
	 * @return
	 */
	@RequestMapping("/selectSysInfoOrgSee")
	public ServiceResponse<List<SysInfoVO>> selectSysInfoOrgSee(@RequestBody SysInfoVO jsonRequest) {
		ServiceResponse<List<SysInfoVO>> serviceResponse = new ServiceResponse<>();
		try {
			logger.info("queryPagedResult 参数 = {}", JsonUtil.toJson(jsonRequest));
			List<SysInfoVO> pages = sysInfoService.selectSysInfoOrgSee(jsonRequest);
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
	public ServiceResponse<SysInfoVO> keep(@RequestBody JsonRequest<List<SysInfoVO>> jsonRequest) {
		ServiceResponse<SysInfoVO> serviceResponse = new ServiceResponse<>();
		
		try {
			logger.info("keep 参数 = {}", JsonUtil.toJson(jsonRequest));
			List<SysInfoVO> sysInfoVOs = jsonRequest.getReqBody();
			if (CollectionUtils.isEmpty(sysInfoVOs)) {
				serviceResponse.setRetCode("");
				return serviceResponse;
			}
			SysInfoVO result =  sysInfoService.keep(sysInfoVOs);
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