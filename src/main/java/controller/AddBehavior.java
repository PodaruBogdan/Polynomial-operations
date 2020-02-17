package controller;

import view.*;

import java.awt.Color;
import java.awt.event.*;

import model.*;

public class AddBehavior {
	private AddPanel _viewAdd;

	public AddBehavior(AddPanel _viewAdd) {
		this._viewAdd = _viewAdd;
		_viewAdd.addAddListener(new AddBtnListener());
	}

	class AddBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String pol1 = _viewAdd.getP1tf().getText();
			Polinom p1 = new Polinom(pol1);
			String pol2 = _viewAdd.getP2tf().getText();
			if (!pol1.equals("") && !pol2.equals("")) {
				String result = p1.sum(pol2);
				_viewAdd.setResult(result);
			} else {
				CustomMonitor.display("Date invalide pentru adunare!", Color.blue);
			}

		}
	}

}
