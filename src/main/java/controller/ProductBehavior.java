package controller;

import view.*;

import java.awt.Color;
import java.awt.event.*;

import model.*;

public class ProductBehavior {
	private ProductPanel _viewPrd;

	public ProductBehavior(ProductPanel _viewPrd) {
		this._viewPrd = _viewPrd;
		_viewPrd.addPrdListener(new PrdListener());
	}

	class PrdListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String pol1 = _viewPrd.getP1tf().getText();
			Polinom p1 = new Polinom(pol1);
			String pol2 = _viewPrd.getP2tf().getText();
			if (!pol1.equals("") && !pol2.equals("")) {
				String result = p1.times(pol2);
				_viewPrd.setResult(result);
			} else {
				CustomMonitor.display("Date invalide pentru inmultire!", Color.blue);
			}
		}
	}

}
