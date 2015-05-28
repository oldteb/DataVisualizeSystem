package controller;

import dataset.ICommonProperties;
import dataset.IGraph;
import yunheTang.model.*;
import yunheTang.view.MainForm;
import yunheTang.view.Panel;

public class ShowEquationController {
	Model model;
	
	public ShowEquationController(Model model){
		this.model = model;
	}
	
	public boolean act(MainForm mainform){
		// Refresh the graph.
		IGraph ig = ((Panel)(mainform.getJPanel())).getGraph();
		
		if(model.getProperties().getProperty(ICommonProperties.trendLineEquationVisible,"invalid").equals("true")){
			model.getProperties().setProperty(ICommonProperties.trendLineEquationVisible, "false");
			mainform.getEquationButton().setText("HideLinearEquation");
		}
		else{
			model.getProperties().setProperty(ICommonProperties.trendLineEquationVisible, "true");
			mainform.getTrendLineButton().setText("HideTrendLine");
			mainform.getEquationButton().setText("ShowLinearEquation");
		}
		
		ig.setProperties(model.getProperties());
		
		mainform.getJPanel().repaint();
		
		return true;
	}
}
