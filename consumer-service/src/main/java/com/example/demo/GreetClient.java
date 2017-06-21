package com.example.demo;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="GREETING")
public interface GreetClient {
	
	@RequestMapping(value="/greeting/{name}" ,method=RequestMethod.GET)
	String greetName(@PathVariable("name") String name);

	

}
