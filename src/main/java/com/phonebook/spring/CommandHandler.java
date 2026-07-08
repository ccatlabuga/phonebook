package com.phonebook.spring;

import com.phonebook.main.CliCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CommandHandler {
    @Autowired
    private PhoneBook phoneBook;

    @Bean({"ADD"})
    public CliCommand addPhone() {
        return this.phoneBook::addPhone;
    }

    @Bean({"REMOVE_PHONE"})
    public CliCommand removePhone() {
        return this.phoneBook::removePhone;
    }

    @Bean({"SHOW"})
    public CliCommand show() {
        return this.phoneBook::show;
    }
}
