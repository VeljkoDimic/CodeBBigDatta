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

		   if (status.hasMine()) {
			   System.out.println("GOT ONE");
			   
			   if (!movingTo) {
				   Server.update("BRAKE"); //Stop movement
					try {
						Thread.sleep(3500); //Wait for stop to complete
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					movingTo = true;
					moveTo(status.getMines().get(0).getx() , status.getMines().get(0).gety());
			   }			   
			   if (Math.abs(status.getPlayer().getx() - status.getMines().get(0).getx()) < 15 ) {
				   if (Math.abs(status.getPlayer().gety() - status.getMines().get(0).gety()) < 15) {
					   Server.update("BRAKE");
					   System.out.println("Brake");
					   try {
							Thread.sleep(3500); //Wait for stop to complete
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					   movingTo = false;
				   }
			   }
		   }
		   else {
			   Server.update("ACCELERATE 1 1");
			   movingTo = false;
		   }
		   try {
			Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //Wait for stop to complete 
	   }
	    //moveTo(10000,0);
	}
	
	public static boolean bomb(double x, double y) {
		String returnMessage = Server.update("BOMB " + x + " " + y);
		if (returnMessage.contains("ERROR")) return false;
		return true;
	}
	
	public static boolean moveTo(double endX, double endY) {
		
		
		
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
		String returnMessage = Server.update("ACCELERATE " + angle + " .2"); //send the command
		if (returnMessage.contains("ERROR")) return false;
		return true;
	}
	/*
	public static boolean moveTo(double endX, double endY) {
		Status status = new Status(Server.update("STATUS")); //Get status	
		double dx = endX - status.getPlayer().getx();
		double dy = endY - status.getPlayer().gety();
		double vx = status.getPlayer().getdx();
		double vy = status.getPlayer().getdx();
		double ax = vx - dx/Math.pow(Math.pow(dx, 2) + Math.pow(dy, 2), .5);
		double ay = -1*vy + dy/Math.pow(Math.pow(dx, 2) + Math.pow(dy, 2), .5);
		
		if (ax == 0) return false; //avoid dividing by 0
		double angle = Math.atan(Math.abs(ay/ax)); //find the angle in rad	
		if (ax < 0 && ay < 0) { //top left
			angle = Math.PI + angle;
		}
		if (ax < 0 && ay > 0) { //bottom left
			angle = Math.PI - angle;
			System.out.println("3nd");
		}
		if (ax > 0 && ay < 0){ // //top left
			angle = 2*Math.PI - angle;
			System.out.println("4th");
		}
		String returnMessage = Server.update("ACCELERATE " + angle + " 1"); //send the command
		if (returnMessage.contains("ERROR")) return false;
		return true;
	}
	*/
}
