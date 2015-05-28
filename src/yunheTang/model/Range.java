package yunheTang.model;

public class Range {
	double max_x;
	double max_y;
	double min_x;
	double min_y;
	
	public Boolean setRangeValues(double maxx,
							double maxy,
							double minx,
							double miny){
		max_x = maxx;
		max_y = maxy;
		min_x = minx;
		min_y = miny;
		return true;
	}
	
	public Range(){
		max_x = 50;
		max_y = 50;
		min_x = -50;
		min_y = -50;
	}
	
	public double setLargestX(double maxx){
		return this.max_x = maxx;
	}
	
	public double setLargestY(double maxy){
		return this.max_y = maxy;
	}
	
	public double setSmallestX(double minx){
		return this.min_x = minx;
	}
	
	public double setSmallestY(double miny){
		return this.min_y = miny;
	}
	
	public double getLargestX(){
		return max_x;
	}

	public double getLargestY(){
		return max_y;
	}

	public double getSmallestX(){
		return min_x;
	}

	public double getSmallestY(){
		return min_y;
	}
	
}
