package controller;

import java.util.Properties;

import propertie.ApplicationPropertie;
import yunheTang.model.ColumnGraph;
import yunheTang.model.Model;
import yunheTang.model.MultipleLinesGraph;
import yunheTang.view.MainForm;
import yunheTang.view.Panel;
import dataset.ICommonProperties;
import dataset.IGraph;

public class MultipleLinesGraphController {
	Model model;
	
	public MultipleLinesGraphController(Model model){
		this.model = model;
	}
	
	public boolean act(MainForm mainform){
		// Prepare properties.
		model.resetProperties();
		model.getProperties().setProperty(ICommonProperties.horizontalLines, "false");
		model.getProperties().setProperty(ICommonProperties.xAxisLabel, "false");
		
		if(((Panel)(mainform.getJPanel())).getGraph() instanceof MultipleLinesGraph){
			// Already multipleLines, do nothing.
			return false;
		}
		else{
			//new multipleLines instance.
			IGraph graph = null;
			try{
				Class myclass = Class.forName(ApplicationPropertie.getInstance().multipleLines);
				graph = (IGraph)myclass.newInstance();
				graph.setDataSet(model);
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}

			//Add color properties.
			Properties p = model.getProperties();
			p.setProperty("color1", ICommonProperties.color1);
			p.setProperty("color2", ICommonProperties.color2);
			graph.setProperties(p);
			
			((Panel)mainform.getJPanel()).setGraph(graph);
			
			// set default GUI
			mainform.getTrendLineButton().setEnabled(false);
			mainform.getTrendLineButton().setText("ShowTrendLine");
			mainform.getEquationButton().setText("ShowLinearEquation");;
			mainform.getEquationButton().setEnabled(false);
			mainform.getHorizontalLineButton().setText("ShowHorizontalLine");
			mainform.getXYAxesButton().setText("ShowXYAxes");
		}

		// Reset general GUI.
		mainform.getJPanel().repaint();
		return true;
	}
	
}
