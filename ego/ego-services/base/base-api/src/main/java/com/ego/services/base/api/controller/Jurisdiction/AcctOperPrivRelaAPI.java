package com.ego.services.base.api.controller.jurisdiction;


import com.ebase.core.service.ServiceResponse;
import com.ego.services.base.api.vo.jurisdiction.AcctOperPrivRelaVO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: zhaoyuhang
 */
@FeignClient(value = "${ser.name.base}") //这个是服务名
public interface AcctOperPrivRelaAPI {

    /**
     * 删除管理
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/delAcctOperPrivRela",method = RequestMethod.POST)
    ServiceResponse<Integer> delAcctOperPrivRela(@RequestBody AcctOperPrivRelaVO jsonRequest);

    /**
     * 添加管理
     * @param jsonRequest
     * @return
     */
    @RequestMapping(value = "/addAcctOperPrivRela",method = RequestMethod.POST)
    ServiceResponse<Integer> addAcctOperPrivRela(@RequestBody AcctOperPrivRelaVO jsonRequest);
}
