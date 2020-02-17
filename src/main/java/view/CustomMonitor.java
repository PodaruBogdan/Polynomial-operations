package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;

public class CustomMonitor extends JPanel{
	private static JTextField msg=new JTextField(60);
	public CustomMonitor() {
		msg.setEditable(true);
		msg.setBackground(this.getBackground());
		msg.setHorizontalAlignment(JTextField.CENTER);
		msg.setEditable(false);
		msg.setBorder(null);
		this.setLayout(new FlowLayout());
		this.add(msg);
	}
	public static void display(String temp,Color color) {
		msg.setText(temp);
		msg.setForeground(color);
	}
}
