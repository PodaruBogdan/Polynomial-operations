package controller;

import view.*;

import java.awt.Color;
import java.awt.event.*;

import model.*;

public class DerivativeBehavior {
	private DerivativePanel _viewDer;

	public DerivativeBehavior(DerivativePanel _viewDer) {
		this._viewDer = _viewDer;

		_viewDer.addDerListener(new DerBtnListener());
	}

	class DerBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String pol1 = _viewDer.getP1tf().getText();
			Polinom p1 = new Polinom(pol1);
			if (!pol1.equals("")) {
				String result = p1.der();
				_viewDer.setResult(result);
			} else {
				CustomMonitor.display("Date invalide pentru derivare!", Color.blue);
			}
		}
	}

}
