package com.ego.services.base.facade.service.Jurisdiction.impl;


import com.ebase.utils.BeanCopyUtil;
import com.ego.services.base.api.vo.Jurisdiction.FunctionManageVO;
import com.ego.services.base.facade.common.IsDelete;
import com.ego.services.base.facade.common.SysPramType;
import com.ego.services.base.facade.dao.Jurisdiction.AcctOperPrivRelaMapper;
import com.ego.services.base.facade.dao.Jurisdiction.FunctionManageMapper;
import com.ego.services.base.facade.dao.Jurisdiction.OrgInfoMapper;
import com.ego.services.base.facade.model.Jurisdiction.FunctionManage;
import com.ego.services.base.facade.service.Jurisdiction.FunctionManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: zhaoyuhang
 */
@Service
public class FunctionManageServiceImpl implements FunctionManageService {

    private static Logger LOG = LoggerFactory.getLogger(FunctionManageServiceImpl.class);

    @Autowired
    private FunctionManageMapper functionManageMapper;

    @Autowired
    private AcctOperPrivRelaMapper acctOperPrivRelaMapper;

    @Autowired
    private OrgInfoMapper orgInfoMapper;

    /**
     * 管理员查询树状图
     * @param functionManageVO
     * @return
     */
    @Override
    public List<FunctionManageVO> functionManageList(FunctionManageVO functionManageVO) {

        try {
            FunctionManage functionManage=new FunctionManage();
            BeanCopyUtil.copy(functionManageVO,functionManage);
            //获取当前用户下的所有组织
            functionManage=getFunctionOrgId(functionManage);


            //查询出所有符合条件的树状图
            List<FunctionManage> ListPath = functionManageMapper.findPath(functionManage);
            int pathNum=0;
            String allId="";
            if(ListPath.size()>0) {
                //拼接
                for(int j=0;j<ListPath.size();j++){
                    if(j==0){
                        allId=allId+ListPath.get(j).getIdFullPath();
                    }else{
                        allId=allId+","+ListPath.get(j).getIdFullPath();
                    }

                }
                String[]  strs=allId.split(",");
                List<String> result = new ArrayList<>();
                boolean flag;
                //去重复
                for(int i=0;i<strs.length;i++){
                    flag = false;
                    for(int j=0;j<result.size();j++){
                        if(strs[i].equals(result.get(j))){
                            flag = true;
                            break;
                        }
                    }
                    if(!flag){
                        result.add(strs[i]);
                    }
                }

                //List<String> allids= Arrays.asList(strs);
                functionManage.setAllIds(result);
            }


            //查询一级树状图
            List<FunctionManage> list = functionManageMapper.find(functionManage);

            List<FunctionManage> listTreeNull=new ArrayList<>();
            for(int i=0;i<list.size();i++){
                list.get(i).setChildren(listTreeNull);
                if(list.get(i).getFunctionId()!=null){
                    functionManage.setParentApplicationId(list.get(i).getFunctionId());
                    //添加数据
                    list.get(i).setChildren(menuChild(functionManage,list.get(i).getFunctionTitle()));
                }
            }
            List<FunctionManageVO> result = BeanCopyUtil.copyList(list, FunctionManageVO.class);
            return result;
        } finally {

        }
    }

    //获取当前用户下的所有组织
    public FunctionManage getFunctionOrgId(FunctionManage functionManage) {
        if (StringUtils.isEmpty(functionManage.getOrgIdAll())) {

        } else {
            String[] orgid = functionManage.getOrgIdAll().split(",");
            List<String> idall = new ArrayList<>();
            for (int i = 0; i < orgid.length; i++) {
                idall.add(orgid[i]);
            }
            functionManage.setOrgIdAlls(idall);
        }
        return functionManage;
    }

    public List<FunctionManage> menuChild(FunctionManage functionManage,String functionTitle){
        String parentApplicationName=functionTitle;
        List<FunctionManage> listTreeNull=new ArrayList<>();
        //根据父Id查询数据
        List<FunctionManage> list = functionManageMapper.findThree(functionManage);
        for(int i=0;i<list.size();i++){
            list.get(i).setChildren(listTreeNull);
            functionManage.setParentApplicationId(list.get(i).getFunctionId());
            list.get(i).setParentApplicationName(parentApplicationName);
            //查询当前级别的下一级是否有数据
            List<FunctionManage> listTree = functionManageMapper.findThree(functionManage);
            if(listTree.size()>0){
                list.get(i).setChildren(menuChild(functionManage,list.get(i).getFunctionTitle()));
            }else{
                list.get(i).setChildren(listTreeNull);
            }
        }
        return list;
    }


    /**
     * 查询角色关联功能树状图
     * @param functionManageVO
     * @return
     */
    @Override
    public List<FunctionManageVO> functionManageRoleList(FunctionManageVO functionManageVO) {

        FunctionManage functionManage=new FunctionManage();
        BeanCopyUtil.copy(functionManageVO,functionManage);
        //获取当前用户下的所有组织
        functionManage=getFunctionOrgId(functionManage);

        List<FunctionManage> list = functionManageMapper.findRole(functionManage);
        List<FunctionManage> listTreeNull=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            list.get(i).setChildren(listTreeNull);
            if(list.get(i).getFunctionId()!=null){
                functionManage.setParentApplicationId(list.get(i).getFunctionId());
                list.get(i).setChildren(menuChildRole(functionManage,list.get(i).getFunctionTitle()));
            }
        }
        List<FunctionManageVO> result = BeanCopyUtil.copyList(list, FunctionManageVO.class);

        return result;
    }

    public List<FunctionManage> menuChildRole(FunctionManage functionManage,String functionTitle){
        String parentApplicationName=functionTitle;
        List<FunctionManage> listTreeNull=new ArrayList<>();
        //根据父Id查询数据
        List<FunctionManage> list = functionManageMapper.findRoleThree(functionManage);

        if(list.size()>0) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setChildren(listTreeNull);
                functionManage.setParentApplicationId(list.get(i).getFunctionId());
                list.get(i).setParentApplicationName(parentApplicationName);
                //查询当前级别的下一级是否有数据
                List<FunctionManage> listTree = functionManageMapper.findRoleThree(functionManage);
                if (listTree.size() > 0) {
                    list.get(i).setChildren(menuChildRole(functionManage, list.get(i).getFunctionTitle()));
                } else {
                    list.get(i).setChildren(listTreeNull);
                }
            }
        }
        return list;
    }



    @Override
    public Integer updateFunctionManageStatus(List<FunctionManageVO> jsonRequest) {
        Integer num=null;
        List<FunctionManage> reqBody = BeanCopyUtil.copyList(jsonRequest, FunctionManage.class);
        for(int i=0;i<reqBody.size();i++){
            num=functionManageMapper.updateByPrimaryKeySelective(reqBody.get(i));
        }
        return num;
    }


    @Override
    public String verificationDeleteFunction(FunctionManageVO jsonRequest) {
        String body="";
        FunctionManage reqBody = new FunctionManage();
        BeanCopyUtil.copy(jsonRequest,reqBody);
        List<FunctionManage> list=functionManageMapper.verificationDeleteFunction(reqBody);
        if(CollectionUtils.isEmpty(list)){
            body="是否删除";
        }else{
            body="功能与角色有关联，是否删除";
        }
        return body;
    }

    @Override
    public FunctionManageVO keepFunctionManage(FunctionManageVO jsonRequest) {
        FunctionManage reqBody = new FunctionManage();
        BeanCopyUtil.copy(jsonRequest,reqBody);

        String opt=reqBody.getOpt();

        if(SysPramType.DELETE.getMsg().equals(opt)){

            FunctionManage functionManage=new FunctionManage();
            functionManage.setFunctionId(reqBody.getFunctionId());
            functionManage.setParentApplicationId(reqBody.getFunctionId());
            functionManage.setChildren(menuChild(functionManage,""));
            FunctionManage parId=functionManageMapper.findParentApplicationId(reqBody);
            reqBody.setParentApplicationId(parId.getParentApplicationId());
            if(CollectionUtils.isEmpty(functionManage.getChildren())){
                //删除角色和功能关联
                acctOperPrivRelaMapper.deleteFunctionId(reqBody.getFunctionId());
                //删除
                reqBody.setIsDelete(IsDelete.YES.getCode());

                functionManageMapper.updateByPrimaryKeySelective(reqBody);
            }else{
                List<String> functionIds=new ArrayList<>();
                functionIds.add(reqBody.getFunctionId().toString());
                functionIds=getParent(functionIds,functionManage.getChildren());
                functionManage.setAllIds(functionIds);
                acctOperPrivRelaMapper.deleteFunctionIdAll(functionManage);
                functionManageMapper.updateFunctionIdAll(functionManage);
            }
        }else if(SysPramType.UPDATE.getMsg().equals(opt)){
            reqBody.setUpdatedBy("修改人");
            reqBody.setUpdatedTime(new Date());
            //修改
            FunctionManage parId=functionManageMapper.findParentApplicationId(reqBody);
            functionManageMapper.updateByPrimaryKeySelective(reqBody);

            reqBody.setParentApplicationId(parId.getParentApplicationId());
        }else if(SysPramType.INSERT.getMsg().equals(opt)){
            reqBody.setCreatedBy("创建人");
            reqBody.setCreatedTime(new Date());
            reqBody.setFunctionId(null);
            String idFullPath = "";
            if (StringUtils.isEmpty(reqBody.getParentApplicationId())) {
                reqBody.setTitleFullPath(reqBody.getFunctionTitle());
            } else {
                FunctionManage functionManageOne = functionManageMapper.selectByPrimaryKey(reqBody.getParentApplicationId());
                reqBody.setTitleFullPath(functionManageOne.getTitleFullPath() + "," + reqBody.getFunctionTitle());
                idFullPath = functionManageOne.getIdFullPath() + ",";
            }
            //添加
            int a = functionManageMapper.insertSelective(reqBody);
            reqBody.setCreatedTime(null);
            FunctionManage functionManageIdFull = new FunctionManage();
            functionManageIdFull.setFunctionId(reqBody.getFunctionId());
            functionManageIdFull.setIdFullPath(idFullPath + reqBody.getFunctionId());
            functionManageMapper.updateByPrimaryKeySelective(functionManageIdFull);
        }
        FunctionManageVO functionManageVO1=BeanCopyUtil.copy(reqBody,FunctionManageVO.class);
        return functionManageVO1;
    }

    //List<FunctionManage> list=functionManageMapper.verificationFunIsTtitle(reqBody);
    //if(CollectionUtils.isEmpty(list)) {

    @Override
    public List<FunctionManageVO> verificationFunIsTtitle(FunctionManageVO jsonRequest) {
        FunctionManage reqBody = new FunctionManage();
        BeanCopyUtil.copy(jsonRequest,reqBody);
        List<FunctionManage> list=functionManageMapper.verificationFunIsTtitle(reqBody);
        List<FunctionManageVO> result = BeanCopyUtil.copyList(list, FunctionManageVO.class);
        return result;
    }


    private List<String> getParent(List<String> functionIds,List<FunctionManage> functionManageList) {
        //根据父Id查询数据
        for(int i=0;i<functionManageList.size();i++){
            functionIds.add(functionManageList.get(i).getFunctionId().toString());
            //查询当前级别的下一级是否有数据
            if(functionManageList.get(i).getChildren().size()>0){
                getParent(functionIds,functionManageList.get(i).getChildren());
            }
        }
        return functionIds;
    }
}