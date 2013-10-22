package com.jivesoftware.spark.customizemenu;



import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jivesoftware.MainWindow;
import org.jivesoftware.PasswordMD5;
import org.jivesoftware.Spark;
import org.jivesoftware.resource.Res;
import org.jivesoftware.resource.SparkRes;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.OfflineMessageHeader;
import org.jivesoftware.smackx.OfflineMessageManager;
import org.jivesoftware.smackx.filetransfer.FileTransferRequest;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.spark.ChatManager;
import org.jivesoftware.spark.SessionManager;
import org.jivesoftware.spark.SparkManager;
import org.jivesoftware.spark.UserManager;
import org.jivesoftware.spark.Workspace;
import org.jivesoftware.spark.component.RolloverButton;
import org.jivesoftware.spark.component.tabbedPane.SparkTabbedPane;
import org.jivesoftware.spark.filetransfer.FileTransferListener;
import org.jivesoftware.spark.filetransfer.SparkTransferManager;
import org.jivesoftware.spark.plugin.ContextMenuListener;
import org.jivesoftware.spark.plugin.Plugin;
import org.jivesoftware.spark.search.SearchManager;
import org.jivesoftware.spark.ui.ChatContainer;
import org.jivesoftware.spark.ui.ChatRoom;
import org.jivesoftware.spark.ui.ChatRoomButton;
import org.jivesoftware.spark.ui.ChatRoomListenerAdapter;
import org.jivesoftware.spark.ui.CommandPanel;
import org.jivesoftware.spark.ui.ContactItem;
import org.jivesoftware.spark.ui.ContactItemHandler;
import org.jivesoftware.spark.ui.ContactList;
import org.jivesoftware.spark.ui.GlobalMessageListener;
import org.jivesoftware.spark.ui.MessageFilter;
import org.jivesoftware.spark.ui.MessageListener;
import org.jivesoftware.spark.ui.PresenceListener;
import org.jivesoftware.spark.ui.TranscriptWindow;
import org.jivesoftware.spark.ui.status.StatusBar;
import org.jivesoftware.spark.util.GraphicUtils;
import org.jivesoftware.spark.util.ModelUtil;
import org.jivesoftware.spark.util.log.Log;
import org.jivesoftware.sparkimpl.plugin.alerts.SparkToaster;
//import org.lobobrowser.jweb.ext.JWebClientletSelector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.BadLocationException;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.jivesoftware.spark.customizemenu.*;
import com.jivesoftware.spark.customizemenu.resource.CustomizeMenuRes;
/**
 * Implements the Spark Plugin framework to display the different possibilities
 * using Spark.
 */
public class CustomizeMenuPlugin implements Plugin,PacketListener {
	JMenuBar functionBar = new JMenuBar();


	/**
	 * Called after Spark is loaded to initialize the new plugin.
	 */
	public void initialize() {
		System.out.println("Welcome To CustomizeMenuPlugin");

		//自定义消息过滤
		installMessageFilter();
		
		//增加自定义按钮
		//addCustomizeMenuToSpark();
		addCustom();
		
		//增加菜单
		addMenuToSpark();

		
	}

	/**
	 * Called when Spark is shutting down to allow for persistence of
	 * information or releasing of resources.
	 */
	public void shutdown() {

	}

	/**
	 * Return true if the Spark can shutdown on users request.
	 * 
	 * @return true if Spark can shutdown on users request.
	 */
	public boolean canShutDown() {
		return true;
	}

	private void addTabToSpark() {
		// Get Workspace UI from SparkManager
		Workspace workspace = SparkManager.getWorkspace();

		// Retrieve the Tabbed Pane from the WorkspaceUI.
		SparkTabbedPane tabbedPane = workspace.getWorkspacePane();

		// Add own Tab.
		// tabbedPane.addTab("BaoSight", null, new JButton("Hello"));
		tabbedPane.addTab(Res.getString("tab.navigation"), SparkRes
				.getImageIcon(SparkRes.SMALL_ALL_CHATS_IMAGE),
				new Button("AAA"));

	}

	private void addContactListListener() {

		// Get Workspace UI from SparkManager
		Workspace workspace = SparkManager.getWorkspace();

		// Retrieve the ContactList from the Workspace
		ContactList contactList = workspace.getContactList();

		// Create an action to add to the Context Menu
		final Action sayHelloAction = new AbstractAction() {
			public void actionPerformed(ActionEvent actionEvent) {
				JOptionPane.showMessageDialog(SparkManager.getMainWindow(),"Welcome to Spark");
			}
		};

		sayHelloAction.putValue(Action.NAME, "Say Hello To Me");

		// Add own Tab.
		contactList.addContextMenuListener(new ContextMenuListener() {
			public void poppingUp(Object object, JPopupMenu popup) {
				if (object instanceof ContactItem) {
					popup.add(sayHelloAction);
				}
			}

			public void poppingDown(JPopupMenu popup) {

			}

			public boolean handleDefaultAction(MouseEvent e) {
				return false;
			}
		});

	}

	/**
	 * Adds a ContextMenuListener to a ChatWindow within a ChatRoom.
	 */
	private void addContactListenerToChatRoom() {
		// Retrieve a ChatManager from SparkManager
		ChatManager chatManager = SparkManager.getChatManager();

		final ContextMenuListener listener = new ContextMenuListener() {
			public void poppingUp(Object object, JPopupMenu popup) {
				final TranscriptWindow chatWindow = (TranscriptWindow) object;
				Action clearAction = new AbstractAction() {
					public void actionPerformed(ActionEvent actionEvent) {
						try {
							chatWindow.insert("My own text :)");
						} catch (BadLocationException e) {
							e.printStackTrace();
						}
					}
				};

				clearAction.putValue(Action.NAME, "Insert my own text");
				popup.add(clearAction);
			}

			public void poppingDown(JPopupMenu popup) {

			}

			public boolean handleDefaultAction(MouseEvent e) {
				return false;
			}
		};

		// Add a ChatRoomListener to the ChatManager to allow for notifications
		// when a room is being opened. Note: I will use a
		// ChatRoomListenerAdapter for brevity.
		chatManager.addChatRoomListener(new ChatRoomListenerAdapter() {
			public void chatRoomOpened(ChatRoom room) {
				room.getTranscriptWindow().addContextMenuListener(listener);
			}

			public void chatRoomLeft(ChatRoom room) {
				room.getTranscriptWindow().removeContextMenuListener(listener);
			}
		});
	}

	/**
	 * Adds a new menu and child menu item to Spark.
	 */
	private void addMenuToSpark() {
		// Retrieve the MainWindow UI from Spark.
		final MainWindow mainWindow = SparkManager.getMainWindow();
	
		// Create new Menu
		JMenu myPluginMenu = new JMenu("工具");

		// Create Action to test Menu install.
		Action showMessage = new AbstractAction() {
			public void actionPerformed(ActionEvent actionEvent) {
			 //	JOptionPane.showMessageDialog(mainWindow, "Yeah, It works.");
				configDialog();
			}
		};

		// Give the menu item a name.
		showMessage.putValue(Action.NAME, "自定义菜单");

		// Add to Menu
		myPluginMenu.add(showMessage);

		// Add Menu To Spark
		mainWindow.getJMenuBar().add(myPluginMenu);
	}
	
	private void addCustom(){
        
		String url ="pluginImage/customizemenu/resources/images";
		File file = new File(Spark.getSparkUserHome(), "/baosight/");
		JPanel commandPanelbroadcast = SparkManager.getWorkspace().getCommandPanel();
		commandPanelbroadcast.removeAll();
	    try{
		  file.mkdir();
          File[] customprofiles = file.listFiles();
          JLabel numlab[] = new JLabel[customprofiles.length];
          final RolloverButton[] addContactButton = new RolloverButton[customprofiles.length];
          int i = 0;
          System.out.println("文件数"+customprofiles.length);
          for (File f : customprofiles) {
        	  
        	  ConfigurationUtil config = new ConfigurationUtil(f.getAbsolutePath());
        	  String activate = config.getValue("activate");
        	  if(activate==null||activate.equals("false"))
        		   continue;
        	  final String configname = config.getValue("name");
        	  final String type = config.getValue("type");
        	  String icon = config.getValue("icon");
        	  final String path = config.getValue("url");
        	  numlab[i] =  new JLabel("(0)");
        	  addContactButton[i] = new RolloverButton(CustomizeMenuRes.getImageIcon(icon));
        	  numlab[i].setName(type);
        	  addContactButton[i].setToolTipText(configname);
        	  addContactButton[i].setText("0");
        	  addContactButton[i].setName(type);
              commandPanelbroadcast.add(addContactButton[i], new GridBagConstraints(2,0,1,1, 1.0, 0.0, GridBagConstraints.SOUTHWEST,GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
           //   commandPanelbroadcast.add(numlab[i], new GridBagConstraints(3,0, 1, 1, 1.0,0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE,new Insets(0, 0, 8, 0), 0, 0));

              commandPanelbroadcast.repaint();
              commandPanelbroadcast.invalidate();
              commandPanelbroadcast.updateUI();
              //更新数字
             SimpleDateFormat CREDENTIAL_FORMAT = new SimpleDateFormat("yyyyMMddHHmm");
		     Calendar now = Calendar.getInstance();
		     String minuteStr = CREDENTIAL_FORMAT.format(now.getTime());
	         String authenType = "CodedPwd";
	         PasswordMD5 passwordMD5 = PasswordMD5.getInstance();
	         String name = passwordMD5.getAdminName();
	         String password = passwordMD5.passwordMD5;
	         String cre = passwordMD5.md5((minuteStr + password));
	       
	         String urlStr = path + "?p_username=" + name + "&p_password=" + cre + "&p_authen=" + authenType+ "&getNum=getNum";
	        
	         getURLContent(urlStr);
             //更新数字结束
              
             addContactButton[i].addMouseListener(new MouseAdapter()
      		 {
      			@Override
      			public void mouseClicked(MouseEvent e)
      			{
      				// String path = "http://localhost:8080/efmpx/EFMPX/IM/IMMail.jsp";   				    
      				 SimpleDateFormat CREDENTIAL_FORMAT = new SimpleDateFormat("yyyyMMddHHmm");
      			     Calendar now = Calendar.getInstance();
      			     String minuteStr = CREDENTIAL_FORMAT.format(now.getTime());
      		         String authenType = "CodedPwd";
      		         PasswordMD5 passwordMD5 = PasswordMD5.getInstance();
      		         String name = passwordMD5.getAdminName();
      		         String password = passwordMD5.passwordMD5;
      		         String cre = passwordMD5.md5((minuteStr + password));
      		         
      		         String urlStr = path + "?p_username=" + name + "&p_password=" + cre + "&p_authen=" + authenType;
      				 
      		         showDialog(configname,urlStr);
      				 
      			}
      		 });
              System.out.println("菜单名称:"+configname);
              i++;
        	 
           }
		 
		 }catch (Exception ex) {
				ex.printStackTrace();
			}
		
	
	}
	/**
	 * 增加自定义的菜单按钮
	 * 
	 */
	private void addCustomizeMenuToSpark(){
		//		String url ="src/plugins/customizemenu/resources/images";
		//		JLabel numlab = new JLabel("(2)");
		//		
		//		final RolloverButton addContactButton = new RolloverButton(
		//				new ImageIcon(url+"/mailImg.jpg"));
		//		JPanel commandPanelbroadcast = SparkManager.getWorkspace().getCommandPanel();
		//        commandPanelbroadcast.add(addContactButton, new GridBagConstraints(2,0,1,1, 1.0, 0.0, GridBagConstraints.SOUTHWEST,
		//		GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		//        
		//        commandPanelbroadcast.add(numlab, new GridBagConstraints(3,0, 1, 1, 1.0,
		//		0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE,new Insets(0, 0, 8, 0), 0, 0));
		//        addContactButton.setToolTipText("邮件");
		//
		//        JLabel mailnumlab = new JLabel("(3)");
		//		
		//		final RolloverButton mailContactButton = new RolloverButton(
		//				new ImageIcon(url+"/documentImg.jpg"));
		//		
		//        commandPanelbroadcast.add(mailContactButton, new GridBagConstraints(2,0,1,1, 1.0, 0.0, GridBagConstraints.SOUTHWEST,
		//		GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		//        
		//        commandPanelbroadcast.add(mailnumlab, new GridBagConstraints(3,0, 1, 1, 1.0,
		//		0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE,new Insets(0, 0, 8, 0), 0, 0));
		//        mailContactButton.setToolTipText("待办");
		
	 //	JMenuBar functionBar = new JMenuBar();
	 	functionBar = new JMenuBar();
     //	Get Workspace UI from SparkManager
        Workspace workspace = SparkManager.getWorkspace();
        StatusBar statusBar =  workspace.getStatusBar();
        CommandPanel panel = workspace.getCommandPanel();
		String url ="pluginImage/customizemenu/resources/images";
		File file = new File(Spark.getSparkUserHome(), "/baosight/");
		
		
      
	   try{
		  file.mkdir();
          File[] customprofiles = file.listFiles();
          JMenu menu[] = new  JMenu[customprofiles.length];
          int i = 0;
          for (File f : customprofiles) {
        	  ConfigurationUtil config = new ConfigurationUtil(f.getAbsolutePath());
        	  String activate = config.getValue("activate");
        	  if(activate==null||activate.equals("false"))
        		   continue;
        	  final String configname = config.getValue("name");
        	  final String type = config.getValue("type");
        	//  name=new String(name.getBytes("ISO-8859-1"),"utf-8");
        	  String icon = config.getValue("icon");
        	  final String path = config.getValue("url");
        	  menu[i] = new JMenu("(0) ");
        	  menu[i].setToolTipText(configname);
        	  menu[i].setName(type);
        	  menu[i].setIcon(CustomizeMenuRes.getImageIcon(icon));
        	  functionBar.add(menu[i]);
        	 menu[i].addMouseListener(new MouseAdapter()
      		 {
      			@Override
      			public void mouseClicked(MouseEvent e)
      			{
      				// String path = "http://localhost:8080/efmpx/EFMPX/IM/IMMail.jsp";   				    
      				 SimpleDateFormat CREDENTIAL_FORMAT = new SimpleDateFormat("yyyyMMddHHmm");
      			     Calendar now = Calendar.getInstance();
      			     String minuteStr = CREDENTIAL_FORMAT.format(now.getTime());
      		         String authenType = "CodedPwd";
      		         PasswordMD5 passwordMD5 = PasswordMD5.getInstance();
      		         String name = passwordMD5.getAdminName();
      		         String password = passwordMD5.passwordMD5;
      		         String cre = passwordMD5.md5((minuteStr + password));
      		         
      		         String urlStr = path + "?p_username=" + name + "&p_password=" + cre + "&p_authen=" + authenType;

      		         System.out.println(urlStr);
      				 showDialog(configname,urlStr);
      			}
      		 });
              System.out.println("菜单名称:"+configname);
              i++;
        	 
           }
      
          JLabel myLabel =  new JLabel("                                                                                                         ");
          panel.removeAll();
    	  panel.add(functionBar);
    	  panel.repaint();
    	  panel.invalidate();
		  panel.add(myLabel);
		  panel.updateUI();
		 
		 
		 }catch (Exception ex) {
				ex.printStackTrace();
			}
		
        
    }

	public void configDialog()
	{
	        BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
	        final JFrame windowframe = new JFrame("自定义菜单");
	        windowframe.setLayout(new BorderLayout());
	        windowframe.setIconImage(SparkManager.getApplicationImage().getImage());
	        JPanel pane = new JPanel();
	        pane.setBackground(Color.white);
	        pane.setFont(new Font("Dialog", Font.PLAIN, 14));
//	        final String url ="pluginImage/customizemenu/resources/images";
	        Box box = Box.createVerticalBox();
	        File file = new File(Spark.getSparkUserHome(), "/baosight/");
	        JPanel p = new JPanel();
	       
	 	     JButton addButton = new JButton("新增");
	 	     JButton subButton = new JButton("确定");
	 	    try{
	 		   file.mkdir();
	           File[] customprofiles = file.listFiles();
		       final JLabel[] labels = new JLabel[customprofiles.length];
		       final JCheckBox[] checkBoxs = new JCheckBox[customprofiles.length];
		       JPanel[] panels = new JPanel[customprofiles.length];
		       
	           int i = 0;
	           for (File f : customprofiles) {
	         	    ConfigurationUtil config = new ConfigurationUtil(f.getAbsolutePath());
	         	    String name = config.getValue("name");
	         	 // name=new String(name.getBytes("ISO-8859-1"),"gbk");
	         	    String icon = config.getValue("icon");
	         	    String activate = config.getValue("activate");
	         	    final String path = config.getValue("url");
	         	
	         	    labels[i] = new JLabel();
	         	   
		        	checkBoxs[i] = new JCheckBox();
		        	panels[i] = new JPanel();
		        	
		        	
		        	labels[i].setText(name);
		        	labels[i].setIcon(CustomizeMenuRes.getImageIcon(icon));
		        	labels[i].setName(f.getAbsolutePath());
		        	checkBoxs[i].setText("");
		        	checkBoxs[i].setName(f.getAbsolutePath());
		        	if(activate!=null&&activate.equals("true"))
		        	   checkBoxs[i].setSelected(true);
		        	panels[i].add(checkBoxs[i]);
		        	panels[i].add(labels[i]);
		        	panels[i].setLayout(new FlowLayout());
		        	box.add(panels[i]);
		        	//给菜单增加修改功能
		        	final int j = i;
		        	labels[i].addMouseListener(new MouseAdapter(){
		        		@Override
		        		public void mouseEntered(MouseEvent e) {
		        			labels[j].setToolTipText("点击修改");
		        			labels[j].setFont(new Font("宋体",Font.BOLD,12));
		        		}
		        		@Override
		        		public void mouseExited(MouseEvent e) {
		        			labels[j].setToolTipText("");
		        			labels[j].setFont(new Font("宋体",Font.PLAIN,12));
		        		}
		        		@Override
		        		public void mouseClicked(MouseEvent e) {
		        			try
		        			{
			        			ConfigurationUtil config = new ConfigurationUtil(labels[j].getName());
			  	         	    String name = config.getValue("name");
			  	         	    String icon = config.getValue("icon");
			  	         	    String iconImage = icon;
			  	         	    String activate = config.getValue("activate");
			  	         	    String path = config.getValue("url");
			  	         	    String type = config.getValue("type");
			  	         	    
			        			final JFrame frame = new JFrame("修改自定义菜单");
						        frame.setLayout(new BorderLayout());
						        frame.setIconImage(SparkManager.getApplicationImage().getImage());
						        JPanel pane = new JPanel();
						        pane.setFont(new Font("Dialog", Font.PLAIN, 14));
						        JLabel nameLabel = new JLabel("菜单名称:");
						        JLabel pathLabel = new JLabel("菜单路径:");
						        JLabel typeLabel = new JLabel("菜单类型:");
						        JLabel iconLabel = new JLabel("图标:");
						        JLabel activateLabel = new JLabel("是否显示:");
						        final JTextField nameField = new JTextField(name);
						        final JTextField pathField = new JTextField(path);
						        final JTextField typeField = new JTextField(type);
						        final JCheckBox aCheckBox = new JCheckBox();
						        if(activate!=null&&activate.equals("true"))
						           aCheckBox.setSelected(true);
						       
						        
						       
						        Vector values = new Vector();
//						        File file = new File(url);
//						        File[] files = file.listFiles();
						        
						        String imgs = CustomizeMenuRes.getString("CUSTOM_IMG");
						        String [] files = imgs.split("/");
						       
						        int index = -1;
						        for (int i = 0; i < files.length; i++) {
						            //ImageIcon tmpicon=new ImageIcon(url+"/"+files[i].getName());
						            values.addElement(new ImagedComboBoxItem(files[i], CustomizeMenuRes.getImageIcon(files[i]), i));
						            if(iconImage!=null&&iconImage.equals(files[i]))
						            	index = i;
						        }
						        final JImagedComboBox comboBox = new JImagedComboBox(values);
						        if(index!=-1)
						           comboBox.setSelectedIndex(index);
						        
						        nameLabel.setBounds(60, 30, 100,30);
						        pathLabel.setBounds(60, 80, 100,30);
						        typeLabel.setBounds(60, 130, 100,30);
						        iconLabel.setBounds(60, 180, 100,30);
						        activateLabel.setBounds(60, 230, 100,30);
						        nameField.setBounds(180, 30, 300,25);
						        pathField.setBounds(180, 80, 300,25);
						        typeField.setBounds(180, 130, 300,25);
						        comboBox.setBounds(180, 180, 300,25);
						        aCheckBox.setBounds(180, 230, 300,30);
						        pane.setLayout(null );
						        pane.add(nameLabel);
						        pane.add(pathLabel);
						        pane.add(typeLabel);
						        pane.add(iconLabel);
						        pane.add(activateLabel);
						        pane.add(nameField);
						        pane.add(pathField);
						        pane.add(typeField);
						        pane.add(comboBox);
						        pane.add(aCheckBox);
						        frame.add(new JScrollPane(pane), BorderLayout.CENTER);
						        JButton button = new JButton("修改");
						        JButton deletebutton = new JButton("删除");
						        JPanel buttonPanel = new JPanel();
						        buttonPanel.add(button);
						        buttonPanel.add(deletebutton);
						        button.addMouseListener(new MouseAdapter()
								 {
									@Override
									public void mouseClicked(MouseEvent e)
									{
										String name =  nameField.getText();
										String path = pathField.getText();
										String type = typeField.getText();
										ImagedComboBoxItem item= (ImagedComboBoxItem)comboBox.getSelectedItem();
										String icon = item.getText();
										boolean activate = aCheckBox.isSelected();
										//String fileName = "/baosight/"+String.valueOf(System.currentTimeMillis())+".properties";
										File f = new File(labels[j].getName());
										try{
										  f.createNewFile();
										  ConfigurationUtil config = new ConfigurationUtil(f.getAbsolutePath());
										  config.setValue("url", path);
										  config.setValue("name", name);
										  config.setValue("icon", icon);
										  config.setValue("type", type);
										  if(activate==true)
										    config.setValue("activate", "true");
										  else
										    config.setValue("activate", "false");
										  config.saveFile();
										  //刷新菜单
										 
										  Workspace workspace = SparkManager.getWorkspace();
										  CommandPanel commandPanel = workspace.getCommandPanel();
									
										  addCustom();
									
										
										  frame.dispose();
										  
										  //刷新设置页面
										  windowframe.dispose();
										  configDialog();
										
										}catch(Exception ex){ex.printStackTrace();}
										
									}
								 });
						        deletebutton.addMouseListener(new MouseAdapter()
								 {
									@Override
									public void mouseClicked(MouseEvent e)
									{
										  File f = new File(labels[j].getName());
									      if(f.exists())
									    	   f.delete();
										  
										  addCustom();
										  frame.dispose();
										  
										  //刷新设置页面
										  windowframe.dispose();
										  configDialog();
									}
								 });
						        frame.add(buttonPanel, BorderLayout.SOUTH);
						        frame.pack();
						        frame.setSize(600, 400);
						        GraphicUtils.centerWindowOnScreen(frame);
						        frame.setVisible(true);
			        		}catch (Exception ex) {
			    				ex.printStackTrace();
			    			}
					        
		        		} 
		        		
		        		
		        	});
		        	
		        	i++;
	           } 
	           
	           //新增菜单按钮
	           addButton.addMouseListener(new MouseAdapter()
				 {
					@Override
					public void mouseClicked(MouseEvent e)
					{
						final JFrame frame = new JFrame("新增自定义菜单");
				        frame.setLayout(new BorderLayout());
				        frame.setIconImage(SparkManager.getApplicationImage().getImage());
				        JPanel pane = new JPanel();
				        //pane.setBackground(Color.white);
				        pane.setFont(new Font("Dialog", Font.PLAIN, 14));
				        JLabel nameLabel = new JLabel("菜单名称:");
				        JLabel pathLabel = new JLabel("菜单路径:");
				        JLabel typeLabel = new JLabel("菜单类型:");
				        JLabel iconLabel = new JLabel("图标:");
				        JLabel activateLabel = new JLabel("是否显示:");
				        final JTextField nameField = new JTextField();
				        final JTextField pathField = new JTextField();
				        final JTextField typeField = new JTextField();
				        final JCheckBox aCheckBox = new JCheckBox();
				        aCheckBox.setSelected(true);
				       
				        
				       
				        Vector values = new Vector();
//				        File file = new File(url);
//				        File[] files = file.listFiles();
				        String imgs = CustomizeMenuRes.getString("CUSTOM_IMG");
				        String [] files = imgs.split("/");
				        for (int i = 0; i < files.length; i++) {
				           // ImageIcon icon=new ImageIcon(url+"/"+files[i].getName());
				            values.addElement(new ImagedComboBoxItem(files[i], CustomizeMenuRes.getImageIcon(files[i]), i));
				        }
				        final JImagedComboBox comboBox = new JImagedComboBox(values);
				       
				        
				        nameLabel.setBounds(60, 30, 100,30);
				        pathLabel.setBounds(60, 80, 100,30);
				        typeLabel.setBounds(60, 130, 100,30);
				        iconLabel.setBounds(60, 180, 100,30);
				        activateLabel.setBounds(60, 230, 100,30);
				        nameField.setBounds(180, 30, 300,25);
				        pathField.setBounds(180, 80, 300,25);
				        typeField.setBounds(180, 130, 300,25);
				        comboBox.setBounds(180, 180, 300,25);
				        aCheckBox.setBounds(180, 230, 300,30);
				        pane.setLayout(null );
				        pane.add(nameLabel);
				        pane.add(pathLabel);
				        pane.add(typeLabel);
				        pane.add(iconLabel);
				        pane.add(activateLabel);
				        pane.add(nameField);
				        pane.add(pathField);
				        pane.add(typeField);
				        pane.add(comboBox);
				        pane.add(aCheckBox);
				        frame.add(new JScrollPane(pane), BorderLayout.CENTER);
				        JButton button = new JButton("确定");
				        button.addMouseListener(new MouseAdapter()
						 {
							@Override
							public void mouseClicked(MouseEvent e)
							{
								//C:\Users\boernuo\AppData\Roaming\Spark
								String name =  nameField.getText();
								String path = pathField.getText();
								String type = typeField.getText();
								ImagedComboBoxItem item= (ImagedComboBoxItem)comboBox.getSelectedItem();
								String icon = item.getText();
								boolean activate = aCheckBox.isSelected();
								String fileName = "/baosight/"+String.valueOf(System.currentTimeMillis())+".properties";
								File f = new File(Spark.getSparkUserHome(), fileName);
								try{
								f.createNewFile();
								  ConfigurationUtil config = new ConfigurationUtil(f.getAbsolutePath());
								  config.setValue("url", path);
								  config.setValue("name", name);
								  config.setValue("icon", icon);
								  config.setValue("type", type);
								  if(activate==true)
								    config.setValue("activate", "true");
								  else
								    config.setValue("activate", "false");
								  config.saveFile();
								  //刷新菜单
								 
								  Workspace workspace = SparkManager.getWorkspace();
								  CommandPanel commandPanel = workspace.getCommandPanel();
								//  commandPanel.removeAll();
								//  functionBar = new JMenuBar();
								  addCustom();
								//  functionBar.repaint();
								
								  frame.dispose();
								  
								  //刷新设置页面
								  windowframe.dispose();
								  configDialog();
								
								}catch(Exception ex){ex.printStackTrace();}
								
							}
						 });
				        frame.add(button, BorderLayout.SOUTH);
				        frame.pack();
				        frame.setSize(600, 400);
				        GraphicUtils.centerWindowOnScreen(frame);
				        frame.setVisible(true);
					}
				 });
	           
	           //选择菜单按钮
		 	    subButton.addMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseClicked(MouseEvent e)
					{
					   for(int i = 0;i<checkBoxs.length;i++)
					   {
						   System.out.println(checkBoxs[i].getName()+"是否选中"+checkBoxs[i].isSelected());
						   boolean activate = checkBoxs[i].isSelected();
						   try{
						   ConfigurationUtil config = new ConfigurationUtil(checkBoxs[i].getName());
						   if(activate==true)
						    {
							   //  config.removeValue("activate");
							    config.setValue("activate", "true");
							    config.saveFile();
						    }
							  else
							  {
							    config.setValue("activate", "false");
							    config.saveFile();
							  }
//							  addCustom();
//							  //刷新设置页面
//							  windowframe.dispose();
						   
						   }catch(Exception ex){ex.printStackTrace();}
					   }
					      addCustom();
						  //刷新设置页面
						  windowframe.dispose();
					}
				 });
		 	    
	 		  }
	 	    catch (Exception ex) {ex.printStackTrace();}
	 		
	 	    
	 	     p.setSize(600, 400);
	 	     p.add(addButton);
	 	     p.add(subButton);
	 	     box.add(p);
	 	    
	        //   frame.add(new JScrollPane(box), BorderLayout.CENTER);
	        windowframe.setLayout(new FlowLayout());
	        windowframe.add(box);
	        windowframe.pack();
	        windowframe.setSize(600, 400);
	        GraphicUtils.centerWindowOnScreen(windowframe);
	        windowframe.setVisible(true);
		    
		
	}
	public void showDialog(String frameName,String urlStr)
	{
		    String nodeXml= getURLContent(urlStr);
		    CustomXMLParse c = new CustomXMLParse();
		    System.out.println(nodeXml);
		    List<TabEntity> list = c.xmlParse(nodeXml);
		    BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
	        final JFrame frame = new JFrame(frameName);
	        frame.setLayout(new BorderLayout());
	        frame.setIconImage(SparkManager.getApplicationImage().getImage());
//	        JPanel pane = new JPanel();
//	        pane.setBackground(Color.white);
//	        pane.setFont(new Font("Dialog", Font.PLAIN, 14));
//	        frame.add(new JScrollPane(pane), BorderLayout.CENTER);      
	        frame.setBackground(Color.WHITE);
	        JTabbedPane tabbedPane=new JTabbedPane();
	        tabbedPane.setTabPlacement(JTabbedPane.TOP);//设置标签置放位置。
	        
	        Object[][][] tableData = new Object[list.size()][][];
	        Object[][] columnTitle =  new Object[list.size()][];
	        final CustomJTable[] table =new CustomJTable[list.size()];
	        DefaultTableModel[] tableModel = new DefaultTableModel[list.size()];
	        System.out.println("tab 数量"+ list.size());
	        for(int i = 0 ;i < list.size();i++)
	        {
	        	TabEntity tabEntity = list.get(i);
	        	tableData[i] = new Object[][]{};
	        	columnTitle[i] = new Object[]{};
		    	
		    	table[i] = new CustomJTable(tableData[i] , columnTitle[i]);
		    	tableModel[i] = new DefaultTableModel(tableData[i],columnTitle[i]);
		    	table[i].setModel(tableModel[i]);
		    	
		    	MetadataEntity metadataEntity = tabEntity.getMetadata();
		        ContentEntity contentEntity = tabEntity.getContent();
		        List<ColumnEntity> columnList = metadataEntity.getColumuList();
		        List<RowEntity> rowList=contentEntity.getRowList();
		      
		        //列标题
		    	for(int j = 0 ;j<columnList.size();j++)
	    		{
		    		ColumnEntity columnEntity = columnList.get(j);  
		    		LinkedHashMap<String, String> columnLinkedHashMap = columnEntity.getColumnLinkedHashMap();
		    		tableModel[i].addColumn(columnLinkedHashMap.get("title"));
	    		}
		    	
		    	for(int j = 0 ;j<rowList.size();j++)
		    	{
		    		RowEntity rowEntity = rowList.get(j);
		    		LinkedHashMap<String, String> rowLinkedHashMap = rowEntity.getRowLinkedHashMap();
		    		Set<String> keys = rowLinkedHashMap.keySet();
		    		Object[] keyObj = keys.toArray();
		    		
		    		Object[] result  = new Object[keyObj.length];
		    		for(int k=0;k < keyObj.length;k++)
		    		{
		    			result[k] = rowLinkedHashMap.get(keyObj[k]);
		    		}
		    		 tableModel[i].addRow(result);  //new Object[]{rowLinkedHashMap.get(keyObj[k])}
		    	}
		    	
		    	
		    	
		    	hiddenColumn(0,table[i]);
				
		    	final int index = i;
				table[index].addMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseClicked(MouseEvent e)
					{
						if(e.getClickCount()==1)
							 return;
						 table[index].getSelectedColumn();
						 table[index].getSelectedRow();
						 String path = (String)table[index].getValueAt(table[index].getSelectedRow(),0);
						 System.out.println(table[index].getValueAt(table[index].getSelectedRow(),0));								    
						 SimpleDateFormat CREDENTIAL_FORMAT = new SimpleDateFormat("yyyyMMddHHmm");
					     Calendar now = Calendar.getInstance();
					     String minuteStr = CREDENTIAL_FORMAT.format(now.getTime());
				         String authenType = "CodedPwd";
				         PasswordMD5 passwordMD5 = PasswordMD5.getInstance();
				         String name = passwordMD5.getAdminName();
				         String password = passwordMD5.passwordMD5; 
				         String cre = passwordMD5.md5((minuteStr + password));
				         String link="?";
				         if(path.contains("?"))
				        	 link="&";
				         String urlStr = path +link +"p_username=" + name + "&p_password=" + cre + "&p_authen=" + authenType;
						 Runtime rt = Runtime.getRuntime();
					     try
					     {
					    	 Desktop.getDesktop().browse(new URI(urlStr));
//					      rt.exec("C:\\Program Files\\Internet Explorer\\iexplore.exe "+urlStr);
					     }
			             catch(Exception ex){ex.printStackTrace();}
					}
				});
		    	 tabbedPane.add(tabEntity.getTitle(), new JScrollPane(table[i]));
	        }
	        

	        tabbedPane.setBackground(Color.WHITE);
	       
	        frame.add(tabbedPane);
	       
//	        final JPanel mainPanel = new JPanel();
//	    	mainPanel.setLayout(new FlowLayout());
//	    	
//	    	final JOptionPane jpanel = new JOptionPane(tabbedPane, JOptionPane.PLAIN_MESSAGE,
//	    		JOptionPane.INFORMATION_MESSAGE, null, null, null);
//
//	    	mainPanel.add(jpanel);
//	        frame.setContentPane(mainPanel);
//	        frame.setLayout(new FlowLayout());
	        frame.pack();
	        frame.setSize(530, 360);
	       
	        GraphicUtils.centerWindowOnScreen(frame);
	        frame.setVisible(true);
	}
	
	/**
	 * Adds a button to each Chat Room that is opened.
	 */
	private void addChatRoomButton() {
		// Retrieve ChatManager from the SparkManager
		ChatManager chatManager = SparkManager.getChatManager();

		// Create a new ChatRoomButton.
		final ChatRoomButton button = new ChatRoomButton("Push Me");

		// Add to a new ChatRoom when the ChatRoom opens.
		chatManager.addChatRoomListener(new ChatRoomListenerAdapter() {
			public void chatRoomOpened(ChatRoom room) {
				room.getToolBar().addChatRoomButton(button);
			}

			public void chatRoomLeft(ChatRoom room) {
				room.getToolBar().removeChatRoomButton(button);
			}
		});
	}

	/**
	 * Listen for incoming transfer requests and either handle them yourself, or
	 * pass them off to be handled by the next listener. If no one handles it,
	 * then Spark will handle it.
	 */
	private void addTransferListener() {

		// Retrieve SparkTransferManager from the SparkManager.
		SparkTransferManager transferManager = SparkManager
				.getTransferManager();

		transferManager.addTransferListener(new FileTransferListener() {
			public boolean handleTransfer(FileTransferRequest request) {
				// If I wanted to handle it, take the request, accept it and get
				// the inputstream.

				// Otherwise, return false.
				return false;
			}
		});
	}

	/**
	 * Sends a file to a user in your ContactList.
	 */
	private void sendFile() {
		// Retrieve SparkTransferManager from the SparkManager.
		SparkTransferManager transferManager = SparkManager
				.getTransferManager();

		// In order to send a file to a person, you will need to know their full
		// Jabber
		// ID.

		// Retrieve the Jabber ID for a user via the UserManager. This can
		// return null if the user is not in the ContactList or is offline.
		UserManager userManager = SparkManager.getUserManager();
		// String jid = userManager.getJIDFromNickname("Matt");

		// if (jid != null) {
		// transferManager.sendFile(new File("MyFile.txt"), jid);
		// }
	}

	/**
	 * Controls the UI of a ContactItem.
	 */
	private void handleUIAndEventsOfContactItem() {

		ContactList contactList = SparkManager.getWorkspace().getContactList();

		ContactItem item = contactList
				.getContactItemByJID("paul@jivesoftware.com/spark");

		ContactItemHandler handler = new ContactItemHandler() {

			public boolean handlePresence(ContactItem item, Presence presence) {
				return false;
			}

			public Icon getIcon(String jid) {
				return null;
			}

			public Icon getTabIcon(Presence presence) {
				return null;
			}

			public boolean handleDoubleClick(ContactItem item) {
				return false;
			}

		};

		SparkManager.getChatManager().addContactItemHandler(handler);
	}

	/**
	 * Allows a plugin to be notified when the Spark users changes their
	 * presence.
	 */
	private void addPersonalPresenceListener() {
		SessionManager sessionManager = SparkManager.getSessionManager();

		sessionManager.addPresenceListener(new PresenceListener() {

			/**
			 * Spark user changed their presence.
			 * 
			 * @param presence
			 *            the new presence.
			 */
			public void presenceChanged(Presence presence) {

			}
		});
	}

	/**
	 * Installs a new MessageFilter.
	 */
	private void installMessageFilter() {
		System.out.println("消息拦截开启！");
		
		ChatManager chatManager = SparkManager.getChatManager();
        /*
		MessageFilter messageFilter = new MessageFilter() {

			public void filterOutgoing(ChatRoom room, Message message) {
			}

			public void filterIncoming(ChatRoom room, Message message) {
				String from = message.getFrom();
				String currentBody = message.getBody();
				System.out.println("来的消息过滤！"+"来自："+from+"  内容："+currentBody);
				System.out.println(message.toXML());
				if(message!=null && message.getProperty("notice")!=null)
				{
				 message.setBody("系统提示：你有新消息,请查收");
				 String num = message.getProperty("num")==null?"0":message.getProperty("num").toString();

				 JPanel commandPanelbroadcast = SparkManager.getWorkspace().getCommandPanel();
				 Component[] roolRolloverButtonsArr = commandPanelbroadcast.getComponents();
				 for(int i = 0 ;i< roolRolloverButtonsArr.length;i++)
				 {
					if(roolRolloverButtonsArr[i].getName()!=null&&roolRolloverButtonsArr[i].getName().equals(message.getProperty("notice")))
					{
						RolloverButton b =  (RolloverButton)roolRolloverButtonsArr[i];
						b.setText(num);
						
					}
				 }
				}
				
			}
		
		};

		
		
		chatManager.addMessageFilter(messageFilter);*/
		
		SessionManager m = SparkManager.getSessionManager();
		XMPPConnection c =  m.getConnection();
	
		PacketFilter packetFilter = new PacketTypeFilter(Message.class);
        c.addPacketListener(this, packetFilter);
	
		
		OfflineMessageManager offlineManager = new OfflineMessageManager(c);
		
		  try {  
	            Iterator<org.jivesoftware.smack.packet.Message> it = offlineManager.getMessages();  
	            System.out.println("离线消息数量: " + offlineManager.getMessageCount());  
	              
	            Map<String,ArrayList<Message>> offlineMsgs = new HashMap<String,ArrayList<Message>>();  
	              
	            while (it.hasNext()) {  
	                org.jivesoftware.smack.packet.Message message = it.next();  
	                /*
	                 * 离线的数量提醒可能已经不是最新的，并且我们发请求获取数据
	                if(message!=null && message.getProperty("notice")!=null)
					{
					  String num = message.getProperty("num")==null?"0":message.getProperty("num").toString();
					  JPanel commandPanelbroadcast = SparkManager.getWorkspace().getCommandPanel();
					  Component[] roolRolloverButtonsArr = commandPanelbroadcast.getComponents();
					  for(int i = 0 ;i< roolRolloverButtonsArr.length;i++)
					  {
						if(roolRolloverButtonsArr[i].getName()!=null&&roolRolloverButtonsArr[i].getName().equals(message.getProperty("notice")))
						{
							RolloverButton b =  (RolloverButton)roolRolloverButtonsArr[i];
							b.setText(num);
							
						}
					  }
					  
					}
	                else
	                {
	                  handleOfflineMessage(message);
	                }*/
	                if(message!=null && message.getProperty("notice")==null)
	                	 handleOfflineMessage(message);
	                
	            }  
	  
             
	            offlineManager.deleteMessages();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
		
		

	}
	
	
	private void handleOfflineMessage(Message message) {
        if(!ModelUtil.hasLength(message.getBody())){
            return;
        }

        String bareJID = StringUtils.parseBareAddress(message.getFrom());
        Workspace workspace = SparkManager.getWorkspace();
        
        ContactItem contact =workspace.getContactList().getContactItemByJID(bareJID);
        String nickname = StringUtils.parseName(bareJID);
        if (contact != null) {
            nickname = contact.getDisplayName();
        }

        ChatRoom room = SparkManager.getChatManager().createChatRoom(bareJID, nickname, nickname);
        if(!SparkManager.getChatManager().getChatContainer().getChatFrame().isVisible())
        {
            SparkManager.getChatManager().getChatContainer().getChatFrame().setVisible(true);
        }

        room.getTranscriptWindow().insertMessage(nickname, message, ChatManager.FROM_COLOR, Color.white);
        room.addToTranscript(message, true);

        // Send display and notified message back.
        SparkManager.getMessageEventManager().sendDeliveredNotification(message.getFrom(), message.getPacketID());
        SparkManager.getMessageEventManager().sendDisplayedNotification(message.getFrom(), message.getPacketID());
    }
	
	CustomJTable table;
	public void showMailDialog(String urlStr)
	{
		    String nodeXml= getURLContent(urlStr);
		    CustomXMLParse c = new CustomXMLParse();
		    List<CustomMail> list = c.xmlMail(nodeXml);
	        final JFrame frame = new JFrame("邮件");
	        frame.setLayout(new BorderLayout());
	        frame.setIconImage(SparkManager.getApplicationImage().getImage());
	        JPanel pane = new JPanel();
	        pane.setBackground(Color.white);
	        pane.setFont(new Font("Dialog", Font.PLAIN, 14));
	        frame.add(new JScrollPane(pane), BorderLayout.CENTER);

	        
	    	//定义二维数组作为表格数据
	    	Object[][] tableData = 
	    	{
//	    		new Object[]{"1","宝信" , 29 , "女"},
	    	
	    	};
	    	
	    	Object[] columnTitle = {"ID","发件人" , "主题" , "时间"};
	    	
	    	
	    	table = new CustomJTable(tableData , columnTitle);
	    	//table.setEnabled(false);
	    	//table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    	
	    	DefaultTableModel tableModel = new DefaultTableModel(tableData,columnTitle);
	    	table.setModel(tableModel);
	    	
	    	System.out.println("邮件数量 "+list.size());
	    	for(int i = 0 ;i<list.size();i++)
    		{
	    		CustomMail mail = (CustomMail)list.get(i);
	    		tableModel.addRow(new Object[]{mail.getMailId(),mail.getFrom(), mail.getSubject(), mail.getDate()});
	    
    		}
	    	
	    	hiddenColumn(0,table);
			
			table.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent e)
				{
					if(e.getClickCount()==1)
						 return;
					table.getSelectedColumn();
					table.getSelectedRow();
					String mailId = (String)table.getValueAt(table.getSelectedRow(),0);
					System.out.println(table.getValueAt(table.getSelectedRow(),0));
//					 String path = "http://localhost:8080/contactsweb/mailList.xml";
					 SimpleDateFormat CREDENTIAL_FORMAT = new SimpleDateFormat("yyyyMMddHHmm");
				     Calendar now = Calendar.getInstance();
				     String minuteStr = CREDENTIAL_FORMAT.format(now.getTime());
			         String authenType = "CodedPwd";
			         PasswordMD5 passwordMD5 = PasswordMD5.getInstance();
			         String name = passwordMD5.getAdminName();
			         String password = passwordMD5.passwordMD5;
			         String cre = passwordMD5.md5((minuteStr + password));
			         String urlStr = path + "?p_username=" + name + "&p_password=" + cre + "&p_authen=" + authenType+ "&mailId=" + mailId;
					 showMailDetailDialog(urlStr);
					
					
				}
			});
	    	
	    	frame.add(new JScrollPane(table));

	        frame.pack();
	        frame.setSize(600, 400);
            
	        GraphicUtils.centerWindowOnScreen(frame);
	        frame.setVisible(true);
	}
	
	
	CustomJTable detailTable;
	public void showMailDetailDialog(String urlStr)
	{
	    String nodeXml= getURLContent(urlStr);
	//    nodeXml = nodeXml.replaceAll("&", "&amp;");
	    CustomXMLParse c = new CustomXMLParse();
	    CustomMail customMail = c.xmlMailDetial(nodeXml);
        final JFrame frame = new JFrame("邮件详情");
        frame.setLayout(new BorderLayout());
        frame.setIconImage(SparkManager.getApplicationImage().getImage());
        JPanel pane = new JPanel();
        pane.setBackground(Color.white);
        pane.setFont(new Font("Dialog", Font.PLAIN, 14));
        frame.add(new JScrollPane(pane), BorderLayout.CENTER);
        
    	//定义二维数组作为表格数据
    	Object[][] tableData = 
    	{
    		new Object[]{customMail.getMailId(),customMail.getNodeId(),"发件人：",customMail.getFrom()},
    		new Object[]{customMail.getMailId(),customMail.getNodeId(),"收件人：",customMail.getRecipientTo()},
    		new Object[]{customMail.getMailId(),customMail.getNodeId(),"主  题：",customMail.getSubject()},
    		new Object[]{customMail.getMailId(),customMail.getNodeId(),"内   容：",""}
    	
    	};
    	
    	Object[] columnTitle = {"","","",""};
    	detailTable = new CustomJTable(tableData , columnTitle);
    	DefaultTableModel tableModel = new DefaultTableModel(tableData,columnTitle);
    	detailTable.setModel(tableModel);
    	detailTable.setShowGrid(false);
    	detailTable.setFont(new Font("宋体",Font.PLAIN,14));
    	hiddenColumn(0,detailTable);
    	hiddenColumn(1,detailTable);
    	//字体居中
    	DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        detailTable.getColumn("").setCellRenderer(render);
    	
    	TableColumn firsetColumn = detailTable.getColumnModel().getColumn(0);
    	firsetColumn.setPreferredWidth(100);
    	firsetColumn.setMaxWidth(100);
    	firsetColumn.setMinWidth(100);
    	
    	
    	frame.add(detailTable,BorderLayout.NORTH);
    	  //内容
    	   JEditorPane editorPane = new JEditorPane();
    	   editorPane = new JEditorPane();
    	   editorPane.setVisible(true);
    	   editorPane.setBackground(Color.white);
    	   editorPane.setContentType("text/html");
    	   editorPane.setEditable(false);
    	   JScrollPane scrollPane = new JScrollPane(editorPane);
    
    	   String con = customMail.getContent();
    	   con = con.replace("&lt;", "<");
    	   con = con.replace("&gt;", ">");
    	   con = con.replace("&amp;", "&");
    	   con = con.replace("&quot;", "'");
    	   
    	  
    	 //  con="<HEAD><TITLE>欢迎信</TITLE><META content='MSHTML 6.00.3790.1830' name=GENERATOR><STYLE></STYLE></HEAD><BODY style='PADDING-RIGHT: 0px; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-TOP: 0px' bgColor=#ffffff><TABLE style='FONT-SIZE: 12px; FONT-FAMILY: Arial' cellSpacing=0 cellPadding=0 width=804 align=center border=0>  <TBODY>  <TR>    <TD><IMG height=134 alt=139邮箱       src='cid:000301ccb96f$d3c0ad30$1b14a8c0@richinfotxg' width=804></TD></TR>  <TR>    <TD     style='PADDING-RIGHT: 48px; PADDING-LEFT: 48px; PADDING-BOTTOM: 0px; PADDING-TOP: 0px'     vAlign=top;>      <H1 style='FONT-SIZE: 18px; MARGIN: 0px'>欢迎您使用由中国移动提供的139邮箱</H1>      <P       style='FONT-SIZE: 12px; LINE-HEIGHT: 22px'>手机号码，就是邮箱帐号。最新支持电信、联通用户，也可以注册139邮箱。</P>      <P>您可以使用手机号@139.com或者别名@139.com作为你的邮箱地址，方便好记。</P>      <P>从现在开始，让我们一起享受139邮箱带给您的优质服务吧。 </P>      <H2       style='FONT-SIZE: 16px; MARGIN: 24px 0px; COLOR: green; TEXT-INDENT: 30px; LINE-HEIGHT: 24px; POSITION: relative'><IMG       style='LEFT: -30px; POSITION: absolute; TOP: -3px' height=25 alt=icon       src='cid:000401ccb96f$d3c2f720$1b14a8c0@richinfotxg'       width=24>电信、联通用户，也可以使用139邮箱：</H2>      <TABLE cellSpacing=0 cellPadding=0 border=0>        <TBODY>        <TR height=80>          <TD>            <TABLE>              <TBODY>              <TR>                <TD align=middle width=60><IMG                   src='cid:000501ccb96f$d3c2f720$1b14a8c0@richinfotxg'></TD>                <TD width=280>                  <H4 style='FONT-SIZE: 12px; MARGIN: 0px'>人性化的界面，便利的操作</H4>                  <P                   style='FONT-SIZE: 12px; MARGIN: 3px 0px; COLOR: #666; LINE-HEIGHT: 20px'>崭新视觉设计，支持换肤界面更清爽。<BR>支持多标签，可同时进行读信、写信多项操作。</P></TD></TR></TBODY></TABLE></TD>          <TD>            <TABLE>              <TBODY>              <TR>                <TD align=middle width=60><IMG                   src='cid:000601ccb96f$d3c2f720$1b14a8c0@richinfotxg'></TD>                <TD width=280>                  <H4 style='FONT-SIZE: 12px; MARGIN: 0px'>自动保存联系人</H4>                  <P                   style='FONT-SIZE: 12px; MARGIN: 3px 0px; COLOR: #666; LINE-HEIGHT: 20px'>发送邮件成功后，收件人自动保存到您的通讯录，<BR>方便您后续保持联系。</P></TD></TR></TBODY></TABLE></TD></TR>        <TR height=80>          <TD>            <TABLE>              <TBODY>              <TR>                <TD align=middle width=60><IMG                   src='cid:000701ccb96f$d3c2f720$1b14a8c0@richinfotxg'></TD>                <TD width=280>                  <H4 style='FONT-SIZE: 12px; MARGIN: 0px'>代收邮件</H4>                  <P                   style='FONT-SIZE: 12px; MARGIN: 3px 0px; COLOR: #666; LINE-HEIGHT: 20px'>简单一步：输入您其他邮箱的帐号、密码，<BR>即可在139邮箱收取您其他邮箱的邮件。                   </P></TD></TR></TBODY></TABLE></TD>          <TD>            <TABLE>              <TBODY>              <TR>                <TD align=middle width=60><IMG                   src='cid:000801ccb96f$d3c2f720$1b14a8c0@richinfotxg'></TD>                <TD width=280>                  <H4 style='FONT-SIZE: 12px; MARGIN: 0px'>邮件分拣</H4>                  <P                   style='FONT-SIZE: 12px; MARGIN: 3px 0px; COLOR: #666; LINE-HEIGHT: 20px'>新建文件夹，可接收指定联系人的邮件，<BR>提高您处理邮件的效率。</P></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>      <H2       style='FONT-SIZE: 16px; MARGIN: 14px 0px; COLOR: green; LINE-HEIGHT: 24px; POSITION: relative'>客户端设置：</H2>      <TABLE       style='BORDER-RIGHT: #e2e2e2 1px solid; BORDER-TOP: #e2e2e2 1px solid; BORDER-LEFT: #e2e2e2 1px solid; BORDER-BOTTOM: #e2e2e2 1px solid; BORDER-COLLAPSE: collapse; TEXT-ALIGN: center; border-spacing: 0'       cellSpacing=0 cellPadding=0 width='100%' border=0>        <TBODY>        <TR style='BACKGROUND: #3c9d3c'>          <TD           style='BORDER-RIGHT: #fff 1px solid; BORDER-TOP: #fff 1px solid; FONT-WEIGHT: bold; BORDER-LEFT: #fff 1px solid; COLOR: #fff; BORDER-BOTTOM: #fff 1px solid; HEIGHT: 28px'>IMAP服务器</TD>          <TD           style='BORDER-RIGHT: #fff 1px solid; BORDER-TOP: #fff 1px solid; FONT-WEIGHT: bold; BORDER-LEFT: #fff 1px solid; COLOR: #fff; BORDER-BOTTOM: #fff 1px solid'>POP3服务器</TD>          <TD           style='BORDER-RIGHT: #fff 1px solid; BORDER-TOP: #fff 1px solid; FONT-WEIGHT: bold; BORDER-LEFT: #fff 1px solid; COLOR: #fff; BORDER-BOTTOM: #fff 1px solid'>SMTP服务器</TD></TR>        <TR style='BACKGROUND: #cce6cb'>          <TD           style='BORDER-RIGHT: #fff 1px solid; BORDER-TOP: #fff 1px solid; BORDER-LEFT: #fff 1px solid; BORDER-BOTTOM: #fff 1px solid; HEIGHT: 32px'>imap.10086.cn</TD>          <TD           style='BORDER-RIGHT: #fff 1px solid; BORDER-TOP: #fff 1px solid; BORDER-LEFT: #fff 1px solid; BORDER-BOTTOM: #fff 1px solid'>pop.10086.cn</TD>          <TD           style='BORDER-RIGHT: #fff 1px solid; BORDER-TOP: #fff 1px solid; BORDER-LEFT: #fff 1px solid; BORDER-BOTTOM: #fff 1px solid'>smtp.10086.cn</TD></TR></TBODY></TABLE>      <P style='MARGIN: 22px 0px 12px; COLOR: #666; LINE-HEIGHT: 20px'>更多功能，等你发现       ...<BR>同时，我们将不定期把邮箱的特色功能亮点和使用技巧发送给您，使您在使用139邮箱的时候，事半功倍！</P></TD></TR>  <TR height=40>    <TD     style='PADDING-RIGHT: 48px; BORDER-TOP: #c2c9c1 1px solid; PADDING-LEFT: 48px; PADDING-BOTTOM: 0px; PADDING-TOP: 0px'>      <TABLE cellSpacing=0 cellPadding=0 width='100%' border=0>        <TBODY>        <TR>          <TD style='FONT-SIZE: 12px; COLOR: #023ca7'             width=200><STRONG>139邮箱</STRONG> <A style='COLOR: #023ca7'             href='http://mail.10086.cn/' target=_blank>mail.10086.cn</A></TD>          <TD style='FONT-SIZE: 12px; COLOR: #333'             align=right>如果您在使用139邮箱过程中遇到问题，请查看<A style='COLOR: #023ca7'             href='http://help.mail.10086.cn/statichtml/0/index.html'             target=_blank>帮助中心</A>，或致电10086</TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></BODY>" ;
    	   
    		   
    		   
    	   System.out.println("邮件内容："+con.toString() );
    	   editorPane.setText(con.toString());
    	   
    	   scrollPane.setPreferredSize(new Dimension(800,400));
    	   scrollPane.setMinimumSize(new Dimension(800,400));
    	   frame.add(scrollPane,BorderLayout.CENTER);
    	////////////////////////////////////////////////////////////////
    	   
    	  JLabel la = new JLabel("                                                                                                                     更多...");
    	  la.setBackground(Color.WHITE);
    	  la.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	  la.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent e)
				{
					 String mailId = (String)detailTable.getValueAt(1,0);
					 String nodeId = (String)detailTable.getValueAt(1,1);
					 String path = "http://localhost:8080/efmpx/CW/ML/CWML0018.jsp";					    
					 SimpleDateFormat CREDENTIAL_FORMAT = new SimpleDateFormat("yyyyMMddHHmm");
				     Calendar now = Calendar.getInstance();
				     String minuteStr = CREDENTIAL_FORMAT.format(now.getTime());
			         String authenType = "CodedPwd";
			         PasswordMD5 passwordMD5 = PasswordMD5.getInstance();
			         String name = passwordMD5.getAdminName();
			         String password = passwordMD5.passwordMD5;
			         String cre = passwordMD5.md5((minuteStr + password));
			         
			         String urlStr = path+"?p_username=" + name + "&p_password=" + cre + "&p_authen=" + authenType+"&mailId="+mailId+"&nodeId="+nodeId;		     
			         			        
			        Runtime rt = Runtime.getRuntime();
				    try
				    {
				    rt.exec("C:\\Program Files\\Internet Explorer\\iexplore.exe "+urlStr);
				    }
				    catch(Exception ex){ex.printStackTrace();}
				}
			});
    	 frame.add(la,BorderLayout.SOUTH);
    	   
    	   
        frame.pack();
        frame.setSize(800, 600);
        frame.setFont(new Font("宋体", Font.PLAIN, 14));
        GraphicUtils.centerWindowOnScreen(frame);
        frame.setVisible(true);
     
       
	}
	
	
	CustomJTable administrationTable;
	CustomJTable partyTable;
	public void showProccessDialog(String urlStr)
	{
		    String nodeXml= getURLContent(urlStr);
		    CustomXMLParse c = new CustomXMLParse();
		    List<CustomProccess> administrationList = c.xmlAdministrationProccess(nodeXml);
		    List<CustomProccess> partyList = c.xmlPartyProccess(nodeXml);
	        final JFrame frame = new JFrame("待办");
	        frame.setLayout(new BorderLayout());
	        
	        frame.setIconImage(SparkManager.getApplicationImage().getImage());
	        JPanel pane = new JPanel();
	        pane.setBackground(Color.white);
	        pane.setFont(new Font("Dialog", Font.PLAIN, 14));
	        frame.add(new JScrollPane(pane), BorderLayout.CENTER);

	        Object[][] tableData = {};
	    	Object[] columnTitle = {"Url", "标题" , "时间"};
	    	Object[][] partyTableData = {};
	    	Object[] partyColumnTitle = {"Url", "标题" , "时间"};
	    	
	    	
	    	administrationTable = new CustomJTable(tableData , columnTitle);
	    	partyTable = new CustomJTable(partyTableData , partyColumnTitle);
	    	administrationTable.setShowVerticalLines(false);
	    	partyTable.setShowVerticalLines(false);

	    	
	    	DefaultTableModel tableModel = new DefaultTableModel(tableData,columnTitle);
	    	administrationTable.setModel(tableModel);
	    	DefaultTableModel partyTableModel = new DefaultTableModel(partyTableData , partyColumnTitle);
	    	partyTable.setModel(partyTableModel);
	    	
	    
	    	for(int i = 0 ;i<administrationList.size();i++)
    		{
	    		CustomProccess administration = (CustomProccess)administrationList.get(i);
	    		tableModel.addRow(new Object[]{administration.getLinkUrl(),administration.getTitle(),administration.getShowDate()});
    		}
	    	for(int i = 0 ;i<partyList.size();i++)
	    	{
	    		CustomProccess party = (CustomProccess)partyList.get(i);
	    		partyTableModel.addRow(new Object[]{party.getLinkUrl(),party.getTitle(),party.getShowDate()});
	    	}
	    	
	    	hiddenColumn(0,administrationTable);
	    	hiddenColumn(0,partyTable);
			
	    	administrationTable.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent e)
				{
					if(e.getClickCount()==1)
						 return;
					 administrationTable.getSelectedColumn();
					 administrationTable.getSelectedRow();
					 String linkUrl = (String)administrationTable.getValueAt(administrationTable.getSelectedRow(),0);
					 System.out.println(administrationTable.getValueAt(administrationTable.getSelectedRow(),0));
					 String path = "http://localhost:8080/efmpx/EFMPX/IM/IMDocumentDetail.jsp";					    
					 SimpleDateFormat CREDENTIAL_FORMAT = new SimpleDateFormat("yyyyMMddHHmm");
				     Calendar now = Calendar.getInstance();
				     String minuteStr = CREDENTIAL_FORMAT.format(now.getTime());
			         String authenType = "CodedPwd";
			         PasswordMD5 passwordMD5 = PasswordMD5.getInstance();
			         String name = passwordMD5.getAdminName();
			         String password = passwordMD5.passwordMD5;
			         String cre = passwordMD5.md5((minuteStr + password));
			         String link = "&";
			         if(!linkUrl.contains("?"))
			        	 link="?";
			         
			      // String urlStr = "http://localhost:8080/efmpx/"+linkUrl+link+"p_username=" + name + "&p_password=" + cre + "&p_authen=" + authenType;
			         
			       //  linkUrl = linkUrl.substring(0,linkUrl.indexOf("&i-0-gridId"));
			      //   linkUrl.replace("<", "&lt;").replace(">", "&gt;").replace("&", "&amp;")
			      //   String urlStr = path+"?p_username=" + name + "&p_password=" + cre + "&p_authen=" + authenType+"&linkUrl="+linkUrl;
			         
			         String serviceName = linkUrl.substring( (linkUrl.indexOf("serviceName=")+"serviceName=".length()), linkUrl.indexOf("&efFormEname="));
			         String efFormEname = linkUrl.substring( (linkUrl.indexOf("&efFormEname=")+"&efFormEname=".length()), linkUrl.indexOf("&methodName="));
			         String methodName = linkUrl.substring( (linkUrl.indexOf("&methodName=")+"&methodName=".length()), linkUrl.indexOf("&i-0-fileGuid="));
			         String fileGuid = linkUrl.substring( (linkUrl.indexOf("&i-0-fileGuid=")+"&i-0-fileGuid=".length()), linkUrl.indexOf("&i-0-taskId="));
			         String taskId = linkUrl.substring( (linkUrl.indexOf("&i-0-taskId=")+"&i-0-taskId=".length()), linkUrl.indexOf("&i-0-gridId="));
			         String gridId = linkUrl.substring( (linkUrl.indexOf("&i-0-gridId=")+"&i-0-gridId=".length()));
			         
			         
			         String urlStr = path+"?p_username=" + name + "&p_password=" + cre + "&p_authen=" + authenType
			         + "&serviceName=" + serviceName+ "&efFormEname=" + efFormEname+ "&methodName=" + methodName+ "&fileGuid=" + fileGuid+ "&taskId=" + taskId;
			         
			        
			        Runtime rt = Runtime.getRuntime();
				    try
				    {
				    rt.exec("C:\\Program Files\\Internet Explorer\\iexplore.exe "+urlStr);
				    }
				    catch(Exception ex){ex.printStackTrace();}
				
				}
			});
	    	
	    	partyTable.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent e)
				{
					if(e.getClickCount()==1)
						 return;
					 partyTable.getSelectedColumn();
					 partyTable.getSelectedRow();
					 String linkUrl = (String)partyTable.getValueAt(partyTable.getSelectedRow(),0);
					 System.out.println(partyTable.getValueAt(partyTable.getSelectedRow(),0));
					 String path = "http://localhost:8080/efmpx/EFMPX/IM/IMDocumentDetail.jsp";					    
					 SimpleDateFormat CREDENTIAL_FORMAT = new SimpleDateFormat("yyyyMMddHHmm");
				     Calendar now = Calendar.getInstance();
				     String minuteStr = CREDENTIAL_FORMAT.format(now.getTime());
			         String authenType = "CodedPwd";
			         PasswordMD5 passwordMD5 = PasswordMD5.getInstance();
			         String name = passwordMD5.getAdminName();
			         String password = passwordMD5.passwordMD5;
			         String cre = passwordMD5.md5((minuteStr + password));
			    
			         String serviceName = linkUrl.substring( (linkUrl.indexOf("serviceName=")+"serviceName=".length()), linkUrl.indexOf("&efFormEname="));
			         String efFormEname = linkUrl.substring( (linkUrl.indexOf("&efFormEname=")+"&efFormEname=".length()), linkUrl.indexOf("&methodName="));
			         String methodName = linkUrl.substring( (linkUrl.indexOf("&methodName=")+"&methodName=".length()), linkUrl.indexOf("&i-0-fileGuid="));
			         String fileGuid = linkUrl.substring( (linkUrl.indexOf("&i-0-fileGuid=")+"&i-0-fileGuid=".length()), linkUrl.indexOf("&i-0-taskId="));
			         String taskId = linkUrl.substring( (linkUrl.indexOf("&i-0-taskId=")+"&i-0-taskId=".length()), linkUrl.indexOf("&i-0-gridId="));
			         String gridId = linkUrl.substring( (linkUrl.indexOf("&i-0-gridId=")+"&i-0-gridId=".length()));
			         
			         
			         String urlStr = path+"?p_username=" + name + "&p_password=" + cre + "&p_authen=" + authenType
			         + "&serviceName=" + serviceName+ "&efFormEname=" + efFormEname+ "&methodName=" + methodName+ "&fileGuid=" + fileGuid+ "&taskId=" + taskId;
			         
			        
			        Runtime rt = Runtime.getRuntime();
				    try
				    {
				    rt.exec("C:\\Program Files\\Internet Explorer\\iexplore.exe "+urlStr);
				    }
				    catch(Exception ex){ex.printStackTrace();}
				
				}
			});
	    	JTabbedPane tabbedPane=new JTabbedPane();
	        tabbedPane.setTabPlacement(JTabbedPane.TOP);//设置标签置放位置。
	        tabbedPane.add("  行     政  ", new JScrollPane(administrationTable));
	        tabbedPane.add("  党     委  ", new JScrollPane(partyTable));

	        //frame.add(new JScrollPane(administrationTable));
	        tabbedPane.setBackground(Color.WHITE);
	        frame.add(tabbedPane);

	        frame.pack();
	        frame.setSize(600, 400);
            
	        GraphicUtils.centerWindowOnScreen(frame);
	        frame.setVisible(true);
	}
	
	
	
	 public String getURLContent(String path)
     {   	 
	     String detail = "";
    	 try
	 		{
    		 URL url = new URL(path);

				HttpURLConnection connect = (HttpURLConnection) url
						.openConnection();
				connect.setDoOutput(true);
				connect.setRequestMethod("GET");
				connect.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
				connect.connect();

				InputStream is = connect.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, "utf-8"));
				String strLine = null;
				
				while ((strLine = br.readLine()) != null ) {
					detail += strLine;
				}
				br.close();
				is.close();
				connect.disconnect();
	 		}
	 		catch (Exception ex) {
				ex.printStackTrace();
			}
	 		System.out.println(path+"   "+detail);
            return detail;
	 		
     }

	 public  void hiddenColumn(int columnIndex, JTable table){   
	        TableColumnModel tcm=table.getColumnModel();   
	        TableColumn tc=tcm.getColumn(columnIndex);   
	        tc.setWidth(0);   
	        tc.setPreferredWidth(0);   
	        tc.setMaxWidth(0);   
	        tc.setMinWidth(0);   
	        table.getTableHeader().getColumnModel().getColumn(columnIndex).setMaxWidth(0);   
	        table.getTableHeader().getColumnModel().getColumn(columnIndex).setMinWidth(0);   
	    }
	/**
	 * Creates a person to person Chat Room and makes it the active chat.
	 */
	private void createPersonToPersonChatRoom() {

		// Get the ChatManager from Sparkmanager
		ChatManager chatManager = SparkManager.getChatManager();

		// Create the room.
		ChatRoom chatRoom = chatManager.createChatRoom("don@jivesoftware.com",
				"Don The Man", "The Chat Title");

		// If you wish to make this the active chat room.

		// Get the ChatRooms UI (This is the container for all ChatRooms)
		ChatContainer chatContainer = chatManager.getChatContainer();

		// Ask the ChatRooms container to make this chat the active chat.
		chatContainer.activateChatRoom(chatRoom);
	}

	/**
	 * Creates a person to person Chat Room and makes it the active chat.
	 */
	private void createConferenceRoom() {

		// Get the ChatManager from Sparkmanager
		ChatManager chatManager = SparkManager.getChatManager();

		Collection serviceNames = null;

		// Get the service name you wish to use.
		try {
			serviceNames = MultiUserChat.getServiceNames(SparkManager
					.getConnection());
		} catch (XMPPException e) {
			e.printStackTrace();
		}

		// Create the room.
		ChatRoom chatRoom = chatManager.createConferenceRoom("BusinessChat",
				(String) serviceNames.toArray()[0]);

		// If you wish to make this the active chat room.

		// Get the ChatRooms UI (This is the container for all ChatRooms)
		ChatContainer chatContainer = chatManager.getChatContainer();

		// Ask the ChatRooms container to make this chat the active chat.
		chatContainer.activateChatRoom(chatRoom);
	}

	public void uninstall() {
		// Remove all resources and components.
	}
	public void processPacket(final Packet packet) {
	   	 if (packet instanceof Message) {
	            final Message message = (Message)packet;
	            if (message!=null && message.getProperty("notice")!=null) {
	            	String number = (String)message.getProperty("num");
	            	if(number ==null||Integer.valueOf(number)<=0)
	            		return;
	           
	            SparkToaster toaster = new SparkToaster();
	            toaster.setCustomAction(new AbstractAction() {
					private static final long serialVersionUID = -2759404307378067515L;

					public void actionPerformed(ActionEvent actionEvent) {
						 String path = 	(String)message.getProperty("url");				    
						 SimpleDateFormat CREDENTIAL_FORMAT = new SimpleDateFormat("yyyyMMddHHmm");
					     Calendar now = Calendar.getInstance();
					     String minuteStr = CREDENTIAL_FORMAT.format(now.getTime());
				         String authenType = "CodedPwd";
				         PasswordMD5 passwordMD5 = PasswordMD5.getInstance();
				         String name = passwordMD5.getAdminName();
				         String password = passwordMD5.passwordMD5;
				         String cre = passwordMD5.md5((minuteStr + password));
				         
				          String urlStr = path+"?p_username=" + name + "&p_password=" + cre + "&p_authen=" + authenType;		     
				         // System.out.println("请求地址==== ："+urlStr);        
					        Runtime rt = Runtime.getRuntime();
						    try
						    {
						    rt.exec("C:\\Program Files\\Internet Explorer\\iexplore.exe "+urlStr);
						    }
						    catch(Exception ex){ex.printStackTrace();}
	                }
	            });

	             String num = message.getProperty("num")==null?"0":message.getProperty("num").toString();
				 JPanel commandPanelbroadcast = SparkManager.getWorkspace().getCommandPanel();
				 Component[] roolRolloverButtonsArr = commandPanelbroadcast.getComponents();
				 String toolTip="";
				 for(int i = 0 ;i< roolRolloverButtonsArr.length;i++)
				 {
					if(roolRolloverButtonsArr[i].getName()!=null&&roolRolloverButtonsArr[i].getName().equals(message.getProperty("notice")))
					{
						RolloverButton b =  (RolloverButton)roolRolloverButtonsArr[i];
						b.setText(num);
						toolTip = b.getToolTipText();
					}
				 }
				 
	            toaster.setDisplayTime(3000000);
	            toaster.setBorder(BorderFactory.createBevelBorder(0));
	            toaster.setToasterHeight(150);
	            toaster.setToasterWidth(200);
	            toaster.setTitle("系统消息");
	            if(toolTip==null || toolTip.trim().length()==0)
	            	toolTip = "消息";
	            toaster.showToaster(null,"系统提示:你有新"+toolTip+",请查收!");
	          }
	        }
			
		}
}
