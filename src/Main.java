import java.awt.event.KeyEvent;
import java.io.IOException;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws IOException {
	   Server.update("CONFIGURATIONS");
	   Server.update("BRAKE");
	   //Server.update("ACCELERATE " + (-0.922328895) + " " + 0.00451603684502747);
	   //moveTo(4119.763502108395, 7439.664626567952, 5000, 5000);
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
		String returnMessage = Server.update("ACCELERATE " + angle + " 1");
		if (returnMessage.contains("ERROR")) return false;
		return true;
		
	}
	
	
}
