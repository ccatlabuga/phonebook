package com.phonebook.env.cli.handler;

import com.phonebook.core.formatter.Formatter;
import com.phonebook.env.cli.command.Command;
import com.phonebook.core.service.PhoneBook;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CommandHandler {
    private final PhoneBook phoneBook;
    private final Formatter renderer;

    public CommandHandler(PhoneBook phoneBook, Formatter renderer) {
        this.phoneBook = phoneBook;
        this.renderer = renderer;
    }

    @Bean({"ADD"})
    public Command addPhone() {
        return (commandArgs) -> {
            if (!this.phoneBook.findAll().containsKey(commandArgs.getFirst())) {
                this.phoneBook.addName(commandArgs.getFirst());
            }
            this.phoneBook.addPhone(commandArgs);
        };
    }

    @Bean({"REMOVE_PHONE"})
    public Command removePhone() {
        return this.phoneBook::removePhone;
    }

    @Bean({"SHOW"})
    public Command show() {
        return (commandArgs) -> {
            if (commandArgs.isEmpty()) {
                this.renderer.show(this.phoneBook.findAll());
            } else {
                this.renderer.show(this.phoneBook.findAllPhonesByName(commandArgs.getFirst()));
            }
        };
    }
}
