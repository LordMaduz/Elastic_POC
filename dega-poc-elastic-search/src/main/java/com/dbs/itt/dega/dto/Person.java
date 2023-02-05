package com.dbs.itt.dega.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@RequiredArgsConstructor
@Data
@Document(indexName = "person")
public class Person {

    @Id
    private String id;
    @Field(type = FieldType.Text, name = "firstName")
    private String firstName;
    @Field(type = FieldType.Text, name = "lastName")
    private String lastName;
    @Field(type = FieldType.Flattened, name = "school")
    private School school;
    @Field(type = FieldType.Integer, name = "age")
    private Integer age;
    @Field(type = FieldType.Keyword, name = "identifier")
    private String identifier;
    @Field(type = FieldType.Text, name = "remarks", index = false)
    private String remarks;

}
