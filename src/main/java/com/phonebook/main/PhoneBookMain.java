package com.phonebook.main;

import com.phonebook.spring.ApplicationConfig;
import com.phonebook.spring.PhoneBookFormatter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;
import java.util.function.Consumer;

/**
 * PhoneBook entry point
 */
public class PhoneBookMain {
    public static void main(String[] args) {
        ApplicationContext context = newApplicationContext(args);

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter(System.lineSeparator());

        PhoneBookFormatter renderer = (PhoneBookFormatter) context.getBean("phoneBookFormatter");

        renderer.info("type 'exit' to quit.");
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (line.equals("exit")) {
                renderer.info("Have a good day...");
                break;
            }
            try {
//                commandHandler.executeCommand(Arrays.asList(line.split(" ")));
                List<String> command = Arrays.asList(line.split(" "));
                ((Consumer<List<String>>) context.getBean(command.get(0))).accept(command.subList(1, command.size()));
            } catch (Exception e) {
                renderer.error(e);
            }
        }
    }

    static ApplicationContext newApplicationContext(String... args) {
        return args.length > 0 && args[0].equals("classPath")
                ? new ClassPathXmlApplicationContext("application-config.xml")
                : new AnnotationConfigApplicationContext(ApplicationConfig.class);
    }
}
