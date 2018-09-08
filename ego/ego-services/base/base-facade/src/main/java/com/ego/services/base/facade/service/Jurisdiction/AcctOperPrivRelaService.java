package com.ego.services.base.facade.service.Jurisdiction;



import com.ego.services.base.api.vo.Jurisdiction.AcctOperPrivRelaVO;

/**
 * @Auther: zhaoyuhang
 */
public interface AcctOperPrivRelaService {

    Integer delAcctOperPrivRela(AcctOperPrivRelaVO jsonRequest);

    Integer addAcctOperPrivRela(AcctOperPrivRelaVO jsonRequest);
}
