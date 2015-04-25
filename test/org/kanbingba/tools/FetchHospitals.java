/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月22日 下午3:37:04
 * @version V1.0  
 */
package org.kanbingba.tools;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月22日 下午3:37:04
 */
public class FetchHospitals {
	static Set<String> filterLevel = new HashSet<String>();
	static {
		filterLevel.add("三级甲等");
		filterLevel.add("三级医院");
		filterLevel.add("二级医院");
		filterLevel.add("二级甲等");
		filterLevel.add("二级乙等");
	}
	/**
	 * @Description TODO
	 * @param args
	 */
	public static void main(String[] args) {
		String url;
		// 上海 综合医院
//		url = "http://www.guahao.com/hospital/areahospitals?sort=&c=%E4%B8%8D%E9%99%90&ht=1&hk=all&o=all&hl=all&ipIsShanghai=true&fg=0&q=&p=%E4%B8%8A%E6%B5%B7&ci=all&pi=2&pageNo=";
//		parseList("综合医院",url,true);
		
//		//妇产科医院
//		url = "http://www.guahao.com/hospital/areahospitals?sort=&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=4&q=&p=%E4%B8%8A%E6%B5%B7&ci=all&hk=all&o=all&pi=2&hl=all&pageNo=";
//		parseList("妇产科医院",url,true);
//		
//		// 皮肤科医院
//		url = "http://www.guahao.com/hospital/areahospitals?sort=&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=12&q=&p=%E4%B8%8A%E6%B5%B7&ci=all&hk=all&o=all&pi=2&hl=all&pageNo=";
//		parseList("皮肤科医院",url,true);
//		
//		//儿科医院
//		url = "http://www.guahao.com/hospital/areahospitals?q=&pi=2&p=%E4%B8%8A%E6%B5%B7&ci=all&c=%E4%B8%8D%E9%99%90&o=all&hl=all&ht=3&hk=all&fg=0&ipIsShanghai=true&sort=";
//		parseList("儿科医院",url,false);
//		
//		// 不孕不育医院
//		url = "http://www.guahao.com/hospital/areahospitals?q=&pi=2&p=%E4%B8%8A%E6%B5%B7&ci=all&c=%E4%B8%8D%E9%99%90&o=all&hl=all&ht=501&hk=all&fg=0&ipIsShanghai=true&sort=";
//		parseList("不孕不育医院",url,false);
		
		
		
		String[] urls = {
				// 北京
//				"http://www.guahao.com/hospital/areahospitals?sort=region_sort&c=%E4%B8%8D%E9%99%90&ht=all&hk=&o=all&hl=all&ipIsShanghai=true&fg=0&q=&p=%E5%8C%97%E4%BA%AC&ci=all&pi=1&pageNo=",
				// 广东
//				"http://www.guahao.com/hospital/areahospitals?sort=&c=%E4%B8%8D%E9%99%90&ht=all&hk=&o=all&hl=all&ipIsShanghai=true&fg=0&q=&p=%E5%B9%BF%E4%B8%9C&ci=all&pi=29&pageNo=",
//				// 江苏
//				"http://www.guahao.com/hospital/areahospitals?sort=region_sort&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E6%B1%9F%E8%8B%8F&ci=all&hk=&o=all&pi=22&hl=all&pageNo=",
//				// 湖南
//				"http://www.guahao.com/hospital/areahospitals?sort=&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E6%B9%96%E5%8D%97&ci=all&hk=&o=all&pi=30&hl=all&pageNo=",
//				// 山西
//				"http://www.guahao.com/hospital/areahospitals?sort=region_sort&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E5%B1%B1%E8%A5%BF&ci=all&hk=&o=all&pi=8&hl=all&pageNo=",
//				// 山东 
//				"http://www.guahao.com/hospital/areahospitals?sort=&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E5%B1%B1%E4%B8%9C&ci=all&hk=&o=all&pi=21&hl=all&pageNo=",
//				// 湖北 
//				"http://www.guahao.com/hospital/areahospitals?sort=region_sort&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E6%B9%96%E5%8C%97&ci=all&hk=&o=all&pi=19&hl=all&pageNo=",
//				// 浙江 
//				"http://www.guahao.com/hospital/areahospitals?sort=&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E6%B5%99%E6%B1%9F&ci=all&hk=&o=all&pi=24&hl=all&pageNo=",
//				// 天津
//				"http://www.guahao.com/hospital/areahospitals?sort=region_sort&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E5%A4%A9%E6%B4%A5&ci=all&hk=&o=all&pi=3&hl=all&pageNo=",
//				// 陕西
//				"http://www.guahao.com/hospital/areahospitals?sort=&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E9%99%95%E8%A5%BF&ci=all&hk=&o=all&pi=9&hl=all&pageNo=",
//				// 安徽
//				"http://www.guahao.com/hospital/areahospitals?sort=region_sort&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E5%AE%89%E5%BE%BD&ci=all&hk=&o=all&pi=23&hl=all&pageNo=",
//				// 河南
//				"http://www.guahao.com/hospital/areahospitals?sort=&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E6%B2%B3%E5%8D%97&ci=all&hk=&o=all&pi=20&hl=all&pageNo=",
//				// 四川 
//				"http://www.guahao.com/hospital/areahospitals?sort=region_sort&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E5%9B%9B%E5%B7%9D&ci=all&hk=&o=all&pi=15&hl=all&pageNo=",
//				// 青海
//				"http://www.guahao.com/hospital/areahospitals?sort=&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E9%9D%92%E6%B5%B7&ci=all&hk=&o=all&pi=12&hl=all&pageNo=",
//				// 辽宁
//				"http://www.guahao.com/hospital/areahospitals?sort=region_sort&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E8%BE%BD%E5%AE%81&ci=all&hk=&o=all&pi=5&hl=all&pageNo=",
//				// 内蒙古
//				"http://www.guahao.com/hospital/areahospitals?sort=&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E5%86%85%E8%92%99%E5%8F%A4&ci=all&hk=&o=all&pi=33&hl=all&pageNo=",
//				// 江西 
//				"http://www.guahao.com/hospital/areahospitals?sort=region_sort&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E6%B1%9F%E8%A5%BF&ci=all&hk=&o=all&pi=25&hl=all&pageNo=",
//				// 黑龙江 
//				"http://www.guahao.com/hospital/areahospitals?sort=&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E9%BB%91%E9%BE%99%E6%B1%9F&ci=all&hk=&o=all&pi=7&hl=all&pageNo=",
//				// 河北 
//				"http://www.guahao.com/hospital/areahospitals?sort=region_sort&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E6%B2%B3%E5%8C%97&ci=all&hk=&o=all&pi=16&hl=all&pageNo=",
//				// 云南
//				"http://www.guahao.com/hospital/areahospitals?sort=&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E4%BA%91%E5%8D%97&ci=all&hk=&o=all&pi=17&hl=all&pageNo=",
//				// 吉林
//				"http://www.guahao.com/hospital/areahospitals?sort=region_sort&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E5%90%89%E6%9E%97&ci=all&hk=&o=all&pi=6&hl=all&pageNo=",
//				// 贵州
//				"http://www.guahao.com/hospital/areahospitals?sort=&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E8%B4%B5%E5%B7%9E&ci=all&hk=&o=all&pi=18&hl=all&pageNo=",
//				// 广西 
//				"http://www.guahao.com/hospital/areahospitals?sort=region_sort&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E5%B9%BF%E8%A5%BF&ci=all&hk=&o=all&pi=31&hl=all&pageNo=",
//				// 重庆 
//				"http://www.guahao.com/hospital/areahospitals?sort=&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E9%87%8D%E5%BA%86&ci=all&hk=&o=all&pi=4&hl=all&pageNo=",
//				// 宁夏 
//				"http://www.guahao.com/hospital/areahospitals?sort=region_sort&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E5%AE%81%E5%A4%8F&ci=all&hk=&o=all&pi=10&hl=all&pageNo=",
//				// 甘肃 
//				"http://www.guahao.com/hospital/areahospitals?sort=&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E7%94%98%E8%82%83&ci=all&hk=&o=all&pi=11&hl=all&pageNo=",
//				// 福建 
//				"http://www.guahao.com/hospital/areahospitals?sort=region_sort&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E7%A6%8F%E5%BB%BA&ci=all&hk=&o=all&pi=27&hl=all&pageNo=",
//				// 海南
//				"http://www.guahao.com/hospital/areahospitals?sort=&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E6%B5%B7%E5%8D%97&ci=all&hk=&o=all&pi=32&hl=all&pageNo=",
//				// 新疆 
//				"http://www.guahao.com/hospital/areahospitals?sort=region_sort&ipIsShanghai=true&fg=0&c=%E4%B8%8D%E9%99%90&ht=all&q=&p=%E6%96%B0%E7%96%86&ci=all&hk=&o=all&pi=13&hl=all&pageNo=",
				// 西藏
				"http://www.guahao.com/hospital/areahospitals?q=&pi=14&p=%E8%A5%BF%E8%97%8F&ci=all&c=%E4%B8%8D%E9%99%90&o=all&hl=all&ht=all&hk=&fg=0&ipIsShanghai=true&sort=",
		};
		
		for(String ul:urls){
			parseList("",ul,false);
			System.out.println("\n");
		}
	}
	
	static void parseList(String htype, String url, boolean isMultiPage){
//		System.out.println(htype+":");
		try {
			int currPg = 1,totalPg = 1;
			while(true){
				if(currPg>totalPg){
					break;
				}
//				System.out.println("第"+currPg+"页");
				String content = FetchUtils.getOneHtml(url+(currPg++));
				String sp_pg1 = "<span class=\"pd\">共<label>";
				String sp_pg2 = "</label>页</span>";
				if(isMultiPage){
					String pg = content.substring(content.indexOf(sp_pg1)+sp_pg1.length(), content.indexOf(sp_pg2));
					totalPg = Integer.valueOf(pg);
				}
				String sp_bg = "<li class=\"g-hospital-item J_hospitalItem\">";
				String sp_end = "<h2 class=\"title\">相关文章</h2>";
				content = content.substring(content.indexOf(sp_bg)+sp_bg.length(), content.indexOf(sp_end));
				String sp_item = sp_bg;
				String[] items = content.split(sp_item);
//				System.out.println(items.length+"家医院：");
				for(String item:items){
					String tmp = item.substring(item.indexOf("<a href=\"")+"<a href=\"".length(),item.indexOf("<div class=\"score g-left\">"));
					String hurl = tmp.substring(0,tmp.indexOf("\""));
					String name = tmp.substring(tmp.indexOf(">")+1,tmp.indexOf("<")).trim();
					String type = tmp.substring(tmp.indexOf("<em>")+4, tmp.indexOf("</em>")).trim();
					if(!filterLevel.contains(type)){
						continue;
					}
					tmp = tmp.substring(tmp.indexOf("<span title=\"")+"<span title=\"".length());
					String tel = tmp.substring(0, tmp.indexOf("\"")).trim();
					tmp = tmp.substring(tmp.indexOf("<span title=\"")+"<span title=\"".length());
					String addr = tmp.substring(0, tmp.indexOf("\"")).trim();
					String area = parseDetail(hurl);
					System.out.println(area+",\t"+type+",\t "+name+",\t\t"+tel+",\t"+addr);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static String parseDetail(String url){
		try {
			String sp = "<a href=";
			String content = FetchUtils.getOneHtml(url);
			content = content.substring(content.indexOf("首页"));
			content = content.substring(content.indexOf(sp)+sp.length());
			String area1 = content.substring(content.indexOf(">")+1,content.indexOf("</a>")).trim();
			content = content.substring(content.indexOf(sp)+sp.length());
			String area2 = content.substring(content.indexOf(">")+1,content.indexOf("</a>")).trim();
			
			return area1+" "+area2;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "";
	}

}
