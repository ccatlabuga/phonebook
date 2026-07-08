package com.phonebook.main;

import com.phonebook.spring.ApplicationConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

/**
 * PhoneBook entry point
 */
public class PhoneBookMain {
    public static ApplicationContext context;

    public static void main(String[] args) {
        context = newApplicationContext(args);

        CliRunner runner = (CliRunner) context.getBean("runner");
        runner.run();
    }

    static ApplicationContext newApplicationContext(String... args) {
        return args.length > 0 && args[0].equals("classPath")
                ? new ClassPathXmlApplicationContext("application-config.xml")
                : new AnnotationConfigApplicationContext(ApplicationConfig.class);
    }
}
