package com.phonebook.component.entrypoint.impl;

import com.phonebook.component.entrypoint.Entrypoint;
import com.phonebook.component.runner.Runner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("cli")
@Component
public class EntrypointCli implements Entrypoint {
    @Override
    public void run(ConfigurableApplicationContext context) {
        Runner runner = context.getBean(Runner.class);
        runner.run();
    }
}
