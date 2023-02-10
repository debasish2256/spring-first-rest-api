package com.spring.project;
import java.util.List;
import java.util.Optional;

import com.spring.project.dto.Library;
import com.spring.project.repository.LibraryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/libraries")
public class LibraryController {

    @Autowired
    private LibraryRepository libraryRepository;

    @PostMapping
    public Library addLibrary(@RequestBody Library library) {
        return libraryRepository.save(library);
    }

    @GetMapping
    public List<Library> getAllLibraries() {
        return libraryRepository.findAll();
    }

    @GetMapping("/{libraryName}")
    public Optional<Library> getLibraryByName(@PathVariable String libraryName) {
        return libraryRepository.findByLibraryName(libraryName);
    }

    @PutMapping("/{id}")
    public Library updateLibrary(@PathVariable Long id, @RequestBody Library library) {
        library.setId(id);
        return libraryRepository.save(library);
    }

    @DeleteMapping("/{id}")
    public void deleteLibrary(@PathVariable Long id) {
        libraryRepository.deleteById(id);
    }
}
