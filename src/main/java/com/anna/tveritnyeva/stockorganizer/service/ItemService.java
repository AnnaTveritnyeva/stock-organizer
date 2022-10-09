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

    public void deleteItem(Item item) throws ItemException {
        if (!itemRepo.existsByIdAndUserId(item.getId(), userId)) {
            throw new ItemException(ExceptionMessage.ITEM_DOES_NOT_EXIST);
        } else {
            itemRepo.delete(item);
        }
    }

    public Item getItemByName(String name) {
        return itemRepo.findByNameAndUserId(name, userId);
    }

    public List<Item> getItemsByCategory(String category) {
        return itemRepo.findByCategoryAndUserId(userId, category);
    }

    public List<Item> getItemsBySection(String section) {
        return itemRepo.findBySectionAndUserId(userId, section);
    }

    public List<Item> getAllItems() {
        return itemRepo.findByUserId(userId);
    }

    public List<Item> getMissingItems() {
        return itemRepo.findByMissingAndUserId(true, userId);
    }
}