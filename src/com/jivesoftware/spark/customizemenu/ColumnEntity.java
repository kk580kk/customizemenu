package com.jivesoftware.spark.customizemenu;

import java.util.LinkedHashMap;

public class ColumnEntity{
	private LinkedHashMap<String, String> columnLinkedHashMap;
	
	public ColumnEntity()
	{
		columnLinkedHashMap = new LinkedHashMap<String, String>();
	}

	public LinkedHashMap<String, String> getColumnLinkedHashMap() {
		return columnLinkedHashMap;
	}

	public void setColumnLinkedHashMap(
			LinkedHashMap<String, String> columnLinkedHashMap) {
		this.columnLinkedHashMap = columnLinkedHashMap;
	}

}