/*
 * Player with velocity and x and y location
 */
public class Player extends gameObject{
	private double dx;
	private double dy;
	
	Player(){
		super();
		this.dx = 0;
		this.dy = 0;
	}
	
	Player(double x, double y, double dx, double dy){
		super(x, y);
		this.dx = dx;
		this.dy = dy;
	}
	
	public double getdx(){
		return dx;
	}
	
	public double getdy(){
		return dy;
	}
	
	public void setdx(double dx){
		this.dx = dx;
	}
	
	public void setdy(double dy){
		this.dy = dy;
	}

}
