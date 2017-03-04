import java.util.*;
public class SafeDeque {
	Deque<Mine> d;
	
	SafeDeque(){
	}
	
	public void addFirst(Mine m){
		if (!m.equals(this.getFirst())){
			d.addFirst(m);
		}
	}
	
	public Mine removeFirst(){
		return d.removeFirst();
	}
	
	public Mine getFirst(){
		return d.getFirst();
	}
	
	public void addLast(Mine m){
		if (!m.equals(this.getLast())){
			d.addLast(m);
		}
	}
	
	public Mine removeLast(){
		return d.removeLast();
	}
	
	public Mine getLast(){
		return d.getLast();
	}
}
