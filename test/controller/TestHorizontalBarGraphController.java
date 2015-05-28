package controller;

import org.junit.Test;

import yunheTang.model.Model;
import yunheTang.view.MainForm;

public class TestHorizontalBarGraphController {
	@Test
	public void testHorizontalBarGraphController(){
		Model model = new Model();
		MainForm mainform = new MainForm(model);
		
		HorizontalBarGraphController horizontalBarGraphController = new HorizontalBarGraphController(model);
		
		horizontalBarGraphController.act(mainform);
		
	}
}
