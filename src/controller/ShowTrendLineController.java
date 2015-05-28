package controller;

import javax.swing.table.DefaultTableModel;






import dataset.ICommonProperties;
import dataset.IGraph;
import yunheTang.model.CartesianGraph;
import yunheTang.model.Graph;
import yunheTang.model.Model;
import yunheTang.view.*;

public class ShowTrendLineController {
	Model model;
	
	public ShowTrendLineController(Model model){
		this.model = model;
	}
	
	public boolean act(MainForm mainform){
		// Refresh the graph.
		IGraph ig = ((Panel)(mainform.getJPanel())).getGraph();
		
		if(model.getProperties().getProperty(ICommonProperties.trendLineVisible,"invalid").equals("true")){
			model.getProperties().setProperty(ICommonProperties.trendLineVisible, "false");
			mainform.getTrendLineButton().setText("ShowTrendLine");
			mainform.getEquationButton().setEnabled(false);
		}
		else{
			model.getProperties().setProperty(ICommonProperties.trendLineVisible, "true");
			mainform.getTrendLineButton().setText("HideTrendLine");
			mainform.getEquationButton().setEnabled(true);
		}
		
		ig.setProperties(model.getProperties());
		
		mainform.getJPanel().repaint();
		return true;
	}
}
