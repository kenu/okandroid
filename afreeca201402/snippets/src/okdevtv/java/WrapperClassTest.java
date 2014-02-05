package okdevtv.java;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class WrapperClassTest {

	@Test
	public void test() {
		int a = 5;
		Integer ai = 5;
		assertEquals(5, a);
		
		String string = ai.toString();
		assertEquals("5", string);
		
		System.out.println(Integer.toHexString(31));
		System.out.println(Integer.toBinaryString(31));
		System.out.println(Integer.toOctalString(31));
	}
	
	@Test
	public void AutoBoxing() {
		Map<String, Integer> map = new HashMap<>();
		map.put("five", 5);
		assertEquals(new Integer(5), map.get("five"));
	}

}
