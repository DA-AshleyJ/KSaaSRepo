package com.apiping.apipings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class apiPingApplication {
	public static void main(String[] args) {
		SpringApplication.run(apiPingApplication.class, args);
		Timer timer = new Timer();
		int begin = 90000; //timer starts after 1 second.
		int timeinterval = 10 * 90000; //timer executes every 10 seconds.
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				TimeTaskPoll();
			}
			public void TimeTaskPoll() {
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
				try {
					APIPings.KMS();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					APIPings.CP();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					timer.scheduleAtFixedRate(new TimerTask() {
						@Override
						public void run() {TimeTaskPoll();} }, begin, timeinterval);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, begin, timeinterval);
	}
}
