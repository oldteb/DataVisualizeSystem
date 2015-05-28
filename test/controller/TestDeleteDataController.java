package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import javax.swing.table.*;

import yunheTang.model.*;
import yunheTang.view.*;


public class TestDeleteDataController {

	@Test
	public void testAct() {
		Model model = new Model();
		model.addPoint(new Point(111, 222));
		MainForm mainForm = new MainForm(model);
		DeleteDataController controller = new DeleteDataController(model);
		boolean result = controller.act(mainForm);
		assertFalse(result);
		
		String[] set = {"111", "222"};
		((DefaultTableModel)mainForm.getJTable().getModel()).addRow(set);
		mainForm.getJTable().setRowSelectionInterval(0, 0);
		result = controller.act(mainForm);
		assertTrue(result);
		
		model.addPoint(new Point(11, 2));
		model.addPoint(new Point(11, 22));
		((DefaultTableModel)mainForm.getJTable().getModel()).addRow(set);
		mainForm.getJTable().setRowSelectionInterval(0, 0);
		result = controller.act(mainForm);
		assertTrue(result);	
	}
}
