import java.awt.event.KeyEvent;
import java.io.IOException;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws IOException {
	   Server.update("STATUS");
	   System.out.println(bomb(7968.434070197629, 5000));  
	}
	public static boolean bomb(double x, double y) {
		String returnMessage = Server.update("BOMB " + x + " " + y);
		if (returnMessage.contains("ERROR")) return false;
		return true;
	}
	
	
}
