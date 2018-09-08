package com.ego.services.base.facade.service.Jurisdiction;

import java.util.List;

import com.ebase.core.page.PageDTO;
import com.ebase.core.web.json.JsonRequest;
import com.ego.services.base.api.vo.Jurisdiction.OrgInfoVO;
import com.ego.services.base.facade.model.Jurisdiction.AcctInfo;
import com.ego.services.base.facade.model.Jurisdiction.OrgInfo;

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

	PageDTO<OrgInfoVO> getListOrgInfo(OrgInfo orgInfo);

	List<OrgInfo> getMaterielOrginfo(AcctInfo acctInfo);

	Boolean getParityOrgName(OrgInfo orgInfo);

	
	

	

	



	
	

}
