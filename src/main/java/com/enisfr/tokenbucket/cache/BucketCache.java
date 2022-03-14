package com.enisfr.tokenbucket.cache;

import com.enisfr.tokenbucket.Bucket;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BucketCache {
	private final RedisTemplate<String, Bucket> redisTemplate;

	public Bucket getFor(String ip) {
		redisTemplate.opsForValue().setIfAbsent(ip, new Bucket());
		return redisTemplate.opsForValue().get(ip);
	}

	public void update(String ip, Bucket bucket) {
		redisTemplate.opsForValue().set(ip, bucket);
	}
}
