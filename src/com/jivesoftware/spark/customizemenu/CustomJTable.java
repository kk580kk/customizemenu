package com.jivesoftware.spark.customizemenu;

import javax.swing.JTable;

public class CustomJTable extends JTable
{
	 public CustomJTable(Object[][] rowData, Object[] columnNames)
	 {
	  super(rowData, columnNames);
	 }
	 
	 public boolean isCellEditable(int row,int col)
	 {
	  return false;
	 }
 
}