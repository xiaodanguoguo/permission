package com.ego.services.juri.facade.controller.jurisdiction;



import com.ebase.core.exception.BusinessException;
import com.ebase.core.service.ServiceResponse;
import com.ebase.utils.JsonUtil;
import com.ego.services.juri.api.vo.jurisdiction.AcctRoleGroupRoleVO;
import com.ego.services.juri.facade.service.jurisdiction.AcctRoleGroupRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统基础模块-  角色组与角色关联
 * @Auther: zhaoyuhang
 */
@RestController
public class AcctRoleGroupRoleController {

    private static Logger LOG = LoggerFactory.getLogger(AcctRoleGroupRoleController.class);

    @Autowired
    private AcctRoleGroupRoleService acctRoleGroupRoleService;


    /**
     * 系统功能管理 角色与角色组关联添加
     * @param jsonRequest
     * @return
     */
    @RequestMapping("/addAcctRoleGroupRole")
    public ServiceResponse<Integer> addAcctRoleGroupRole(@RequestBody AcctRoleGroupRoleVO jsonRequest){
        ServiceResponse<Integer> response = new ServiceResponse();
        try {
            //角色组ID
            if(StringUtils.isEmpty(jsonRequest.getRoleGroupId())){
                response.setRetCode("0102005");
                return response;
            }
            //角色ID
            if(StringUtils.isEmpty(jsonRequest.getRoleId())){
                response.setRetCode("0102005");
                return response;
            }
            LOG.info("list 参数 = {}",JsonUtil.toJson(jsonRequest));
            Integer acctRoleGroupRole = acctRoleGroupRoleService.addAcctRoleGroupRole(jsonRequest);
            response.setRetContent(acctRoleGroupRole);
        } catch (Exception e) {
            response.setException(new BusinessException("异常编码", new Object[]{"参数"}));
            LOG.error(e.getMessage());
        }
        return response;
    }


    /**
     * 系统功能管理 角色与角色组关联删除
     * @param jsonRequest
     * @return
     */
    @RequestMapping("/delAcctRoleGroupRole")
    public ServiceResponse<Integer> delAcctRoleGroupRole(@RequestBody AcctRoleGroupRoleVO jsonRequest){
        ServiceResponse<Integer> response = new ServiceResponse<>();
        try {
            //角色组ID
            if(StringUtils.isEmpty(jsonRequest.getRoleGroupId())){
                response.setRetCode("0102005");
                return response;
            }
            LOG.info("list 参数 = {}",JsonUtil.toJson(jsonRequest));
            Integer acctRoleGroupRole = acctRoleGroupRoleService.delAcctRoleGroupRole(jsonRequest);
            response.setRetContent(acctRoleGroupRole);
        } catch (Exception e) {
            throw new BusinessException("0106001");
        }
        return response;
    }
}
