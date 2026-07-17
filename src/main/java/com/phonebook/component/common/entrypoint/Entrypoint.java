package com.phonebook.component.common.entrypoint;

import org.springframework.context.ConfigurableApplicationContext;

public interface Entrypoint {
    void run(ConfigurableApplicationContext context);
}
