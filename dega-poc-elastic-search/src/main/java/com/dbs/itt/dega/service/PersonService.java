package com.dbs.itt.dega.service;

import com.dbs.itt.dega.dto.Person;
import com.dbs.itt.dega.exception.ValidationFailedException;
import com.dbs.itt.dega.repo.PersonRepository;
import com.dbs.itt.dega.vo.Pagination;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final RestHighLevelClient client;

    public ResponseEntity<Person> addPerson(final Person person) {
        return ResponseEntity.status(HttpStatus.OK).body(personRepository.save(person));
    }

    public ResponseEntity<Person> getPersonById(String id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(person.get());
        } else {
            throw new ValidationFailedException("No Person Found for Id");
        }
    }

    public ResponseEntity<List<Person>> getAll(final Pagination pagination) {
        PageRequest pageable = PageRequest.of(pagination.getPage(), pagination.getSize());
        try {
            Iterable<Person> personIterable = personRepository.findAll(pageable);
            return ResponseEntity.status(HttpStatus.OK).body(IterableUtils.toList(personIterable));
        } catch (Exception e) {
            throw new ValidationFailedException(e.getMessage());
        }
    }

    public ResponseEntity<Object> delete(String id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            personRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            throw new ValidationFailedException("No Person Found for Id");
        }
    }

    public ResponseEntity<List<Person>> findByFirstName(String name) {
        return ResponseEntity.status(HttpStatus.OK).body(personRepository.findByFirstNameContaining(name));
    }

    public ResponseEntity<List<Person>> findByIdentifier(String name) {
        return ResponseEntity.status(HttpStatus.OK).body(personRepository.findByIdentifierContaining(name));
    }

    public ResponseEntity<List<Person>> findBySchoolClassName(String name) {
        return ResponseEntity.status(HttpStatus.OK).body(personRepository.findBySchoolClassListName(name));
    }

    public ResponseEntity<List<Person>> findBySchoolName(String name) {
        return ResponseEntity.status(HttpStatus.OK).body(personRepository.findBySchoolName(name));
    }

    public ResponseEntity<List<Person>> findBySchoolClassTeacherName(String name) {
        return ResponseEntity.status(HttpStatus.OK).body(personRepository.findBySchoolClassListTeacherName(name));
    }

    public ResponseEntity<ClusterHealthResponse> getClusterHealth() {
        return null;
//        ClusterHealthRequest request = new ClusterHealthRequest();
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(client.cluster().health(request, RequestOptions.DEFAULT));
//        } catch (IOException e) {
//            throw new ValidationFailedException(e.getMessage());
//        }
    }
}
