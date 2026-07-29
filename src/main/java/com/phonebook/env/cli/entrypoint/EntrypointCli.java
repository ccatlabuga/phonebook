package com.phonebook.env.cli.entrypoint;

import com.phonebook.core.entrypoint.Entrypoint;
import com.phonebook.env.cli.runner.Runner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class EntrypointCli implements Entrypoint {
    @Override
    public void run(ConfigurableApplicationContext context) {
        Runner runner = context.getBean(Runner.class);
        runner.run();
    }
}
