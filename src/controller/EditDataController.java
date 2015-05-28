package controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import yunheTang.model.*;
import yunheTang.view.*;


public class EditDataController {
	Model model;
	
	public EditDataController(Model model){
		this.model = model;
	}
	
	public Boolean act(MainForm mainform){
		JTable jTable = mainform.getJTable();
		int rowToEdit=jTable.getSelectedRow();
		if (rowToEdit == -1) {
			return false;
		}
		else{
			String sx = mainform.getJTextFieldX().getText();
			String sy = mainform.getJTextFieldY().getText();
			double x = Double.parseDouble(sx);
			double y = Double.parseDouble(sy);
			
			DefaultTableModel table = (DefaultTableModel)mainform.getJTable().getModel();
			
			// Perform on model
			model.editPoint(rowToEdit, new Point(x, y));
			
			// Perform on table.
			table.setValueAt(sx, rowToEdit, 0);
			table.setValueAt(sy, rowToEdit, 1);
			
			
			// Refresh the graph.
			model.refreshRange();
			//((Panel)(mainform.getJPanel())).getGraph().setDomain();
			mainform.getJPanel().repaint();
			
			// Reset.
			mainform.getJTextFieldX().setText(null);
			mainform.getJTextFieldY().setText(null);
			mainform.getEditButton().setEnabled(false);
		}

		return true;
	}
	
}
