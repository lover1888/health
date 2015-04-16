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
	 * 投赞同票
	 */
//	public static String VoteType_Add = "1";
	/**
	 * 投反对票
	 */
//	public static String VoteType_Reduce = "0";
	// 关注
	public static String ActType_Add = "1";
	// 取消关注
	public static String ActType_Reduce = "0";
	
	
	
	
	// 状态
	public static enum Status {
		/**
		 * 问题解答中
		 */
		QuestionOpen(0),
		
		/**
		 * 问题已关闭
		 */
		QuestionClose(-1),
		
		/**
		 * 问题已解决
		 */
		QuestionResolved(1);
		
		private int value;
		Status(int value){
			this.value = value;
		}
		
		public int getValue(){
			return this.value;
		}
		
	}
	
	
	/**
	 * 提问题
	 */
	public static String Stragety_QuestionAsk = "question:ask";
	
	/**
	 * 问题被赞
	 */
	public static String Stragety_QuestionBeVoteAdd = "question:be:vote:add";
	/**
	 * 问题被踩
	 */
	public static String Stragety_QuestionBeVoteReduce = "question:be:vote:reduce";
	
	/**
	 * 反对问题
	 */
	public static String Stragety_QuestionVoteReduce = "question:vote:reduce";
	
	/**
	 * 问题被收藏
	 */
	public static String Stragety_QuestionBeFavorite = "question:be:favorite";
	/**
	 * 问题被删除
	 */
	public static String Stragety_QuestionBeDel = "question:be:del";
	
	/**
	 * 问题被回答
	 */
	public static String Stragety_QuestionBeAnswer = "question:be:answer";
	
	/**
	 * 回答
	 */
	public static String Stragety_AnswerQuestion = "answer:question";
	
	/**
	 * 采纳答案
	 */
	public static String Stragety_AnswerAdopt = "answer:adopt";
	
	/**
	 * 答案被采纳
	 */
	public static String Stragety_AnswerBeAdopt = "answer:be:adopt";
	
	/**
	 * 回答被赞
	 */
	public static String Stragety_AnswerBeVoteAdd = "answer:be:vote:add";
	
	/**
	 * 回答被踩
	 */
	public static String Stragety_AnswerBeVoteReduce = "answer:be:vote:reduce";
	
	/**
	 * 反对答案
	 */
	public static String Stragety_AnswerVoteReduce = "answer:vote:reduce";
	
	/**
	 * 回答被删除
	 */
	public static String Stragety_AnswerBeDel = "answer:be:del";
	
	/**
	 * 发布文章
	 */
	public static String Stragety_ArticlePost = "article:post";
	
	/**
	 * 文章被赞
	 */
	public static String Stragety_ArticleBeVoteAdd = "article:be:vote:add";
	
	/**
	 * 文章被收藏
	 */
	public static String Stragety_ArticleBeFavorite = "article:be:favorite";
	
	
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
