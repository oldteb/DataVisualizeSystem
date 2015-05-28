package model;

import static org.junit.Assert.*;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

import org.junit.Test;

import yunheTang.model.Model;
import yunheTang.model.Point;

public class TestModel {

	@Test
	public void testAddPoint() {
		Model model = new Model();
		
		int index = model.addPoint(new Point(111.11, 222.22));
		assertTrue(index==0);
		
		index = model.addPoint(new Point(-111.11, -222.22));
		assertTrue(index==1);
	}
	
	@Test
	public void testEditPoint() {
		Model model = new Model();
		
		int index = model.addPoint(new Point(111.11, 222.22));
		boolean result = model.editPoint(index+3, new Point(333.33, 444.44));
		assertFalse(result);
		
		result = model.editPoint(index, new Point(333.33, 444.44));
		assertTrue(result);
	}

	@Test
	public void testDeletePoint() {
		Model model = new Model();
		
		int index = model.addPoint(new Point(111.11, 222.22));
		assertTrue(index==0);
		
		index = model.addPoint(new Point(-111.11, -222.22));
		assertTrue(index==1);
		
		boolean result = model.deletePoint(-1);
		assertFalse(result);
		
		result = model.deletePoint(index+10);
		assertFalse(result);
		
		result = model.deletePoint(0);
		assertTrue(result);
	}
	
	@Test
	public void testGetXOfIndex() {
		Model m = new Model();
		Point d = new Point(111.11, 222.22); 
		int idx = m.addPoint(d);
		assertEquals(m.getXOfIndex(idx), d.getXofPoint(), 10);
	}
	
	@Test
	public void testGetYOfIndex() {
		Model m = new Model();
		Point d = new Point(111.11, 222.22); 
		int idx = m.addPoint(d);
		assertEquals(m.getYOfIndex(idx), d.getYofPoint(), 10);
	}

	@Test
	public void testSize() {
		Model m = new Model();
		Point d = new Point(111.11, 222.22);
		
		int idx = m.addPoint(d);
		int size = m.size();
		assertEquals(idx, size, 10);
	}
 
	@Test
	public void testSaveToFile() {
		Model m = new Model();
		Point d = new Point(111.11, 222.22);
		
		m.addPoint(d);
		
		FileSystemView fsv = FileSystemView.getFileSystemView();
		File homeDir = fsv.getHomeDirectory();
		
		boolean result = m.saveToFile(new File(homeDir.getPath()+"\\test.csv"));
		assertTrue(result);
		
		result = m.saveToFile(new File("@! \\illegal file"));
		assertFalse(result);
	}

	@Test
	public void testLoadFromFile() {
		Model m = new Model();
		Point d = new Point(111.11, 222.22);
		
		m.addPoint(d);
		
		FileSystemView fsv = FileSystemView.getFileSystemView();
		File homeDir = fsv.getHomeDirectory();
		File f = new File(homeDir.getPath()+"\\test.csv");
		
		boolean result = m.saveToFile(f);
		assertTrue(result);
		
		Model m1 = new Model();
		result = m1.loadFromFile(f);
		assertTrue(result);
		
		assertEquals(m.getXOfIndex(0), m1.getXOfIndex(0), 10);
		assertEquals(m.getYOfIndex(0), m1.getYOfIndex(0), 10);
	}

	@Test
	public void testRefreshRange() {

		Model m = new Model();
		Point p = new Point(111.11, 222.22);
		
		m.refreshRange();
		
		double max_x = m.getRange().getLargestX();
		m.addPoint(p);
		m.refreshRange();
		double max_x1 = m.getRange().getLargestX();
		assertNotEquals(max_x, max_x1);
	}
}
