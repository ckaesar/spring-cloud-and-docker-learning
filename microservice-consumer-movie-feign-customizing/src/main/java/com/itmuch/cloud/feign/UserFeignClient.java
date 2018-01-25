package com.itmuch.cloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itmuch.cloud.config.FeignConfiguration;
import com.itmuch.cloud.pojo.User;

import feign.Param;
import feign.RequestLine;

/**
 * 使用@FeignClient的configuration属性，指定feign的配置类。
 * @author chengkai
 *
 */
@FeignClient(name = "microservice-provider-user", configuration = FeignConfiguration.class)
// 或者不使用eureka： @FeignClient(name = "microservice-provider-user", url = "http://localhost:8763/")
public interface UserFeignClient {
	
	/**
	 * 使用feign自带的注解@RequestLine
	 * @see https://github.com/OpenFeign/feign
	 * @param id 用户id
	 * @return 用户信息
	 */
	@RequestLine("GET /{id}")
	public User findById(@Param("id") Long id);
}
