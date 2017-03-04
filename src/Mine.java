
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
}
