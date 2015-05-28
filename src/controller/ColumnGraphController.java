package controller;

import java.util.Properties;

import propertie.ApplicationPropertie;
import dataset.ICommonProperties;
import dataset.IGraph;
import yunheTang.model.*;
import yunheTang.view.*;

public class ColumnGraphController {
	
	Model model;
	
	public ColumnGraphController(Model model){
		this.model = model;
	}
	
	public boolean act(MainForm mainform){
		
		if(((Panel)(mainform.getJPanel())).getGraph() instanceof ColumnGraph){
			// Already ColumnGraph, do nothing.
			return false;
		}
		else{
			// Prepare properties.
			model.resetProperties();
			model.getProperties().setProperty(ICommonProperties.horizontalLines, "false");
			model.getProperties().setProperty(ICommonProperties.xAxisLabel, "false");
			
			//new cartesian instance.
			IGraph graph = null;
			try{
				Class myclass = Class.forName(ApplicationPropertie.getInstance().column);
				graph = (IGraph)myclass.newInstance();
				graph.setDataSet(model);
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
			
			//Add color properties.
			graph.setProperties(model.getProperties());
			
			((Panel)mainform.getJPanel()).setGraph(graph);
			
			// refresh specific GUI
			mainform.getTrendLineButton().setEnabled(false);
			mainform.getEquationButton().setText("ShowLinearEquation");;
			mainform.getEquationButton().setEnabled(false);
		}

		// Reset general GUI.
		mainform.getTrendLineButton().setText("ShowTrendLine");
		mainform.getHorizontalLineButton().setText("ShowHorizontalLine");
		mainform.getXYAxesButton().setText("ShowXYAxes");

		mainform.getJPanel().repaint();
		return true;
	}
	
}
