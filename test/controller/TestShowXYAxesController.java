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

public class TestShowXYAxesController {
	@Test
	public void TestShowXYAxesController () {
		Model model;
		MainForm mainform;

		ShowXYAxesController showXYAxesController;
		
		model = new Model();
		mainform = new MainForm(model);
		showXYAxesController = new ShowXYAxesController(model);
		
		// prepare GUI
		Point p;
		p = new Point(10,10);
		model.addPoint(p);
		p = new Point(20,15);
		model.addPoint(p);
		
		Properties pro = new Properties();
		pro.setProperty(ICommonProperties.xAxisLabel, "true");
		showXYAxesController.act(mainform);
		
		pro.setProperty(ICommonProperties.xAxisLabel, "false");
		showXYAxesController.act(mainform);

	}
}
