package yunheTang.model;

public class Point {
	double x;
	double y;
	
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void setValues(double x, double y){
		this.x = x;
		this.y = y;
	}

	public double getXofPoint(){
		return this.x;
	}
	
	public double getYofPoint(){
		return this.y;
	}

}



