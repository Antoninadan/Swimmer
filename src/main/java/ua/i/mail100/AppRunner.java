package ua.i.mail100;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import ua.i.mail100.config.FileStorageConfig;

@EnableSwagger2
@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageConfig.class
})
public class AppRunner {

	public static void main(String[] args) {
		SpringApplication.run(AppRunner.class, args);

	}

}
