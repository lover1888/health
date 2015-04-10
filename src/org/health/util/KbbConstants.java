/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月3日 上午10:34:54
 * @version V1.0  
 */
package org.health.util;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月3日 上午10:34:54
 */
public class KbbConstants {
	
	public static String SESSION_USER_ID = "userId"; 
	
	/**
	 * 资源类型
	 * @Description TODO
	 * @author jhengfei
	 * @date 2015年4月3日 上午10:41:57
	 */
	public static enum SourceType {
		QUESTION("1"),
		ANSWERS("2"),
		TAGS("3"),
		ARTICLE("4"),
		USER("5");
		private final String value;
		private SourceType(String value){
			this.value = value;
		}
		
		public String getValue(){
			return this.value;
		}
		
	}
	

}
