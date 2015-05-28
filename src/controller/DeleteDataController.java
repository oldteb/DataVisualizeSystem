package controller;

import javax.swing.JTable;
import javax.swing.table.*;

import yunheTang.model.*;
import yunheTang.view.*;


public class DeleteDataController {

	Model model;
	
	public DeleteDataController(Model model){
		this.model = model;
	}
	
	public Boolean act(MainForm mainform){
		JTable jTable = mainform.getJTable();
		int dataToDelete[]=jTable.getSelectedRows();
		if (dataToDelete.length<=0) {
			return false;
		}
		else{
			DefaultTableModel table = (DefaultTableModel)mainform.getJTable().getModel();
			for (int i = dataToDelete.length; i > 0; i--){
				// Perform on Model
				model.deletePoint(jTable.getSelectedRow());
		
				// Perform on GUI
				table.removeRow(jTable.getSelectedRow());
			}
			
			// Refresh the graph.
			model.refreshRange();
			//((Panel)(mainform.getJPanel())).getGraph().setDomain();
			mainform.getJPanel().repaint();
		}
		
		// Reset GUI.
		mainform.getEditButton().setEnabled(false);
		mainform.getDeleteButton().setEnabled(false);
		mainform.getJTextFieldX().setText("");
		mainform.getJTextFieldY().setText("");
		if(model.size() < 2){
			mainform.getTrendLineButton().setEnabled(false);
		}

		return true;
	}

		
}