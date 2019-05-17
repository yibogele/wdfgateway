package com.example.wdfgatewayservice;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.TEXT_PLAIN;

@SpringBootApplication
@EnableAsync
@EnableEurekaClient
@RestController
public class WdfGatewayServiceApplication {

//	@Bean
//	public GsonHttpMessageConverter gsonHttpMessageConverter(Gson gson) {
//		GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
//		converter.setGson(gson);
//		List<MediaType> supportedMediaTypes = converter.getSupportedMediaTypes();
//		if (! supportedMediaTypes.contains(TEXT_PLAIN)) {
//			supportedMediaTypes = new ArrayList<>(supportedMediaTypes);
//			supportedMediaTypes.add(TEXT_PLAIN);
//			converter.setSupportedMediaTypes(supportedMediaTypes);
//		}
//		return converter;
//	}

	public static void main(String[] args) {
		SpringApplication.run(WdfGatewayServiceApplication.class, args);
	}
}
