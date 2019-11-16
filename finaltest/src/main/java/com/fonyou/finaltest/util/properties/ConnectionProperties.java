package com.fonyou.finaltest.util.properties;

import com.fonyou.finaltest.config.CustomConfigWatcher;
import com.fonyou.root.utils.lib.interfaces.GsonUtilsInterface;
import com.fonyou.root.utils.lib.interfaces.UtilsInterface;
import jnr.ffi.annotations.In;
import org.slf4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ConnectionProperties  implements GsonUtilsInterface, UtilsInterface
{

	private CustomConfigWatcher config;

	public ConnectionProperties(CustomConfigWatcher config) {
		this.config = config;
	}


	// Connection to Redis
	private String msIp;
	private Integer msPort;
	private Boolean msblockedWhenExhausted;
	private Boolean msFairness;
	private Integer msDefaultTimeout;
	private Integer msMaxConnections;
	private Integer msMaxIdleConnections;
	private Integer msMaxWaitMillis;
	private Integer msMinEvictableIdleMillis;
	

    @PostConstruct
    public void initProperties()
	{
		this.setMsIp(this.getValidateConfig("main.connection.msIp", "127.0.0.1"));
		this.setMsPort(this.getValidateConfig("main.connection.msPort", 6379));
		this.setMsblockedWhenExhausted(this.getValidateConfig("main.connection.msblockedWhenExhausted", Boolean.TRUE));
		this.setMsFairness(this.getValidateConfig("main.connection.msFairness", Boolean.TRUE));
		this.setMsDefaultTimeout(this.getValidateConfig("main.connection.msDefaultTimeout", 2000));
		this.setMsMaxConnections(this.getValidateConfig("main.connection.msMaxConnections", 300));
		this.setMsMaxIdleConnections(this.getValidateConfig("main.connection.msMaxIdleConnections", 30));
		this.setMsMaxWaitMillis(this.getValidateConfig("main.connection.msMaxWaitMillis", -1));
		this.setMsMinEvictableIdleMillis(this.getValidateConfig("main.connection.msMinEvictableIdleMillis", 60000));
	}

	
	/*
	 * Getter & Setter
	 */
	
	public String getMsIp() 
	{
		return msIp;
	}

	public void setMsIp(String msIp) 
	{
		this.msIp = msIp;
	}

	public Integer getMsPort() 
	{
		return msPort;
	}

	public void setMsPort(Integer msPort) 
	{
		this.msPort = msPort;
	}

	public Boolean getMsblockedWhenExhausted() 
	{
		return msblockedWhenExhausted;
	}

	public void setMsblockedWhenExhausted(Boolean msblockedWhenExhausted) 
	{
		this.msblockedWhenExhausted = msblockedWhenExhausted;
	}

	public Boolean getMsFairness() 
	{
		return msFairness;
	}

	public void setMsFairness(Boolean msFairness) 
	{
		this.msFairness = msFairness;
	}

	public Integer getMsDefaultTimeout() 
	{
		return msDefaultTimeout;
	}

	public void setMsDefaultTimeout(Integer msDefaultTimeout) 
	{
		this.msDefaultTimeout = msDefaultTimeout;
	}

	public Integer getMsMaxConnections() 
	{
		return msMaxConnections;
	}

	public void setMsMaxConnections(Integer msMaxConnections) 
	{
		this.msMaxConnections = msMaxConnections;
	}

	public Integer getMsMaxIdleConnections() 
	{
		return msMaxIdleConnections;
	}

	public void setMsMaxIdleConnections(Integer msMaxIdleConnections) 
	{
		this.msMaxIdleConnections = msMaxIdleConnections;
	}

	public Integer getMsMaxWaitMillis() 
	{
		return msMaxWaitMillis;
	}

	public void setMsMaxWaitMillis(Integer msMaxWaitMillis) 
	{
		this.msMaxWaitMillis = msMaxWaitMillis;
	}

	public Integer getMsMinEvictableIdleMillis() 
	{
		return msMinEvictableIdleMillis;
	}

	public void setMsMinEvictableIdleMillis(Integer msMinEvictableIdleMillis) 
	{
		this.msMinEvictableIdleMillis = msMinEvictableIdleMillis;
	}

	private String getValidateConfig(String keyConfig, String defaultValue)
	{
		String valueConfig = this.config.get(keyConfig);
		return valueConfig == null ? defaultValue : valueConfig;
	}

	private Integer getValidateConfig(String keyConfig, Integer defaultValue)
	{
		String valueConfig = this.config.get(keyConfig);
		return valueConfig == null ? defaultValue : Integer.parseInt(valueConfig);
	}

	private Boolean getValidateConfig(String keyConfig, Boolean defaultValue)
	{
		String valueConfig = this.config.get(keyConfig);
		return valueConfig == null ? defaultValue : Boolean.parseBoolean(valueConfig);
	}

	@Override
	public Logger getLogger()
	{
		return GsonUtilsInterface.super.getLogger();
	}
}
