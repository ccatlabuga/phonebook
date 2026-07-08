package com.phonebook.main;

import java.util.List;

public interface CliRunner {
    void execute(String commandName, List<String> commandArgs);
    void run();
}
