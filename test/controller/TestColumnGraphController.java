package controller;

import org.junit.Test;

import yunheTang.model.ColumnGraph;
import yunheTang.model.Model;
import yunheTang.view.MainForm;
import yunheTang.view.Panel;
import dataset.IGraph;

public class TestColumnGraphController {
	@Test
	public void testColumnGraph(){
		Model model = new Model();
		MainForm mainform = new MainForm(model);
		
		ColumnGraphController columnGraphController = new ColumnGraphController(model);
		
		columnGraphController.act(mainform);
		
	}
}
