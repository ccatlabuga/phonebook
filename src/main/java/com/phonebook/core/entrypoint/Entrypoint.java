package com.phonebook.core.entrypoint;

import org.springframework.context.ConfigurableApplicationContext;

public interface Entrypoint {
    void run(ConfigurableApplicationContext context);
}
