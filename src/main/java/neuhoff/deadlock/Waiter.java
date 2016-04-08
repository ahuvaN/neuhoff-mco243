package neuhoff.deadlock;

//philosopher must ask waiter before picking up forks
public class Waiter {
	
	public synchronized boolean tryToEat(Fork a, Fork b){
		if (!a.isInUse() && !b.isInUse()){
			a.setInUse(true);
			b.setInUse(true);
			return true;
		}
		return false;
	}

}
