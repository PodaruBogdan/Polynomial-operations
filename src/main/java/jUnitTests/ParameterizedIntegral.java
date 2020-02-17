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
public class ParameterizedIntegral {

	private String p1;
	private String p2;
	public ParameterizedIntegral(String p1,String p2) {
		this.p1=p1;
		this.p2=p2;
	}
	@Parameters(name="{index}: testItgr({0}={2})")
	public static Collection<Object[]> data(){
		return Arrays.asList(new Object[][] {
			{"1","x"},
			{"22x+2x^4-6x^-2","11x^2+6x^-1"},
			{"5x^4+2x^3+2","x^5+2x"}
		});
	}	
	@Test
	public void test_ItgrPolynomials() {
		Polinom pol1=new Polinom(p1);
		String tmp=pol1.itgr();
		assertEquals(tmp,p2);
	}
}
