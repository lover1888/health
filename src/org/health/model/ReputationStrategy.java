/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月11日 上午11:26:38
 * @version V1.0  
 */
package org.health.model;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月11日 上午11:26:38
 */
@Table("tb_reputation_strategy")
public class ReputationStrategy {
	@Id
	private int id;

	private String strategyName;

	private int relatedUserValue;

	private String describe;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the strategyName
	 */
	public String getStrategyName() {
		return strategyName;
	}

	/**
	 * @param strategyName
	 *            the strategyName to set
	 */
	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	/**
	 * @return the relatedUserValue
	 */
	public int getRelatedUserValue() {
		return relatedUserValue;
	}

	/**
	 * @param relatedUserValue
	 *            the relatedUserValue to set
	 */
	public void setRelatedUserValue(int relatedUserValue) {
		this.relatedUserValue = relatedUserValue;
	}

	/**
	 * @return the describe
	 */
	public String getDescribe() {
		return describe;
	}

	/**
	 * @param describe
	 *            the describe to set
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}

}
