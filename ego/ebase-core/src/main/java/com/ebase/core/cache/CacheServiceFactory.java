package com.ebase.core.cache;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.ebase.core.StringHelper;
import com.ebase.core.cache.service.impl.CacheServiceImpl;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@Component
@Scope("singleton")
public class CacheServiceFactory implements EnvironmentAware {
	private JedisCluster jc;
//	private Jedis jedis;
	private String cacheOn;
	private Environment env;
	private RelaxedPropertyResolver resolver;

//	private JedisPool jedisPool;
	
	public JedisCluster getJc() {
		if (null == jc)
			init();
		return this.jc;
	}

	private void init() {
		resolver = new RelaxedPropertyResolver(env, "redis.");

		String servers = resolver.getProperty("servers");
		if (StringHelper.isEmpty(servers)) {
			return;
		}

		String[] serverArr = servers.split(";");
		cacheOn = resolver.getProperty("enable");
		if ("true".equals(cacheOn))
			cacheOn = CacheServiceImpl.CACHE_ON;
		else 
			cacheOn = CacheServiceImpl.CACHE_OFF;

//		String ip = resolver.getProperty("servers.ip");
//		jedisPool = initPool(ip);

		initJedis(serverArr);

	}

	private void initJedis(String[] serverArr) {
		// 获取所有 redis的 ip
		// if (serverArr.length == 1) {
		// String[] ipAndPort = serverArr[0].split(":");
		// if (ipAndPort != null && ipAndPort.length > 1) {
		// String ip = ipAndPort[0];
		// String port = ipAndPort[1];
		// jedis = new Jedis(ip, Integer.parseInt(port));
		// }
		// } else {

		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		for (int i = 0; i < serverArr.length; i++) {

			if (!StringHelper.isEmpty(serverArr[i]) && serverArr[i].indexOf(":") != -1) {
				String[] ipAndPort = serverArr[i].split(":");
				if (ipAndPort != null && ipAndPort.length > 1) {
					String ip = ipAndPort[0];
					String port = ipAndPort[1];
					jedisClusterNodes.add(new HostAndPort(ip, Integer.parseInt(port)));
					// 把主节点配置在第一个
					// if (i == 0)
					// jedis = new Jedis(ip, Integer.parseInt(port));
				}
			}
		}
		String maxWaitMillis = resolver.getProperty("maxWaitMillis");
		String maxTotal = resolver.getProperty("maxTotal");
		String minIdle = resolver.getProperty("minIdle");
		String maxIdle = resolver.getProperty("maxIdle");
		// String testOnBorrow = resolver.getProperty("testOnBorrow");

		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxWaitMillis(Long.parseLong(maxWaitMillis));
		poolConfig.setMaxTotal(Integer.parseInt(maxTotal));
		poolConfig.setMaxIdle(Integer.parseInt(maxIdle));
		poolConfig.setMinIdle(Integer.parseInt(minIdle));
		poolConfig.setTestOnBorrow(true);
		String connectionTimeout = resolver.getProperty("connectionTimeout");
		String soTimeout = resolver.getProperty("cluster.soTimeout");
		String maxRedirections = resolver.getProperty("cluster.maxRedirections");

		// String auth = resolver.getProperty("auth");
		// if (StringUtils.isBlank(auth)) {
		jc = new JedisCluster(jedisClusterNodes, Integer.parseInt(connectionTimeout), Integer.parseInt(soTimeout),
				Integer.parseInt(maxRedirections), poolConfig);
		// } else {
		//// jc = new JedisCluster(jedisClusterNodes,
		// Integer.parseInt(connectionTimeout),
		//// Integer.parseInt(soTimeout), Integer.parseInt(maxRedirections),
		// auth, poolConfig);
		// jc = new JedisCluster(jedisClusterNodes,
		// Integer.parseInt(connectionTimeout),
		// Integer.parseInt(soTimeout), Integer.parseInt(maxRedirections),
		// poolConfig);
		// }
		// }
	}

//			jedis

	public CacheService createCacheService() {
		try {
			if (null == jc)
				init();

			if (jc != null) {
				CacheServiceImpl cacheClusterService = new CacheServiceImpl();
				cacheClusterService.setJc(jc);
				cacheClusterService.setCacheOn(cacheOn);
				return cacheClusterService;
			}
				return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}
}
