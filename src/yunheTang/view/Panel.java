package yunheTang.view;

import java.awt.Graphics;

import javax.swing.JPanel;

import yunheTang.model.*;
import dataset.IGraph;

public class Panel extends JPanel{
	
	Model model;
	IGraph igraph;
	
	Panel(Model model){
		this.model = model;
		this.igraph = new CartesianGraph();
		this.igraph.setDataSet(model);
	}
	
	public boolean setGraph(IGraph g){
		this.igraph = g;
		return true;
	}
	
	public IGraph getGraph(){
		return igraph;
	}
	
	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent (g);
		igraph.draw(g,this);
	}
}
