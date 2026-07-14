package com.phonebook.runner;

import java.util.List;

public interface Runner {
    void execute(String commandName, List<String> commandArgs);
    void run();
}
