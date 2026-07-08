package com.phonebook.spring;

import com.phonebook.main.InMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Keeps phoneBook data in memory in ordered in accordance to addition.
 */
@Repository
public class InMemoryRepositoryImpl implements InMemoryRepository {
    @Autowired
    PhoneBookFormatterImpl renderer;

    private final Map<String, Set<String>> data;

    /**
     * no args constructor
     */
    public InMemoryRepositoryImpl() {
        // LinkedHashMap is chosen because usually iteration order matters
        this(new LinkedHashMap<>());
    }

    /**
     * this constructor allows to inject initial data to the repository
     *
     * @param data
     */
    public InMemoryRepositoryImpl(Map<String, Set<String>> data) {
        this.data = new LinkedHashMap<>(data);
    }

    @Override
    public Map<String, Set<String>> findAll() {
        Map<String, Set<String>> data = new LinkedHashMap<>(this.data);
        this.renderer.info("Retrieved all data in phonebook");
        return data;
    }

    @Override
    public Set<String> findAllPhonesByName(String name) {
        Set<String> data = this.data.getOrDefault(name, null);
        this.renderer.info(String.format("Retrieved phone numbers: %s for name: %s", data.toString(), name));
        return data;
    }

    @Override
    public String findNameByPhone(String phone) {
        String name = this.data.entrySet()
                .stream()
                .filter(entry -> entry.getValue().contains(phone))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Phone not found"));
        this.renderer.info(String.format("Found name: %s for phone: %s", name, phone));
        return name;
    }

    @Override
    public void addPhone(String name, String phone) {
        this.data.computeIfAbsent(name, k -> new java.util.HashSet<>()).add(phone);
        this.renderer.info(String.format("Added phone number: %s for name: %s ", phone, name));
    }

    @Override
    public void removePhone(String phone) throws IllegalArgumentException {
        String name = this.findNameByPhone(phone);
        this.data.get(name).remove(phone);
        this.renderer.info(String.format("Removed number: %s", phone));

        if (this.findAllPhonesByName(name).isEmpty()) {
            this.data.remove(name);
            this.renderer.info(String.format("Removed name: %s", name));
        }
    }
}
