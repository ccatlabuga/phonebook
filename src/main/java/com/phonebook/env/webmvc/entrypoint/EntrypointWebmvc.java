package com.phonebook.env.webmvc.entrypoint;

import com.phonebook.core.entrypoint.Entrypoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication(scanBasePackages = {
        "com.phonebook.core",
        "com.phonebook.env.webmvc"
})
public class EntrypointWebmvc implements Entrypoint {
    @Override
    public void run(ConfigurableApplicationContext context) {
        new SpringApplicationBuilder(EntrypointWebmvc.class)
                .parent(context)
                .run();
    }
}
