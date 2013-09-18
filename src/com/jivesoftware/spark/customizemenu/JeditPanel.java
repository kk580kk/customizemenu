package com.jivesoftware.spark.customizemenu;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
public class JeditPanel {
public static void main(String[] args)
{
   JEditorPane editorPane = new JEditorPane();
   editorPane = new JEditorPane();
   editorPane.setVisible(true);
   editorPane.setLayout(new GridBagLayout());
   editorPane.setBackground(Color.white);
//设置显示类型
   editorPane.setContentType("text/html");
   //必须设置为false，才能显示html
   editorPane.setEditable(false);
   //必须放在JScrollPane面板上显示
   JScrollPane scrollPane = new JScrollPane(editorPane);
   // 获得editorPane的宽度
   int editorPaneLength = 300;
   int work_name_length = editorPaneLength * 2 / 10;
   int work_content_length = editorPaneLength * 6 / 10;
   int apply_name_length = editorPaneLength * 2 / 10;
   StringBuffer content = new StringBuffer();
   content.append("<table width='" + editorPaneLength
     + "' height='5' bgcolor='#FFFFFF'>");
   content.append("<tr>");
   content.append("<td colspan='3' bgcolor='#FF8040'>&nbsp;&nbsp;</td>");
   content.append("</tr>");
   content.append("<tr>");
   content
     .append(
       "<td width='"
         + work_name_length
         + "' bgcolor='#E8E7FE' align='center'><b>分类</b></td>")
     .append(
       "<td width='"
         + work_content_length
         + "' bgcolor='#E8E7FE' align='center'><b>事项</b></td>")
     .append(
       "<td width='"
         + apply_name_length
         + "' bgcolor='#E8E7FE' align='center'><b>申请人</b></td>");
   content.append("</tr>");
   for (int i = 0; i < 3; i++) {
    // 解析待办各项的内容
    String work_name = "111", work_content = "1111", apply_name = "111";
    if (i % 2 == 0) {
     content.append("<tr bgcolor='#E8E7FE'>");
     content.append("<td width='" + work_name_length
       + "' align='center'>" + work_name + "</td>"
       + "<td width='" + work_content_length
       + "' align='center'>" + work_content + "</td>"
       + "<td width='" + apply_name_length
       + "' align='center'>" + apply_name + "</td>");
     content.append("</tr>");
    } else {
     content.append("<tr>");
     content.append("<td width='" + work_name_length
       + "' align='center'>" + work_name + "</td>"
       + "<td width='" + work_content_length
       + "' align='center'>" + work_content + "</td>"
       + "<td width='" + apply_name_length
       + "' align='center'>" + apply_name + "</td>");
     content.append("</tr>");
    }
   }
   content.append("</table>");
   editorPane.setText(content.toString());
   JFrame frame = new JFrame("JTableDemo");
   frame.setSize(800, 600);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setContentPane(scrollPane);
   frame.pack();
   frame.setVisible(true);
}
}