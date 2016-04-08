package neuhoff.weather;

import java.util.concurrent.LinkedBlockingQueue;

//producer
public class CheckWeatherThread extends Thread {

	private LinkedBlockingQueue<String> queue;

	public CheckWeatherThread(LinkedBlockingQueue<String> q) {
		queue = q;
	}

	public void run() {

		while (true) {

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			notifyBadWeather();
		}
	}

	private void notifyBadWeather() {
		queue.add("Bad Weather");
	}
}
