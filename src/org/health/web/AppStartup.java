/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月1日 上午11:20:52
 * @version V1.0  
 */
package org.health.web;

import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月1日 上午11:20:52
 */
public class AppStartup implements Setup {

	/* (non Javadoc)
	 * @see org.nutz.mvc.Setup#init(org.nutz.mvc.NutConfig)
	 */
	@Override
	public void init(NutConfig nc) {
		
		System.out.println("init....");
		
	}

	/* (non Javadoc)
	 * @see org.nutz.mvc.Setup#destroy(org.nutz.mvc.NutConfig)
	 */
	@Override
	public void destroy(NutConfig nc) {
		
		
		
	}

}
