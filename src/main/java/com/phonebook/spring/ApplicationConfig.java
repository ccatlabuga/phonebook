package com.phonebook.spring;

import com.phonebook.datarepository.impl.InMemoryRepository;
import com.phonebook.runner.Runner;
import com.phonebook.datarepository.DataRepository;
import com.phonebook.runner.impl.CliRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.*;

@Configuration
@ComponentScan(value = {"com.phonebook.spring"})
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

    /**
     * Property placeholder configurer is needed to interpolate property values
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Map<String, Set<String>> defaultData() {
        Map<String, Set<String>> data = new LinkedHashMap<>();
        data.put("Alex", new HashSet<>(Arrays.asList("+79601232233")));
        data.put("Billy", new HashSet<>(Arrays.asList("+79213215566", "+79213215567", "+79213215568")));
        return data;
    }

    @Bean(name = "repository")
    public DataRepository inMemoryRepository(Map<String, Set<String>> defaultData) {
        return new InMemoryRepository(defaultData);
    }

    @Bean(name = "runner")
    public Runner runner() {
        return new CliRunner();
    }
}
