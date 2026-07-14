package com.phonebook.handler;

import com.phonebook.command.Command;
import com.phonebook.spring.PhoneBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CommandHandler {
    @Autowired
    private PhoneBook phoneBook;

    @Bean({"ADD"})
    public Command addPhone() {
        return this.phoneBook::addPhone;
    }

    @Bean({"REMOVE_PHONE"})
    public Command removePhone() {
        return this.phoneBook::removePhone;
    }

    @Bean({"SHOW"})
    public Command show() {
        return this.phoneBook::show;
    }
}
