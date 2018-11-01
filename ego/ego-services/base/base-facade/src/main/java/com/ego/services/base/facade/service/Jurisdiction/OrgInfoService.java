package com.ego.services.base.facade.service.jurisdiction;

import java.util.List;

import com.ebase.core.page.PageInfo;
import com.ego.services.base.api.vo.jurisdiction.OrgInfoVO;
import com.ego.services.base.api.vo.jurisdiction.SysInfoVO;
import com.ego.services.base.facade.model.jurisdiction.AcctInfo;
import com.ego.services.base.facade.model.jurisdiction.OrgInfo;

/**
 * 
 * @author zhangx
 *
 */
public interface OrgInfoService {
	
	Long addOrgInfo(OrgInfo orgInfo);
	
	Integer removeOrgInfo(OrgInfo orgInfo);
	
	List<OrgInfo> getListTreeOrgInfo(OrgInfo orgInfo);
	
	OrgInfo getChildTreeOrgInfo(OrgInfo orgInfo);
	
	Integer saveOrgInfo(OrgInfo orgInfo);
	
	OrgInfo getOrgInfo(OrgInfo orgInfo);

	PageInfo<OrgInfoVO> getListOrgInfo(OrgInfoVO orgInfo);

	PageInfo<OrgInfoVO> selectRoleYesQuote(OrgInfoVO orgInfo);

	List<OrgInfo> getMaterielOrginfo(AcctInfo acctInfo);

	List<OrgInfoVO> selectSysQuoteOrgInof(SysInfoVO acctInfo);

	List<OrgInfoVO> selectRoleQuoteOrg(OrgInfoVO acctInfo);

	Boolean getParityOrgName(OrgInfo orgInfo);

	
	

	

	



	
	

}
