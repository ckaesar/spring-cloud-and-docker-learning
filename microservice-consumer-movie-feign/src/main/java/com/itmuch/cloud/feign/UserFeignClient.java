package com.itmuch.cloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itmuch.cloud.pojo.User;

@FeignClient(name = "microservice-provider-user")
// 或者不使用eureka： @FeignClient(name = "microservice-provider-user", url = "http://localhost:8763/")
public interface UserFeignClient {
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User findById(@PathVariable("id") Long id);
}
