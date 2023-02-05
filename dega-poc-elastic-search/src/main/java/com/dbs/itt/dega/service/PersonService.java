package com.dbs.itt.dega.service;

import com.dbs.itt.dega.dto.Person;
import com.dbs.itt.dega.repo.PersonRepository;
import com.dbs.itt.dega.vo.Pagination;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final RestHighLevelClient client;

    public Person addPerson(final Person person) {
        return personRepository.save(person);
    }

    public Person getPersonById(String id) {
        Optional<Person> person = personRepository.findById(id);
        if(person.isPresent()) {
            return person.get();
        } else {
            throw new RuntimeException("No Person Found for Id");
        }
    }

    public List<Person> getAll(final Pagination pagination) {
        PageRequest pageable = PageRequest.of(pagination.getPage(),pagination.getSize());
        Iterable<Person> personIterable =  personRepository.findAll(pageable);
        return IterableUtils.toList(personIterable);
    }

    public void delete(String id) {
        Optional<Person> person = personRepository.findById(id);
        if(person.isPresent()) {
            personRepository.deleteById(id);
        } else {
            throw new RuntimeException("No Person Found for Id");
        }
    }

    public List<Person> findByFirstName(String name) {
        return personRepository.findByFirstNameContaining(name);
    }

    public List<Person> findByIdentifier(String name) {
        return personRepository.findByIdentifierContaining(name);
    }

    public List<Person> findBySchoolClassName(String name) {
        return personRepository.findBySchoolClassListName(name);
    }

    public List<Person> findBySchoolName(String name) {
        return personRepository.findBySchoolName(name);
    }

    public List<Person> findBySchoolClassTeacherName(String name) {
        return personRepository.findBySchoolClassListTeacherName(name);
    }

    public ClusterHealthResponse getClusterHealth() {
        ClusterHealthRequest request = new ClusterHealthRequest();
        try {
            return client.cluster().health(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
