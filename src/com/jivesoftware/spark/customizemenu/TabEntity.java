package com.jivesoftware.spark.customizemenu;

import java.util.ArrayList;
import java.util.List;
import com.jivesoftware.spark.customizemenu.*;
public class TabEntity{
	private String title;
	private MetadataEntity metadata;
	private ContentEntity content;
	
	public String getTitle(){
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public ContentEntity getContent() {
		return content;
	}
	public void setContent(ContentEntity content) {
		this.content = content;
	}
	public MetadataEntity getMetadata() {
		return metadata;
	}
	public void setMetadata(MetadataEntity metadata) {
		this.metadata = metadata;
	}
	
	
}