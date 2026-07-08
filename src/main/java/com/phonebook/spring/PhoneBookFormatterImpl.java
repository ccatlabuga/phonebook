package com.phonebook.spring;

import com.phonebook.main.PhoneBookFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * Responsible for formatting in {@code PhoneBook} application
 */
@Component
public class PhoneBookFormatterImpl implements PhoneBookFormatter {
    // comes from application.properties file
    @Value("${lowerCaseNames}")
    private boolean lowerCaseNames = false;
    @Value("${columnsSeparator}")
    private char columnsSeparator = '|';

    /**
     * output level
     */
    public enum Level {
        INFO("[INFO]"),
        ERROR("[ERROR]");

        private String level;

        Level(String level) {
            this.level = level;
        }

        @Override
        public String toString() {
            return this.level;
        }
    }

    @Override
    public void show(String data) {
        System.out.println(String.format("%-10s", data));
    }

    @Override
    public void show(Set<String> data) {
        String joined = String.join(", ", data);
        this.show(joined);
    }

    @Override
    public void show(Map<String, Set<String>> data) {
        data.forEach((key, values) -> {
            Set<String> processedValues = lowerCaseNames
                    ? values.stream().map(String::toLowerCase).collect(Collectors.toSet())
                    : values;


            String prefix = String.format("%-20.20s%s", key, this.columnsSeparator);
            System.out.print(prefix);

            this.show(processedValues);
        });
    }

    /**
     * add {@code Level.INFO} message to stout
     *
     * @param message
     */
    @Override
    public void info(String message) {
        System.out.println(format("\u001B[32m%s: %s\u001B[0m", Level.INFO, message));
    }

    /**
     * add {@code Level.ERROR} message to stout
     *
     * @param message
     */
    @Override
    public void error(String message) {
        System.out.println(format("\u001B[31m%s: %s\u001B[0m", Level.ERROR, message));
    }

    /**
     * add {@code Level.ERROR} cause to stout
     *
     * @param cause of an error
     */
    @Override
    public void error(Throwable cause) {
        this.error(cause.toString());
    }

    /*************************
     * getters required for bean properties to be injected
     *************************/

    /**
     * @param lowerCaseNames
     */
    public void setLowerCaseNames(boolean lowerCaseNames) {
        this.lowerCaseNames = lowerCaseNames;
    }

    /**
     * @param columnsSeparator
     */
    public void setColumnsSeparator(char columnsSeparator) {
        this.columnsSeparator = columnsSeparator;
    }
}
