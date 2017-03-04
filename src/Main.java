import java.awt.event.KeyEvent;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
	   Server.update("CONFIGURATIONS");
	   //Server.update("BRAKE");
	   Server.update("ACCELERATE 0 1");
	   
	   //System.out.println(status.x);
	   for( int i = 0; i < 10000; i++) {
		   Status status = new Status(Server.update("STATUS"));
		   moveTo(status.x,status.y,9000,9000);
	   }
	   //bomb(Status.x, Status.y);
	}
	public static boolean bomb(double x, double y) {
		String returnMessage = Server.update("BOMB " + x + " " + y);
		if (returnMessage.contains("ERROR")) return false;
		return true;
	}
	public static boolean moveTo(double startX, double startY, double endX, double endY) {
		double dx = endX - startX;
		double dy = endY - startY;
		if (dx == 0) return false;
		double angle = Math.atan(dy/dx);
		System.out.println(angle);
		if (dx < 0 && dy > 0) {
			angle += Math.PI/2;
		}
		if (dx < 0 && dy < 0) {
			angle += Math.PI;
		}
		if (dx > 0 && dy > 0) {
			angle += Math.PI * 3/2;
		}
		String returnMessage = Server.update("ACCELERATE " + angle + " 1");
		if (returnMessage.contains("ERROR")) return false;
		return true;
		
	}
	
	
}
