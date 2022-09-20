package lab3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import org.junit.Before;

import balloon4.Balloon;

public class BalloonTests {

	private static final double EPSILON = 10e-07;

	private Balloon bb;

	@Before
	public void setup() // runs before every test case
	{
		bb = new Balloon(10);
	}

	@Test
	public void testInitialIsPopped() {
		assertEquals(false, bb.isPopped());
	}

	@Test
	public void testInitialRadius() {
		assertEquals(0.0, bb.getRadius(), EPSILON);
	}

	@Test
	public void testBlow1() {
		bb.blow(5);
		assertEquals(5, bb.getRadius(), EPSILON);
	}
	
	@Test
	public void testBlow2() {
		bb.blow(15);
		assertEquals(0.0, bb.getRadius(), EPSILON); // Balloon1, doesn't pop if blow over the maximum, and the radius
													// doesn't becoeme 0.
		assertEquals(true, bb.isPopped());
	}
	
	@Test
	public void testBlow3() {
		bb.blow(5);
		bb.pop();
		bb.blow(5);
		assertEquals(0.0, bb.getRadius(), EPSILON); //Balloon1 and Balloon2, continue to inflate the balloon after popping.
		assertEquals(true, bb.isPopped());
	}
	
	@Test
	public void testBlow4() {
		bb.blow(-5);
		assertEquals(0.0, bb.getRadius(), EPSILON);
		assertEquals(false, bb.isPopped());
	}
	
	@Test
	public void testBlow5() {
		bb.blow(5);
		bb.blow(5);
		assertEquals(10.0, bb.getRadius(), EPSILON); //Balloon3, doesn't add the radius on the infalted balloon.
	}

	@Test
	public void testDeflate1() {
		bb.blow(5);
		assertEquals(5.0, bb.getRadius(), EPSILON);
		bb.deflate();
		assertEquals(0.0, bb.getRadius(), EPSILON);
		assertEquals(false, bb.isPopped()); // Ballon4, pop() return true when the balloon is deflated.
	}
	
	@Test
	public void testDeflate2() {
		bb.blow(5);
		assertEquals(5.0, bb.getRadius(), EPSILON);
		bb.deflate();
		assertEquals(0.0, bb.getRadius(), EPSILON);
		bb.blow(5);
		assertEquals(5.0, bb.getRadius(), EPSILON); //Balloon4, doesn't inflate the balloon after deflating.
	}

	@Test
	public void IsPopped1() {
		bb.pop();
		assertEquals(true, bb.isPopped());
		assertEquals(0.0, bb.getRadius(), EPSILON);
	}

	@Test
	public void IsPopped2() {
		bb.pop();
		bb.pop();
		assertEquals(true, bb.isPopped());
		assertEquals(0.0, bb.getRadius(), EPSILON);
	}
}