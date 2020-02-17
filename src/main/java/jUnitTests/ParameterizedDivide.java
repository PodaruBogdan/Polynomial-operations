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
public class ParameterizedDivide {

	private String p1;
	private String p2;
	private String p3;
	public ParameterizedDivide(String p1,String p2,String p3) {
		this.p1=p1;
		this.p2=p2;
		this.p3=p3;
	}
	@Parameters(name="{index}: testDiv({0} / {1}={2})")
	public static Collection<Object[]>data(){
		return Arrays.asList(new Object[][] {
			{"1","-2","Cat=0 Rest=1"},
			{"22x^2","2x","Cat=11x Rest=0"},
			{"5x^4+2x^3+2","x^2+2","Cat=5x^2+2x-10 Rest=-4x+22"}
		});
	}	
	@Test
	public void test_DivPolynomials() {
		Polinom pol1=new Polinom(p1);
		String tmp=pol1.div(p2);
		assertEquals(tmp,p3);
	}
}
