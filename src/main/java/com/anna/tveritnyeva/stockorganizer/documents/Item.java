package com.anna.tveritnyeva.stockorganizer.documents;

import com.anna.tveritnyeva.stockorganizer.beans.OrganizationHierarchy;
import com.anna.tveritnyeva.stockorganizer.beans.StoreUrl;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "items")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @Id
    private String id;
    @Field(name = "user_id")
    private String userId;
    private String name;
    private boolean missing;

    @Field(name = "store_URLs")
    @Singular
    private List<StoreUrl> storeURLs;

    @Field(name = "organization_hierarchy")
    private OrganizationHierarchy organizationHierarchy;

    @Singular("purchase_date")
    @Field("purchase_history")
    private List<LocalDate> purchaseHistory;

    public Item(String userId, String name, boolean missing, OrganizationHierarchy organizationHierarchy) {
        this.userId = userId;
        this.name = name;
        this.missing = missing;
        this.organizationHierarchy = organizationHierarchy;
        this.storeURLs = new ArrayList<>();
        this.purchaseHistory = new ArrayList<>();
    }

    public Item(String userId, String name, OrganizationHierarchy organizationHierarchy) {
        this.userId = userId;
        this.name = name;
        this.missing = false;
        this.organizationHierarchy = organizationHierarchy;
        this.storeURLs = new ArrayList<>();
        this.purchaseHistory = new ArrayList<>();
    }
}