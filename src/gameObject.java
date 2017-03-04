/* 
 * Object for players, bombs, mines with x and y position
 */
public class gameObject {
	private double x;
	private double y;
	
	gameObject(){
		this.x = 0;
		this.y = 0;
	}
	
	gameObject(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double getx(){
		return x;
	}
	
	public double gety(){
		return y;
	}
	
	public void setx(double x){
		this.x = x;
	}
	
	public void sety(double y){
		this.y = y;
	}

	public boolean equals(gameObject o){
		return (this.x == o.getx() && this.y == o.gety());
	}
}
