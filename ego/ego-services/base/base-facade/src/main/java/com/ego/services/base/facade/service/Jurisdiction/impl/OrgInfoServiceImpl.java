package com.ego.services.base.facade.service.Jurisdiction.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebase.core.StringHelper;
import com.ebase.core.page.PageDTO;
import com.ebase.core.page.PageDTOUtil;
import com.ebase.core.web.json.JsonRequest;
import com.ebase.utils.BeanCopyUtil;
import com.ebase.utils.math.MathHelper;
import com.ego.services.base.api.vo.Jurisdiction.OrgInfoVO;
import com.ego.services.base.facade.dao.Jurisdiction.AcctInfoMapper;
import com.ego.services.base.facade.dao.Jurisdiction.OrgInfoMapper;
import com.ego.services.base.facade.model.Jurisdiction.AcctInfo;
import com.ego.services.base.facade.model.Jurisdiction.OrgInfo;
import com.ego.services.base.facade.service.Jurisdiction.OrgInfoService;



@Service
public class OrgInfoServiceImpl implements OrgInfoService{
		
	@Autowired
	private OrgInfoMapper orgInfoMapper;
	
	@Autowired
	private AcctInfoMapper acctInfoMapper;
	
	/**
	 * 组织机构信息添加
	 * 传入父类ParentId添加子类OrgCode
	 */
	@Override
	public synchronized Long addOrgInfo(OrgInfo orgInfo) {
		String orgInfoId = getOrgInfoId(orgInfo.getParentId());
		orgInfo.setId(orgInfoId);
		Long i = orgInfoMapper.insertOrgInfo(orgInfo);
		return i;
	}
	
	/**
	 * 查询指定长度的id最大值
	 * 如果存在加一，不存在就将父节点的parentId后面拼上101
	 * 最高节点parentId为0
	 * @return
	 */
	public String getOrgInfoId(String orgCode) {
		StringBuilder result = new StringBuilder(orgCode);
		String orgInfoId = orgInfoMapper.getOrgInfoId(orgCode);
		if (StringHelper.isBlank(orgInfoId))
			orgInfoId = result.append("101").toString();
		else 
			orgInfoId = MathHelper.add(orgInfoId);
		return orgInfoId;
	}
	
	/**
	 * 组织机构信息刪除
	 * 判断该组织及其子类组织是否有用户绑定，
	 * 有：不准删除并抛出异常提示      没有：可以删除
	 */
	@Override
	public Integer removeOrgInfo(OrgInfo orgInfo) {
		//需要删除的组织id集合，如果有返回需要删除的组织，如果没有返回空	
    	List<String> cascadeDeletionOrgInfo = cascadeDeletionOrgInfo(orgInfo);
    	cascadeDeletionOrgInfo.add(orgInfo.getId());
    	//到用户表查询是否有用户绑定组织
    	int a = acctInfoMapper.getAcctOrgid(cascadeDeletionOrgInfo);
    	//批量删除组织
    	int i = 0;
    	if(a == 0) {
    		i = orgInfoMapper.deleteOrgInfo(cascadeDeletionOrgInfo);	
    	}
    	return i;
	}
	/**
	 * 根据父节点id级联删除下面所有的子id
	 * @param orgInfo
	 * @return
	 */
	public List<String> cascadeDeletionOrgInfo(OrgInfo orgInfo) {
		List<String> childorgInfoMapper = orgInfoMapper.getOrgInfoIdAll(orgInfo);
		return childorgInfoMapper;
	}
	
	/**
	 * 组织机构信息父查子
	 */
	@Override
	public List<OrgInfo> getListTreeOrgInfo(OrgInfo orgInfo) {
		//如果id有值就查子类，如果id无值就按id2查询父类id
		List<OrgInfo> childorgInfoMapper = null;
		if(orgInfo.getId() != "") {
            childorgInfoMapper = orgInfoMapper.getChildOrgInfo(orgInfo);
		}else {
			childorgInfoMapper = orgInfoMapper.selectListOrgInfoAll(orgInfo);
		}
		return childorgInfoMapper;
	}
	
	/**
	 * 组织机构信息递归查询
	 */
	@Override
	public OrgInfo getChildTreeOrgInfo(OrgInfo orgInfo) {

        List<OrgInfo> childorgInfoMapper = orgInfoMapper.queryChildOrgInfo(orgInfo);
        Map<String, OrgInfo> map = new HashMap<>();
        OrgInfo rootOrg = null;
        
        childorgInfoMapper.forEach(org -> map.put(org.getId(), org));
        
        for (OrgInfo org : childorgInfoMapper) {
        	OrgInfo parent = map.get(org.getParentId());
			if (parent != null) 
				parent.addChild(org);
			else 
				rootOrg = org;
		}	
	   return rootOrg;
	}
	
	/**
	 * 组织机构信息修改
	 */
	@Override
	public Integer saveOrgInfo(OrgInfo OrgInfo) {
		int i = orgInfoMapper.updateOrgInfo(OrgInfo);
		return i;	
	}
	
	/**
	 * 组织机构信息查詢
	 */
	@Override
	public OrgInfo getOrgInfo(OrgInfo orgInfo) {
		OrgInfo selectOrgInfo = orgInfoMapper.selectOrgInfo(orgInfo);
		return selectOrgInfo;
	}
	
	/**
	  * 根据当前用户的组织id，查询出当前用户及当前用户一下的组织
	  * 物料综合查询用
	  * @return
	  */
	@Override
	public List<OrgInfo> getMaterielOrginfo(AcctInfo acctInfo) {
		//根据当前用户id查出当前用户绑定的组织
		String acctInfoId = acctInfoMapper.getAcctInfo(acctInfo.getAcctId());
		List<OrgInfo> listOrgInfo= orgInfoMapper.getMaterielOrginfo(acctInfoId);
		return listOrgInfo;
	}
	
	/**
	 * 组织名称唯一性校验
	 */
	@Override
	public Boolean getParityOrgName(OrgInfo orgInfo) {
		Integer i = orgInfoMapper.selectParityOrgName(orgInfo);
		//返回0说明当前组织名称没有被使用
		if(i == 0) {
			return true;
		//不等于0说明当前用户已存在
		}else {
			return false;
		}
	}
	
	
	

	/**
	 * 组织机构信息查詢分页
	 */
	@Override
	public  PageDTO<OrgInfoVO> getListOrgInfo(OrgInfo orgInfo) {
		OrgInfoVO reqBody = new OrgInfoVO();
		try {
			
            //可能还有更多参数
            PageDTOUtil.startPage(reqBody);
			List<OrgInfo> list = orgInfoMapper.selectListOrgInfo(orgInfo);
			PageDTO<OrgInfo> page = PageDTOUtil.transform(list);
			
			//转换
            PageDTO<OrgInfoVO> pageVo = new PageDTO<OrgInfoVO>(page.getPageNum(),page.getPageSize());
            pageVo.setTotal(page.getTotal());
            List<OrgInfo> resultData = page.getResultData();
            
            List<OrgInfoVO> result = BeanCopyUtil.copyList(resultData, OrgInfoVO.class);
            pageVo.setResultData(result);
            return pageVo;
		} finally {
            PageDTOUtil.endPage();
        }
	}

	/**
	 * 在内存中拼接出查询树
	 *//*
	@Override
	public JsonResponse<List<OrgInfo>> getListRecursionOrgInfo(JsonRequest<OrgInfoVO> jsonRequest) {
		JsonResponse<List<OrgInfo>> jsonResponse = new JsonResponse<List<OrgInfo>>();
		OrgInfoVO reqBody = jsonRequest.getReqBody();
		OrgInfo orgInfo = new OrgInfo(); 
		BeanCopyUtil.copy(reqBody, orgInfo);		
		
		List<OrgInfo> orgInfos = new ArrayList<OrgInfo>();
		List<OrgInfo> allOrgInfo = orgInfoMapper.selectListOrgInfo(orgInfo);
		Map<Long, OrgInfo> orgInfoMap = new HashMap<Long, OrgInfo>();
		for ( OrgInfo orgInfo1 : allOrgInfo ) {
			orgInfoMap.put(orgInfo1.getId(), orgInfo1);
		}
		for ( OrgInfo orgInfo1 : allOrgInfo ) {
			// 子菜单
			OrgInfo child = orgInfo1;
			if ( child.getParentId() == 0 ) {
				orgInfos.add(orgInfo1);
			} else {
				// 父菜单
				OrgInfo parent = orgInfoMap.get(child.getParentId());
				// 组合菜单之间的关系
				if(parent != null) {
					parent.getChildren().add(child);
				}	
			}
		}
		jsonResponse.setRspBody(orgInfos);
		return jsonResponse;
	}*/	

}


