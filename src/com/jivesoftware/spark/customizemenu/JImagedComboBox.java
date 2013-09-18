package com.jivesoftware.spark.customizemenu;

import java.util.*;

import java.awt.*;
import javax.swing.*;

public class JImagedComboBox extends JComboBox {
    public JImagedComboBox(Vector values) {
        super(values);
        ListCellRenderer renderer = new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(
                JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof ImagedComboBoxItem) {
                    ImagedComboBoxItem item = (ImagedComboBoxItem) value;
                    this.setText(item.getText());
                    this.setIcon(item.getIcon());
                    if (isPopupVisible()) {
                       // int offset = 10 * item.getIndent();
                    	 int offset = 0;
                        this.setBorder(BorderFactory.createEmptyBorder(0, offset, 0, 0));
                    }
                }
                return this;
            }
        };
        this.setRenderer(renderer);
    }
}