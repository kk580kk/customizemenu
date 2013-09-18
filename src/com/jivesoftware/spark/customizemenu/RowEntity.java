package com.jivesoftware.spark.customizemenu;

import java.util.LinkedHashMap;

public class RowEntity{
	private LinkedHashMap<String, String> rowLinkedHashMap;
	
	public RowEntity()
	{
		rowLinkedHashMap = new LinkedHashMap<String, String>();
	}
	public LinkedHashMap<String, String> getRowLinkedHashMap() {
		return rowLinkedHashMap;
	}

	public void setRowLinkedHashMap(LinkedHashMap<String, String> rowLinkedHashMap) {
		this.rowLinkedHashMap = rowLinkedHashMap;
	}
}