import java.util.*;
/*
 * Scans for mines, players, and bombs
 */
public class Scan {
	private double x;
	private double y;
	
	Scan(){
		x = 5000;
		y = 7000;
	}
	
	Scan(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	List<Mine> update(){
		Status s = new Status(Server.update("SCAN " + x + " " + y));
		x += 1450;
		y += 483;
		x %= 10000;
		y %= 10000;
		return s.getMines();
	}
	
	
}
