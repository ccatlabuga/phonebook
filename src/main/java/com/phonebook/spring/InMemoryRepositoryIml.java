package com.phonebook.spring;

import com.phonebook.main.InMemoryRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Keeps phoneBook data in memory in ordered in accordance to addition.
 */
@Repository
public class InMemoryRepositoryIml implements InMemoryRepository {

    private Map<String, Set<String>> data;

    /**
     * no args constructor
     */
    public InMemoryRepositoryIml() {
        // LinkedHashMap is chosen because usually iteration order matters
        this(new LinkedHashMap<>());
    }

    /**
     * this constructor allows to inject initial data to the repository
     *
     * @param data
     */
    public InMemoryRepositoryIml(Map<String, Set<String>> data) {
        this.data = new LinkedHashMap<>(data);
    }

    @Override
    public Map<String, Set<String>> findAll() {
        return new LinkedHashMap<>(this.data);
    }

    @Override
    public Set<String> findAllPhonesByName(String name) {
        return this.data.getOrDefault(name, null);
    }

    @Override
    public String findNameByPhone(String phone) {
        return this.data.entrySet()
                .stream()
                .filter(entry -> entry.getValue().contains(phone))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Phone not found"));
    }

    @Override
    public void addPhone(String name, String phone) {
        this.data.computeIfAbsent(name, k -> new java.util.HashSet<>()).add(phone);
    }

    @Override
    public void removePhone(String phone) throws IllegalArgumentException {
        this.data.get(this.findNameByPhone(phone)).remove(phone);
    }
}
