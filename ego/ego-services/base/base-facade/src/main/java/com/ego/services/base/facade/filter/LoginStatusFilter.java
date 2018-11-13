package com.ego.services.base.facade.filter;

import com.ebase.core.AssertContext;
import com.ebase.core.cache.CacheService;
import com.ebase.core.service.ServiceResponse;
import com.ebase.core.session.*;
import com.ebase.core.web.json.JsonResponse;
import com.ebase.utils.CookieUtil;
import com.ebase.utils.JsonUtil;
import com.ebase.utils.WebUtil;
import com.ebase.utils.secret.Md5Util;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @Auther: wangyu
 */
@Component
@ServletComponentScan("com.ego.services.base.facade.filter")
@WebFilter(value = {"/*"},filterName = "loginStatusFilter")
public class LoginStatusFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(LoginStatusFilter.class);

    @Value("${acct.register.url}")
    private String returnUrl;


    @Autowired
    private CacheService cacheService;



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest servletRequest = (HttpServletRequest)request;
        HttpServletResponse servletResponse = (HttpServletResponse)response;
        Enumeration<String> keys = request.getParameterNames();

        while(keys.hasMoreElements()) {
            String k = keys.nextElement();
            log.info(k + " = " + request.getParameter(k));
        }
        Cookie[] cookie=((HttpServletRequest) request).getCookies();
        System.out.println(" ");
        Map a=request.getParameterMap();
        System.out.println("过滤条件："+request.getParameterMap().toString());
        System.out.println(" ");


        HttpServletRequest requestWrapper = null;
        if (request instanceof HttpServletRequest) {
            //httpMethod = ((HttpServletRequest) request).getMethod();
            requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request);
        }

        String param = this.getBodyString(requestWrapper.getReader());
        if(param.indexOf("reqHeader")!=-1){
            JSONObject json =new JSONObject(param);
            if(!json.get("reqHeader").equals(null)){
                JSONObject jsonObject = json.getJSONObject("reqHeader");
                if(jsonObject.has("acctId")){
                    if(!jsonObject.get("acctId").equals(null)){
                        String acctId=jsonObject.getString("acctId");
                        if(!StringUtils.isEmpty(acctId)){
                            List<TableCondition> resut= cacheService.getList("a_"+acctId,TableCondition.class);
                            User user=new User();
                            user.setAcctId(acctId);
                            user.setTableConditions(resut);
                            SessUser.init(user);
                        }
                    }
                }

            }
        }
        log.info("filter读取body中的参数>>>>>>>>>"+param);
        chain.doFilter(requestWrapper, response);
    }

    // 获取request请求body中参数
    public static String getBodyString(BufferedReader br) {
        String inputLine;
        String str = "";
        try {
            while ((inputLine = br.readLine()) != null) {
                str += inputLine;
            }
        } catch (IOException e) {
            log.error("IOException: " + e);
        }
        return str;
    }

    @Override
    public void destroy() {
    }

}

//package com.ego.services.base.facade.filter;
//
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Enumeration;
//
///**
// * @Auther: wangyu
// */
//public class LoginStatusFilter implements RequestInterceptor {
//
//    @Override
//    public void apply(RequestTemplate template) {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
//                .getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        Enumeration<String> headerNames = request.getHeaderNames();
//        if (headerNames != null) {
//            while (headerNames.hasMoreElements()) {
//                String name = headerNames.nextElement();
//                String values = request.getHeader(name);
//                template.header(name, values);
//            }
//        }
//        Enumeration<String> bodyNames = request.getParameterNames();
//        StringBuffer body =new StringBuffer();
//        if (bodyNames != null) {
//            while (bodyNames.hasMoreElements()) {
//                String name = bodyNames.nextElement();
//                String values = request.getParameter(name);
//                body.append(name).append("=").append(values).append("&");
//            }
//        }
//        //设置请求头信息(这样就可以不需要在每一个方法中都设置了)
//        //template.header("Content-Type", "application/json");
//        System.out.println("   --->>> 这是自定义拦截器   <<<---  ");
//    }
//}