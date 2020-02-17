package controller;

import view.*;

import java.awt.Color;
import java.awt.event.*;

import model.*;

public class SubBehavior {
	private SubPanel _viewSub;

	public SubBehavior(SubPanel _viewSub) {
		this._viewSub = _viewSub;
		_viewSub.addSubListener(new BtnSubListener());
	}

	class BtnSubListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String pol1 = _viewSub.getP1tf().getText();
			Polinom p1 = new Polinom(pol1);
			String pol2 = _viewSub.getP2tf().getText();
			if (!pol1.equals("") && !pol2.equals("")) {
				String result = p1.sub(pol2);
				_viewSub.setResult(result);
			} else {
				CustomMonitor.display("Date invalide pentru scadere!", Color.blue);
			}
		}
	}

}
