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
public class ParameterizedProduct {

	private String p1;
	private String p2;
	private String p3;
	public ParameterizedProduct(String p1,String p2,String p3) {
		this.p1=p1;
		this.p2=p2;
		this.p3=p3;
	}
	@Parameters(name="{index}: testProd({0} * {1}={2})")
	public static Collection<Object[]>data(){
		return Arrays.asList(new Object[][] {
			{"1","-2","-2"},
			{"2x^2+3x^-1","2x-8","4x^3-16x^2+6-24x^-1"},
			{"5x^4+2x^3+2","x^2+2","5x^6+2x^5+10x^4+4x^3+2x^2+4"}
		});
	}	
	@Test
	public void test_ProdPolynomials() {
		Polinom pol1=new Polinom(p1);
		String tmp=pol1.times(p2);
		assertEquals(tmp,p3);
	}
	
}
