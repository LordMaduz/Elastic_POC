package com.dbs.itt.dega.repo;

import com.dbs.itt.dega.dto.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends ElasticsearchRepository<Person, String> {
    List<Person> findBySchoolName(String name);
    List<Person> findBySchoolClassListName(String name);
    List<Person> findBySchoolClassListTeacherName(String name);
    List<Person> findByFirstNameContaining(String name);
    List<Person> findByIdentifierContaining(String name);
}
