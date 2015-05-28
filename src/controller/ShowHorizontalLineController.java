package controller;

import dataset.ICommonProperties;
import dataset.IGraph;
import yunheTang.model.*;
import yunheTang.view.*;

public class ShowHorizontalLineController {
	Model model;
	
	public ShowHorizontalLineController(Model model){
		this.model = model;
	}
	
	public boolean act(MainForm mainform){
		IGraph ig = ((Panel)(mainform.getJPanel())).getGraph();
		
		
		if(model.getProperties().getProperty(ICommonProperties.horizontalLines,"invalid").equals("true")){
			model.getProperties().setProperty(ICommonProperties.horizontalLines, "false");
			mainform.getHorizontalLineButton().setText("ShowHorizontalLine");
		}
		else{
			model.getProperties().setProperty(ICommonProperties.horizontalLines, "true");
			mainform.getHorizontalLineButton().setText("HideHorizontalLine");
		}
		
		ig.setProperties(model.getProperties());
		
		mainform.getJPanel().repaint();
		return true;
	}
	
}
