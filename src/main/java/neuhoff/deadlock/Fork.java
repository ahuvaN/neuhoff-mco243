package neuhoff.deadlock;

public class Fork {
	
	private int number;
	private boolean inUse;

	public Fork(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Fork [number=" + number + "]";
	}

	public boolean isInUse() {
		return inUse;
	}

	public void setInUse(boolean using) {
		inUse = using;
	}
	
	
	
}
