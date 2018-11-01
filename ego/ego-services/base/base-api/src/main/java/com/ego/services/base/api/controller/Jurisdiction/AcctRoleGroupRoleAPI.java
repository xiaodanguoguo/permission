package com.ego.services.base.api.controller.jurisdiction;


import com.ebase.core.service.ServiceResponse;
import com.ego.services.base.api.vo.jurisdiction.AcctRoleGroupRoleVO;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: zhaoyuhang
 */
@FeignClient(value = "${ser.name.base}") //这个是服务名
public interface AcctRoleGroupRoleAPI {

    /**
     * 删除管理
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/delAcctRoleGroupRole",method = RequestMethod.POST)
    ServiceResponse<Integer> delAcctRoleGroupRole(@RequestBody AcctRoleGroupRoleVO jsonRequest);

    /**
     * 添加管理
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/addAcctRoleGroupRole",method = RequestMethod.POST)
    ServiceResponse<Integer> addAcctRoleGroupRole(@RequestBody AcctRoleGroupRoleVO jsonRequest);
}
