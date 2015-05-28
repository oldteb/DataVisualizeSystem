package controller;

import static org.junit.Assert.assertTrue;

import java.util.Properties;

import yunheTang.model.CartesianGraph;
import yunheTang.model.Model;
import yunheTang.model.Point;

import org.junit.Test;

import controller.*;
import dataset.ICommonProperties;
import yunheTang.view.MainForm;
import yunheTang.view.Panel;

public class TestShowEquationController {
	@Test
	public void testShowEquation () {
		Model model;
		MainForm mainform;

		ShowEquationController showEquationController;
		
		model = new Model();
		mainform = new MainForm(model);
		showEquationController = new ShowEquationController(model);
		
		// prepare GUI
		Point p;
		p = new Point(10,10);
		model.addPoint(p);
		p = new Point(20,15);
		model.addPoint(p);

		Properties pro = new Properties();
		pro.setProperty(ICommonProperties.trendLineEquationVisible, "true");
		showEquationController.act(mainform);
		
		pro.setProperty(ICommonProperties.trendLineEquationVisible, "false");
		showEquationController.act(mainform);

	}
}
