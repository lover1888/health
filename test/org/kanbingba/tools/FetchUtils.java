/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年3月20日 下午6:00:15
 * @version V1.0  
 */
package org.kanbingba.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年3月20日 下午6:00:15
 */
public class FetchUtils {
	public static List<String> getLink(final String s) {
		String regex;
		final List<String> list = new ArrayList<String>();
		regex = "<a[^>]*href=(\"([^\"]*)\"|\'([^\']*)\'|([^\\s>]*))[^>]*>(.*?)</a>";
		final Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		final Matcher ma = pa.matcher(s);
		while (ma.find()) {
			list.add(ma.group());
		}
		return list;
	}

	public static String getNamesContent(String txt) {
		String begin = "<div class=\"jibing-list\">";
		String end = "<div class=\"page\">";
		int bpos = txt.indexOf(begin);
		int epos = txt.indexOf(end);
		return txt.substring(bpos, epos);
	}

	/**
	 * 读取一个网页全部内容
	 */
	public static String getOneHtml(final String htmlurl) throws IOException {

		String[] agents = {
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.75 Safari/535.7",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.75 Safari/535.7",
				"Mozilla/4.0 (compatible; MSIE 6.0; X11; Linux i686; ja) Opera 10.10",
				"Opera/9.80 (Windows NT 6.1; U; ja) Presto/2.9.168 Version/11.50",
				"Opera/9.80 (Windows NT 6.1; U; ja) Presto/2.10.229 Version/11.60",
				"Mozilla/5.0 (Macintosh; U; PPC Mac OS X; ja-jp) AppleWebKit/312.8 (KHTML, like Gecko) Safari/312.6",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.52.7 (KHTML, like Gecko) Version/5.1.2 Safari/534.52.7",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8) AppleWebKit/536.25 (KHTML, like Gecko) Version/6.0 Safari/536.25",
				"Mozilla/5.0(iPad; U; CPU iPhone OS 3_2 like Mac OS X; en-us) AppleWebKit/531.21.10 (KHTML, like Gecko) Version/4.0.4 Mobile/7B314 Safari/531.21.10" };
		int k = new Random().nextInt(agents.length);
		System.out.println("k=" + k + ">>>" + agents[k]);
		try {
			Thread.sleep((k == 0 ? 1 : k) * 1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		URL url;
		String temp;
		final StringBuffer sb = new StringBuffer();

		try {
			url = new URL(htmlurl);
			URLConnection con = url.openConnection();
			con.setDoOutput(true);
			con.setRequestProperty("User-Agent", agents[k]);
			final BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream(), "utf-8"));// 读取网页全部内容
			while ((temp = in.readLine()) != null) {
				sb.append(temp);
			}
			in.close();
		} catch (final MalformedURLException me) {
			System.out.println("你输入的URL格式有问题！请仔细输入");
			me.getMessage();
			throw me;
		} catch (final IOException e) {
			e.printStackTrace();
			throw e;
		}
		return sb.toString();
	}

	public static String getDetailContent(String txt) {
		String begin = "<div class=\"lemma-main\">";
		String end = "<div class=\"jbct-ctrr_xgkp\">";
		int bpos = txt.indexOf(begin);
		int epos = txt.indexOf(end);
		// System.out.println(begin+"位置："+bpos);
		// System.out.println(end+"位置："+epos);
		return txt.substring(bpos, epos);
	}
}
