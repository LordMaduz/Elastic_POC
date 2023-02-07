package com.dbs.itt.dega.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@Data
public class Class {

    @Id
    private String id;
    private String name;
    private Teacher teacher;
}
