package dataset;

public interface ICommonProperties {
	// mandatory properties for all graphs
	public final String horizontalLines          = "horizontalLines";
	public final String xAxisLabel               = "xAxisLabel";
	public final String yAxisLabel               = "yAxisLabel";
	
	// mandatory properties for cartesian plots
	public final String trendLineVisible         = "trendLineVisible";
	public final String trendLineEquationVisible = "trendLineEquationVisible";
	
	// optional properties (comma-separated R,G,B values all int 0-255)
	public final String color1                   = "255,0,0"; 	//red
	public final String color2                   = "128,0,255"; //blue
	
	// mandatory system properties
	public final String cartesian                = "cartesian";   
	public final String column                   = "column";      
	public final String horizontalBarGraph       = "horizontalBarGraph";   
	public final String multipleLines            = "multipleLines";	
}