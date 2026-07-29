package com.phonebook.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Profile("cli")
@ComponentScan(value = {
        "com.phonebook.core",
        "com.phonebook.env.cli"
})
public class CliConfig {
    /**
     * Property placeholder configurer is needed to interpolate property values
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
