package neuhoff.deadlock;

public class DiningPhilosophers {

	public static void main(String[] args) {

		Waiter waiter = new Waiter();
		
		Fork f1 = new Fork(1);
		Fork f2 = new Fork(2);
		Fork f3 = new Fork(3);
		Fork f4 = new Fork(4);
		Fork f5 = new Fork(5);
		
		Philosopher a = new Philosopher("A", waiter, f1, f2);
		Philosopher b = new Philosopher("B", waiter, f2, f3);
		Philosopher c = new Philosopher("C", waiter, f3, f4);
		Philosopher d = new Philosopher("D", waiter, f4, f5);
		Philosopher e = new Philosopher("E", waiter, f1, f5);
		
		a.start();
		b.start();
		c.start();
		d.start();
		e.start();
		
	}

}