package com.itmuch.cloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.itmuch.cloud.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import rx.Observable;

@Service
public class AggregationService {

	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "fallback")
	public Observable<User> getUserById(Long id) {
		//创建一个被观察者
		return Observable.create(Observer -> {
			//请求用户微服务的/{id}端点
			User user = restTemplate.getForObject("http://microservice-provider-user/{id}", User.class, id);
			Observer.onNext(user);
			Observer.onCompleted();
		});
	}
	
	@HystrixCommand(fallbackMethod = "fallback")
	public Observable<User> getMovieUserByUserId(Long id) {
		return Observable.create(Observer -> {
			//请求电影微服务的/user/{id}端点
			User movieUser = restTemplate.getForObject("http://microservice-consumer-movie/user/{id}", User.class, id);
			Observer.onNext(movieUser);
			Observer.onCompleted();
		});
	}
	
	public User fallback(Long id) {
		User user = new User();
		user.setId(-1L);
		return user;
	}
}
