package jUnitTests;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import model.Polinom;
@RunWith(Parameterized.class)
public class ParameterizedDerivative {

	private String p1;
	private String p2;
	public ParameterizedDerivative(String p1,String p2) {
		this.p1=p1;
		this.p2=p2;
	}
	@Parameters(name="{index}: testDer({0} ' ={2})")
	public static Collection<Object[]> data(){
		return Arrays.asList(new Object[][] {
			{"1","0"},
			{"22x^2+2x^4-6x^-2","8x^3+44x+12x^-3"},
			{"5x^4+2x^3+2","20x^3+6x^2"}
		});
	}	
	@Test
	public void test_DerPolynomials() {
		Polinom pol1=new Polinom(p1);
		String tmp=pol1.der();
		assertEquals(tmp,p2);
	}
}
