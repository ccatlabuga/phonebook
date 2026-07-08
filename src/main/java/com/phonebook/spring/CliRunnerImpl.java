package com.phonebook.spring;

import com.phonebook.main.CliCommand;
import com.phonebook.main.CliRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class CliRunnerImpl implements CliRunner {
    @Autowired
    private Map<String, CliCommand> commands;

    @Autowired
    PhoneBookFormatterImpl renderer;

    @Override
    public void execute(String commandName, List<String> commandArgs) {
        CliCommand command = commands.get(commandName);

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
