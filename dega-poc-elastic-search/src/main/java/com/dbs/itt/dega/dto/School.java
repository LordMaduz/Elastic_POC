package com.dbs.itt.dega.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;
@NoArgsConstructor
@Data
public class School {

    @Id
    private String id;
    private String name;
    private List<Class> classList;
}
