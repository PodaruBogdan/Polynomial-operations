package controller;

import view.*;

import java.awt.Color;
import java.awt.event.*;

import model.*;

public class DivideBehavior {
	private DividePanel _viewDiv;

	public DivideBehavior(DividePanel _viewDiv) {
		this._viewDiv = _viewDiv;
		_viewDiv.addDivListener(new BtnDivListener());
	}

	class BtnDivListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String pol1 = _viewDiv.getP1tf().getText();
			Polinom p1 = new Polinom(pol1);
			String pol2 = _viewDiv.getP2tf().getText();
			if (pol1.equals("0") && pol2.equals("0")) {
				CustomMonitor.display("Impartirea 0 / 0 nu e permisa!", Color.RED);
			} else if (!pol1.equals("") && !pol2.equals("")) {
				String result = p1.div(pol2);
				_viewDiv.setResult(result);
			} else {
				CustomMonitor.display("Date invalide pentru impartire!", Color.blue);
			}
		}
	}

}
