package com.example.demo;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import feign.Feign;


@EnableFeignClients
@EnableEurekaClient
@RestController
@EnableCircuitBreaker
@SpringBootApplication
public class ConsumerServiceApplication {
	
	/*@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient discoveryClient;*/
	
	@Autowired
	private GreetClient greetClient;
	
	
	

	public static void main(String[] args) {
		SpringApplication.run(ConsumerServiceApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return  builder.build();
	}
	
	@RequestMapping(value="/sample", 
            method=RequestMethod.GET, 
            produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> sample(){
		Map<String,Object>obj=new HashMap<String,Object>();
		obj.put("value", "1");
		
		
		return obj ;
		
	}
	@GetMapping("/consume/{name}")
	@HystrixCommand(fallbackMethod="dummy")
	public String con(@PathVariable ("name")String name){
		//using feign client
		return greetClient.greetName(name);
		
		//Using RestTemplate 
		//List<ServiceInstance> greeting=discoveryClient.getInstances("GREETING");
		//URI greetingService= greeting.get(0).getUri();
		//return restTemplate.getForObject(greetingService+"/greeting/abhishek", String.class);
		
	}
	public String dummy(){
		return "greeting service down dummy O/P";
	}
}




