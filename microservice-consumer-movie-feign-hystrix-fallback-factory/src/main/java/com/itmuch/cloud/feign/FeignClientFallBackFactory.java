package com.itmuch.cloud.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.itmuch.cloud.pojo.User;

import feign.hystrix.FallbackFactory;

/**
 * UserFeignClient的fallbackFactory类，该类需实现FallbackFactory接口，并覆写create方法
 * The fallback factory must produce instances of fallback classes that implement the interface annotated by {@link FeignClient}}
 * @author chengkai
 *
 */
@Component
public class FeignClientFallBackFactory implements FallbackFactory<UserFeignClient> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FeignClientFallBackFactory.class);
	
	@Override
	public UserFeignClient create(Throwable cause) {
		return new UserFeignClient() {
			
			@Override
			public User findById(Long id) {
				// 日志最好放在各个fallback方法中，而不要直接放在create方法中。
				// 否则在引用启动时，就会打印该日志。
				// 详见 https://github.com/spring-cloud/spring-cloud-netflix/issues/1471
				FeignClientFallBackFactory.LOGGER.info("fallback; reason was: " + cause);
				User user = new User();
				user .setId(-1L);
				user.setName("默认用户");
				return user;
			}
		};
	}


}
