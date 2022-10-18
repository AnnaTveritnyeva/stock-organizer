package com.anna.tveritnyeva.stockorganizer.repo;

import com.anna.tveritnyeva.stockorganizer.documents.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepo extends MongoRepository<Category, String> {
    Optional<Category> findByIdAndUserId(String id, String userId);

    boolean existsByIdAndUserId(String id, String userId);

    List<Category> findByUserId(String userId);

    boolean existsByNameAndUserId(String name, String userId);

//    @Query(value = "{'user_id': :#{#user_id} ,'sections.name': :#{#section_name}}")
//    Optional<Category> findByUserIdAndSectionName(@Param("user_id") String userId, @Param("section_name") String sectionName);
}
