package com.enisfr.tokenbucket.interceptor;

import com.enisfr.tokenbucket.handler.BucketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class RequestInterceptor implements HandlerInterceptor {
	private final BucketHandler bucketHandler;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(request.getDispatcherType().equals(DispatcherType.REQUEST)) {
			String ip = request.getRemoteAddr();
			boolean canConsume = bucketHandler.canConsume(ip);

			if(canConsume) {
				return true;
			} else {
				response.sendError(429);
			}
		}
		return false;
	}
}


