package com.cditer.free.security.intceptor;

import com.cditer.free.security.intceptor.valid.ApiAuthPassportValid;
import com.cditer.free.coreweb.security.AuthorityApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginIntceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private ApiAuthPassportValid apiAuthPassportValid;
	
	private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {		
		long beginTime = System.currentTimeMillis();// 1、开始时间
		startTimeThreadLocal.set(beginTime);

		boolean authResult=true;
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			//如果是api的请求
			if (((HandlerMethod) handler).getBean() instanceof AuthorityApi) {
				authResult= apiAuthPassportValid.valid(request, response, handler);
			}
		}
		return authResult;
	}	
	

	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			super.postHandle(request, response, handler, modelAndView);
			return;
		}
		long endTime = System.currentTimeMillis();// 2、结束时间
		long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）
		long consumeTime = endTime - beginTime;// 3、消耗的时间
		if (consumeTime > 200) {// 此处认为处理时间超过500毫秒的请求为慢请求
			log.debug(String.format("%s consume %d millis",request.getRequestURI(), consumeTime));
		}
		super.postHandle(request, response, handler, modelAndView);
	}
}