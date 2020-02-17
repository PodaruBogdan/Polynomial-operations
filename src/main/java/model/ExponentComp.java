package model;

import java.util.Comparator;

public class ExponentComp implements Comparator<Monom> {

	@Override
	public int compare(Monom arg0, Monom arg1) {
		// TODO Auto-generated method stub
		if (arg0.getExp() > arg1.getExp()) {
			return -1;
		} else {
			return 1;
		}

	}

}
