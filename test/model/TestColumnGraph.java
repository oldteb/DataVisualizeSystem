package model;

import yunheTang.model.*;
import org.junit.Test;
import dataset.ICommonProperties;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.util.Properties;
import javax.swing.JPanel;

public class TestColumnGraph {
	
	BufferedImage bi = new BufferedImage(100, 100, BufferedImage.TYPE_INT_BGR);
	Graphics g = bi.getGraphics();
	
	@Test
	public void testDraw() {
		Model m = new Model();
		ColumnGraph cg = new ColumnGraph();
		cg.setDataSet(m);
		
		Properties p = new Properties();
		p.setProperty(ICommonProperties.trendLineVisible, "true");
		p.setProperty(ICommonProperties.trendLineEquationVisible, "true");
		p.setProperty(ICommonProperties.xAxisLabel, "true");
		p.setProperty(ICommonProperties.horizontalLines, "true");
		cg.setProperties(p);
		
		cg.draw(g, new JPanel());
		
		Point p1 = new Point(111.11, 222.22);
		m.addPoint(p1);
		m.refreshRange();
		cg.draw(g, new JPanel());
		
		m.addPoint(p1);
		m.refreshRange();
		cg.draw(g, new JPanel());
		
		m.deletePoint(0);
		m.deletePoint(0);
		m.addPoint(new Point(-111.11, -222.22));
		m.refreshRange();
		cg.draw(g, new JPanel());
		
		Point p2 = new Point(-111.11, -222.22);
		Point p3 = new Point(111.11, 0);
		Point p4 = new Point(0, 222.22);
		m.addPoint(p2);
		m.addPoint(p3);
		m.addPoint(p4);
		m.addPoint(p4);
		m.addPoint(p4);
		m.addPoint(p4);
		m.addPoint(p4);
		m.addPoint(p4);
		m.addPoint(p4);
		m.addPoint(p4);
		m.addPoint(p4);
		m.addPoint(p4);
		m.addPoint(p4);
		m.addPoint(p4);
		m.addPoint(p4);
		m.addPoint(p4);
		m.addPoint(p4);
		m.addPoint(p4);
		m.addPoint(p4);
		m.refreshRange();
		cg.draw(g, new JPanel());
	}
}
