package com.phonebook.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

@Component
public class CommandHandler {
    @Autowired
    private PhoneBook phoneBook;

    @Bean({"ADD"})
    public Consumer<List<String>> addPhone() {
        return this.phoneBook::addPhone;
    }

    @Bean({"REMOVE_PHONE"})
    public Consumer<List<String>> removePhone() {
        return this.phoneBook::removePhone;
    }

    @Bean({"SHOW"})
    public Consumer<List<String>> show() {
        return this.phoneBook::show;
    }
}
