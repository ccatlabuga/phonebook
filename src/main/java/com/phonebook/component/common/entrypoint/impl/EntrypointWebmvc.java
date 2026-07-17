package com.phonebook.component.common.entrypoint.impl;

import com.phonebook.component.common.entrypoint.Entrypoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("webmvc")
@Component
@SpringBootApplication
public class EntrypointWebmvc implements Entrypoint {
    @Override
    public void run(ConfigurableApplicationContext context) {
        new SpringApplicationBuilder(EntrypointWebmvc.class)
                .parent(context)
                .run();
    }
}
