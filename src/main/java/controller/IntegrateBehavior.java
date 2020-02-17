package controller;

import view.*;

import java.awt.Color;
import java.awt.event.*;

import model.*;

public class IntegrateBehavior {
	private IntegralPanel _viewIntgr;

	public IntegrateBehavior(IntegralPanel _viewIntgr) {
		this._viewIntgr = _viewIntgr;

		_viewIntgr.addItgrListener(new ItgrBtnListener());
	}

	class ItgrBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String pol1 = _viewIntgr.getP1tf().getText();
			Polinom p1 = new Polinom(pol1);
			if (!pol1.equals("")) {
				String result = p1.itgr();
				_viewIntgr.setResult(result);
			} else {
				CustomMonitor.display("Date invalide pentru integrare!", Color.blue);
			}
		}
	}

}
