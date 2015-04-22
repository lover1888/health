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
		url = "http://www.guahao.com/hospital/areahospitals?q=&pi=2&p=%E4%B8%8A%E6%B5%B7&ci=all&c=%E4%B8%8D%E9%99%90&o=all&hl=all&ht=501&hk=all&fg=0&ipIsShanghai=true&sort=";
		parseList("不孕不育医院",url,false);
		
		
	}
	
	static void parseList(String htype, String url, boolean isMultiPage){
		System.out.println(htype+":");
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
