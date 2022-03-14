package com.enisfr.tokenbucket;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

@Data
@Slf4j
public class Bucket implements Serializable {

	@Value("${bucket.capacity}")
	private double capacity;

	@Value("${bucket.refill-rate}")
	private long refillRate;

	private double availableTokens;
	private long lastRefill;

	public Bucket() {
		this.availableTokens = capacity;
		this.lastRefill = System.currentTimeMillis();
	}

	public boolean tryConsume() {
		refillIfRequired();
		if(availableTokens > 0) {
			availableTokens--;
			return true;
		} else {
			return false;
		}
	}

	public void refillIfRequired() {
		if(requiresRefill()) {
			availableTokens = capacity;
			lastRefill = System.currentTimeMillis();
		}
		log.info("Available tokens: {}", availableTokens);
	}

	private boolean requiresRefill() {
		long now = System.currentTimeMillis();
		return lastRefill + refillRate < now;
	}
}
