package com.phonebook.env.cli.handler;

import com.phonebook.env.cli.command.Command;
import com.phonebook.core.service.PhoneBook;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CommandHandler {
    private final PhoneBook phoneBook;

    public CommandHandler(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }

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
