package com.phonebook.command;

import java.util.List;
import java.util.function.Consumer;

@FunctionalInterface
public interface Command extends Consumer<List<String>> {
}
