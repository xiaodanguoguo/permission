package com.ego.services.juri.api.controller.jurisdiction;


import com.ebase.core.page.PageInfo;
import com.ebase.core.service.ServiceResponse;
import com.ego.services.juri.api.vo.jurisdiction.RoleInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoyuhang
 */
@FeignClient(value = "${ser.name.juri}") //这个是服务名
public interface RoleInfoAPI {


    /**
     * list 分页查询
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/roleInfoList",method = RequestMethod.POST)
    ServiceResponse<PageInfo<RoleInfoVO>> roleInfoList(@RequestBody RoleInfoVO jsonRequest);

    /**
     * 所有可用角色
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/roleInfoAll",method = RequestMethod.POST)
    ServiceResponse<List<RoleInfoVO>> roleInfoAll(@RequestBody RoleInfoVO jsonRequest);

    /**
     * 所有可用角色 模糊查询
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/roleInfoAllLike",method = RequestMethod.POST)
    ServiceResponse<List<RoleInfoVO>> roleInfoAllLike(@RequestBody RoleInfoVO jsonRequest);

    /**
     * 组织引用多个角色
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/orgQuoteRoleInfo",method = RequestMethod.POST)
    ServiceResponse<List<RoleInfoVO>> orgQuoteRoleInfo(@RequestBody RoleInfoVO jsonRequest);

    /**
     * 所有用户关联角色返回状态
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/roleRoleAcctInfo",method = RequestMethod.POST)
    ServiceResponse<Map> roleRoleAcctInfo(@RequestBody RoleInfoVO jsonRequest);


    /**
     * 树状图
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/roleInfoTree",method = RequestMethod.POST)
    ServiceResponse<Map> roleInfoTree(@RequestBody RoleInfoVO jsonRequest);

    /**
     * 删除验证是否关联
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/verificationDeleteRoelInfo",method = RequestMethod.POST)
    ServiceResponse<String> verificationDeleteRoelInfo(@RequestBody RoleInfoVO jsonRequest);

    /**
     * 验证引用时角色名是否重复
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/verQuoteRoleTitle",method = RequestMethod.POST)
    ServiceResponse<String> verQuoteRoleTitle(@RequestBody RoleInfoVO jsonRequest);

    /**
     * 验证引用时角色名是否重复
     * 一个组织引用多个角色
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/verQuoteRoleIds",method = RequestMethod.POST)
    ServiceResponse<String> verQuoteRoleIds(@RequestBody RoleInfoVO jsonRequest);

    /**
     * 角色停用或删除
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/keepRoleInfoStatus",method = RequestMethod.POST)
    ServiceResponse<Integer> keepRoleInfoStatus(@RequestBody RoleInfoVO jsonRequest);


    /**
     * 增加复制角色
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/saveCopyRole",method = RequestMethod.POST)
    ServiceResponse<RoleInfoVO> saveCopyRole(@RequestBody RoleInfoVO jsonRequest);

    /**
     * 增加，删除，修改
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/keepRoleInfo",method = RequestMethod.POST)
    ServiceResponse<RoleInfoVO> keepRoleInfo(@RequestBody RoleInfoVO jsonRequest);
}
