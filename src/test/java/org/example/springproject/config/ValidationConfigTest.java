package org.example.springproject.config;

import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ValidationConfigTest {

    @Test
    void testValidator() {
        ValidationConfig validationConfig = new ValidationConfig();
        LocalValidatorFactoryBean validator = validationConfig.validator();
        assertNotNull(validator);
    }

    @Test
    void testValidationPostProcessor() {
        ValidationConfig validationConfig = new ValidationConfig();
        MethodValidationPostProcessor postProcessor = validationConfig.validationPostProcessor();
        assertNotNull(postProcessor);
    }
}
