package com.itmuch.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * 该类为Ribbon的配置类
 * 注意：该类不应该出现在主应用程序上下文的@ComponentScan中。
 * @author chengkai
 *
 */
@Configuration
public class RibbonConfiguration {
	
	@Bean
	public IRule ribbonRule() {
		//负载均衡规则，改为随机
		return new RandomRule();
	}
	
}
