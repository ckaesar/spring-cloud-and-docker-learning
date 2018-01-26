package com.itmuch.cloud.feign;

import org.springframework.stereotype.Component;

import com.itmuch.cloud.pojo.User;

/**
 * 回退类FeignClientFallBack需实现FeignClient接口
 * FeignClientFallBack可以是public class，没有区别
 * @author chengkai
 *
 */
@Component
public class FeignClientFallBack implements UserFeignClient {

	@Override
	public User findById(Long id) {
		User user = new User();
		user.setId(-1L);
		user.setName("默认用户");
		return user;
	}

}
