package org.easywechat.servlet;

import org.easywechat.msg.BaseMsg;
import org.easywechat.msg.TextMsg;
import org.easywechat.msg.req.BaseEvent;
import org.easywechat.msg.req.BaseReqMsg;
import org.easywechat.msg.req.ImageReqMsg;
import org.easywechat.msg.req.LocationReqMsg;
import org.easywechat.msg.req.MenuEvent;
import org.easywechat.msg.req.TextReqMsg;
import org.easywechat.msg.req.VideoReqMsg;
import org.easywechat.msg.req.VoiceReqMsg;

/**
 * �����ṩ��һϵ�н��ղ���Ӧ��Ϣ�ļ�㷽����ʹ���߲���Ҫ����XxxMsgReq����<br>
 * �����Ҫ��ȡ��Ϣ��FromUserName,CreateTime,MagId�����ԣ��벻Ҫʹ�ø��࣬ʹ��WeixinServletSupport
 */
@SuppressWarnings("serial")
public abstract class SimpleWeixinSupport extends WeixinServletSupport {

	/**
	 * �����û����͵��ı���Ϣ�������ı���Ϣ��Ӧ<br>
	 * ע�⣺�÷���Ϊ�������ı���Ϣ��Ӧ�ı���Ϣ���ṩ������ ���������Ҫ���������͵���Ϣȥ��Ӧ�ı���Ϣ��������д�÷�����Ӧ����дonText����<br>
	 * 
	 * ��д�÷����ᵼ��onText����ʧЧ
	 * 
	 * @param content
	 *            �û����͵���Ϣ
	 * 
	 * @return ��Ӧ�û����ı�
	 */
	protected String handleTextWithText(String content) {
		return null;
	}

	/**
	 * ��Ӧ�û����͵��ı���Ϣ
	 * 
	 * @param content
	 *            �û����͵���Ϣ
	 * 
	 * @return ��Ӧ�û�����Ϣ
	 */
	protected BaseMsg onText(String content) {
		return null;
	}

	/**
	 * ��Ӧ�û����͵�ͼƬ��Ϣ
	 * 
	 * @param mediaId
	 *            ͼƬ��Ϣý��id
	 * @param picUrl
	 *            ͼƬ����
	 * 
	 * @return ��Ӧ�û�����Ϣ
	 */
	protected BaseMsg onImage(String mediaId, String picUrl) {
		return null;
	}

	/**
	 * ��Ӧ�û����͵�������Ϣ
	 * 
	 * @param mediaId
	 *            ������Ϣý��id
	 * @param format
	 *            ������ʽ
	 * 
	 * @return ��Ӧ�û�����Ϣ
	 */
	protected BaseMsg onVoice(String mediaId, String format) {
		return null;
	}

	/**
	 * ��Ӧ�û����͵���Ƶ��Ϣ
	 * 
	 * @param mediaId
	 *            ��Ƶ��Ϣý��id
	 * @param thumbMediaId
	 *            ��Ƶ��Ϣ����ͼ��ý��id
	 * 
	 * @return ��Ӧ�û�����Ϣ
	 */
	protected BaseMsg onVideo(String mediaId, String thumbMediaId) {
		return null;
	}

	/**
	 * ��Ӧ�û����͵ĵ���λ����Ϣ
	 * 
	 * @param locationX
	 *            γ��
	 * @param locationY
	 *            ����
	 * @param scale
	 *            ��ͼ���Ŵ�С
	 * @param label
	 *            ����λ����Ϣ
	 * 
	 * @return ��Ӧ�û�����Ϣ
	 */
	protected BaseMsg onLocation(double locationX, double locationY, int scale,
			String label) {
		return null;
	}

	/**
	 * ��Ӧ�û����͵�������Ϣ
	 * 
	 * @param title
	 *            ��Ϣ����
	 * @param description
	 *            ��Ϣ����
	 * @param url
	 *            ��Ϣ����
	 * 
	 * @return ��Ӧ�û�����Ϣ
	 */
	protected BaseMsg onLink(String title, String description, String url) {
		return null;
	}

	/**
	 * ��Ӧ�˵�����¼�
	 * 
	 * @param eventKey
	 *            �¼�KEYֵ�����Զ���˵��ӿ���KEYֵ��Ӧ
	 * 
	 * @return ��Ӧ�û�����Ϣ
	 */
	private BaseMsg onMenuClick(String eventKey) {
		return null;
	}

	@Override
	protected final BaseMsg handleTextMsg(TextReqMsg msg) {

		String reqText = msg.getContent();

		String respText = handleTextWithText(reqText);
		if (respText != null) {// handleTextWithText��������д
			return new TextMsg(respText);
		}

		BaseMsg respMsg = onText(reqText);
		return responseMsgOrDefault(respMsg, msg);
	}

	@Override
	protected final BaseMsg handleImageMsg(ImageReqMsg msg) {
		BaseMsg respMsg = onImage(msg.getMediaId(), msg.getPicUrl());
		return responseMsgOrDefault(respMsg, msg);
	}

	@Override
	protected final BaseMsg handleVoiceMsg(VoiceReqMsg msg) {
		BaseMsg respMsg = onVoice(msg.getMediaId(), msg.getFormat());
		return responseMsgOrDefault(respMsg, msg);
	}

	@Override
	protected final BaseMsg handleVideoMsg(VideoReqMsg msg) {
		BaseMsg respMsg = onVideo(msg.getMediaId(), msg.getThumbMediaId());
		return responseMsgOrDefault(respMsg, msg);
	}

	@Override
	protected final BaseMsg handleLocationMsg(LocationReqMsg msg) {
		BaseMsg respMsg = onLocation(msg.getLocationX(), msg.getLocationY(),
				msg.getScale(), msg.getLabel());
		return responseMsgOrDefault(respMsg, msg);
	}

	@Override
	protected BaseMsg handleMenuClickEvent(MenuEvent event) {
		BaseMsg respMsg = onMenuClick(event.getEventKey());
		return responseMsgOrDefault(respMsg, event);
	}

	/**
	 * ���respMsg��Ϊnull���򷵻�respMsg�����򷵻�Ĭ����Ϣ
	 */
	private BaseMsg responseMsgOrDefault(BaseMsg respMsg, BaseReqMsg reqMsg) {
		if (respMsg != null) {
			return respMsg;
		}

		return handleDefaultMsg(reqMsg);
	}

	/**
	 * ���respEvent��Ϊnull���򷵻�respEvent�����򷵻�Ĭ����Ϣ
	 */
	private BaseMsg responseMsgOrDefault(BaseMsg respMsg, BaseEvent reqEvent) {
		if (respMsg != null) {
			return respMsg;
		}

		return handleDefaultEvent(reqEvent);
	}
}
