package yunheTang.model;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Properties;

import javax.swing.JPanel;

import dataset.ICommonProperties;
import dataset.IDataSet;
import dataset.IGraph;


public class CartesianGraph extends Graph implements IGraph{

	public Properties properties = new Properties();
	
	int xAxes = 340;
	int yAxes = 170;
	
	IDataSet model;
	double max_X = 50;
	double max_Y = 50;
	double min_X = -50;
	double min_Y = -50;
	double height = 100;
	double length = 100;
	
	double k,b;
	int x,y;
	/**
	 * Create the panel.
	 */
	public CartesianGraph() {}
	
	boolean setDomain(){
		max_X = model.getMaxX();
		max_Y = model.getMaxY();
		min_X = model.getMinX();
		min_Y = model.getMinY();
		
		length = max_X - min_X;
		height = max_Y - min_Y;
		
		max_X += 0.25 * length;
		max_Y += 0.25 * height;
		min_X -= 0.25 * length;
		min_Y -= 0.25 * height;
		
		length = max_X - min_X;
		height = max_Y - min_Y;
		
		if(length == 0){
			if(max_X < 0){
				max_X = Math.abs(max_X) * 0.5;
				min_X = -max_X * 3;
			}
			else{
				max_X = max_X * 1.5;
				min_X = -max_X * ((double)1/(double)3);
			}
		}
		
		if(height == 0){
			if(max_Y < 0){
				max_Y = Math.abs(max_Y) * 0.5;
				min_Y = -max_Y * 3;
			}
			else{
				max_Y = max_Y * 1.5;
				min_Y = -max_Y * ((double)1/(double)3);
			}
		}
		length = max_X - min_X;
		height = max_Y - min_Y;
		
		return true;
	}
	
	@Override
	public void draw(Graphics g, JPanel panel){
		
		setDomain();
		
		if(properties.getProperty(ICommonProperties.trendLineVisible,"invalid").equals("true")){		//....................
			drawTreandLine(g);
			if(properties.getProperty(ICommonProperties.trendLineEquationVisible,"invalid").equals("true")){		//..................
				drawEquation(g);
			}
		}

		//205!!!!
		g.drawLine(0,0,0,170);
		g.drawLine(0,0,340,0);
		g.drawLine(0,170,340,170);
		g.drawLine(340,0,340,170);
		// Draw X/YAxes Line.
		drawXYAxes(g);
		// Draw points.
		if(model.size() <= 0){
			//
		}
		else{
			for(int i = 0; i < model.size(); i++){
				drawPoint(g, model.getCoordinate(i, 0), model.getCoordinate(i, 1));
			}
		}
		if(properties.getProperty(ICommonProperties.horizontalLines,"invalid").equals("true")){				//...........
	        drawHorizontalLine(g,y);
		}
	}
	
	void drawTreandLine(Graphics g){
		double sum_x = 0;
		double sum_x2 = 0;
		double sum_y = 0;
		double sum_xy = 0;
		
		int size = model.size();
		if(size == -1 ){
			// Exception
			return;
		}
		for(int i = 0; i < size; i++){
			sum_x += model.getCoordinate(i, 0);
			sum_x2 += Math.pow(model.getCoordinate(i, 0), 2);
			sum_y += model.getCoordinate(i, 1);
			sum_xy += model.getCoordinate(i, 0) * model.getCoordinate(i, 1);
		}
		
		k = (sum_xy - sum_x*sum_y/size)/(sum_x2 - Math.pow(sum_x, 2)/size);
		b = sum_y/size - k*(sum_x/size);
		
		int x1 = (int)((model.getMinX() - min_X) * (((double)340)/length));
		int y1 = (int)((max_Y - (k*model.getMinX()+b)) * (((double)170)/height));
		int x2 = (int)((model.getMaxX() - min_X) * (((double)340)/length));
		int y2 = (int)((max_Y - (k*model.getMaxX()+b)) * (((double)170)/height));

		g.drawLine(x1,y1,x2,y2);
	}
	
	void drawEquation(Graphics g){
		String sk = Double.toString(k);
		String sb = Double.toString(b);
		
		if(b<0){
			g.drawString("Y = "+sk+"X"+sb, 40, 190);
		}
		else{
			g.drawString("Y = "+sk+"X+"+sb, 40, 190);
		}
		
	}
	
	void drawXYAxes(Graphics g){
		boolean hasO = true;
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(2.0F));
		x = (int)(-min_X * (((double)340)/length));
		y = (int)(max_Y * (((double)170)/height));
		
		if(x < 0){
			x = 1;
			hasO = false;
		}
		else if(x > 340){
			x = 340;
			
			hasO = false;
		}
		
		if(y < 0){
			y = 1;
			hasO = false;
		}
		else if(y > 170){
			y = 170;
			hasO = false;
		}
		
		g2d.drawLine(0, y, 340, y);
		g2d.drawLine(x, 0, x, 170);
		
		if(hasO == true){
			g.drawString("o", x, y);
		}
		g2d.drawLine(340, y, 330, y-5);
		g2d.drawLine(340, y, 330, y+5);
		g2d.drawLine(x, 0, x-10, 5);
		g2d.drawLine(x, 0, x+10, 5);
		
		if(properties.getProperty(ICommonProperties.xAxisLabel,"invalid").equals("true")){                //............
			drawXYAxesMark(g2d);
		}
		
		
	}
	
	void drawXYAxesMark(Graphics2D g2d) {
		// Draw on X Axes.
        int d = x/34;
        for(int i = 0; i < d; i++){
        	if(y<4){
        		g2d.drawLine(x-(i+1)*34, y, x-(i+1)*34, y+4);
        	}
        	else{
        		g2d.drawLine(x-(i+1)*34, y, x-(i+1)*34, y-4);
        	}
        }
        for(int i = 0; i < 9-d; i++){
        	if(y<4){
        		g2d.drawLine(x+(i+1)*34, y, x+(i+1)*34, y+4);
        	}
        	else{
        		g2d.drawLine(x+(i+1)*34, y, x+(i+1)*34, y-4);
        	}
        }
        
		// Draw on Y Axes.
        d = y/34;
        for(int i = 0; i < d; i++){
        	if(x>336){
        		g2d.drawLine(x, y-(i+1)*34, x-4, y-(i+1)*34);
        	}
        	else{
        		g2d.drawLine(x, y-(i+1)*34, x+4, y-(i+1)*34);
        	}
        }
        for(int i = 0; i < 4-d; i++){
        	if(x>336){
        		g2d.drawLine(x, y+(i+1)*34, x-4, y+(i+1)*34);
        	}
        	else{
        		g2d.drawLine(x, y+(i+1)*34, x+4, y+(i+1)*34);
        	}
        }
		
	}
	
	void drawPoint(Graphics g, double x, double y){
		int ix = (int)((x - min_X)*((double)340/length));
		int iy = (int)((max_Y - y)*((double)170/height));
		
		g.fillOval(ix-2, iy-2, 4, 4);
		
	}


	@Override
	public void setDataSet(IDataSet ds) {
		// TODO Auto-generated method stub
		this.model = (Model) ds;
	}

	
	@Override
	public void setProperties(Properties p) {
		// TODO Auto-generated method stub
		properties = p;
	}

}
