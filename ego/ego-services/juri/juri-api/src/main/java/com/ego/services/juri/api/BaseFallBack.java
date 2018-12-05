package com.ego.services.juri.api;

import org.springframework.stereotype.Component;

import com.ebase.core.service.ServiceResponse;

/**
 * @Auther: kim
 */
@Component
public class BaseFallBack implements BaseApi {

    @Override
    public ServiceResponse<Integer> test(String testParam) {
        System.out.println("testFallBack");
        return null;
    }

}