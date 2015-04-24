/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月16日 下午1:11:33
 * @version V1.0  
 */
package org.health.vo;


/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月16日 下午1:11:33
 */
public class CommentVo<T> extends BaseVo {
	
	private T comment;

	/**  
	 * @return the comment  
	 */
	public T getComment() {
		return comment;
	}

	/**  
	 * @param comment the comment to set  
	 */
	public void setComment(T comment) {
		this.comment = comment;
	}

}
