import java.util.*;
public class SafeDeque {
	Deque<Mine> d;
	
	SafeDeque(){
		d = new LinkedList<Mine>();
	}
	
	public void addFirst(Mine m){
		if (this.getFirst() != null && !m.equals(this.getFirst())){
			d.addFirst(m);
		}
	}
	
	public Mine removeFirst(){
		return d.removeFirst();
	}
	
	public Mine getFirst(){
		if (d.getFirst() != null) 
			return d.getFirst();
		return null;
	}
	
	public void addLast(Mine m){
		if (this.getLast() != null && !m.equals(this.getLast())){
			d.addLast(m);
		}
	}
	
	public Mine removeLast(){
		return d.removeLast();
	}
	
	public Mine getLast(){
		return d.getLast();
	}
	
	public int size() {
		return d.size();
	}
}
