package com.phonebook.component.webmvc.controller;

import com.phonebook.component.common.datarepository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/contacts")
public class PhoneBookController {
    @Autowired
    private DataRepository repository;

    @GetMapping("/")
    public ResponseEntity<Map<String, Set<String>>> getAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Set<String>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(this.repository.findAllPhonesByName(name));
    }

    @PutMapping("/{name}")
    public ResponseEntity<Set<String>> addPhoneNumber(@PathVariable String name, @RequestBody List<String> phones) {
        if (Objects.isNull(this.repository.findAllPhonesByName(name))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            this.repository.addPhones(name, phones);
            return new ResponseEntity<>(this.repository.findAllPhonesByName(name), HttpStatus.CREATED);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Set<String>> addRecord(@RequestBody Map.Entry<String, List<String>> body) {
        if (!Objects.isNull(this.repository.findAllPhonesByName(body.getKey()))) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            this.repository.addPhones(body.getKey(), body.getValue());
            return new ResponseEntity<>(this.repository.findAllPhonesByName(body.getKey()), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{number}")
    public ResponseEntity<Void> deleteRecord(@PathVariable String number) {
        try {
            this.repository.removePhone(number);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
