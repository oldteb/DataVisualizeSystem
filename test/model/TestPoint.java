package model;

import static org.junit.Assert.*;
import yunheTang.model.*;

import org.junit.Test;

public class TestPoint {

	@Test
	public void testValues() {
		Point p = new Point(111.11, 222.22);
		p.setValues(111.11, 222.22);
		double x = p.getXofPoint();
		assertTrue(x == 111.11);
		double y = p.getYofPoint();
		assertTrue(y == 222.22);
		
		Point p1 = new Point(-111.11, -222.22);
		p1.setValues(111.11, 222.22);
		x = p.getXofPoint();
		assertTrue(x == 111.11);
		y = p.getYofPoint();
		assertTrue(y == 222.22);
	}
}
