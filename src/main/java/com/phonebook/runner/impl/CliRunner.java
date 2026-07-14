package com.phonebook.runner.impl;

import com.phonebook.command.Command;
import com.phonebook.runner.Runner;
import com.phonebook.formatter.impl.PhoneBookFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class CliRunner implements Runner {
    @Autowired
    private Map<String, Command> commands;

    @Autowired
    PhoneBookFormatter renderer;

    @Override
    public void execute(String commandName, List<String> commandArgs) {
        Command command = commands.get(commandName);

        if (command != null) {
            command.accept(commandArgs);
        } else {
            this.renderer.error("Unknown command: " + commandName);
        }
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter(System.lineSeparator());

        renderer.info("type 'exit' to quit.");
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (line.equals("exit")) {
                renderer.info("Have a good day...");
                break;
            }
            try {
                List<String> command = Arrays.asList(line.split(" "));
                this.execute(command.get(0), command.subList(1, command.size()));
            } catch (Exception e) {
                renderer.error(e);
            }
        }
    }
}
