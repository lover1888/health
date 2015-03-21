/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年3月20日 下午6:01:36
 * @version V1.0  
 */
package org.kanbingba.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年3月20日 下午6:01:36
 */
public class FetchDoctors {

	/**
	 * @Description TODO
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//http://www.guahao.com/hospital/cad3aa78-5ed3-4de9-b389-6e74705bb501
		
		try {
//			String text = FetchUtils.getOneHtml("http://www.guahao.com/department/shiftcase/1ee24b4a-5442-4396-8acb-4fcc19993b79?pageNo=1");
//			System.out.println(text);
			
			// 下载图片
			URL url = new URL("http://img.guahao.cn/images/image140//expert/cad3aa78-5ed3-4de9-b389-6e74705bb501/63296bdd-1d7e-4d3a-b794-f48ff2ff0410.jpg");
			File outFile = new File("/Users/kaihua/Desktop/abc.jpg");
			OutputStream os = new FileOutputStream(outFile);
			InputStream is = url.openStream();
			byte[] buff = new byte[1024];
			byte[] temp;
			while(true) {
				int readed = is.read(buff);
				if(readed == -1) {
					break;
				}
				temp = new byte[readed];
				System.arraycopy(buff, 0, temp, 0, readed);
				os.write(temp);
			}
			is.close();  
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
