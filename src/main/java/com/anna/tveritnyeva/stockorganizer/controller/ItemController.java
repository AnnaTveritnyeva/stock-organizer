package com.anna.tveritnyeva.stockorganizer.controller;

import com.anna.tveritnyeva.stockorganizer.documents.Item;
import com.anna.tveritnyeva.stockorganizer.exception.ItemException;
import com.anna.tveritnyeva.stockorganizer.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PutMapping("addItem")
    @ResponseStatus(HttpStatus.CREATED)
    public Item addItem(@RequestBody Item item) throws ItemException {
        return itemService.addItem(item);
    }

    @PutMapping("updateItem")
    @ResponseStatus(HttpStatus.OK)
    public Item updateItem(@RequestBody Item item) throws ItemException {
        return itemService.updateItem(item);
    }

    @DeleteMapping("deleteItem/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteItem(@RequestParam String id) throws ItemException {
        itemService.deleteItemById(id);
    }

    @GetMapping("getAllItems")
    @ResponseStatus(HttpStatus.OK)
    public List<Item> getAllItems () {
        return itemService.getAllItems();
    }

    @GetMapping("getItemsBySection/{section}")
    @ResponseStatus(HttpStatus.OK)
    public List<Item> getItemsBySection (@RequestParam String section) {
        return itemService.getItemsBySection(section);
    }

    @GetMapping("getItemsByStoreName/{storeName}")
    @ResponseStatus(HttpStatus.OK)
    public List<Item> getItemsByStoreName (@RequestParam String storeName){
        return itemService.getItemsByStoreName(storeName);
    }

    @GetMapping("getMissingItems")
    @ResponseStatus(HttpStatus.OK)
    public List<Item> getMissingItems () {
        return itemService.getMissingItems();
    }

    @GetMapping("getMissingItemsBySection/{section}")
    @ResponseStatus(HttpStatus.OK)
    public List<Item> getMissingItemsBySection (@RequestParam String section) {
        return itemService.getMissingItemsBySection(section);
    }

    @GetMapping("getMissingItemsByStoreName/{storeName}")
    @ResponseStatus(HttpStatus.OK)
    public List<Item> getMissingItemsByStoreName (@RequestParam String storeName){
        return itemService.getMissingItemsByStoreName(storeName);
    }
}