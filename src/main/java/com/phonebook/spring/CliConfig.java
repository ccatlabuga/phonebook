package com.phonebook.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("cli")
@ComponentScan(value = {
        "com.phonebook.component.cli",
        "com.phonebook.component.common"
})
public class CliConfig {}
