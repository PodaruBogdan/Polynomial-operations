package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class MainGUI extends JFrame {
	public MainGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel content = new JPanel();// continutul frame-ului
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel p2 = new JPanel();
		p2.setBorder(new LineBorder(Color.LIGHT_GRAY, 5));
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		CustomMonitor msgMonitor = new CustomMonitor();// panou care afiseaza mesaje
		SplitPane splitPane = new SplitPane();// meniul+panourile cu oparatii
		p1.add(splitPane.getSplitPane());
		p2.add(msgMonitor);
		content.add(p1);
		content.add(p2);
		this.getContentPane().add(content);// reda continut
		this.pack();
		this.setVisible(true);
	}
}
