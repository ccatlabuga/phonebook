package com.phonebook.config;

import com.phonebook.core.datarepository.impl.InMemoryRepository;
import com.phonebook.core.datarepository.DataRepository;
import org.springframework.context.annotation.*;

import java.util.*;

@Configuration
@Import({CliConfig.class, WebmvcConfig.class})
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
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
