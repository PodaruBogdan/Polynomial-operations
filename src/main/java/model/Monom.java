package model;

public class Monom {
	private String rawString;// reprezentarea String a monomului
	private int coeff;
	private int exp;

	public Monom(String raw) {
		this.rawString = raw;
		process(raw);
		if (rawString.charAt(0) != '-' && rawString.charAt(0) != '+') {
			rawString = "+" + rawString;// pune semnul + pentru coeficienti pozitivi
		}
	}

	public void setRawString(String rs) {
		this.rawString = rs;
	}

	// O serie de getters si setters
	public int getCoeff() {
		return coeff;
	}

	public String getRawString() {
		return rawString;
	}

	public void setExp(int exp) {
		this.exp = exp;
		updateRaw(coeff, exp);
	}

	public int getExp() {
		return exp;
	}

	/**
	 * <h1>process</h1></br>
	 * public void process(String raw)
	 * <p>
	 * Seteaza coeficientul si exponentul unui monom reprezentat ca String.
	 * </p>
	 * 
	 * @param raw - reprezentarea String a monomului
	 * @return void
	 */
	private void process(String raw) {
		int co = getCoeff(raw);
		if (co != Integer.MAX_VALUE) {// o preluare esuata returneaza MAX_VALUE al int
			this.coeff = co;
		}
		int exp = getExponent(raw);
		if (exp != Integer.MIN_VALUE) {
			this.exp = exp;
		}
	}

	/**
	 * <h1>getCoeff</h1></br>
	 * private int getCoeff(String raw)
	 * <p>
	 * Extrage coeficientul ca si intreg dintr-un monom reprezentat ca String.
	 * </p>
	 * 
	 * @param raw - reprezentarea String a monomului
	 * @return coeficientul intreg
	 */
	private int getCoeff(String raw) {
		int co = Integer.MAX_VALUE;// presupunem ca utilizatorul a introdus date sub format gresit
		try {
			co = Integer.parseInt(raw);// raw e doar exponentul
		} catch (NumberFormatException e) {
			int posOfX = raw.indexOf('x');// raw contine si o putere a lui x
			if (posOfX == 0) {
				co = 1;
			}
			if (posOfX > 0) {
				String beforeX = raw.substring(0, posOfX);// obtine coeficientul diferit de 1
				try {
					co = Integer.parseInt(beforeX);// nu are caracterul '+' in fata coeff
				} catch (NumberFormatException e2) {
					String beforeXSigned = raw.substring(1, posOfX);
					if (raw.charAt(0) == '-' && raw.charAt(1) == 'x') // stabileste semnul
						co = -1;
					else if (raw.charAt(0) == '+' && raw.charAt(1) == 'x')
						co = 1;
					else
						co = -Integer.parseInt(beforeXSigned);

				}
			}
		}
		return co;
	}

	/**
	 * <h1>getExponent</h1></br>
	 * private int getExponent(String raw)
	 * <p>
	 * Extrage exponentul ca si intreg dintr-un monom reprezentat ca String.
	 * </p>
	 * 
	 * @param raw - reprezentarea String a monomului
	 * @return exponentul intreg
	 */
	private int getExponent(String raw) {
		int posOfX = raw.indexOf('x');
		if (posOfX != -1) {
			int ind = raw.indexOf('^');// cauta daca are putere
			if (ind == -1) {
				return 1;
			} else {// are putere diferita de 1 si 0
				String afterExp = raw.substring(ind + 1, raw.length());// preia puterea ca String
				int e = Integer.parseInt(afterExp);// conversia la intreg
				return e;

			}
		} else {
			return Integer.MIN_VALUE;
		}
	}

	/**
	 * <h1>add</h1></br>
	 * public void add(Monom a)
	 * <p>
	 * Aduna un monom la monomul this si updateaza datele lui this.
	 * </p>
	 * 
	 * @param a - obiectul de tip monom care se aduna la this
	 * @return void
	 */
	public void add(Monom a) {
		this.coeff = a.coeff + this.coeff;
		updateRaw(coeff, exp);// update reprezentare string a monomului
	}

	public String toString() {
		return "Monom : " + this.rawString + " has coeff : " + this.coeff + " and exp : " + this.exp;
	}

	/**
	 * <h1>updateRaw</h1></br>
	 * public void updateRaw(int coeff, int exp)
	 * <p>
	 * Updateaza coeficientul si exponentul din reprezentarea String a monomului .
	 * </p>
	 * 
	 * @param coeff - coeficientul de schimbat
	 * @param exp   - exponentul de schimbat
	 * @return void
	 */
	public void updateRaw(int coeff, int exp) {
		String temp = "";
		if (coeff != 1) {
			if (coeff > 0) {
				temp += "+";// seteaza semnul + pentru monoamele care au coeficienti pozitivi
			}
			if (coeff != 0) {
				String co = String.valueOf(coeff);
				if (co.equals("-1")) {
					temp += "-";
				} else
					temp += co;// constructia incepe cu coeficientul
				if (exp > 1 || exp < 0) {// avem exponent diferita de 1 si 0
					String ex = String.valueOf(exp);
					temp += "x^" + ex;// constructia continua cu formatul "^exponent"
				} else
					temp += (exp == 0) ? "" : "x";// daca exponentul e 0 ramane doar coeficientul,daca e 1 constructia
													// continua cu "x"
			} else
				temp = "0";// coeficient 0 rezulta in monom = 0
		} else {// coeficient 1,se verifica aceeasi pasi ca mai sus
			temp += "+";
			if (exp != 1 && exp != 0) {
				String ex = String.valueOf(exp);
				temp += "x^" + ex;
			} else
				temp += (exp == 0) ? "1" : "x";
		}
		this.rawString = temp;
	}

	/**
	 * <h1>changeSign</h1></br>
	 * public void changeSign()
	 * <p>
	 * Schimba semnul monomului(semnul coeficientului).
	 * </p>
	 * 
	 * @return void
	 */
	public void changeSign() {
		coeff = -coeff;
		updateRaw(coeff, exp);
	}

	/**
	 * <h1>der</h1></br>
	 * public void der()
	 * <p>
	 * Deriveaza monomul this conform relatiei matematice. Updateaza si
	 * reprezentarea String a monomului.
	 * </p>
	 * 
	 * @return void
	 */
	public void der() {
		if (exp == 0) {
			coeff = 0;// derivata unui numar e 0
		} else {
			coeff = coeff * exp;// (ax^b)'=abx^b-1
			exp--;
		}
		updateRaw(coeff, exp);
	}

	/**
	 * <h1>itgr</h1></br>
	 * public void itgr()
	 * <p>
	 * Integreaza monomul this conform relatiei matematice. Updateaza si
	 * reprezentarea String a monomului.
	 * </p>
	 * 
	 * @return void
	 */
	public void itgr() {
		exp++;
		coeff = coeff / exp;// integrala(ax^b)=(a/b+1)x^(b+1)
		updateRaw(coeff, exp);
	}

	/**
	 * <h1>times</h1></br>
	 * public Monom times(Monom t)
	 * <p>
	 * Inmulteste monomul this cu monom t dat ca parametru. Updateaza si
	 * reprezentarea String a monomului this.
	 * </p>
	 * 
	 * @return void
	 */
	public Monom times(Monom t) {
		int co, exp;
		co = this.coeff * t.coeff;
		exp = this.exp + t.exp;
		Monom temp = new Monom("3x^2");// reda un format favorabil
		temp.updateRaw(co, exp);
		return temp;
	}

	public Monom div(Monom t) {
		int co, exp;
		co = this.coeff / t.coeff;
		exp = this.exp - t.exp;
		Monom temp = new Monom("3x^2");// reda un format favorabil
		temp.updateRaw(co, exp);
		return temp;
	}
}
