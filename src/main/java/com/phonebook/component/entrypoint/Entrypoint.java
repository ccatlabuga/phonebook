package com.phonebook.component.entrypoint;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

public interface Entrypoint {
    void run(ConfigurableApplicationContext context);
}
