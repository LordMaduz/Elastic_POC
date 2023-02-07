package com.dbs.itt.dega.api;

import com.dbs.itt.dega.dto.Person;
import com.dbs.itt.dega.service.PersonService;
import com.dbs.itt.dega.vo.Pagination;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController()
@RequestMapping("/product")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping("/add")
    public ResponseEntity<Person> addPerson(@RequestBody final Person person) {
        return personService.addPerson(person);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable final String id) {
        return personService.getPersonById(id);
    }

    @GetMapping("/first/{name}")
    public ResponseEntity<List<Person>> getPersonByName(@PathVariable final String name) {
        return personService.findByFirstName(name);
    }

    @GetMapping("/identifier/{name}")
    public ResponseEntity<List<Person>> getPersonByIdentifier(@PathVariable final String name) {
        return personService.findByIdentifier(name);
    }

    @PostMapping("/find")
    public ResponseEntity<List<Person>> getAll(@RequestBody final Pagination pagination) {
        return personService.getAll(pagination);
    }

    @GetMapping("/school/{name}")
    public ResponseEntity<List<Person>> findBySchoolName(@PathVariable String name) {
        return personService.findBySchoolName(name);
    }

    @GetMapping("/school/class/{name}")
    public ResponseEntity<List<Person>> findBySchoolClassName(@PathVariable String name) {
        return personService.findBySchoolClassName(name);
    }

    @GetMapping("/school/class/teacher/{name}")
    public ResponseEntity<List<Person>> findBySchoolClassTeacherName(@PathVariable String name) {
        return personService.findBySchoolClassTeacherName(name);
    }

    @GetMapping("/health")
    public ResponseEntity<ClusterHealthResponse> getClusterHealth() throws IOException {
        return personService.getClusterHealth();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable final String id) {
        return personService.delete(id);
    }


}
