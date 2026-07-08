package com.phonebook.main;

import java.util.List;
import java.util.function.Consumer;

@FunctionalInterface
public interface CliCommand extends Consumer<List<String>> {
}
