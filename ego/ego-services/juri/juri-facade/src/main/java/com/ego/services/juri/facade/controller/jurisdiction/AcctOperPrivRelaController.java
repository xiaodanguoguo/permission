package com.ego.services.juri.facade.controller.jurisdiction;



import com.ebase.core.exception.BusinessException;
import com.ebase.core.service.ServiceResponse;
import com.ebase.utils.JsonUtil;
import com.ego.services.juri.api.vo.jurisdiction.AcctOperPrivRelaVO;
import com.ego.services.juri.facade.service.jurisdiction.AcctOperPrivRelaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统基础模块-  角色功能关联
 * @Auther: zhaoyuhang
 */
@RestController
public class AcctOperPrivRelaController {

    private static Logger LOG = LoggerFactory.getLogger(AcctOperPrivRelaController.class);

    @Autowired
    private AcctOperPrivRelaService acctOperPrivRelaService;


    /**
     * 系统功能管理 list 接口
     * @param jsonRequest
     * @return
     */
    @RequestMapping("/addAcctOperPrivRela")
    public ServiceResponse<Integer> addAcctOperPrivRela(@RequestBody AcctOperPrivRelaVO jsonRequest){
        ServiceResponse<Integer> response = new ServiceResponse<>();
        try {
            LOG.info("list 参数 = {}",JsonUtil.toJson(jsonRequest));
            Integer acctOperPrivRela = acctOperPrivRelaService.addAcctOperPrivRela(jsonRequest);
            response.setRetContent(acctOperPrivRela);
        } catch (Exception e) {
            response.setException(new BusinessException("异常编码", new Object[]{"参数"}));
            LOG.error(e.getMessage());
        }
        return response;
    }


    /**
     * 系统功能管理 修改功能使用状态
     * @param jsonRequest
     * @return
     */
    @RequestMapping("/delAcctOperPrivRela")
    public ServiceResponse<Integer> delAcctOperPrivRela(@RequestBody AcctOperPrivRelaVO jsonRequest){
        ServiceResponse<Integer> response = new ServiceResponse<>();
        try {
            LOG.info("list 参数 = {}",JsonUtil.toJson(jsonRequest));
            Integer acctOperPrivRela = acctOperPrivRelaService.delAcctOperPrivRela(jsonRequest);
            response.setRetContent(acctOperPrivRela);
        } catch (Exception e) {
            response.setException(new BusinessException("异常编码", new Object[]{"参数"}));
            LOG.error(e.getMessage());
        }
        return response;
    }
}
