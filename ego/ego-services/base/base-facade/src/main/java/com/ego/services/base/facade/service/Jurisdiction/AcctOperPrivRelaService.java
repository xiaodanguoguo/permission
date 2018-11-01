package com.ego.services.base.facade.service.jurisdiction;



import com.ego.services.base.api.vo.jurisdiction.AcctOperPrivRelaVO;

/**
 * @Auther: zhaoyuhang
 */
public interface AcctOperPrivRelaService {

    Integer delAcctOperPrivRela(AcctOperPrivRelaVO jsonRequest);

    Integer addAcctOperPrivRela(AcctOperPrivRelaVO jsonRequest);
}
