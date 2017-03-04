import java.util.*;

public class Status {
	Player player;
	int numMines;
	List<Mine> mines = new ArrayList<Mine>();
	int numOpp;
	List<Player> opponents = new ArrayList<Player>();
	int numBomb;
	List<Bomb> bombs = new ArrayList<Bomb>();
	
	Status(String in){
		StringTokenizer st = new StringTokenizer(in);
		if (st.hasMoreTokens() && st.nextToken().equals("STATUS_OUT")){
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			double dx = Double.parseDouble(st.nextToken());
			double dy = Double.parseDouble(st.nextToken());
			player = new Player(x, y, dx, dy);
		}
		if (st.hasMoreTokens() && st.nextToken().equals("MINES")){
			numMines = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < numMines; i ++){
			String temp = st.nextToken();
			if (isNumeric(temp)){
				double x = Double.parseDouble(temp);
				double y = Double.parseDouble(st.nextToken());
				Mine mine = new Mine(x,y);
				mines.add(mine);
			}
			else{
				double x = Double.parseDouble(st.nextToken());
				double y = Double.parseDouble(st.nextToken());
				Mine mine = new Mine(x,y,temp);
				mines.add(mine);
			}
		}
		
		if (st.hasMoreTokens() && st.nextToken().equals("PLAYERS")){
			numOpp = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < numOpp; i ++){
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			double dx = Double.parseDouble(st.nextToken());
			double dy = Double.parseDouble(st.nextToken());
			
		}
		
		
		
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public static boolean isNumeric(String str){  
		try{  
			double d = Double.parseDouble(str);  
		}  
		catch(NumberFormatException nfe){  
			return false;  
		}  
		return true;  
	}
}
