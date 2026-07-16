package com.phonebook.component.formatter;

import java.util.Map;
import java.util.Set;

public interface Formatter {
    void show(String data);
    void show(Set<String> data);
    void show(Map<String, Set<String>> data);
    void info(String message);
    void error(String message);
    void error(Throwable cause);
}
