package com.jivesoftware.spark.customizemenu;

import java.util.ArrayList;
import java.util.List;
import com.jivesoftware.spark.customizemenu.*;

public class MetadataEntity{
	private List<ColumnEntity> columnList;
	public  MetadataEntity()
	{
		columnList = new ArrayList<ColumnEntity>();
	}
	public List<ColumnEntity> getColumuList() {
		return columnList;
	}

	public void setColumnList(List<ColumnEntity> columnList) {
		this.columnList = columnList;
	}
}