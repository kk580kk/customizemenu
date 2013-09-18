package com.jivesoftware.spark.customizemenu;

import javax.swing.Icon;

class ImagedComboBoxItem {
    private Icon icon = null;
    private String text = null;
    private int indent = 0;

    ImagedComboBoxItem(String text, Icon icon, int indent) {
        this.text = text;
        this.icon = icon;
        this.indent = indent;
    }

    public String getText() {
        return text;
    }

    public Icon getIcon() {
        return icon;
    }

    public int getIndent() {
        return indent;
    }
}