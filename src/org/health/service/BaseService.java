/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月1日 下午2:14:46
 * @version V1.0  
 */
package org.health.service;

import java.util.List;

import org.health.common.page.Pagination;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.lang.Lang;
import org.nutz.service.IdEntityService;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月1日 下午2:14:46
 */
public class BaseService<T> extends IdEntityService<T> {

	protected final static int DEFAULT_PAGE_NUMBER = 20;

	public BaseService() {
		super();
	}

	public BaseService(Dao dao) {
		super(dao);
	}

	public Pagination<T> getObjListByPager(Integer pageNumber, int pageSize, Condition cnd) {
		pageNumber = getPageNumber(pageNumber);
		Pager pager = dao().createPager(pageNumber, pageSize);
		List<T> list = dao().query(getEntityClass(), cnd, pager);
		pager.setRecordCount(dao().count(getEntityClass(), cnd));
		return new Pagination<T>(pageNumber, pageSize, pager.getRecordCount(), list);
	}

	public Pagination<T> getObjListByPager(Integer pageNumber, Condition cnd) {
		return getObjListByPager(pageNumber, DEFAULT_PAGE_NUMBER, cnd);
	}

	protected int getPageNumber(Integer pageNumber) {
		return Lang.isEmpty(pageNumber) ? 1 : pageNumber;
	}
	
	public void delete(String[] ids) {
		dao().clear(getEntityClass(), Cnd.where("id", "in", ids));
	}
}
