package com.jivesoftware.spark.customizemenu.resource;

import java.net.URL;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;

import org.jivesoftware.spark.util.log.Log;


public class CustomizeMenuRes {
	 private static PropertyResourceBundle prb;

	    static ClassLoader cl = CustomizeMenuRes.class.getClassLoader();

	    static {
	        prb = (PropertyResourceBundle) ResourceBundle.getBundle("customizemenu");
	    }

	    public static final String getString(String propertyName) {
	        try {
	            return prb.getString(propertyName);
	        } catch (Exception e) {
	            Log.error(e);
	            return propertyName;
	        }
	    }
	    
	    public static final ImageIcon getImageIcon(String imageName) {
	        try {
	            final String iconURI = getString(imageName);
	            final URL imageURL = cl.getResource(iconURI);
	            return new ImageIcon(imageURL);
	        }
	        catch (Exception ex) {
	            System.out.println(imageName + " not found.");
	        }
	        return null;
	    }
}
