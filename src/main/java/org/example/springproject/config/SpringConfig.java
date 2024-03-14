package org.example.springproject.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "org.example.springproject.controller",
        "org.example.springproject.exception",
        "org.example.springproject.server",
        "org.example.springproject.service"
})
public class SpringConfig extends WebMvcConfigurationSupport {
}