/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月11日 下午12:55:31
 * @version V1.0  
 */
package org.health.service;

import org.health.model.ReputationStrategy;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月11日 下午12:55:31
 */
@IocBean(fields = { "dao" })
public class StrategyService extends BaseService<ReputationStrategy> {

}
