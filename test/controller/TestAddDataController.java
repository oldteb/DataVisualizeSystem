package controller;

import org.junit.Test;

import model.*;
import yunheTang.model.Model;
import yunheTang.view.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestAddDataController{

	@Test
	public void testAddData () {
		Model model;
		MainForm mainform;

		AddDataController addDataController;
		
		model = new Model();
		mainform = new MainForm(model);
		addDataController = new AddDataController(model);
		// prepare GUI
		mainform.getJTextFieldX().setText("1");
		mainform.getJTextFieldY().setText("1");	
		addDataController.act(mainform);
		assertTrue(1 == model.getXOfIndex(0));
		assertTrue(1 == model.getYOfIndex(0));
		
		
		mainform.getJTextFieldX().setText("0");
		mainform.getJTextFieldY().setText("0.5");	
		addDataController.act(mainform);
		assertTrue(0 == model.getXOfIndex(1));
		assertTrue(0.5 == model.getYOfIndex(1));
		
		mainform.getJTextFieldX().setText("-2");
		mainform.getJTextFieldY().setText("-30");	
		addDataController.act(mainform);
		assertTrue(-2 == model.getXOfIndex(2));
		assertTrue(-30 == model.getYOfIndex(2));
		
		mainform.getJTextFieldX().setText("-0.5");
		mainform.getJTextFieldY().setText("-0.5");
		addDataController.act(mainform);
		
		mainform.getJTextFieldX().setText("-030");
		mainform.getJTextFieldY().setText("-030");
		addDataController.act(mainform);
		
		mainform.getJTextFieldX().setText("0039");
		mainform.getJTextFieldY().setText("0039");
		addDataController.act(mainform);
		
		mainform.getJTextFieldX().setText("-00.5");
		mainform.getJTextFieldY().setText("00.1");
		addDataController.act(mainform);
		
		mainform.getJTextFieldX().setText("00");
		mainform.getJTextFieldY().setText("00.1");
		addDataController.act(mainform);
		
		mainform.getJTextFieldX().setText("00.1");
		mainform.getJTextFieldY().setText("123");
		addDataController.act(mainform);
	}
}