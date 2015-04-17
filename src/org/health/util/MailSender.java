/**
 * Copyright © 2015 凯华（上海）健康管理有限公司. All rights reserved.
 *
 * @author jhengfei
 * @date 2015年4月17日 上午9:12:30
 * @version V1.0  
 */
package org.health.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * @Description TODO
 * @author jhengfei
 * @date 2015年4月17日 上午9:12:30
 */
public class MailSender {
	private String from 	= 	"no-reply@kanbingba.cn";
	private String fromNick = 	"看病吧-问答社区";
	private String userName = 	"no-reply@kanbingba.cn";
	private String password = 	"870222687";
	private String smtpHost = 	"smtp.ym.163.com";
	
	Log log = Logs.getLog(MailSender.class);
	
	MailSender(){}
	
	public MailSender(String from, String fromNick, String userName, String password, String smtpHost){
		this.from = from;
		this.fromNick = fromNick;
		this.userName = userName;
		this.password = password;
		this.smtpHost = smtpHost;
	}
	
	private MimeMessage createMimeMessage() throws Exception{
		Properties props = System.getProperties();
		// 创建信件服务器
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.auth", "true");
		props.put("mail.transport.protocol", "smtp");
		// 得到默认的对话对象
		Authenticator a = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		// 创建Session实例
		Session session = Session.getDefaultInstance(props, a);
		// 创建MimeMessage实例对象
		MimeMessage msg = new MimeMessage(session);
		String nick = javax.mail.internet.MimeUtility.encodeText(fromNick);
		msg.setFrom(new InternetAddress(nick + " <" + from + ">"));
		return msg;
	}
	
	public void sendHtml(String to, String subject, String content) {
		try {
			MimeMessage msg = createMimeMessage();
			// 设置收信人
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			// 设置发送日期
			msg.setSentDate(new Date());
			// 设置邮件主题
			msg.setSubject(subject);
			// 设置邮件正文
			msg.setContent(content,"text/html;charset=utf-8");
			Transport.send(msg);
			log.info("发送邮件[ "+to+" ]成功");
		} catch (AddressException e) {
			log.error(e.getMessage(), e);
		} catch (MessagingException e) {
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 发送文本消息
	 * @param to 发送目的地址
	 * @param subject 邮件标题
	 * @param content 邮件内容
	 */
	public void sendText(String to, String subject, String content) {
		try {
			MimeMessage msg = createMimeMessage();
			// 设置收信人
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			// 设置发送日期
			msg.setSentDate(new Date());
			// 设置邮件主题
			msg.setSubject(subject);
			// 设置邮件正文
			msg.setText(content);
			Transport.send(msg);
			log.info("发送邮件[ "+to+" ]成功");
		} catch (AddressException e) {
			log.error(e.getMessage(), e);
		} catch (MessagingException e) {
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * @Description TODO
	 * @param args
	 */
	public static void main(String[] args) {
		MailSender sender = new MailSender();
		
//		sender.sendText("hengfei.jin@kaihuahealth.com","欢迎","欢迎加入看病吧问答社区");
		
		sender.sendHtml("hengfei.jin@kaihuahealth.com","欢迎","<h1>欢迎加入看病吧问答社区</h1><hr><h3>最专业的问答社区</h3>");
	}

}
