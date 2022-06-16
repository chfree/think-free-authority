package com.cditer.free.behavior.inceptor;

import cn.hutool.core.date.DateUtil;
import com.cditer.free.behavior.anno.WebApiVisitLog;
import com.cditer.free.behavior.entity.model.WebVisitLimit;
import com.cditer.free.behavior.entity.model.WebVisitLog;
import com.cditer.free.behavior.entity.viewmodel.LimitMatcherItem;
import com.cditer.free.behavior.entity.viewmodel.WebVisitLimitSearch;
import com.cditer.free.behavior.entity.viewmodel.WebVisitLogSearch;
import com.cditer.free.behavior.enums.WebVisitLimitType;
import com.cditer.free.behavior.service.IWebVisitLimitService;
import com.cditer.free.behavior.service.IWebVisitLogService;
import com.cditer.free.core.exception.BizException;
import com.cditer.free.core.message.security.LoginModel;
import com.cditer.free.coreweb.webapi.FirstApi;
import com.cditer.free.security.baseapi.TokenApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Component
@Slf4j
public class WebVisitLogInceptor implements HandlerInterceptor {

    @Autowired
    private IWebVisitLogService webVisitLogService;

    @Autowired
    private IWebVisitLimitService webVisitLimitService;

    public static final String REQUEST_ATTRIBUTE_START_TIEM = "__request_start_time";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(REQUEST_ATTRIBUTE_START_TIEM, DateUtil.date());

        WebApiVisitLog apiVisitLog = getSupport(handler);
        checkVisitLimit(request, apiVisitLog);

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    private void checkVisitLimit(HttpServletRequest request,WebApiVisitLog apiVisitLog){
        if(apiVisitLog==null){
            return;
        }

        LoginModel loginModel = getCurrLoginModel(request);
        if(loginModel==null){
            return;
        }
        WebVisitLimitSearch search = new WebVisitLimitSearch();
        search.setVisitType(apiVisitLog.type());
        search.setEnableDt(DateUtil.date());

        setLimitSearch(loginModel, search);

        WebVisitLimit webVisitLimit = webVisitLimitService.queryModelBySearch(search);
        if(webVisitLimit==null){
            return;
        }
        WebVisitLimitType webVisitLimitType = WebVisitLimitType.parseByText(webVisitLimit.getLimitType());
        if(webVisitLimitType==null){
            log.error("访问控制类型不正确");
            return;
        }
        WebVisitLogSearch countSearch = new WebVisitLogSearch();
        countSearch.setTimeDiff(webVisitLimitType.getValue());
        countSearch.setUserId(loginModel.getId());
        countSearch.setType(apiVisitLog.type());

        int visitSumCount = webVisitLogService.queryVisitCountBySearch(countSearch);
        if(visitSumCount>webVisitLimit.getMaxCount()){
            throw new BizException("该请求被限制访问频次，请稍后在访问");
        }
    }

    private void setLimitSearch(LoginModel loginModel, WebVisitLimitSearch search) {
        search.setMatcherItems(new ArrayList<>());

        if(StringUtils.hasText(loginModel.getRoleId())){
            LimitMatcherItem limitMatcherItem = new LimitMatcherItem();
            limitMatcherItem.setType("roleId");
            limitMatcherItem.setRule(loginModel.getRoleId());

            search.getMatcherItems().add(limitMatcherItem);
        }

        if(StringUtils.hasText(loginModel.getDeptId())){
            LimitMatcherItem limitMatcherItem = new LimitMatcherItem();
            limitMatcherItem.setType("deptId");
            limitMatcherItem.setRule(loginModel.getDeptId());

            search.getMatcherItems().add(limitMatcherItem);
        }

        if(StringUtils.hasText(loginModel.getId())){
            LimitMatcherItem limitMatcherItem = new LimitMatcherItem();
            limitMatcherItem.setType("userId");
            limitMatcherItem.setRule(loginModel.getId());

            search.getMatcherItems().add(limitMatcherItem);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);

        WebApiVisitLog apiVisitLog = getSupport(handler);
        saveOperLog(request, apiVisitLog);
    }

    private void saveOperLog(HttpServletRequest request,WebApiVisitLog apiVisitLog){
        if(apiVisitLog==null){
            return;
        }
        WebVisitLog visitLog = new WebVisitLog();

        Object traceId = request.getAttribute("traceId");
        if(traceId!=null){
            visitLog.setTraceId(traceId.toString());
        }
        visitLog.setType(apiVisitLog.type());
        setBsnId(request, visitLog, apiVisitLog);
        Date startDate = (Date) request.getAttribute(REQUEST_ATTRIBUTE_START_TIEM);
        visitLog.setStartDt(startDate);
        visitLog.setEndDt(DateUtil.date());
        visitLog.setIp(getClientIP(request));

        if(apiVisitLog.recordUrl()){
            visitLog.setUrl(getRequestUrl(request));
        }

        LoginModel loginModel = getCurrLoginModel(request);

        webVisitLogService.batchSaveOperLog(Arrays.asList(visitLog), loginModel);
    }

    private LoginModel getCurrLoginModel(HttpServletRequest request){
        return (LoginModel)request.getAttribute(TokenApi.LOGIN_KEY);
    }

    private void setBsnId(HttpServletRequest request,WebVisitLog visitLog, WebApiVisitLog apiVisitLog){
        Object paramName = request.getAttribute(apiVisitLog.bsnIdOfParamName());
        if(paramName==null){
            paramName = request.getParameter(apiVisitLog.bsnIdOfParamName());
        }
        if(paramName!=null){
            visitLog.setBsnId(paramName.toString());
        }
    }

    private WebApiVisitLog getSupport(Object handler) {
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            return null;
        }
        // 如果是api的请求，目前所有Api都基于FirstApi
        if (!(((HandlerMethod) handler).getBean() instanceof FirstApi)) {
            return null;
        }

        // 对注解WebApiVisitLog的进行记录
        return ((HandlerMethod) handler).getMethod().getAnnotation(WebApiVisitLog.class);
    }

    private String getRequestUrl(HttpServletRequest request){
        return request.getRequestURI().replace(request.getContextPath(),"");
    }


    private String getClientIP(HttpServletRequest httpServletRequest) {
        if (httpServletRequest == null) {
            return "";
        }

        String ip = httpServletRequest.getHeader("X-Forwarded-For");

        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("Proxy-Client-IP");
        }

        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("WL-Proxy-Client-IP");
        }

        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("HTTP_Client_IP");
        }

        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (!StringUtils.hasText(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getRemoteAddr();
        }

        if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                log.error("getClientIP is UnknownHostException", e);
            }
        }
        return ip;
    }
}
