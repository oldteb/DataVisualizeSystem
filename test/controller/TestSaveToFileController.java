package controller;

import yunheTang.view.*;
import yunheTang.model.*;
import org.junit.Test;

public class TestSaveToFileController {

	@Test
	public void testAct() {
		Model model = new Model();
		model.addPoint(new Point(111, 222));
		model.addPoint(new Point(11, 22));
		MainForm mainForm = new MainForm(model);
		SaveToFileController controller = new SaveToFileController(model);
	}
}
