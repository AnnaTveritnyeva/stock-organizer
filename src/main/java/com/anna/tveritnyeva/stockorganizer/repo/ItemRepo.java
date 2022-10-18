package com.anna.tveritnyeva.stockorganizer.repo;

import com.anna.tveritnyeva.stockorganizer.beans.OrganizationHierarchy;
import com.anna.tveritnyeva.stockorganizer.documents.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends MongoRepository<Item, String> {
    boolean existsByNameAndOrganizationHierarchyAndUserId(String name, OrganizationHierarchy organizationHierarchy, String userId);

    @Query(value = "{'user_id': :#{#user_id}, 'organization_hierarchy.category_name': :#{#category_name}}")
    List<Item> findByCategoryAndUserId(@Param("user_id") String userId, @Param("category_name") String categoryName);

    @Query(value = "{'user_id': :#{#user_id}, 'organization_hierarchy.section_name': :#{#section_name}}")
    List<Item> findBySectionAndUserId(@Param("user_id") String userId, @Param("section_name") String sectionName);

    //boolean existsByOrganizationHierarchy (String section);

    List<Item> findByMissingAndUserId(boolean missing, String userId);

    Item findByNameAndUserId(String name, String userId);

    List<Item> findByUserId(String userId);

    boolean existsByIdAndUserId(String id, String userId);

    @Query(value = "{'user_id': :#{#user_id}, 'organization_hierarchy.section_name': :#{#section_name}, 'missing':true}")
    List<Item> findByMissingAndSectionAndUserId(@Param("user_id") String userId, @Param("section_name") String sectionName);

    @Query(value = "{'user_id': :#{#user_id}, 'store_URLs.name': :#{#storeName}}")
    List<Item> findByStoreNameAndUserId(@Param("user_id") String userId, @Param("storeName") String storeName);

    @Query(value = "{'user_id': :#{#user_id}, 'store_URLs.name': :#{#storeName}, 'missing':true}")
    List<Item> findMissingByStoreNameAndUserId(@Param("user_id") String userId, @Param("storeName") String storeName);
}
