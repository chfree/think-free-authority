package com.tennetcn.free.security.intceptor.valid;

import cn.hutool.json.JSONUtil;
import com.tennetcn.free.core.message.web.BaseResponse;
import com.tennetcn.free.security.annotation.ApiAuthPassport;
import com.tennetcn.free.web.message.WebResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@Component
public class ApiAuthPassportValid {

	@Autowired
	TokenHelper tokenHelper;

	
	public boolean valid(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception{
		if(checkAppAuthorizeRule(handler)){
			BaseResponse rm=new BaseResponse(WebResponseStatus.AUTHORIZE_ERROR,"登陆超时或授权错误，请重新登录");

			boolean authorizeResult=tokenHelper.checkAuthorizeJwt(request);
			
			if(authorizeResult){
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8");
				return true;
			}
			
			response(response,rm);
			return false;
		}
		return true;
	}
	
	private void response(HttpServletResponse response,BaseResponse rm) throws IOException{
		response.setContentType("application/json; charset=utf-8");
		OutputStream os =  response.getOutputStream();
		String mapJsonStr=JSONUtil.toJsonStr(rm);
		os.write(mapJsonStr.getBytes());
		os.flush();
		os.close();
	}
	
	private boolean checkAppAuthorizeRule(Object handler){
		ApiAuthPassport authorizePassport = ((HandlerMethod) handler).getMethodAnnotation(ApiAuthPassport.class);
		ApiAuthPassport authorizePassportBean = ((HandlerMethod) handler).getBeanType().getAnnotation(ApiAuthPassport.class);
		
		//return false 则不验证 true则要验证
		if(authorizePassportBean!=null){  //类上面有
			if(authorizePassport==null){  //方法上没有  不验证
				return false;
			}else if(authorizePassport.validate()){   //方法上有，切标记validate 为true 则验证
				return true;
			}
		}else if(authorizePassportBean==null){  //类上面没有
			if(authorizePassport==null||authorizePassport.validate()){  //方法上没有或是有标记validate为true 则验证
				return true;
			}else if(!authorizePassport.validate()){  //方法上面有 且标记validate 为false 则不验证
				return false;
			}
		}
		return true;
	}
}
