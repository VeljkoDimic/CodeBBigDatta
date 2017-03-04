import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Main {
	public static String USER, PASS;
	static boolean hasTarget = false;
	static Deque<Mine> mineStack = new LinkedList<Mine>();
	 
	public static void main(String[] args) throws IOException {     

	   getCredentials();
	   
	   Server.update("CONFIGURATIONS");
	   Server.update("ACCELERATE 1 1");
	    
	   while(true) { //Game Loop	   
		   
		   Scan scan = new Scan();
		   List<Mine> mines = scan.update();		   
		   if (mines.size() > 0) {
			   for (int i = 0; i < mines.size(); i++) {
				   if (!mines.get(i).equals(mineStack.getLast())){
					   mineStack.addLast(mines.get(i));
				   }
			   }
		   }
		   
		   Status status = new Status(Server.update("STATUS")); //Get status
		   if (status.hasMine()) {
			   for (int i = 0; i < status.getMines().size(); i++) {	   
				   if (mineStack.size() > 0 && !status.getMines().get(i).equals(mineStack.getFirst())){
					   mineStack.addFirst(status.getMines().get(i));
				   }
				   else if (mineStack.size() == 0) {
					   mineStack.addFirst(status.getMines().get(i));
				   }
			   }
				   
		   }
		   
		   if (!hasTarget && mineStack.size() > 0) {
			   System.out.println("Target Aquired");
			   Server.update("BRAKE"); //Stop movement
			   while (true){
				   Status speed = new Status(Server.update("STATUS")); //Get status
				   if (Math.pow(speed.getPlayer().getdy(),2) + Math.pow(speed.getPlayer().getdx(),2) < .001) break;
				   try {
					Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   
			   }
				
				hasTarget = true;
				moveTo(mineStack.getFirst().getx() , mineStack.getFirst().gety());
		   }
		   
		   else if (hasTarget) {
			   System.out.println("Hunting");
			   moveTo(mineStack.getFirst().getx() , mineStack.getFirst().gety());
			   if (Math.abs(status.getPlayer().getx() - mineStack.getFirst().getx()) < 20 ) {
				   if (Math.abs(status.getPlayer().gety() - mineStack.getFirst().gety()) < 20) {
					   while (true){
						   Status speed = new Status(Server.update("STATUS")); //Get status
						   if (Math.pow(speed.getPlayer().getdy(),2) + Math.pow(speed.getPlayer().getdx(),2) < .001) break;
						   try {
							Thread.sleep(20);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}   
					   }			   
				   }
			   }
			   
			   System.out.println(mineStack.getFirst().getOwner());
			   if (mineStack.getFirst().getOwner().equals(USER)) {
				   System.out.println("Captured");
				   hasTarget = false;
				   mineStack.removeFirst();
			   }
		   }			   
		   else {
			   Server.update("ACCELERATE 1 1");
		   }
		   
		   try {
			Thread.sleep(200);
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
		String returnMessage = Server.update("ACCELERATE " + angle + " 1"); //send the command
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
	
	private static void getCredentials() throws IOException {
        File file = new File("user.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		USER = bufferedReader.readLine();
		PASS = bufferedReader.readLine();
		bufferedReader.close();
	}
}
