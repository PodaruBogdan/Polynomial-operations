package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class IntegralPanel extends JPanel {
	private JButton itgrBtn;
	private JLabel p1;
	private JTextField p1tf;
	private JTextField result;

	public IntegralPanel() {
		this.setLayout(new GridLayout(2, 1));
		JPanel half = new JPanel();
		half.setLayout(new GridLayout(4, 1));
		JPanel comp1 = new JPanel();
		JPanel comp2 = new JPanel();
		JPanel comp3 = new JPanel();
		p1 = new JLabel("Polinom : ");
		p1tf = new JTextField(25);
		itgrBtn = new JButton("Calculate integral");
		result=new JTextField(50);
		result.setEditable(false);;
		comp1.add(p1);
		comp1.add(p1tf);
		comp3.setLayout(new FlowLayout(FlowLayout.LEFT));
		comp3.add(Box.createRigidArea(new Dimension(30, 0)));
		comp3.add(itgrBtn);
		comp3.add(result);
		half.add(Box.createRigidArea(new Dimension(10, 10)));
		half.add(comp1);
		half.add(comp2);
		half.add(comp3);
		this.add(half);

	}

	public void addItgrListener(ActionListener mal) {
		itgrBtn.addActionListener(mal);
	}

	public JTextField getP1tf() {
		return p1tf;
	}
	public void setResult(String result) {
		this.result.setText(result); 
	}
	
}
