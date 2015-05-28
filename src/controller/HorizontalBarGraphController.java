package controller;

import java.util.Properties;

import propertie.ApplicationPropertie;
import yunheTang.model.Model;
import yunheTang.model.MultipleLinesGraph;
import yunheTang.view.MainForm;
import yunheTang.view.Panel;
import dataset.ICommonProperties;
import dataset.IGraph;

public class HorizontalBarGraphController {
	Model model;
	
	public HorizontalBarGraphController(Model model){
		this.model = model;
	}
	
	public boolean act(MainForm mainform){
			// Prepare properties.
			model.resetProperties();
			model.getProperties().setProperty(ICommonProperties.horizontalLines, "false");
			model.getProperties().setProperty(ICommonProperties.xAxisLabel, "false");
		
			//new multipleLines instance.
			IGraph graph = null;
			try{
				Class myclass = Class.forName(ApplicationPropertie.getInstance().horizontalBarGraph);
				graph = (IGraph)myclass.newInstance();
				graph.setDataSet(model);
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
			
			//Add color properties.
			graph.setProperties(model.getProperties());
			
			((Panel)mainform.getJPanel()).setGraph(graph);
			
			// set default GUI
			mainform.getTrendLineButton().setEnabled(false);
			mainform.getTrendLineButton().setText("ShowTrendLine");
			mainform.getEquationButton().setText("ShowLinearEquation");;
			mainform.getEquationButton().setEnabled(false);
			mainform.getHorizontalLineButton().setText("ShowHorizontalLine");
			mainform.getXYAxesButton().setText("ShowXYAxes");

			// Reset general GUI.
			mainform.getJPanel().repaint();
			return true;
	}
}
