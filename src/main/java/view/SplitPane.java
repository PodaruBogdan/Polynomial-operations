package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import controller.*;
import javax.swing.*;
import javax.swing.event.*;

public class SplitPane extends JPanel implements ListSelectionListener {

	private JPanel currentOpPanel;//panoul care se afiseaza in functie de operatia selectata
	private JList<String> opList;//lista operatiilor disponibile
	private JSplitPane splitPane;//panou format din 2 jumatati cu reglaj de frontiera
	private String[] ops = { "Adunare", "Scadere", "Inmultire", "Impartire", "Derivare", "Integrare" };
	private Dimension minimumSize;
	private JScrollPane opScrollPane;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//preia rezolutia ecranului	
	public SplitPane() {
		opList = new JList<String>(ops);
		Font myFont = new Font("Bitstream Vera Sans", Font.BOLD, 20);
		opList.setFont(myFont);
		opList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//o singura operatie o data
		opList.setSelectedIndex(0);//selecteaza "Adunare" la incarcarea panoului
		opList.addListSelectionListener(this);

		opScrollPane = new JScrollPane(opList);//meniul de operatii interactiv
		currentOpPanel = new JPanel();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, opScrollPane, currentOpPanel);//injumatatirea se face pe orizontala
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(150);//frontiera se afla la 150 pixeli de marginea stanga

		minimumSize = new Dimension(100, 40);
		opScrollPane.setMinimumSize(minimumSize);
		currentOpPanel.setMinimumSize(minimumSize);
		splitPane.setPreferredSize(new Dimension(screenSize.width/2, screenSize.height/4));
		updatePanel(ops[opList.getSelectedIndex()]);
	}

	public void valueChanged(ListSelectionEvent e) {
		JList list = (JList) e.getSource();
		updatePanel(ops[list.getSelectedIndex()]);//listener pentru selectarea unei operatii din meniu
	}
	/**
	 * <h1>updatePanel</h1></br>
	 * protected void updatePanel(String name)
	 * <p>
	 * Seteaza continutul unui panou in functie de name.
	 * </p>
	 * @return void
	 * */	
	protected void updatePanel(String name) {
		switch (name) {
		case "Adunare":
			AddPanel ap=new AddPanel();
			new AddBehavior(ap);
			currentOpPanel = ap;
			break;
		case "Scadere":
			SubPanel sp=new SubPanel();
			new SubBehavior(sp);
			currentOpPanel = sp;
			break;
		case "Inmultire":
			ProductPanel pp=new ProductPanel();
			new ProductBehavior(pp);
			currentOpPanel = pp;
			break;
		case "Impartire":
			DividePanel dp=new DividePanel();
			new DivideBehavior(dp);
			currentOpPanel = dp;
			break;
		case "Derivare":
			DerivativePanel der=new DerivativePanel();
			new DerivativeBehavior(der);
			currentOpPanel = der;
			break;
		case "Integrare":
			IntegralPanel ip=new IntegralPanel();
			new IntegrateBehavior(ip);
			currentOpPanel = ip;
			break;
		default:
			break;
		}
		splitPane.setDividerLocation(150);
		splitPane.setRightComponent(currentOpPanel);
	}

	public JList<String> getOpList() {
		return opList;
	}

	public JSplitPane getSplitPane() {
		return splitPane;
	}

}
