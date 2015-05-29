package org.easywechat.util;

import org.easywechat.msg.ImageMsg;
import org.easywechat.msg.MusicMsg;
import org.easywechat.msg.NewsMsg;
import org.easywechat.msg.TextMsg;
import org.easywechat.msg.VideoMsg;
import org.easywechat.msg.VoiceMsg;

/**
 * ������Ӧ��Ϣ�Ĺ�����
 */
public class MessageFactory {

	/**
	 * ��������Ϊ�յ��ı���Ϣ
	 */
	public static TextMsg createTextMsg() {
		return new TextMsg();
	}

	/**
	 * ����ָ�����ݵ��ı���Ϣ
	 * 
	 * @param content
	 *            �ظ�����Ϣ����
	 */
	public static TextMsg createTextMsg(String content) {
		return new TextMsg(content);
	}

	/**
	 * ����ͼƬ��Ϣ
	 * 
	 * @param mediaId
	 *            ͨ���ϴ���ý���ļ����õ���id
	 */
	public static ImageMsg createImageMsg(String mediaId) {
		return new ImageMsg(mediaId);
	}

	/**
	 * ����������Ϣ
	 * 
	 * @param mediaId
	 *            ͨ���ϴ���ý���ļ����õ���id
	 */
	public static VoiceMsg createVoiceMsg(String mediaId) {
		return new VoiceMsg(mediaId);
	}

	/**
	 * ������Ƶ��Ϣ
	 * 
	 * @param mediaId
	 *            ͨ���ϴ���ý���ļ����õ���id
	 * @param title
	 *            ��Ƶ��Ϣ�ı��⡣�粻��Ҫ����Ϊnull
	 * @param description
	 *            ��Ƶ��Ϣ���������粻��Ҫ����Ϊnull
	 */
	public static VideoMsg createVideoMsg(String mediaId, String title,
			String description) {
		return new VideoMsg(mediaId, title, description);
	}

	/**
	 * ����������Ϣ
	 * 
	 * @param thumbMediaId
	 *            ����ͼ��ý��id��ͨ���ϴ���ý���ļ����õ���id
	 * @param title
	 *            ���ֱ��⡣�粻��Ҫ����Ϊnull
	 * @param description
	 *            �����������粻��Ҫ����Ϊnull
	 * @param musicUrl
	 *            �������ӡ��粻��Ҫ����Ϊnull
	 * @param hqMusicUrl
	 *            �������������ӣ�WIFI��������ʹ�ø����Ӳ������֡��粻��Ҫ����Ϊnull
	 */
	public static MusicMsg createMusicMsg(String thumbMediaId, String title,
			String description, String musicUrl, String hqMusicUrl) {
		return new MusicMsg(thumbMediaId, title, description, musicUrl,
				hqMusicUrl);
	}

	/**
	 * ��������Ϊ�յ�ͼ����Ϣ
	 */
	public static NewsMsg createNewsMsg() {
		return new NewsMsg();
	}

	/**
	 * ���ؾ���һ��Article��ͼ����Ϣ
	 * 
	 * @param title
	 *            ͼ����Ϣ���⡣��Ϊnull
	 * @param description
	 *            ͼ����Ϣ��������Ϊnull
	 * @param picUrl
	 *            ͼƬ���ӡ���Ϊnull
	 * @param url
	 *            ���ͼ����Ϣ��ת���ӡ���Ϊnull
	 */
	public static NewsMsg createNewsMsg(String title, String description,
			String picUrl, String url) {
		return new NewsMsg().add(title, description, picUrl, url);
	}

}
