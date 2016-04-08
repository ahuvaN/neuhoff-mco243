package neuhoff.deadlock;

import java.util.logging.Logger;

public class Philosopher extends Thread {

	private static final Logger LOG = Logger.getLogger(Philosopher.class.getName());
	
	private Fork f1;
	private Fork f2;
	private String name;
	private Waiter waiter;

	public Philosopher(String name, Waiter water, Fork f1, Fork f2) {
		this.name = name;
		this.f1 = f1;
		this.f2 = f2;
		waiter = water;
	}

	public void run() {
		while (true) {
			think();
			eat();
		}
	}
	
	public void eat() {
		
		LOG.info(this.toString() + " attempting to eat");
		if (waiter.tryToEat(f1, f2)){
			LOG.info(this.toString() + " eating");
			waitForAFewSeconds(10);
			f1.setInUse(false);
			f2.setInUse(false);
			LOG.info(this.toString() + " done eating");
		}
	}
	
	public void think() {
		waitForAFewSeconds(5);
		LOG.info(this.toString() + " thinking");
	}

	private void waitForAFewSeconds(int seconds) {
		try {
			Thread.sleep((long) (seconds * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Philosopher [name=" + name + "]";
	}
	
	
}
