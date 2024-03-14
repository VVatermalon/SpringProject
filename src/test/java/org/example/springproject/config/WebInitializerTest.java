package org.example.springproject.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WebInitializerTest {

    @Test
    void testGetRootConfigClasses() {
        WebInitializer initializer = new WebInitializer();
        Class<?>[] rootConfigClasses = initializer.getRootConfigClasses();
        assertNotNull(rootConfigClasses);
        assertArrayEquals(new Class<?>[]{DatasourceConfig.class, ValidationConfig.class, PropertiesConfig.class}, rootConfigClasses);
    }

    @Test
    void testGetServletConfigClasses() {
        WebInitializer initializer = new WebInitializer();
        Class<?>[] servletConfigClasses = initializer.getServletConfigClasses();
        assertNotNull(servletConfigClasses);
        assertArrayEquals(new Class<?>[]{SpringConfig.class}, servletConfigClasses);
    }

    @Test
    void testGetServletMappings() {
        WebInitializer initializer = new WebInitializer();
        String[] servletMappings = initializer.getServletMappings();
        assertNotNull(servletMappings);
        assertEquals(1, servletMappings.length);
        assertEquals("/*", servletMappings[0]);
    }
}
