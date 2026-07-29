package com.phonebook.env.webmvc.controller;

import com.phonebook.core.datarepository.DataRepository;
import com.phonebook.core.service.PhoneBook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/contacts")
public class PhoneBookController {
    private final PhoneBook phoneBook;

    public PhoneBookController(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }

    @GetMapping({"/", ""})
    public ResponseEntity<Map<String, Set<String>>> getAll() {
        return ResponseEntity.ok(this.phoneBook.findAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Set<String>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(this.phoneBook.findAllPhonesByName(name));
    }

    @PutMapping("/{name}")
    public ResponseEntity<Set<String>> addPhoneNumber(@PathVariable String name, @RequestBody List<String> phones) {
        this.phoneBook.addPhones(name, phones);
        return new ResponseEntity<>(this.phoneBook.findAllPhonesByName(name), HttpStatus.CREATED);
    }

    @PostMapping({"/", ""})
    public ResponseEntity<URI> addRecord(@RequestBody Map.Entry<String, List<String>> body) {
        this.phoneBook.addName(body.getKey());
        this.phoneBook.addPhones(body.getKey(), body.getValue());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{name}")
                .buildAndExpand(body.getKey())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{number}")
    public ResponseEntity<Void> deleteRecord(@PathVariable String number) {
        this.phoneBook.removePhone(number);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
