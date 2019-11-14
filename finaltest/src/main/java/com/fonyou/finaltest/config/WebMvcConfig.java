package com.fonyou.finaltest.config;

import java.io.IOException;

import com.fonyou.root.utils.lib.interfaces.LogUtilsInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
//@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer
{
	
	/**
	 * Manage the virtual paths of the front end framework.
	 *
	 * @param ResourceHandlerRegistry registry the registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{

		registry.addResourceHandler("/**/*").addResourceLocations("classpath:/static/").resourceChain(true).addResolver(new PathResourceResolver() 
		{
			@Override
			protected Resource getResource(String resourcePath, Resource location) throws IOException 
			{
				Resource requestedResource = location.createRelative(resourcePath);
				return requestedResource.exists() && requestedResource.isReadable() ? requestedResource : new ClassPathResource("/static/index.html");
			}
		});
	}
	/**
	 * Manage the requests of the external clients 
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry)
	{
		registry.addMapping("/**");
	}

	@Bean
	public CustomConfigWatcher configWatcher(@Value("${business.properties.name}") String propertiesFilename,
			@Value("${business.properties.charset:UTF-8}") String propertiesCharset)
	{
		return new CustomConfigWatcher(propertiesFilename, propertiesCharset);
	}
	
}
