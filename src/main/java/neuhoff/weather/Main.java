package neuhoff.weather;

import java.util.concurrent.LinkedBlockingQueue;

public class Main {

	public static void main(String[] args) {
		
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		
		CheckWeatherThread check = new CheckWeatherThread(queue);
		NotifyWeatherThread notify = new NotifyWeatherThread(queue);
		
		check.start();
		notify.start();
	}

}
