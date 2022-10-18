package com.anna.tveritnyeva.stockorganizer.service;

import com.anna.tveritnyeva.stockorganizer.beans.Section;
import com.anna.tveritnyeva.stockorganizer.documents.Category;
import com.anna.tveritnyeva.stockorganizer.documents.Item;
import com.anna.tveritnyeva.stockorganizer.exception.CategoryException;
import com.anna.tveritnyeva.stockorganizer.exception.ExceptionMessage;
import com.anna.tveritnyeva.stockorganizer.repo.CategoryRepo;
import com.anna.tveritnyeva.stockorganizer.repo.ItemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepo categoryRepo;
    private final ItemRepo itemRepo;

    private final String userId = "someId";

    public Category addCategory(Category category) throws CategoryException {
        System.out.println("addCategory: " + category);
        if (categoryRepo.existsByNameAndUserId(category.getName(), userId)) {
            throw new CategoryException(ExceptionMessage.CATEGORY_NAME_ALREADY_EXIST);
        }
        category.setUserId(userId);
        return categoryRepo.save(category);
    }

    public Category updateCategory(Category category) throws CategoryException {
        Optional<Category> categoryToUpdate = categoryRepo.findByIdAndUserId(category.getId(), userId);
        if (categoryToUpdate.isEmpty()) {
            throw new CategoryException(ExceptionMessage.CATEGORY_DOES_NOT_EXIST);
        }
        if (!categoryToUpdate.get().getName().equals(category.getName())) {
            updateCategoryNameInItems(categoryToUpdate.get().getName(), category.getName());
        }
        return categoryRepo.save(category);
    }

    public void deleteCategoryById(String id) throws CategoryException {
        if (!categoryRepo.existsByIdAndUserId(id, userId)) {
            throw new CategoryException(ExceptionMessage.CATEGORY_DOES_NOT_EXIST);
        }
        categoryRepo.deleteById(id);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findByUserId(userId);
    }

    public Category addSection(String id, Section section) throws CategoryException {
        Optional<Category> category = categoryRepo.findByIdAndUserId(id, userId);
        //Optional<Category> section1 = categoryRepo.findByUserIdAndSectionName(userId, section.getName());
        if (category.isEmpty()) {
            throw new CategoryException(ExceptionMessage.CATEGORY_DOES_NOT_EXIST);
        }
        if (category.get().getSections().stream().anyMatch(section1 -> section1.getName().equals(section.getName()))) {
            throw new CategoryException(ExceptionMessage.SECTION_ALREADY_EXISTS);
        }
        category.get().getSections().add(section);
        return categoryRepo.save(category.get());
    }

    public Category deleteSection(String id, Section section) throws CategoryException {
        Optional<Category> category = categoryRepo.findByIdAndUserId(id, userId);

        if (category.isEmpty()) {
            throw new CategoryException(ExceptionMessage.CATEGORY_DOES_NOT_EXIST);
        }

        category.get().getSections().remove(section);
        return categoryRepo.save(category.get());
    }

    private void updateCategoryNameInItems(String categoryOldName, String categoryNewName) {
        List<Item> items = itemRepo.findByCategoryAndUserId(userId, categoryOldName);
        items.forEach(item ->
        {
            item.getOrganizationHierarchy().setCategoryName(categoryNewName);
            itemRepo.save(item);
        });
    }
}
