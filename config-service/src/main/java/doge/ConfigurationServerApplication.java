package doge;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * This will look in the .git repository configured in {@code application.yml}
 * and attempt to resolve configuration files in the remote .git repository
 * matching the {@code spring.application.name}.properties. Thus
 * {@code spring.application.name} of {@code account-service} would yeild
 * all the values in {@code account-service.properties} being available
 * through the {@link org.springframework.core.env.Environment}.
 * <p>
 * You can confirm that this works by visiting $URI/$APP_NAME/default. This will show you for example the values in
 * {@code doges.yml} if your {@code $APP_NAME} is 'doges'
 * <p>
 *
 * @author Josh Long
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableEurekaClient
@EnableConfigServer
public class ConfigurationServerApplication {

    @Bean
    CommandLineRunner init(Environment e) {
        return args -> {
            System.out.println("default profile: " + e.getProperty("spring.profiles.default"));
            System.out.println("eureka credentials: " + e.getProperty("vcap.services.eureka-service.credentials.uri"));
            System.out.println("eureka.client.serviceUrl.defaultZone: " + e.getProperty("eureka.client.serviceUrl.defaultZone"));
            System.out.println("eureka.client.instance.hostname: " + e.getProperty("eureka.client.instance.hostname"));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationServerApplication.class, args);
    }
}
