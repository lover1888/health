/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年3月24日 上午10:40:24
 * @version V1.0  
 */
package org.kanbingba.dao;

import java.util.List;

import org.health.model.City;
import org.health.model.District;
import org.health.model.Province;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年3月24日 上午10:40:24
 */
public class DaoTest {

	/**
	 * @Description TODO
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Ioc ioc = new NutIoc(new JsonLoader("ioc.js"));
		
		NutDao dao = ioc.get(NutDao.class,"dao");
		
		// 查询单个
		Province p = dao.fetch(Province.class);
		System.out.println(p.getProvinceName());
		
		// 查询全部
		List<Province> provinces = dao.query(Province.class, null);
		System.out.println("共有"+provinces.size()+"个省");
		
		// 按条件查询
		List<City> cities = dao.query(City.class, Cnd.where("provinceID", "=", "5"));
		System.out.println("共有"+cities.size()+"个市");
		// 通过映射查询（一对多、一对一）
		City city = dao.fetchLinks(cities.get(0), null);
		System.out.println(city.getProvince().getProvinceName());
		
		// 按条件查询2
		List<Province> p2 = dao.query(Province.class, Cnd.where("provinceName","like","%上海%"));
		System.out.println(p2.size());
		if(p2.size()>0){
			Province p1 = dao.fetchLinks(p2.get(0), null);
			System.out.println(p1.getCities().size());
		}
		
		List<City> ccs = dao.query(City.class, Cnd.where("cityName", "like","%上海%"));
		if(ccs.size()>0){
			City c =dao.fetchLinks(ccs.get(0), null);
			for(District dd:c.getDistricts()){
				System.out.println(dd.getDistrictName());
			}
		}
		
	}

}
