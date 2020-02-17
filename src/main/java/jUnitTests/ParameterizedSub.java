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
public class ParameterizedSub {

	private String p1;
	private String p2;
	private String p3;
	public ParameterizedSub(String p1,String p2,String p3) {
		this.p1=p1;
		this.p2=p2;
		this.p3=p3;
	}
	@Parameters(name="{index}: testSub({0} - {1}={2})")
	public static Collection<Object[]>data(){
		return Arrays.asList(new Object[][] {
			{"1","-2","3"},
			{"x^2+5x+2","-9x+x^4-x^-1-2","-x^4+x^2+14x+4+x^-1"},
			{"0+2x-88x^3","2x^5+10x","-2x^5-88x^3-8x"}
		});
	}	
	@Test
	public void test_SubPolynomials() {
		Polinom pol1=new Polinom(p1);
		String tmp=pol1.sub(p2);
		assertEquals(tmp,p3);
	}
	
}
