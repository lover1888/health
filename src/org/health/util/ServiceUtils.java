package org.health.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.health.model.ReputationStrategy;
import org.health.model.User;
import org.health.model.UserTags;
import org.nutz.dao.Dao;

public class ServiceUtils {
	
	// 获取声望策略
	public static Map<String, Integer> getReputationStrategy(Dao dao){
		List<ReputationStrategy> strategies = dao.query(ReputationStrategy.class, null);
		Map<String, Integer> maps = new HashMap<String, Integer>();
		for(ReputationStrategy stra:strategies) {
			maps.put(stra.getStrategyName(), stra.getRelatedUserValue());
		}
		return maps;
	}
	
	/**
	 * 更新用户关联标签的得分
	 * 必须在包含事务的服务类中调用
	 * @param userId 用户
	 * @param questionTags 问题标签，逗号分隔
	 * @param value 分值
	 * @param dao
	 */
	public static void updateUserTagsValue(String userId, String questionTags, int value, Dao dao){
		User u = new User();
		u.setUserId(userId);
		u = dao.fetchLinks(u, "userTags");
		List<UserTags> upds = new ArrayList<UserTags>();
		List<UserTags> utags = u.getUserTags();
		String[] tgs = questionTags.split(",");
		for(UserTags ut:utags){
			for(String tg:tgs){
				if(ut.getTagName().equals(tg)){
					ut.setValue(ut.getValue()+value);
					upds.add(ut);
					break;
				}
			}
		}
		if(upds.size()>0){
			dao.update(upds);
		}
	}
	
}
