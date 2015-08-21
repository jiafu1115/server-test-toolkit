package com.googlecode.test.toolkit.services.memcached;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.googlecode.test.toolkit.services.Service;

import net.spy.memcached.MemcachedClient;

public abstract class AbstractMemcachedService implements Service{

	abstract MemcachedClient getMemcachedClient();

	abstract void set(String key, Object value, int cacheTime, long timeout,
			TimeUnit timeUnit);

	abstract Object get(String key);

	abstract Object asyncGet(String key, long timeout, TimeUnit timeUnit);

	abstract Map<String, Object> getBulk(Collection<String> keys);

	abstract Map<String, Object> asyncGetBulk(Collection<String> keys, long timeout,
			TimeUnit timeUnit);

	abstract List<String> deleteBulk(Collection<String> keys, long timeout,
			TimeUnit timeUnit);

	abstract void delete(String key, long timeout, TimeUnit timeUnit);

	abstract void flush();

	abstract void shutdown();

	@Override
	public String getServiceName() {
 		return "memcached";
	}

}