package com.anna.tveritnyeva.stockorganizer.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationHierarchy {
    @Field(name = "category_name")
    private String categoryName;
    @Field(name = "section_name")
    private String sectionName;
}
