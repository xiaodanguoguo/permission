package com.ego.services.juri.facade.service.jurisdiction;



import com.ego.services.juri.api.vo.jurisdiction.AcctOperPrivRelaVO;

/**
 * @Auther: zhaoyuhang
 */
public interface AcctOperPrivRelaService {

    Integer delAcctOperPrivRela(AcctOperPrivRelaVO jsonRequest);

    Integer addAcctOperPrivRela(AcctOperPrivRelaVO jsonRequest);
}
