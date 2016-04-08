package neuhoff.weather;

import java.util.concurrent.LinkedBlockingQueue;

//consumer
public class NotifyWeatherThread extends Thread {

	private volatile boolean badWeather;
	private LinkedBlockingQueue<String> queue;

	public NotifyWeatherThread(LinkedBlockingQueue<String> q) {
		queue = q;
	}

	public boolean isBadWeather() {
		return badWeather;
	}

	public void setBadWeather(boolean badWeather) {
		this.badWeather = badWeather;
	}

	public void run() {

		if (badWeather) {
			try {
				String message = queue.take();
				soundAlarm(message);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void soundAlarm(String message) {
		for (int i = 0; i < 1000000; i++){
			//send email using message
		}
	}
}
