package com.phonebook.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("webmvc")
@ComponentScan(value = {
        "com.phonebook.core",
        "com.phonebook.env.webmvc"
})
public class WebmvcConfig {}
