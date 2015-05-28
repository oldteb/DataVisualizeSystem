package controller;

import yunheTang.model.*;
import yunheTang.view.*;

import javax.swing.JOptionPane;
import javax.swing.table.*;


public class AddDataController {

	Model model;
	
	public AddDataController(Model model){
		this.model = model;
	}
	
	static void checkInput(String s) throws IllegalArgumentException{
		if(Double.parseDouble(s) >= 0){
			if(Double.parseDouble(s) == 0){
				if(!s.equals("0")){
					throw new IllegalArgumentException();
				}
			}
			else if(0 < Double.parseDouble(s) && Double.parseDouble(s) < 1){
				if(s.charAt(1) == '0'){
					throw new IllegalArgumentException();
				}
			}
			else{
				if(s.charAt(0) == '0'){
					throw new IllegalArgumentException();
				}
			}
		}
		else if(Double.parseDouble(s) > -1){
			if(s.charAt(2) == '0'){
				throw new IllegalArgumentException();
			}
		}
		else if(s.charAt(1) == '0'){
			throw new IllegalArgumentException();
		}
	}
	
	
	public boolean act(MainForm mainform){
		double x;
		double y;
		
		String valueX = mainform.getJTextFieldX().getText();
		mainform.getJTextFieldX().setText("");
		String valueY = mainform.getJTextFieldY().getText();
		mainform.getJTextFieldY().setText("");
		
		// Perform on model.
		try{
			x = Double.parseDouble(valueX);
			y = Double.parseDouble(valueY);
			//throw new IllegalArgumentException();
			checkInput(valueX);
			checkInput(valueY);
		}
		catch(Exception e){
			//JOptionPane.showMessageDialog(null, "Please input valid data.", "Error", JOptionPane.ERROR_MESSAGE);	
			return false;
		}
	
		model.addPoint(new Point(x,y));
		
		// Perform in table.
		DefaultTableModel table = (DefaultTableModel)mainform.getJTable().getModel();
		String[] set = {valueX,valueY};
		table.addRow(set);
		
		// Refresh the graph.
		model.refreshRange();
		mainform.getJPanel().repaint();
		
		// Reset GUI.
		if(model.size() > 1 && (((Panel)(mainform.getJPanel())).getGraph() instanceof CartesianGraph)){
			mainform.getTrendLineButton().setEnabled(true);
		}
	
		return true;
	}
	
}
