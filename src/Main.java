import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	public static String USER, PASS;
	
	public static void main(String[] args) throws IOException {     
        File file = new File("user.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		USER = bufferedReader.readLine();
		PASS = bufferedReader.readLine();
		bufferedReader.close();
		
	   Server.update("CONFIGURATIONS");
	   Server.update("ACCELERATE 1 1");
	   boolean movingTo = false;
	   while(true) {
		   Status status = new Status(Server.update("STATUS")); //Get status
		   if (status.hasMine() && !movingTo) {
			   System.out.println("GOT ONE");
			   movingTo = true;
			   moveTo(status.getMines().get(0).getx() , status.getMines().get(0).gety());
		   }
	   }
	    //moveTo(10000,0);
	}
	
	public static boolean bomb(double x, double y) {
		String returnMessage = Server.update("BOMB " + x + " " + y);
		if (returnMessage.contains("ERROR")) return false;
		return true;
	}
	
	public static boolean moveTo(double endX, double endY) {
		Server.update("BRAKE"); //Stop movement
		try {
			Thread.sleep(3500); //Wait for stop to complete
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Status status = new Status(Server.update("STATUS")); //Get status	
		double dx = endX - status.getPlayer().getx();
		double dy = endY - status.getPlayer().gety();
		if (dx == 0) return false; //avoid dividing by 0
		double angle = Math.atan(Math.abs(dy/dx)); //find the angle in rad	
		if (dx < 0 && dy < 0) { //top left
			angle = Math.PI + angle;
		}
		if (dx < 0 && dy > 0) { //bottom left
			angle = Math.PI - angle;
			System.out.println("3nd");
		}
		if (dx > 0 && dy < 0){ // //top left
			angle = 2*Math.PI - angle;
			System.out.println("4th");
		}
		String returnMessage = Server.update("ACCELERATE " + angle + " 1"); //send the command
		if (returnMessage.contains("ERROR")) return false;
		return true;
	}
	
	
}
