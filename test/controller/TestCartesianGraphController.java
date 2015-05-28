package controller;

import static org.junit.Assert.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Properties;

import javax.swing.JPanel;

import controller.CartesianGraphController;
import yunheTang.view.Panel;
import yunheTang.model.*;

import org.junit.Test;

import dataset.IGraph;
import yunheTang.view.MainForm;

public class TestCartesianGraphController {

	@Test
	public void testCartesianGraph(){
		Model model = new Model();
		MainForm mainform = new MainForm(model);
		IGraph graph = new ColumnGraph();
		
		CartesianGraphController cartesianGraphController = new CartesianGraphController(model);
		((Panel)(mainform.getJPanel())).setGraph(graph);
		
		cartesianGraphController.act(mainform);
		
	}
	
	
}
