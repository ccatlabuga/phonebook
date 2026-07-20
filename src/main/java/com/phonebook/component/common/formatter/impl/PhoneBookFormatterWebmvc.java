package com.phonebook.component.common.formatter.impl;

import com.phonebook.component.common.formatter.Formatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Profile("webmvc")
public class PhoneBookFormatterWebmvc implements Formatter {
    @Value("${lowerCaseNames}")
    private boolean lowerCaseNames = false;
    @Value("${columnsSeparator}")
    private char columnsSeparator = '|';

    private final Logger logger = LoggerFactory.getLogger("PhoneBook");

    @Override
    public void show(String data) {
        logger.info(String.format("%-10s", data));
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

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void error(String message) {
        logger.error(message);
    }

    @Override
    public void error(Throwable cause) {
        logger.error("Error: ", cause);
    }
}
