package org.example.springproject.config;

import org.example.springproject.repository.OrderComponentRepository;
import org.example.springproject.repository.OrderRepository;
import org.example.springproject.repository.SushiRepository;
import org.example.springproject.repository.SushiTypeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@Configuration
public class DatasourceConfigTest {

    @Bean
    public SushiRepository sushiRepository() {
        return mock(SushiRepository.class);
    }

    @Bean
    public OrderRepository orderRepository() {
        return mock(OrderRepository.class);
    }

    @Bean
    public SushiTypeRepository sushiTypeRepository() {
        return mock(SushiTypeRepository.class);
    }

    @Bean
    public OrderComponentRepository orderComponentRepository() {
        return mock(OrderComponentRepository.class);
    }
}