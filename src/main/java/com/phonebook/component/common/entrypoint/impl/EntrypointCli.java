package com.phonebook.component.common.entrypoint.impl;

import com.phonebook.component.common.entrypoint.Entrypoint;
import com.phonebook.component.cli.runner.Runner;
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
