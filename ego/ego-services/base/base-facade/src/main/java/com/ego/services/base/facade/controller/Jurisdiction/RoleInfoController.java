package com.ego.services.base.facade.controller.Jurisdiction;


import com.ebase.core.exception.BusinessException;
import com.ebase.core.service.ServiceResponse;
import com.ebase.utils.BeanCopyUtil;
import com.ebase.utils.JsonUtil;
import com.ego.services.base.api.vo.Jurisdiction.RoleInfoVO;
import com.ego.services.base.facade.common.SysPramType;
import com.ego.services.base.facade.dao.Jurisdiction.RoleInfoMapper;
import com.ego.services.base.facade.model.Jurisdiction.RoleInfo;
import com.ego.services.base.facade.service.Jurisdiction.RoleInfoService;
import com.github.pagehelper.PageHelper;
import com.ebase.core.page.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统基础模块-  系统角色定义
 * @Auther: zhaoyuhang
 */
@RestController
public class RoleInfoController {

    private static Logger LOG = LoggerFactory.getLogger(RoleInfoController.class);

    @Autowired
    private RoleInfoMapper roleInfoMapper;

    @Autowired
    private RoleInfoService roleInfoService;


    /**
     * 系统参数 所有可用角色 接口
     * @param jsonRequest
     * @return
     */
    @RequestMapping("/verificationDeleteRoelInfo")
    public ServiceResponse<String> verificationDeleteRoelInfo(@RequestBody RoleInfoVO jsonRequest){
        ServiceResponse<String> jsonResponse = new ServiceResponse();
        try {
            LOG.info("list 参数 = {}",JsonUtil.toJson(jsonRequest));
            String ver= roleInfoService.verificationDeleteRoelInfo(jsonRequest);
            jsonResponse.setRetContent(ver);
        } catch (Exception e) {
            throw new BusinessException("0103002");
        }
        return jsonResponse;
    }


    /**
     * 系统参数 所有可用角色 接口  模糊查询
     * @param jsonRequest
     * @return
     */
    @RequestMapping("/roleInfoAllLike")
    public ServiceResponse<List<RoleInfoVO>> roleInfoAllLike(@RequestBody RoleInfoVO jsonRequest){
        ServiceResponse<List<RoleInfoVO>> jsonResponse = new ServiceResponse();
        try {
            LOG.info("list 参数 = {}",JsonUtil.toJson(jsonRequest));
            List<RoleInfoVO> roleInfoVO = roleInfoService.roleInfoAllLike(jsonRequest);
            jsonResponse.setRetContent(roleInfoVO);
        } catch (Exception e) {
            throw new BusinessException("0103001");
        }
        return jsonResponse;
    }

    /**
     * 系统参数 所有可用角色 接口
     * @param jsonRequest
     * @return
     */
    @RequestMapping("/roleInfoAll")
    public ServiceResponse<List<RoleInfoVO>> roleInfoAll(@RequestBody RoleInfoVO jsonRequest){
        ServiceResponse<List<RoleInfoVO>> jsonResponse = new ServiceResponse();
        try {
            LOG.info("list 参数 = {}",JsonUtil.toJson(jsonRequest));
            List<RoleInfoVO> roleInfoVO = roleInfoService.roleInfoAll(jsonRequest);
            jsonResponse.setRetContent(roleInfoVO);
        } catch (Exception e) {
            throw new BusinessException("0103001");
        }
        return jsonResponse;
    }

    /**
     * 系统参数 角色关联用户查询
     * @param jsonRequest
     * @return
     */
    @RequestMapping("/roleRoleAcctInfo")
    public ServiceResponse<Map> roleRoleAcctInfo(@RequestBody RoleInfoVO jsonRequest){
        ServiceResponse<Map> jsonResponse = new ServiceResponse();
        try {
            LOG.info("list 参数 = {}",JsonUtil.toJson(jsonRequest));
//            if(StringUtils.isEmpty(jsonRequest.getOrgId())){
//                jsonResponse.setRetCode("0102005");
//                return jsonResponse;
//            }
            if(StringUtils.isEmpty(jsonRequest.getAcctId())){
                jsonResponse.setRetCode("0102005");
                return jsonResponse;
            }
            List<RoleInfoVO> roleInfoVO = roleInfoService.roleRoleAcctInfo(jsonRequest);
            Map map=new HashMap();
            map.put("resultData",roleInfoVO);
            jsonResponse.setRetContent(map);
        } catch (Exception e) {
            throw new BusinessException("0103001");
        }
        return jsonResponse;
    }


    /**
     * 系统参数 list 接口
     * @param jsonRequest
     * @return
     */
    @RequestMapping("/roleInfoList")
    public ServiceResponse<PageInfo<RoleInfoVO>> roleInfoList(@RequestBody RoleInfoVO jsonRequest){
        ServiceResponse<PageInfo<RoleInfoVO>> jsonResponse = new ServiceResponse();
        try {
            LOG.info("list 参数 = {}",JsonUtil.toJson(jsonRequest));
            PageInfo<RoleInfoVO> page = roleInfoService.roleInfoList(jsonRequest);
            jsonResponse.setRetContent(page);
        } catch (Exception e) {
            throw new BusinessException("0103001");
        }
        return jsonResponse;
    }


    /**
     * 系统参数 list 接口
     * @param roleInfoc
     * @return
     */
    @RequestMapping("/pageList")
    public ServiceResponse<PageInfo<RoleInfoVO>> pageList(@RequestBody RoleInfo roleInfoc){
        ServiceResponse<PageInfo<RoleInfoVO>> jsonResponse = new ServiceResponse();
        int pageNo = 1;
        int pageSize = 3;
        PageHelper.startPage(pageNo, pageSize);
        List<RoleInfo> list=roleInfoMapper.findTwo(roleInfoc);

        PageInfo<RoleInfo> pageInfo = new PageInfo<>(list);

        List<RoleInfoVO> roleInfoVo= BeanCopyUtil.copyList(list, RoleInfoVO.class);
        PageInfo<RoleInfoVO> pageVo = new PageInfo(roleInfoVo);
        pageVo.setTotal(pageInfo.getTotal());
        pageVo.setPages(pageInfo.getPages());

        jsonResponse.setRetContent(pageVo);
        return jsonResponse;
    }





    /**
     * 系统参数 树状图 接口
     * @param jsonRequest
     * @return
     */
    @RequestMapping("/roleInfoTree")
    public ServiceResponse<Map> roleInfoTree(@RequestBody RoleInfoVO jsonRequest){
        ServiceResponse<Map> jsonResponse = new ServiceResponse();
        try {
            LOG.info("list 参数 = {}",JsonUtil.toJson(jsonRequest));
            List<RoleInfoVO> page = roleInfoService.roleInfoTree(jsonRequest);
            Map objData = new HashMap();
            objData.put("resultData",page);
            jsonResponse.setRetContent(objData);
        } catch (Exception e) {
            throw new BusinessException("0103001");
        }
        return jsonResponse;
    }

    @RequestMapping("/keepRoleInfo")
    public ServiceResponse<RoleInfoVO> keepRoleInfo(@RequestBody RoleInfoVO jsonRequest){
        ServiceResponse<RoleInfoVO> jsonResponse = new ServiceResponse();
        try {

            if(StringUtils.isEmpty(jsonRequest.getOpt())){
                jsonResponse.setRetCode("0102005");
                return jsonResponse;
            }else {
                if(SysPramType.DELETE.getMsg().equals(jsonRequest.getOpt())) {
                    //角色ID
                    if(StringUtils.isEmpty(jsonRequest.getRoleId())){
                        jsonResponse.setRetCode("0102005");
                        return jsonResponse;
                    }
                }
                if(SysPramType.UPDATE.getMsg().equals(jsonRequest.getOpt())) {
                    //角色ID
                    if(StringUtils.isEmpty(jsonRequest.getRoleId())){
                        jsonResponse.setRetCode("0102005");
                        return jsonResponse;
                    }
//                    //组织ID
//                    if(StringUtils.isEmpty(jsonRequest.getOrgId())){
//                        jsonResponse.setRetCode("0102005");
//                        return jsonResponse;
//                    }
                }
                if(SysPramType.INSERT.getMsg().equals(jsonRequest.getOpt())) {

                    if(StringUtils.isEmpty(jsonRequest.getRoleTitle())){
                        jsonResponse.setRetCode("0102005");
                        return jsonResponse;
                    }
//                    //组织ID
//                    if(StringUtils.isEmpty(jsonRequest.getOrgId())){
//                        jsonResponse.setRetCode("0102005");
//                        return jsonResponse;
//                    }
                    List<RoleInfoVO> roleInfoVOS = roleInfoService.verificationIsTtitleRole(jsonRequest);
                    if(CollectionUtils.isEmpty(roleInfoVOS)){

                    }else{
                        jsonResponse.setRetCode("0103004");
                        return jsonResponse;
                    }
                }
            }
            LOG.info("list 参数 = {}",JsonUtil.toJson(jsonRequest));
            RoleInfoVO roleInfoVO = roleInfoService.keepRoleInfo(jsonRequest);
            jsonResponse.setRetContent(roleInfoVO);
        } catch (Exception e) {
            throw new BusinessException("0103003");
        }
        return jsonResponse;
    }
}
