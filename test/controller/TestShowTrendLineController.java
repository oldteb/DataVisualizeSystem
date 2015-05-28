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

public class TestShowTrendLineController {
	@Test
	public void TestShowTrendLineController () {
		Model model;
		MainForm mainform;

		ShowTrendLineController showTrendLineController;
		
		model = new Model();
		mainform = new MainForm(model);
		showTrendLineController = new ShowTrendLineController(model);
		
		// prepare GUI
		Point p;
		p = new Point(10,10);
		model.addPoint(p);
		p = new Point(20,15);
		model.addPoint(p);
		
		
		Properties pro = new Properties();
		pro.setProperty(ICommonProperties.trendLineVisible, "true");
		showTrendLineController.act(mainform);
		
		pro.setProperty(ICommonProperties.trendLineVisible, "false");
		showTrendLineController.act(mainform);
		
	}
}
