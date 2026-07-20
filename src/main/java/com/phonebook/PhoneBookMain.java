package com.phonebook;

import com.phonebook.component.common.entrypoint.Entrypoint;
import com.phonebook.spring.ApplicationConfig;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PhoneBookMain {
    static ConfigurableApplicationContext context;

    static void main(String[] args) {
        context = newApplicationContext(args);
//        context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.register(ApplicationConfig.class);
//        context.refresh();

        for (String bean : context.getBeanDefinitionNames()) {
            System.out.println(bean);
        }

        Entrypoint entrypoint = context.getBean(Entrypoint.class);

        entrypoint.run(context);
    }

    private static ConfigurableApplicationContext newApplicationContext(String... args) {
        return args.length > 0 && args[0].equalsIgnoreCase("classPath")
                ? new ClassPathXmlApplicationContext("application-config.xml")
                : new AnnotationConfigApplicationContext(ApplicationConfig.class);
    }
}