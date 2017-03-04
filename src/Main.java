import java.awt.event.KeyEvent;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
	   Server.update("CONFIGURATIONS");
	   //Server.update("BRAKE");
	   //Server.update("ACCELERATE " +  Math.PI * 11/6 + " 1");
	   
	    
		Status status = new Status(Server.update("STATUS"));
		//System.out.println(status.getPlayer().getx() + "Y IS " + status.getPlayer().gety());
		
	    moveTo(status.getPlayer().getx(),status.getPlayer().gety(),5000,5000);
	   //bomb(Status.x, Status.y);
	    for (int i = 0; i < 10000; i++) {
	    	Server.update("STATUS");
	    }
	}
	public static boolean bomb(double x, double y) {
		String returnMessage = Server.update("BOMB " + x + " " + y);
		if (returnMessage.contains("ERROR")) return false;
		return true;
	}
	public static boolean moveTo(double startX, double startY, double endX, double endY) {
		Server.update("BRAKE");
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Status status = new Status(Server.update("STATUS"));
		//double vx = status.getPlayer().getdx() / 0.025;
		//double vy = status.getPlayer().getdx() / 0.025;		
		double dx = endX - startX;
		double dy = endY - startY;
		//double ax = vx - dx/(Math.pow((Math.pow(dx,2) + Math.pow(dy, 2)),0.5));
		//double ay = -1*vy - dy/(Math.pow((Math.pow(dx,2) + Math.pow(dy, 2)),0.5));
		if (dx == 0) return false;
		double angle = Math.atan(Math.abs(dy/dx));
		
		if (dx < 0 && dy < 0) {
			angle = Math.PI + angle;
			System.out.println("2nd");
		}
		if (dx < 0 && dy > 0) {
			angle = Math.PI - angle;
			System.out.println("3nd");
		}
		if (dx > 0 && dy < 0){
			angle = 2*Math.PI - angle;
			System.out.println("4th");
		}
		System.out.println(dx + " " + dy);
		String returnMessage = Server.update("ACCELERATE " + angle + " 1");
		if (returnMessage.contains("ERROR")) return false;
		return true;
	}
	
	
}
