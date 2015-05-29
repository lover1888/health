/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年5月6日 上午11:52:43
 * @version V1.0  
 */
package org.health.wechat;

import javax.servlet.annotation.WebServlet;

import org.easywechat.servlet.WeixinServletSupport;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年5月6日 上午11:52:43
 */
@WebServlet("/wechat")
public class WechatServlet extends WeixinServletSupport {

	/**
	 * @fieldName serialVersionUID
	 * @fieldType long
	 * @Description TODO
	 */
	private static final long serialVersionUID = 1806548718235386305L;

	/* (non Javadoc)
	 * @see org.easywechat.servlet.WeixinServletSupport#getToken()
	 */
	@Override
	protected String getToken() {
		
		
		
		return null;
	}

}
