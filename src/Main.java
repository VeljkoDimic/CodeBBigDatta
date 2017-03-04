import java.awt.event.KeyEvent;
import java.io.IOException;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws IOException {
	   Server.update("STATUS");
	   //Server.update("BRAKE");
	   //Server.update("ACCELERATE " + Math.PI * 3/2 + " .5");
	   moveTo(3060.787526453938, 7718.087931618746, 5000, 5000);
	   //System.out.println(bomb(7968.434070197629, 5000));  
	}
	public static boolean bomb(double x, double y) {
		String returnMessage = Server.update("BOMB " + x + " " + y);
		if (returnMessage.contains("ERROR")) return false;
		return true;
	}
	public static boolean moveTo(double startX, double startY, double endX, double endY) {
		double dx = endX - startX;
		double dy = endY - startY;
		double angle = Math.atan(dy/dx);
		if (dx < 0 && dy > 0) {
			angle += Math.PI/2;
		}
		if (dx < 0 && dy < 0) {
			angle += Math.PI;
		}
		if (dx > 0 && dy > 0) {
			angle += Math.PI * 3/2;
		}
		Server.update("ACCELERATE " + angle + " 1");
		return true;
		
	}
	
	
}
