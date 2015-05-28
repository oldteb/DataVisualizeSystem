package controller;

import dataset.ICommonProperties;
import dataset.IGraph;
import yunheTang.model.*;
import yunheTang.view.*;


public class ShowXYAxesController {
	
	Model model;
	
	public ShowXYAxesController(Model model){
		this.model = model;
	}
	
	public boolean act(MainForm mainform){
		IGraph ig = ((Panel)(mainform.getJPanel())).getGraph();
		
		if(model.getProperties().getProperty(ICommonProperties.xAxisLabel,"invalid").equals("true")){
			model.getProperties().setProperty(ICommonProperties.xAxisLabel, "false");
			mainform.getXYAxesButton().setText("ShowXYAxes");
		}
		else{
			model.getProperties().setProperty(ICommonProperties.xAxisLabel, "true");
			mainform.getXYAxesButton().setText("HideXYAxes");
		}
		
		ig.setProperties(model.getProperties());
		
		mainform.getJPanel().repaint();
		return true;
	}
	
}
