package mem.MrPonerYea.PoolControl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PoolControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoolControlApplication.class, args);
	}

}
