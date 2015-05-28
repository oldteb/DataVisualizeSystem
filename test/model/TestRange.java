package model;

import static org.junit.Assert.*;

import org.junit.Test;

import yunheTang.model.Range;

public class TestRange {

	@Test
	public void testValues() {
		Range r = new Range();
		r.setLargestX(111.11);
		r.setLargestY(222.22);
		r.setSmallestX(-333.33);
		r.setSmallestY(-444.44);
	}

}
