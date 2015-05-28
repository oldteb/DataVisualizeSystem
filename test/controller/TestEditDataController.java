package controller;

import static org.junit.Assert.*;

import javax.swing.table.DefaultTableModel;

import yunheTang.model.Model;
import yunheTang.model.Point;

import org.junit.Test;

import yunheTang.view.MainForm;

public class TestEditDataController {

	@Test
	public void testAct() {
		Model model = new Model();
		model.addPoint(new Point(111, 222));
		MainForm mainForm = new MainForm(model);
		EditDataController controller = new EditDataController(model);
		boolean result = controller.act(mainForm);
		assertFalse(result);
		
		String[] set = {"111", "222"};
		((DefaultTableModel)mainForm.getJTable().getModel()).addRow(set);
		mainForm.getJTable().setRowSelectionInterval(0, 0);
		mainForm.getJTextFieldX().setText("111.11");
		mainForm.getJTextFieldY().setText("222.22");
		model.addPoint(new Point(111, 222));
		result = controller.act(mainForm);
		assertTrue(result);
	}

}
