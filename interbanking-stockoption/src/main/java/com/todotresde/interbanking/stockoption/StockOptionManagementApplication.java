package com.todotresde.interbanking.stockoption;

import com.todotresde.interbanking.stockoption.service.FileUploadService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * The type StockOption management application.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class StockOptionManagementApplication implements CommandLineRunner {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(StockOptionManagementApplication.class, args);
	}

	/**
	 * The File upload service.
	 */
	@Resource
	FileUploadService fileUploadService;

	/**
	 * Cors configurer web mvc configurer.
	 *
	 * @return the web mvc configurer
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer(){
			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

	@Override
	public void run(String... arg) throws Exception {
		fileUploadService.deleteAll();
		fileUploadService.init();
	}
}
