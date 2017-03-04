import java.util.*;

public class Status {
	Player player;
	int numMines;
	static double x;
	static double y;
	List<Mine> mines = new ArrayList<Mine>();
	int numOpp;
	List<Player> opponents = new ArrayList<Player>();
	int numBomb;
	List<Bomb> bombs = new ArrayList<Bomb>();
	
	private Status() {}
	
	Status(String in){
		StringTokenizer st = new StringTokenizer(in);
		if (st.hasMoreTokens() && st.nextToken().equals("STATUS_OUT")){
			x = Double.parseDouble(st.nextToken());
			y = Double.parseDouble(st.nextToken());
			double dx = Double.parseDouble(st.nextToken());
			double dy = Double.parseDouble(st.nextToken());
			player = new Player(x, y, dx, dy);
		}
		
	}
	
	public Player getPlayer(){
		return player;
	}
}
