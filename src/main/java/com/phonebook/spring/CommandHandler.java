package com.phonebook.spring;

import com.phonebook.enums.Command;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Component
public class CommandHandler {
    private final Map<Command, Consumer<List<String>>> commandMapping = new HashMap<>();
    private Consumer<List<String>> fallbackCommandAction = commandArgs -> {
        System.out.println("Command is not supported");
    };

    public CommandHandler() {}

    public void setFallbackCommandAction(Consumer<List<String>> fallbackCommandAction) {
        this.fallbackCommandAction = fallbackCommandAction;
    }

    public void addCommand(Command command, Consumer<List<String>> commandAction) {
        this.commandMapping.put(command, commandAction);
    }

    public void executeCommand(Command command, List<String> commandArgs) {
        this.commandMapping.getOrDefault(command, fallbackCommandAction).accept(commandArgs);
    }

    public void executeCommand(String command, List<String> commandArgs) {
        try {
            Command parsedCommand = Command.valueOf(command);
            this.commandMapping.getOrDefault(parsedCommand, fallbackCommandAction).accept(commandArgs);
        } catch (IllegalArgumentException e) {
            this.fallbackCommandAction.accept(commandArgs);
        }
    }

    public void executeCommand(List<String> command) {
        this.executeCommand(command.get(0), command.subList(1, command.size()));
    }
}
