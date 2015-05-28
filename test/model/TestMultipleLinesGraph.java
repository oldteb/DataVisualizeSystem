package model;

import static org.junit.Assert.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Properties;

import javax.swing.JPanel;

import org.junit.Test;

import dataset.ICommonProperties;
import yunheTang.model.Model;
import yunheTang.model.MultipleLinesGraph;
import yunheTang.model.Point;

public class TestMultipleLinesGraph {
	
	BufferedImage bi = new BufferedImage(100, 100, BufferedImage.TYPE_INT_BGR);
	Graphics g = bi.getGraphics();
	
	@Test
	public void testdraw(){
		Model m = new Model();
		MultipleLinesGraph cg = new MultipleLinesGraph();
		
		cg.setDataSet(m);
		Properties pro = new Properties();
		pro.setProperty(ICommonProperties.horizontalLines, "true");
		pro.setProperty(ICommonProperties.xAxisLabel, "true");
		cg.setProperties(pro);
		
		cg.draw(g, new JPanel());
		
		m.addPoint(new Point(11,22));
		m.refreshRange();
		pro.setProperty(ICommonProperties.horizontalLines, "false");
		pro.setProperty(ICommonProperties.xAxisLabel, "false");
		cg.setProperties(pro);
		cg.draw(g, new JPanel());
		
		m.addPoint(new Point(22,44));
		m.refreshRange();
		cg.draw(g, new JPanel());
		

	}
}
