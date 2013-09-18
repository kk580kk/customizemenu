/*package com.jivesoftware.spark.customizemenu;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
public class SimpleWebBrowserExample {
  public static JComponent createContent(String url) {
    JPanel contentPane = new JPanel(new BorderLayout());
    JPanel webBrowserPanel = new JPanel(new BorderLayout());
    webBrowserPanel.setBorder(null);
    final JWebBrowser webBrowser = new JWebBrowser();
    webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
    webBrowser.navigate(url);
    contentPane.add(webBrowserPanel, BorderLayout.CENTER);
    webBrowser.setLocationBarVisible(false);
    webBrowser.setStatusBarVisible(false);
    webBrowser.setButtonBarVisible(false);
    webBrowser.setMenuBarVisible(false);
    return contentPane;
  }
  public static void addwebbrowser(final String url,final String title) {
    NativeInterface.open();
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
    	BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
        JFrame frame = new JFrame(title);
        frame.getContentPane().add(createContent(url), BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
      }
    });
    NativeInterface.runEventPump();
  }

}
*/