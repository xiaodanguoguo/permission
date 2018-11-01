package com.ego.services.base.api.controller.jurisdiction;


import com.ebase.core.page.PageInfo;
import com.ebase.core.service.ServiceResponse;
import com.ego.services.base.api.vo.jurisdiction.RoleGroupVO;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Auther: zhaoyuhang
 */
@FeignClient(value = "${ser.name.base}") //这个是服务名
public interface RoleGroupAPI {


    /**
     * list 分页查询
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/roleGroupList",method = RequestMethod.POST)
    ServiceResponse<PageInfo<RoleGroupVO>> roleGroupList(@RequestBody RoleGroupVO jsonRequest);

    /**
     * 删除验证
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/verificationDeleteRoleGroup",method = RequestMethod.POST)
    ServiceResponse<String> verificationDeleteRoleGroup(@RequestBody RoleGroupVO jsonRequest);

    /**
     * 所有角色组
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/roleGroupAll",method = RequestMethod.POST)
    ServiceResponse<List<RoleGroupVO>> roleGroupAll(@RequestBody RoleGroupVO jsonRequest);


    /**
     * 增加，删除，修改
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/keepRoleGroup",method = RequestMethod.POST)
    ServiceResponse<RoleGroupVO> keepRoleGroup(@RequestBody RoleGroupVO jsonRequest);
}
