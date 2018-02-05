package com.itmuch.cloud.fallback;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class UserFallbackProvider implements ZuulFallbackProvider {

	@Override
	public String getRoute() {
		//表明是为哪个微服务提供回退
		return "microservice-provider-user";
	}

	@Override
	public ClientHttpResponse fallbackResponse() {
		return new ClientHttpResponse() {
			
			@Override
			public HttpHeaders getHeaders() {
				// Headers设定
				HttpHeaders headers = new HttpHeaders();
				MediaType mt = new org.springframework.http.MediaType("application", "json", Charset.forName("UTF-8"));
				headers.setContentType(mt);
				return headers;
			}
			
			@Override
			public InputStream getBody() throws IOException {
				// 响应体
				return new ByteArrayInputStream("用户微服务不可用，请稍后再试。".getBytes());
			}
			
			@Override
			public String getStatusText() throws IOException {
				// 状态文本，本例返回的其实就是OK，详见HttpStatus
				return this.getStatusCode().getReasonPhrase();
			}
			
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}
			
			@Override
			public int getRawStatusCode() throws IOException {
				// 数字状态的状态码，本例返回的其实就是200，详见HttpStatus
				return this.getStatusCode().value();
			}
			
			@Override
			public void close() {
				
			}
		};
	}

}
