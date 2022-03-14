package com.enisfr.tokenbucket.handler;

import com.enisfr.tokenbucket.Bucket;
import com.enisfr.tokenbucket.cache.BucketCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BucketHandler {

	private final BucketCache bucketCache;

	public boolean canConsume(String ip) {
		Bucket bucket = bucketCache.getFor(ip);
		boolean canConsume = bucket.tryConsume();
		bucketCache.update(ip, bucket);
		return canConsume;
	}
}
