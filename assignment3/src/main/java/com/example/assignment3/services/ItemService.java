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

    public ItemService(ItemRepository repo) {
        itemRepo = repo;
    }

    @SuppressWarnings("null")
    public int saveItem(Item item) {
        itemRepo.save(item);
        return -1;
    }

    @SuppressWarnings("null")
    public void deleteItem(Item item) {
        itemRepo.delete(item);
    }

    public Page<Item> getItems(int pageNumber, String sortField, String sortDirection, String brand, int year) {
        Sort sort = Sort.by(sortField);
        sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
            sort.ascending() :
            sort.descending(); 

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        if (brand == null || year == 0)
            return itemRepo.findAll(pageable);
        else {
            return itemRepo.findByBrandAndYear(brand, year, pageable);
        }
    }

    // Wanted filtering to work with pagination (and integrate it 
    // in normal getItems() method) but that didn't work.
    public List<Item> getFilteredItems(String brand, int year) {
        return itemRepo.findByBrandAndYear(brand, year);
    }

    public Optional<Item> getItemById(int id) {
        return itemRepo.findById(id);
    }
}
