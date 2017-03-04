import java.util.*;

public class Status {
	private Player player;
	private int numMines;
	private List<Mine> mines = new ArrayList<Mine>();
	private int numOpp;
	private List<Player> opponents = new ArrayList<Player>();
	private int numBomb;
	private List<Bomb> bombs = new ArrayList<Bomb>();
	
	Status() {
		player = new Player();
		numMines = 0;
		numOpp = 0;
		numBomb = 0;
	}

	Status(String in){
		
		StringTokenizer st = new StringTokenizer(in);
		if (st.hasMoreTokens()){
			String temp = st.nextToken();
			if (temp.equals("STATUS_OUT")){
				double x = Double.parseDouble(st.nextToken());
				double y = Double.parseDouble(st.nextToken());
				double dx = Double.parseDouble(st.nextToken());
				double dy = Double.parseDouble(st.nextToken());
				player = new Player(x, y, dx, dy);
			}
		}
		if (st.hasMoreTokens() && st.nextToken().equals("MINES")){
			numMines = Integer.parseInt(st.nextToken());
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
		}
		
		
		int count = 0;
		for (int i = numMines-1; i >= 0; i --){
			if (mines.get(i).getOwner().equals(Main.USER)){
				count ++;
				mines.remove(i);
			}
		}
		numMines -= count;
		
		
		if (st.hasMoreTokens() && st.nextToken().equals("PLAYERS")){
			numOpp = Integer.parseInt(st.nextToken());
			for (int i = 0; i < numOpp; i ++){
				double x = Double.parseDouble(st.nextToken());
				double y = Double.parseDouble(st.nextToken());
				double dx = Double.parseDouble(st.nextToken());
				double dy = Double.parseDouble(st.nextToken());
				Player player = new Player(x,y,dx,dy);
				opponents.add(player);
			}
		}
		
		
		if (st.hasMoreTokens() && st.nextToken().equals("BOMBS")){
			numBomb = Integer.parseInt(st.nextToken());
			for (int i = 0; i < numBomb; i ++){
				double x = Double.parseDouble(st.nextToken());
				double y = Double.parseDouble(st.nextToken());
				Bomb bomb = new Bomb(x,y);
				bombs.add(bomb);
			}
		}
		
		

	}
	
	public Player getPlayer(){
		return player;
	}
	
	public List<Mine> getMines(){
		return mines;
	}
	
	public List<Player> getOpponents(){
		return opponents;
	}
	
	public List<Bomb> getBombs(){
		return bombs;
	}
	
	public boolean hasMine(){
		return numMines!= 0;
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
