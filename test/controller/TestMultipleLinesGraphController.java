package controller;

import org.junit.Test;

import yunheTang.model.Model;
import yunheTang.model.MultipleLinesGraph;
import yunheTang.view.MainForm;

public class TestMultipleLinesGraphController {
	@Test
	public void testMultipleLinesGraph(){
		Model model = new Model();
		MainForm mainform = new MainForm(model);
		
		MultipleLinesGraphController multipleLinesGraphController = new MultipleLinesGraphController(model);
		
		multipleLinesGraphController.act(mainform);
		
	}
}
