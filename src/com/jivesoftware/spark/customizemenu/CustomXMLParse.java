package com.jivesoftware.spark.customizemenu;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import com.jivesoftware.spark.customizemenu.*;

public class CustomXMLParse {
	
	public  List xmlMail(String xmlDoc) {
        StringReader read = new StringReader(xmlDoc);
        InputSource source = new InputSource(read);
        SAXBuilder sb = new SAXBuilder();
        List<CustomMail> result = new ArrayList<CustomMail>();
   
        try {
            Document doc = sb.build(source);
            Element root = doc.getRootElement();
            System.out.println(root.getName());
            //得到根元素所有子元素的集合
            List nodeList = root.getChildren();
         
              for(int i=0;i<nodeList.size();i++)
              {
            	  Element e = (Element) nodeList.get(i);//循环依次得到子元素            
                  String id = e.getAttributeValue("id");
                  String nodeId = e.getAttributeValue("nodeId");
                  String from = e.getAttributeValue("from");
                  String subject = e.getAttributeValue("subject");
                  String date = e.getAttributeValue("date");
                  CustomMail c = new CustomMail();
                  c.setMailId(id);
                  c.setNodeId(nodeId);
                  c.setFrom(from);
                  c.setSubject(subject);
                  c.setDate(date);
                  result.add(c);
              }      
              
            
          

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return result;
    }
	
	
	//TODO
	public  List xmlAdministrationProccess(String xmlDoc) {
        StringReader read = new StringReader(xmlDoc);
        InputSource source = new InputSource(read);
        SAXBuilder sb = new SAXBuilder();
        List<CustomProccess> result = new ArrayList<CustomProccess>();
        
        try {
            Document doc = sb.build(source);
            Element root = doc.getRootElement();
            System.out.println(root.getName());
            //得到根元素所有子元素的集合
            List node = root.getChildren();
            if( node.size()==2 )
            { 
              Element et = (Element) node.get(0); //<listCWCRXZ>
              List rowList = et.getChildren();  // <CWCRXZ>的list
              for(int i=0;i<rowList.size();i++)
              {
            	  Element e = (Element) rowList.get(i);//循环依次得到子元素            
                  //row 里面的
                  List l = e.getChildren();
                
                  if(l.size()==3)
                  {
                	   CustomProccess p = new CustomProccess();
                	   Element titleElement = (Element) l.get(0);
                	   String title = titleElement.getValue();
                	   Element linkUrlElement = (Element) l.get(1);
                	   String linkUrl = linkUrlElement.getValue();
                	   Element showDateElement = (Element) l.get(2);
                	   String showDate  = showDateElement.getValue();
                	   
                	   p.setShowDate(showDate);
                	   p.setLinkUrl(linkUrl.replace("&amp;", "&"));
                	   p.setTitle(title.replace("&lt;", "<").replace("&gt;", ">").replace("&amp;", "&"));
                	   if(p.getTitle()!=null&&p.getTitle().startsWith("<img src='/efmpx/CW/CR/images/flag.gif' alt='警告'/>"))
                	   {
                		   String t  = p.getTitle();
                		   String [] titleArr = t.split("<img src='/efmpx/CW/CR/images/flag.gif' alt='警告'/>");
                		   if(titleArr!=null&&titleArr.length>0)
                		   p.setTitle(titleArr[1]);
                	   }
                	   result.add(p);
                  }
                 
              }      
              
            }
          

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return result;
    }
	
	public  List xmlPartyProccess(String xmlDoc) {
        StringReader read = new StringReader(xmlDoc);
        InputSource source = new InputSource(read);
        SAXBuilder sb = new SAXBuilder();
        List<CustomProccess> result = new ArrayList<CustomProccess>();
        
        try {
            Document doc = sb.build(source);
            Element root = doc.getRootElement();
            System.out.println(root.getName());
            //得到根元素所有子元素的集合
            List node = root.getChildren();
            if( node.size()==2 )
            { 
              Element et = (Element) node.get(1); //<listCWCRXZ>
              List rowList = et.getChildren();  // <CWCRXZ>的list
              for(int i=0;i<rowList.size();i++)
              {
            	  Element e = (Element) rowList.get(i);//循环依次得到子元素            
                  //row 里面的
                  List l = e.getChildren();
                
                  if(l.size()==3)
                  {
                	   CustomProccess p = new CustomProccess();
                	   Element titleElement = (Element) l.get(0);
                	   String title = titleElement.getValue();
                	   Element linkUrlElement = (Element) l.get(1);
                	   String linkUrl = linkUrlElement.getValue();
                	   Element showDateElement = (Element) l.get(2);
                	   String showDate  = showDateElement.getValue();
                	   p.setShowDate(showDate);
                	   p.setLinkUrl(linkUrl.replace("&amp;", "&"));
                	   p.setTitle(title.replace("&lt;", "<").replace("&gt;", ">").replace("&amp;", "&"));
                	   if(p.getTitle()!=null&&p.getTitle().startsWith("<img src='/efmpx/CW/CR/images/flag.gif' alt='警告'/>"))
                	   {
                		   String t  = p.getTitle();
                		   String [] titleArr = t.split("<img src='/efmpx/CW/CR/images/flag.gif' alt='警告'/>");
                		   if(titleArr!=null&&titleArr.length>0)
                		   p.setTitle(titleArr[1]);
                	   }
                	   result.add(p);
                  }
                 
              }      
              
            }
          

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return result;
    }
	
	public  CustomMail xmlMailDetial(String xmlDoc) {
        StringReader read = new StringReader(xmlDoc);
        InputSource source = new InputSource(read);
        SAXBuilder sb = new SAXBuilder();
        CustomMail c = new CustomMail();
     //   List<CustomMail> result = new ArrayList<CustomMail>();
   
        try {
            Document doc = sb.build(source);
            Element root = doc.getRootElement();
            System.out.println(root.getName());
             
           List node = root.getChildren();
           if( node.size()==2 )
           { 
	            Element e = (Element) node.get(0); //<mailDetail>
	            String id = e.getAttributeValue("id");
	            String nodeId = e.getAttributeValue("nodeId");
	            String from = e.getAttributeValue("from");
	            String subject = e.getAttributeValue("subject");
	            String content = e.getAttributeValue("content");
	            String recipientTo = e.getAttributeValue("recipientTo");
	            String recipientCc = e.getAttributeValue("recipientCc");
	            String date = e.getAttributeValue("date");
	          
	            c.setMailId(id);
	            c.setNodeId(nodeId);
	            c.setFrom(from);
	            c.setSubject(subject);
	            c.setContent(content);
	            c.setRecipientTo(recipientTo);
	            c.setRecipientCc(recipientCc);
	            c.setDate(date);
	        //    result.add(c);
            
           }
              
            
          

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return c;
    }
	
	
	
	
	public  List xmlParse(String xmlDoc) {
        StringReader read = new StringReader(xmlDoc);
        InputSource source = new InputSource(read);
        SAXBuilder sb = new SAXBuilder();
        CustomMail c = new CustomMail();
        //List<LinkedHashMap<String, String>> resultList = new ArrayList<LinkedHashMap<String,String>>();
        List<TabEntity> tabEntityList = new ArrayList<TabEntity>() ;
        TabEntity tabEntity ;
        MetadataEntity metadataEntity ;
        ContentEntity contentEntity;
        List<ColumnEntity> columntempList;
        List<RowEntity> rowtempList;
        LinkedHashMap<String, String> columnLinkedHashMap;
        LinkedHashMap<String, String> rowLinkedHashMap;
        try {
            Document doc = sb.build(source);
            Element root = doc.getRootElement();//根节点
           
            List tabList = root.getChildren();  //Tab的list
            if(tabList==null || tabList.size()<1)
        	    return null;
            
            for(int i = 0 ;i< tabList.size();i++) //对Tab进行遍历
            {
               Element tabElt = (Element)tabList.get(i);
               String title = tabElt.getAttributeValue("title");
               tabEntity = new TabEntity();
               tabEntity.setTitle(title);
        	   List tabAttrList =  ((Element)tabList.get(i)).getChildren();  //Tab子节点的list,包含metadata和content节点
        	  
        	   Element metadata = (Element) tabAttrList.get(0); //metadata
        	   List columnList =  metadata.getChildren();
        	   metadataEntity  = new MetadataEntity();
        	   columntempList = new ArrayList<ColumnEntity>();
        	   
        	   for(int j = 0 ;j< columnList.size();j++) //column的list
        	   {
        		   Element cloumn = (Element)columnList.get(j); //column
        		   List columnAttrList = cloumn.getChildren();  //column子节点的lit
        		   ColumnEntity columnEntity = new ColumnEntity();
        		   columnLinkedHashMap = new LinkedHashMap<String, String>();
        		  
        		   for(int k = 0 ;k < columnAttrList.size(); k++)  //例如 id name age
        		   {
        			   Element  columnAttr= (Element)columnAttrList.get(k);
        			   String name = columnAttr.getName();
        			   String value = columnAttr.getValue();
        			   columnLinkedHashMap.put(name, value);
        		   }
        		   columnEntity.setColumnLinkedHashMap(columnLinkedHashMap);
        		   columntempList.add(columnEntity);
        	   }
        	   metadataEntity.setColumnList(columntempList);
        	   tabEntity.setMetadata(metadataEntity);
        	   
        	   
        	   
        	   
        	   
        	   Element content = (Element) tabAttrList.get(1); //content
        	   List rowList =  content.getChildren();
        	   contentEntity  = new ContentEntity();
        	   rowtempList = new ArrayList<RowEntity>();
        	   for(int j = 0 ;j< rowList.size();j++)
        	   {
        		   Element row = (Element)rowList.get(j); //row
        		   List rowAttrList = row.getChildren();  //row子节点的lit
        		   RowEntity rowEntity = new RowEntity();
        		   rowLinkedHashMap = new LinkedHashMap<String, String>();
        		   for(int k = 0 ;k < rowAttrList.size(); k++)
        		   {
        			   Element  rowAttr= (Element)rowAttrList.get(k);
        			   String name = rowAttr.getName();
        			   String value = rowAttr.getValue();
        			   value = value.replace("&lt;", "<").replace("&gt;", ">").replace("&amp;", "&");
        			   rowLinkedHashMap.put(name, value);
        		   }
        		   rowEntity.setRowLinkedHashMap(rowLinkedHashMap);
        		   rowtempList.add(rowEntity);
        		   
        	   }
        	   contentEntity.setRowList(rowtempList);
        	   tabEntity.setContent(contentEntity);
        	   
        	   tabEntityList.add(tabEntity);
        	   
        	   
            }//try
           
         

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return tabEntityList;
    }
	
	
	
	
    

}
