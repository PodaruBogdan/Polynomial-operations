package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class SubPanel extends JPanel {
	private JButton subBtn;
	private JLabel p1;
	private JLabel p2;
	private JTextField p1tf;
	private JTextField p2tf;
	private JTextField result;

	public SubPanel() {
		this.setLayout(new GridLayout(2, 1));
		JPanel half = new JPanel();
		half.setLayout(new GridLayout(4, 1));
		JPanel comp1 = new JPanel();
		JPanel comp2 = new JPanel();
		JPanel comp3 = new JPanel();
		p1 = new JLabel("Polinom 1 : ");
		p2 = new JLabel("Polinom 2 : ");
		p1tf = new JTextField(25);
		p2tf = new JTextField(25);
		subBtn = new JButton("Calculate Sub");
		result=new JTextField(50);
		result.setEditable(false);;
		comp1.add(p1);
		comp1.add(p1tf);
		comp2.add(p2);
		comp2.add(p2tf);
		comp3.setLayout(new FlowLayout(FlowLayout.LEFT));
		comp3.add(Box.createRigidArea(new Dimension(30, 0)));
		comp3.add(subBtn);
		comp3.add(result);
		half.add(Box.createRigidArea(new Dimension(10, 10)));
		half.add(comp1);
		half.add(comp2);
		half.add(comp3);
		this.add(half);

	}

	public void addSubListener(ActionListener mal) {
		subBtn.addActionListener(mal);
	}

	public JTextField getP1tf() {
		return p1tf;
	}

	public JTextField getP2tf() {
		return p2tf;
	}

	public void setResult(String result) {
		this.result.setText(result); 
	}
	
}
