package com.dbs.itt.dega.api;

import com.dbs.itt.dega.dto.Person;
import com.dbs.itt.dega.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController()
@RequestMapping("/product")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping("/add")
    public Person addPerson(@RequestBody final Person person) {
        return personService.addPerson(person);
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable final String id) {
        return personService.getPersonById(id);
    }

    @GetMapping("/first/{name}")
    public List<Person> getPersonByName(@PathVariable final String name) {
        return personService.findByFirstName(name);
    }

    @GetMapping("/identifier/{name}")
    public List<Person> getPersonByIdentifier(@PathVariable final String name) {
        return personService.findByIdentifier(name);
    }

    @GetMapping()
    public List<Person> getAll() {
        return personService.getAll();
    }

    @GetMapping("/school/{name}")
    public List<Person> findBySchoolName(@PathVariable String name) {
        return personService.findBySchoolName(name);
    }

    @GetMapping("/school/class/{name}")
    public List<Person> findBySchoolClassName(@PathVariable String name) {
        return personService.findBySchoolClassName(name);
    }

    @GetMapping("/school/class/teacher/{name}")
    public List<Person> findBySchoolClassTeacherName(@PathVariable String name) {
        return personService.findBySchoolClassTeacherName(name);
    }

    @GetMapping("/health")
    public ClusterHealthResponse getClusterHealth() throws IOException {
        return personService.getClusterHealth();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final String id) {
        personService.delete(id);
    }


}
