import java.awt.event.KeyEvent;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
	   Server.update("CONFIGURATIONS");
	   Server.update("BRAKE");
	   Server.update("ACCELERATE " +  Math.PI * 11/6 + " 1");
	   
	   //System.out.println(status.x);
	   for( int i = 0; i < 10000; i++) {
		   Status status = new Status(Server.update("STATUS"));
		   moveTo(status.getPlayer().getx(),status.getPlayer().gety(),5000,50);
	   }
	   //bomb(Status.x, Status.y);
	}
	public static boolean bomb(double x, double y) {
		String returnMessage = Server.update("BOMB " + x + " " + y);
		if (returnMessage.contains("ERROR")) return false;
		return true;
	}
	public static boolean moveTo(double startX, double startY, double endX, double endY) {
		Status status = new Status(Server.update("STATUS"));
		double vx = status.getPlayer().getdx() / 0.025;
		double vy = status.getPlayer().getdx() / 0.025;		
		double dx = endX - startX;
		double dy = endY - startY;
		double ax = vx - dx/(Math.pow((Math.pow(dx,2) + Math.pow(dy, 2)),0.5));
		return true;
		/*
		System.out.println("DY IS " + dy + " DX IS " + dx);
		if (dx == 0) return false;
		double angle = Math.atan(Math.abs(dy/dx));
		System.out.println(angle);
		if (dx < 0 && dy < 0) {
			angle += Math.PI;
		}
		if (dx < 0 && dy > 0) {
			angle += Math.PI * 1/2;
		}
		if (dx > 0 && dy < 0) {
			angle += Math.PI * 3/2;
		}
		String returnMessage = Server.update("ACCELERATE " + angle + " 1");
		if (returnMessage.contains("ERROR")) return false;
		return true;
		*/
	}
	
	
}
