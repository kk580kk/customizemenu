package com.jivesoftware.spark.customizemenu;

/**
 * @author Administrator
 *
 */
public class CustomProccess {
    //标题
	private String title;
	//链接地址
	private String linkUrl;
	//显示方式
	private String viewmode;
	//图片地址
	private String imageUrl;
	//时间
	private String showDate;
	//简短描述
	private String description;
	//图标
	private String logo;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getShowDate() {
		return showDate;
	}
	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getViewmode() {
		return viewmode;
	}
	public void setViewmode(String viewmode) {
		this.viewmode = viewmode;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	

}

