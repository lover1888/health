/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年3月18日 下午5:06:09
 * @version V1.0  
 */
package org.health.web;

import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年3月18日 下午5:06:09
 */
@Modules(scanPackage=true)
@IocBy(type=ComboIocProvider.class, 
	   args={"*json",
			 "ioc.js",
			 "*anno",
			 "org.health.service",
			 "org.health.web"})
@SetupBy(AppStartup.class)
@Fail("jsp:jsp.error")
public class MainModule {

}
