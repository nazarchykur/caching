package com.example.cachingdemo1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        log.info("Request received!");
        // retrieving the desired data
        List<Author> authors = authorService.retrieveAll();
        log.info("Data retrieved!");

//        return new ResponseEntity<>(
//                authors,
//                HttpStatus.OK
//        );
        return ResponseEntity
                .ok() // == .status(HttpStatus.OK)
                .body(authors);
    }

    @GetMapping("/{id}")
    public Author getOrCreateAuthor(@PathVariable("id") Integer id) {
        return authorService.getOrCreateAuthor(id);
    }
}
