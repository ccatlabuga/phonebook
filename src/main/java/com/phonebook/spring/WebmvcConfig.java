package com.phonebook.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("webmvc")
@ComponentScan(value = {
        "com.phonebook.component.webmvc",
        "com.phonebook.component.common"
})
public class WebmvcConfig {}
