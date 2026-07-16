package com.phonebook.spring;

import com.phonebook.component.datarepository.impl.InMemoryRepository;
import com.phonebook.component.runner.Runner;
import com.phonebook.component.datarepository.DataRepository;
import com.phonebook.component.runner.impl.CliRunner;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.*;

@Configuration
@ComponentScan(value = {"com.phonebook.component"})
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
}
