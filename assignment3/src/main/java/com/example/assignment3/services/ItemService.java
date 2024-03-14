package com.example.assignment3.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.assignment3.models.Item;
import com.example.assignment3.repositories.ItemRepository;

@Service
public class ItemService {

    // Not necessarily the best idea - if there's plans 
    // to allow users to set page size on their own -
    // but this simplfies the methods below a little.
    @Value("${page.size}")
    private int pageSize;

    private final ItemRepository itemRepo;

    /** Dependency injection of Item Repository */
    public ItemService(ItemRepository repo) {
        itemRepo = repo;
    }

    /** Saves the given item to the repository */
    @SuppressWarnings("null")
    public int saveItem(Item item) {
        itemRepo.save(item);
        return -1;
    }

    /** Deletes the given item from the repository */
    @SuppressWarnings("null")
    public void deleteItem(Item item) {
        itemRepo.delete(item);
    }

    /** Retrieves a page of items using the given parameters */
    public Page<Item> getItems(int pageNumber, String sortField, String sortDirection, String brand, int year) {
        // Figure out sorting
        Sort sort = Sort.by(sortField);
        sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
            sort.ascending() :
            sort.descending(); 

        // Create pageable
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        if (brand == null || year == 0)
            return itemRepo.findAll(pageable);
        else {
            return itemRepo.findByBrandAndYear(brand, year, pageable);
        }
    }

    /** Retrieves a list of items filtered by the given brand and year */
    public List<Item> getFilteredItems(String brand, int year) {
        // Wanted filtering to work with pagination (and integrate it 
        // in normal getItems() method) but that didn't work.
        return itemRepo.findByBrandAndYear(brand, year);
    }

    /** Retrieves a single item by the given ID */
    public Optional<Item> getItemById(int id) {
        return itemRepo.findById(id);
    }
}
