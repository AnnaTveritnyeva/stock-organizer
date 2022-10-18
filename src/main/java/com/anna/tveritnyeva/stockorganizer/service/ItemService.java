package com.anna.tveritnyeva.stockorganizer.service;

import com.anna.tveritnyeva.stockorganizer.documents.Item;
import com.anna.tveritnyeva.stockorganizer.exception.ExceptionMessage;
import com.anna.tveritnyeva.stockorganizer.exception.ItemException;
import com.anna.tveritnyeva.stockorganizer.repo.ItemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepo itemRepo;
    private final String userId = "someUser";

    public Item addItem(Item item) throws ItemException {
        if (itemRepo.existsByNameAndOrganizationHierarchyAndUserId(
                item.getName(), item.getOrganizationHierarchy(), userId
        )) {
            throw new ItemException(ExceptionMessage.ITEM_ALREADY_EXISTS);
        } else {
            item.setUserId(userId);
            return itemRepo.save(item);
        }
    }

    public Item updateItem(Item item) throws ItemException {
        if (!itemRepo.existsByIdAndUserId(item.getId(), userId)) {
            throw new ItemException(ExceptionMessage.ITEM_DOES_NOT_EXIST);
        } else {
            return itemRepo.save(item);
        }
    }

    public void deleteItemById(String id) throws ItemException {
        if (!itemRepo.existsByIdAndUserId(id, userId)) {
            throw new ItemException(ExceptionMessage.ITEM_DOES_NOT_EXIST);
        } else {
            itemRepo.deleteById(id);
        }
    }

    public Item getItemByName(String name) {
        return itemRepo.findByNameAndUserId(name, userId);
    }

    public List<Item> getItemsByCategory(String categoryName) {
        return itemRepo.findByCategoryAndUserId(userId, categoryName);
    }

    public List<Item> getItemsBySection(String sectionName) {
        return itemRepo.findBySectionAndUserId(userId, sectionName);
    }

    public List<Item> getAllItems() {
        return itemRepo.findByUserId(userId);
    }

    public List<Item> getMissingItems() {
        return itemRepo.findByMissingAndUserId(true, userId);
    }

    public List<Item> getMissingItemsBySection(String sectionName) {
        return itemRepo.findByMissingAndSectionAndUserId(userId, sectionName);
    }

    public List<Item> getItemsByStoreName(String storeName) {
        return itemRepo.findByStoreNameAndUserId(userId, storeName);
    }

    public List<Item> getMissingItemsByStoreName(String storeName) {
        return itemRepo.findMissingByStoreNameAndUserId(userId, storeName);
    }
}