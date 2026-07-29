package com.phonebook.core.service;

import com.phonebook.core.datarepository.DataRepository;
import com.phonebook.core.exception.UserConflictException;
import com.phonebook.core.exception.UserNotFoundException;
import com.phonebook.core.formatter.Formatter;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * PhoneBook service implementation
 */
@Service
public class PhoneBook {
    private final DataRepository repository;
    private final Formatter renderer;

    public PhoneBook(Formatter renderer, DataRepository repository) {
        this.renderer = renderer;
        this.repository = repository;
    }

    /**
     * @return all pairs of type {name: [phone1, phone2]}
     */
    public Map<String, Set<String>> findAll() {
        return repository.findAll();
    }

    /**
     * TODO: please add required methods here
     */

    public void addPhone(String name, String phone) {
        this.repository.addPhone(name, phone);
    }

    public void addPhone(String name, List<String> phones) {
        this.repository.addPhones(name, phones);
    }

    public void addPhone(List<String> commandArgs) {
        this.addPhone(commandArgs.get(0), Arrays.asList(commandArgs.get(1).split(",")));
    }

    public void removePhone(String phone) {
        try {
            this.repository.removePhone(phone);
        } catch (IllegalArgumentException exception) {
            throw new UserNotFoundException("User with phone '%s' has not been found".formatted(phone));
        }
    }

    public void removePhone(List<String> commandArgs) {
        this.removePhone(commandArgs.get(0));
    }

    public void show(List<String> commandArgs) {
        if (commandArgs.isEmpty()) {
            this.renderer.show(repository.findAll());
        } else {
            this.renderer.show(repository.findAllPhonesByName(commandArgs.get(0)));
        }
    }

    public Set<String> findAllPhonesByName(String name) {
        Set<String> results = this.repository.findAllPhonesByName(name);
        if (Objects.isNull(results)) {
            throw new UserNotFoundException("User with name '%s' has not been found".formatted(name));
        }

        return results;
    }

    public void addName(String name) {
        if (!Objects.isNull(this.repository.findAllPhonesByName(name))) {
            throw new UserConflictException("User with name '%s' already exists".formatted(name));
        }

        this.repository.addName(name);
    }

    public void addPhones(String name, List<String> phones) {
        if (Objects.isNull(this.repository.findAllPhonesByName(name))) {
            throw new UserNotFoundException("User with name '%s' has not been found".formatted(name));
        }

        this.repository.addPhones(name, phones);
    }
}
