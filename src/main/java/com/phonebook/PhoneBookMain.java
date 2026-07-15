package com.phonebook;

import com.phonebook.runner.Runner;
import com.phonebook.spring.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class PhoneBookMain {

    static void main(String[] args) {
        // 1. Determine which profile is active (passed via -Dspring.profiles.active)
        String activeProfile = System.getProperty("spring.profiles.active", "webmvc");

        if (activeProfile.equalsIgnoreCase("cli")) {
            // === RUN AS VANILLA SPRING (NO SPRING BOOT) ===
            // This is completely silent, starts instantly, and spawns no web server.

            // Silence standard Spring framework logging if you want absolute quiet
            System.setProperty("logging.level.root", "OFF");
            System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "off");

            ApplicationContext context = newApplicationContext(args);
            Runner runner = (Runner) context.getBean("runner");
            runner.run();

        } else {
            SpringApplication.run(PhoneBookMain.class, args);
        }
    }

    private static ApplicationContext newApplicationContext(String... args) {
        return args.length > 0 && args[0].equalsIgnoreCase("classPath")
                ? new ClassPathXmlApplicationContext("application-config.xml")
                : new AnnotationConfigApplicationContext(ApplicationConfig.class);
    }
}