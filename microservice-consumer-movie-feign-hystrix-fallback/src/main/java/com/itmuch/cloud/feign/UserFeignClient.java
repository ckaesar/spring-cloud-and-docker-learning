package com.itmuch.cloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itmuch.cloud.pojo.User;

/**
 * Feign的fallback测试
 * 使用@FeignClient的fallback属性指定回退类
 * @author chengkai
 *
 */
@FeignClient(name = "microservice-provider-user", fallback = FeignClientFallBack.class)
public interface UserFeignClient {
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User findById(@PathVariable("id") Long id);
}
