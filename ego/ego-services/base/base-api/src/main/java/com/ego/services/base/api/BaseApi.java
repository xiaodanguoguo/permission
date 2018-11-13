package com.ego.services.base.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ebase.core.service.ServiceResponse;

/**
 * 测试controller类
 * @Auther: kim
 */
@FeignClient(value = "${ser.name.base}")
public interface BaseApi {

    /**
     * 客户基本信息取得
     *
     * @param custId
     *            客户号
     * @return 客户基本信息
     */
    @RequestMapping(path = "/test", method = RequestMethod.GET)
    ServiceResponse<Integer> test(@RequestParam("testParam") String testParam);
}
