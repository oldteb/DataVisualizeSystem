package controller;

import static org.junit.Assert.assertTrue;

import java.util.Properties;

import yunheTang.model.CartesianGraph;
import yunheTang.model.Model;
import yunheTang.model.Point;

import org.junit.Test;

import dataset.ICommonProperties;
import yunheTang.view.MainForm;
import yunheTang.view.Panel;

public class TestShowHorizontalLineController {
	@Test
	public void testShowHorizontalLine () {
		Model model;
		MainForm mainform;

		ShowHorizontalLineController showHorizontalLineController;
		
		model = new Model();
		mainform = new MainForm(model);
		showHorizontalLineController = new ShowHorizontalLineController(model);
		
		// prepare GUI
		Point p;
		p = new Point(10,10);
		model.addPoint(p);
		p = new Point(20,15);
		model.addPoint(p);
		
		
		Properties pro = new Properties();
		pro.setProperty(ICommonProperties.horizontalLines, "true");
		showHorizontalLineController.act(mainform);
		
		pro.setProperty(ICommonProperties.horizontalLines, "false");
		showHorizontalLineController.act(mainform);
	
	}
}
