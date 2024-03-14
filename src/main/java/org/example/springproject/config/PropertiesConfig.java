package org.example.springproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class PropertiesConfig {

    private static final String PROPERTIES_FILE = "application.properties";

    @Bean
    public PropertySourcesPlaceholderConfigurer properties() throws IOException {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setProperties(properties(PROPERTIES_FILE));
        return propertySourcesPlaceholderConfigurer;
    }

    private Properties properties(String filename) throws IOException {
        Resource resource = new ClassPathResource(filename);
        return PropertiesLoaderUtils.loadProperties(resource);
    }
}
