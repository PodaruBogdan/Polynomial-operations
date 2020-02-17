package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.regex.*;
public class Polinom {
	private String polinom;
	private ArrayList<Monom> monomList;
	private int grade;
	public Polinom(String polinom) {
		this.polinom = polinom;
		monomList = new ArrayList<Monom>();
		if (!polinom.equals("") && !polinom.equals("0")) {//polinomul nu e empty
			process(polinom);
			normalize();
			grade = monomList.get(0).getExp();
		}
		else if(polinom.equals("0")) {
			Monom a=new Monom("0");
			grade=0;
			monomList.add(a);
		}
	}
	/**
	 * <h1>proces</h1></br>
	 * private void process(String raw)
	 * <p>
	 * Imparte String-ul raw primit ca parametru dupa formatul Regex semn:coeficient:x^:putere.
	 * Monoamele rezultate sunt salvate intr-un ArrayList.
	 * </p>
	 * @return void
	 * */
	private void process(String raw) {

		Pattern p = Pattern.compile("((-?\\d+(?=x))?(-?[x])(\\^(-?\\d+))?)|((-?)[x])|(-?\\d+)");
		raw = raw.replaceAll("\\s+", "");//utilizatorul poate introduce spatii
		Matcher m = p.matcher(raw);
		while (m.find()) {
			if (!m.group(0).equals("")) {
				if (!isZero(m.group(0))) {//nu adauga monoamele = 0
					Monom mon = new Monom(m.group(0));
					monomList.add(mon);
				}
			}
		}
	}
	public void displayMonoms() {
		for (Monom a : monomList) {
			System.out.println(a);
		}
	}
	/**
	 * <h1>isZero</h1></br>
	 * private boolean isZero(String a) 
	 * <p>
	 * Testeaza reprezentarea ca intreg a lui a e 0 sau nu;
	 * </p>
	 * @return true daca intregul e 0,false altfel
	 * */
	private boolean isZero(String a) {
		try {
			int temp = Integer.parseInt(a);
			if (temp == 0)
				return true;
			else
				return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	/**
	 * <h1>sum</h1></br>
	 *public String sum(String pol2)
	 * <p>
	 * Adunarea unui polinom reprezentat ca String la this.
	 * </p>
	 * @param pol2 - polinomul cu care se aduna this 
	 * @return rezultatul adunarii reprezentat ca String
	 * */
	public String sum(String pol2) {

		try {
			int n1 = Integer.parseInt(this.polinom);//polinom format dintr-un numar
			int n2 = Integer.parseInt(pol2);//polinom format dintr-un numar
			return String.valueOf(n1 + n2);//aduna simplu numerele
		} catch (NumberFormatException e) {//polinom cu variabila
			Polinom p2 = new Polinom(pol2);
			String res = this.polinom + p2.polinom;
			Polinom p = new Polinom(res);
			if (p.polinom.charAt(0) == '+')
				p.polinom = p.polinom.substring(1);//preia polinomul fara + daca acesta incepe cu acest caracter
			return p.polinom;
		}

	}
	/**
	 * <h1>der</h1></br>
	 * public String der()
	 * <p>
	 * Derivarea fiecarui monom in parte a lui this.
	 * </p>
	 * @return rezultatul derivarii reprezentat ca String
	 * */
	public String der() {
		String res = this.polinom;
		try {
			Integer.parseInt(polinom);
			return "0";
		}catch(NumberFormatException e) {
		if (res.charAt(0) == '+')
			res = res.substring(1);
		for (Monom a:monomList) {
			//daca prin derivare coeficientul ajunge 0 ,elimina din lista de monoame
			a.der(); 
			if(a.getRawString().equals("0")) {
				a.setRawString("+"+a.getRawString());
			}
		}
		normalize();
		Polinom p=new Polinom(polinom);
		polinom=p.polinom;
		if(polinom.charAt(0)=='+') {
			polinom=polinom.substring(1);
		}
		}
		return polinom;
	}
	/**
	 * <h1>itgr</h1></br>
	 * public String itgr() 
	 * <p>
	 * Integrarea fiecarui monom in parte a lui this.
	 * </p>
	 * @return rezultatul integrarii reprezentat ca String
	 * */
	public String itgr() {
		String res = this.polinom;
		if (res.charAt(0) == '+')
			res = res.substring(1);//elimina posibilitatea parsarii cu caracterul +
		for (Iterator<Monom> it = monomList.iterator(); it.hasNext();) {
			Monom a = it.next();
			if (a.getCoeff() == 0) {//daca prin integrare coeficientul ajunge 0 ,elimina din lista de monoame
				monomList.remove(a);
			} else {
				a.itgr();
			}
		}
		normalize();//normalizeaza(aduna coeficienti egali)
		if(polinom.charAt(0)=='+') {
			polinom=polinom.substring(1);
		}
		return polinom;
	}
	/**
	 * <h1>sub</h1></br>
	 * public String sub(String pol2)
	 * <p>
	 * Scaderea unui polinom reprezentat ca String la this.
	 * </p>
	 * @param pol2 - polinomul care se scade din this 
	 * @return rezultatul scaderii reprezentat ca String
	 * */
	public String sub(String pol2) {
		try {
			int n1 = Integer.parseInt(this.polinom);//polinom format dintr-un numar
			int n2 = Integer.parseInt(pol2);//polinom format dintr-un numar
			return String.valueOf(n1 - n2);//aduna simplu numerele
		} catch (NumberFormatException e) {//polinom cu variabila
			Polinom p2 = new Polinom(pol2);
			p2.changeSign();
			String res = this.polinom + p2.polinom;
			Polinom p = new Polinom(res);
			if (p.polinom.charAt(0) == '+')
				p.polinom = p.polinom.substring(1);//preia polinomul fara + daca acesta incepe cu acest caracter
			return p.polinom;
		}

	}

	/**
	 * <h1>normalize</h1></br>
	 * public void normalize()
	 * <p>
	 * Normalizarea polinomului:sortarea monoamelor in ordinea descrescatoare
	 * a gradelor si adunarea monoamelor cu acelasi grad, precum si eliminarea
	 * monoamelor cu coeficient 0.
	 * </p>
	 * @return void
	 * */
	public void normalize() {
		Collections.sort(this.monomList, new ExponentComp());
		int i = 0;
		while (i < monomList.size() - 1) {
			if (monomList.get(i).getCoeff() == 0) {//nu adauga in lista de monoame , monoame nule
				monomList.remove(i);
			} else {
				if (monomList.get(i).getExp() == monomList.get(i + 1).getExp()) {
					monomList.get(i + 1).add(monomList.get(i));
					monomList.remove(i);//ordoneaza in ordine descrescatoare a gradelor 
					//aduna termenul precedent la termenul imediat urmator daca au gradele egale si elimina precedentul
				} else
					i++;
			}
		}
		this.polinom = "";
		for (Iterator<Monom> it = this.monomList.iterator(); it.hasNext();) {
			this.polinom += it.next().getRawString();
		}

	}
	/**
	 * <h1>changeSign</h1></br>
	 * public void changeSign()
	 * <p>
	 * Au loc schimbari individuale de semn pentru fiecare monom
	 * constituent al polinomului this.
	 * </p>
	 * @return void
	 * */
	public void changeSign() {
		this.polinom = "";
		for (Iterator<Monom> it = this.monomList.iterator(); it.hasNext();) {
			Monom a = it.next();
			a.changeSign();//schimbarea semnului fiecarui monom
			this.polinom += a.getRawString();
		}
	}
	/**
	 * <h1>times</h1></br>
	 *public String times(String pol2)
	 * <p>
	 * Inmultirea lui this cu polinomul pol2 dat ca String.
	 * Are loc inmultirea axb unde a e un monom a lui this 
	 * si b e un monom a lui pol2.
	 * </p>
	 * @param pol2 - polinomul cu care se inmulteste this 
	 * @return rezultatul inmultirii reprezentat ca String
	 * */
	public String times(String pol2) {
		Polinom p2 = new Polinom(pol2);
		String temp = "";
		for (Monom a : this.monomList) {//foreach loop
			for (Monom b : p2.monomList) {
				Monom c = a.times(b);
				temp += c.getRawString();//inmultire monom cu monom
			}
		}
		Polinom prod = new Polinom(temp);//creare polinom nou care normalizeaza rezultatul(adunarea coeficientilor
		//egali si eliminarea monoamelor nule).
		if(prod.polinom.charAt(0)=='+') {
			prod.polinom=prod.polinom.substring(1);
		}
		return prod.polinom;
	}
	/**
	 * <h1>div</h1></br>
	 *public String div(String pol2)
	 * <p>
	 * Metoda traditionala de impartire a polinoamelor.
	 * Impartirea lui this la un polinom reprezentat ca String.
	 * </p>
	 * @param pol2 - deimpartitul
	 * @return rezultatul impartirii reprezentat ca String
	 * */
	public String div(String pol2) {
		try {
			int n1 = Integer.parseInt(this.polinom);//polinom format dintr-un numar
			int n2 = Integer.parseInt(pol2);//polinom format dintr-un numar
			return "Cat="+String.valueOf(n1 / n2)+" Rest="+String.valueOf(n1 % n2);
		}catch(NumberFormatException e){
		Polinom d = new Polinom(pol2);// deimpartit
		String q = "";// cat
		Polinom r = new Polinom(this.polinom);// rest
		String t = "";// temp variable
		Monom leadingD = new Monom(d.monomList.get(0).getRawString());//termenul cu gradul max al deimpartitului
		while (!r.polinom.equals("0") && r.grade >= d.grade) {//grad impartitor >= grad deimpartit
			t = (r.monomList.get(0).div(leadingD)).getRawString();//aici se impart monoamele
			q = q + t;//constructie cat rezultat
			Polinom use=new Polinom(q);
			q=use.polinom;
			Polinom tmp = new Polinom(t);
			tmp.changeSign();
			String y=r.sum(tmp.times(d.polinom));
			if(y.equals("0")) {
				r.polinom="0";
			}else
				r = new Polinom(y);//elimina termen cu grad max curent din r
		}
		if(q.charAt(0)=='+') {
			q=q.substring(1);
		}
		if(r.polinom.charAt(0)=='+') {
			r.polinom=r.polinom.substring(1);
		}
		return "Cat="+q+" Rest="+r.polinom;
		}
	}
}
