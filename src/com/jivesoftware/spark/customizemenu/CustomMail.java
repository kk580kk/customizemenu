package com.jivesoftware.spark.customizemenu;

/**
 * @author Administrator
 *
 */
public class CustomMail {
	private String mailId;
	private String nodeId;
	private String from;
	private String subject;
	private String date;
	private String content;
	private String recipientTo;
	private String recipientCc;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRecipientCc() {
		return recipientCc;
	}
	public void setRecipientCc(String recipientCc) {
		this.recipientCc = recipientCc;
	}
	public String getRecipientTo() {
		return recipientTo;
	}
	public void setRecipientTo(String recipientTo) {
		this.recipientTo = recipientTo;
	}


}

