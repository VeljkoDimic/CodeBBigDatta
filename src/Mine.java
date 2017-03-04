/*
 * Mine with location and owner
 */
public class Mine extends gameObject{
	private String owner;
	Mine(){
		super();
		owner = "";
	}
	
	Mine(double x, double y){
		super(x, y);
		this.owner = "";
	}
	
	Mine(double x, double y, String owner){
		super(x, y);
		this.owner = owner;
	}
	
	boolean hasOwner(){
		return owner.equals("");
	}
	String getOwner(){
		return owner;
	}
}
