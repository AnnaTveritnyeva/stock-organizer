package com.anna.tveritnyeva.stockorganizer.repo;

import com.anna.tveritnyeva.stockorganizer.documents.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends MongoRepository<Item, String> {
    boolean existsByNameAndOrganizationHierarchyAndUserId(String name, List<String> organizationHierarchy, String userId);

    @Query(value = "{'user_id': :#{#user_id}, 'organization_hierarchy.0': :#{#category}}")
    List<Item> findByCategoryAndUserId(@Param("user_id") String userId, @Param("category") String category);

    @Query(value = "{'user_id': :#{#user_id}, 'organization_hierarchy': :#{#section}}")
    List<Item> findBySectionAndUserId(@Param("user_id") String userId, @Param("section") String section);

    //boolean existsByOrganizationHierarchy (String section);

    List<Item> findByMissingAndUserId(boolean missing, String userId);

    Item findByNameAndUserId(String name, String userId);

    List<Item> findByUserId(String userId);

    boolean existsByIdAndUserId(String id, String userId);

    @Query(value = "{'user_id': :#{#user_id}, 'organization_hierarchy': :#{#section}, 'missing':true}")
    List<Item> findByMissingAndSectionAndUserId(@Param("user_id") String userId, @Param("section") String section);
}
