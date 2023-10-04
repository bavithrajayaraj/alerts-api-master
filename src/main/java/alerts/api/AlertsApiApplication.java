package alerts.api;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlertsApiApplication {

	static Logger log = LoggerFactory.getLogger(AlertsApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AlertsApiApplication.class, args);
		log.info("Alerts API started successfully at {}", LocalDateTime.now());
	}

}
