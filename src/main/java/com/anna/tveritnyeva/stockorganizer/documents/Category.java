package com.anna.tveritnyeva.stockorganizer.documents;

import com.anna.tveritnyeva.stockorganizer.beans.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "categories")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class Category extends Section {
    @Id
    private String id;
    @Field(name = "user_id")
    private String userId;
    @Singular
    private List<Section> sections;

    public Category(String name, String color) {
        super(name, color);
        this.sections = new ArrayList<>();
    }
}
