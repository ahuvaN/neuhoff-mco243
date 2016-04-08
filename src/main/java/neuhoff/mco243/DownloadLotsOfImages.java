package neuhoff.mco243;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class DownloadLotsOfImages {

	private AtomicInteger total = new AtomicInteger(0);
	
	//public synchronized static void add(){}....
	public synchronized void add(){
		total.incrementAndGet();
	}
	
	public void downloadAll() throws InterruptedException{
		ExecutorService service = Executors.newFixedThreadPool(6);
		for (int i = 0; i < 10000; i++){
			Runnable runnable = new Runnable(){

				@Override
				public void run() {
					add();
				}
			};
			service.execute(runnable);
		}
		service.shutdown();
		service.awaitTermination(10, TimeUnit.SECONDS);
		System.out.println(total);
	}
	
	public static void main(String[] args) throws InterruptedException{
		
		new DownloadLotsOfImages().downloadAll();
	}
}
