package com.phonebook;

import com.phonebook.runner.Runner;
import com.phonebook.spring.ApplicationConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * PhoneBook entry point
 */
public class PhoneBookMain {
    public static ApplicationContext context;

    public static void main(String[] args) {
        context = newApplicationContext(args);

        Runner runner = (Runner) context.getBean("runner");
        runner.run();
    }

    static ApplicationContext newApplicationContext(String... args) {
        return args.length > 0 && args[0].equals("classPath")
                ? new ClassPathXmlApplicationContext("application-config.xml")
                : new AnnotationConfigApplicationContext(ApplicationConfig.class);
    }
}
