package com.ksaas.logparse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class LogparseApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(LogparseApplication.class, args);
		Timer timer = new Timer();
		int begin = 1000; //timer starts after 1 second.
		int timeinterval = 10 * 30000; //timer executes every 10 seconds.
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				try {
					APIPings.Sac();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					APIPings.DAE();
				} catch (IOException e) {
					e.printStackTrace();
				}
				/*try {
					APIPings.KMS();
				} catch (IOException e) {
					e.printStackTrace();
				}*/
				try {
					APIPings.CP();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		},begin, timeinterval);
	}

}
