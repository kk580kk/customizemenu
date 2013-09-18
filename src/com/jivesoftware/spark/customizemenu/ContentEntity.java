package com.jivesoftware.spark.customizemenu;

import java.util.ArrayList;
import java.util.List;
import com.jivesoftware.spark.customizemenu.RowEntity;

public class ContentEntity{
	private List<RowEntity> rowList;
	public  ContentEntity()
	{
		rowList = new ArrayList<RowEntity>();
	}
	public List<RowEntity> getRowList() {
		return rowList;
	}

	public void setRowList(List<RowEntity> rowList) {
		this.rowList = rowList;
	}
	
}