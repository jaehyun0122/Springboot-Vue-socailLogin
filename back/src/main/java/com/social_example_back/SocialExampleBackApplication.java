package com.social_example_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.social_example_back.config.properties.AppProperties;
import com.social_example_back.config.properties.CorsProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    CorsProperties.class,
    AppProperties.class
})
public class SocialExampleBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialExampleBackApplication.class, args);
	}

}
