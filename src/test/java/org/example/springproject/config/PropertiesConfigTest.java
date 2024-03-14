package org.example.springproject.config;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PropertiesConfigTest {

    @Test
    void testPropertiesBean() throws IOException {
        PropertiesConfig propertiesConfig = new PropertiesConfig();
        PropertySourcesPlaceholderConfigurer configurer = propertiesConfig.properties();

        assertNotNull(configurer);
    }
}
