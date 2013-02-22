package com.topn.struts2.interceptor;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.topn.action.LoginAction;
import com.topn.action.RegisterAction;
import com.topn.action.help.CookieHelp;
import com.topn.bean.PersonalInfo;
import com.topn.cache.UserCache;
import com.topn.collection.UserInRegister;
import com.topn.controller.PersonalController;
import com.topn.util.StringUtil;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-12 下午03:19:43
 * 
 * 拦截所有的action请求，看是否登录
 * 
 * 7.2日修改了实现，修改后就可以实现热启动
 */
public class LoginInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		Object action = ai.getAction();
		//如果是真的登录就让其执行action
		
        if (action instanceof LoginAction || action instanceof RegisterAction) {
            return ai.invoke();
        }
        
        Cookie[] cookies = ServletActionContext.getRequest().getCookies();
        if(null == cookies){
        	return "nullcookie";
        }
        for(int i = 0; i < cookies.length; i++){        	
        	Cookie cook = cookies[i];
        	if(cook.getName().equals("id")){
        		String value = cook.getValue();
        		//这里要验证cookie中id是否存在
        		if(value == null || "".equals(value)){
        			return "nullIdLogin";
        		}
        		//只有在缓存中找不到这个人，而求在数据库中也没有找到就说明没有这个人
        		boolean isExist = UserCache.getInstance().isExist(Integer.parseInt(value));
        		if(!isExist){ 
        			PersonalInfo pi = PersonalController.getInstance().getPersonById(Integer.parseInt(value), -1, false);
        			if(null == pi){
        				return "illigalAccess"; 
        			}else{
        				//重新放入缓存
        				UserCache.getInstance().add(Integer.parseInt(value), UserInRegister.getTableName(CookieHelp.getUsername(ServletActionContext.getRequest())));
        			}
        		}
        	}
        }
        if(StringUtil.isRightPath(ServletActionContext.getRequest().getRequestURI())){
        	return ai.invoke();
        }else{
        	return "loginerror";
        }
		
	}

}
