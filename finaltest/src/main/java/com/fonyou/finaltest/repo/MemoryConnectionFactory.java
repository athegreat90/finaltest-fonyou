package com.fonyou.finaltest.repo;

import com.fonyou.finaltest.util.properties.ConnectionProperties;
import com.fonyou.root.utils.lib.interfaces.RedisConnectionFactoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;

@Component
public class MemoryConnectionFactory implements RedisConnectionFactoryInterface
{
	@Autowired
	private ConnectionProperties connection;
	
	private String ip;
	private Integer port;
	private Boolean blockedWhenExhausted;
	private Boolean fairness;
	private Integer defaultTimeout;
	private Integer maxConnections;
	private Integer maxIdleConnections;
	private Integer maxWaitMillis;
	private Integer minEvictableIdleMillis;

	private JedisPool jedisPool;
	
	/**
	 * Init Variables.
	 */
	
	@PostConstruct
	public void postConstruct() 
	{
		ip = connection.getMsIp();
		port = connection.getMsPort();
		blockedWhenExhausted = connection.getMsblockedWhenExhausted();
		fairness = connection.getMsFairness();
		defaultTimeout = connection.getMsDefaultTimeout();
		maxConnections = connection.getMsMaxConnections();
		maxIdleConnections = connection.getMsMaxIdleConnections();
		maxWaitMillis = connection.getMsMaxWaitMillis();
		minEvictableIdleMillis = connection.getMsMinEvictableIdleMillis();
		init();
	}
	/*
	 * Getter & Setter
	 */

	@Override
	public String getIp()
	{
		return ip;
	}

	@Override
	public Integer getPort()
	{
		return port;
	}

	@Override
	public Boolean isBlockedWhenExhausted()
	{
		return blockedWhenExhausted;
	}

	@Override
	public Boolean isFairness()
	{
		return fairness;
	}

	@Override
	public Integer getDefaultTimeout()
	{
		return defaultTimeout;
	}

	@Override
	public Integer getMaxConnections()
	{
		return maxConnections;
	}

	@Override
	public Integer getMaxIdleConnections()
	{
		return maxIdleConnections;
	}

	@Override
	public Integer getMaxWaitMillis()
	{
		return maxWaitMillis;
	}

	@Override
	public Integer getMinEvictableIdleMillis()
	{
		return minEvictableIdleMillis;
	}

	@Override
	public JedisPool getJedisPool()
	{
		return jedisPool;
	}

	@Override
	public void setJedisPool(JedisPool jedisPool)
	{
		this.jedisPool = jedisPool;
	}
}
