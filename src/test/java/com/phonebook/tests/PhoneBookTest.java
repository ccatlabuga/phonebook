package com.phonebook.tests;

import com.phonebook.config.ApplicationConfig;
import com.phonebook.core.exception.UserNotFoundException;
import com.phonebook.core.service.PhoneBook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = { ApplicationConfig.class })
public class PhoneBookTest {
    @Autowired
    private PhoneBook phoneBook;

    @Test
    public void addSinglePhoneTest() {
        String NAME = "Alex";
        String PHONE = "test";

        this.phoneBook.addPhone(NAME, PHONE);
        Assertions.assertTrue(this.phoneBook.findAllPhonesByName(NAME).contains(PHONE));
    }

    @Test
    public void addSinglePhoneWithNonexistentNameThrowsTest() {
        String NAME = "Notexist";
        String PHONE = "test";

        Assertions.assertThrows(UserNotFoundException.class, () -> this.phoneBook.addPhone(NAME, PHONE));
    }

    @Test
    public void addListOfPhonesTest() {
        String NAME = "Alex";
        List<String> PHONES = List.of("test1", "test2");

        this.phoneBook.addPhone(NAME, PHONES);
        Assertions.assertTrue(this.phoneBook.findAllPhonesByName(NAME).containsAll(PHONES));
    }

    @Test
    public void addListOfPhonesWithNonexistentNameThrowsTest() {
        String NAME = "Notexist";
        List<String> PHONES = List.of("test1", "test2");

        Assertions.assertThrows(UserNotFoundException.class, () -> this.phoneBook.addPhone(NAME, PHONES));
    }

    @Test
    public void addListOfPhonesAsArgsTest() {
        List<String> ARGS = List.of("Alex", "test3,test4");

        this.phoneBook.addPhone(ARGS);
        Assertions.assertTrue(this.phoneBook.findAllPhonesByName(ARGS.getFirst())
                .containsAll(Arrays.stream(ARGS.getLast().split(",")).toList()));
    }

    @Test
    public void addListOfPhonesWithNonexistentNameAsArgsThrowsTest() {
        List<String> ARGS = List.of("Noexist", "test3,test4");

        Assertions.assertThrows(UserNotFoundException.class, () -> this.phoneBook.addPhone(ARGS));
    }
}
