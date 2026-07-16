package com.phonebook.component.service;

import com.phonebook.component.datarepository.DataRepository;
import com.phonebook.component.formatter.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * PhoneBook service implementation
 */
@Service
public class PhoneBook {
    @Autowired
    private DataRepository repository;

    @Autowired
    private Formatter renderer;

    public PhoneBook() {
        // be careful this.repository will not be initialised if injection on setter is chosen
    }

    /**
     * injection is supported on constructor level.
     *
     * @param repository
     */
    // @Autowired
    public PhoneBook(DataRepository repository) {
        this.repository = repository;
    }

    /**
     * injection is supported on setter level
     *
     * @param repository
     */
    public void setRepository(DataRepository repository) {
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
        for (String phone : phones) {
            this.addPhone(name, phone);
        }
    }

    public void addPhone(List<String> commandArgs) {
        this.addPhone(commandArgs.get(0), Arrays.asList(commandArgs.get(1).split(",")));
    }

    public void removePhone(String phone) {
        this.repository.removePhone(phone);
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
}
