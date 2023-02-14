package com.example.cachingdemo1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class AuthorService {

    @Cacheable("authors")
    public List<Author> retrieveAll() {
        // simulating a delay due to the data retrieval operation
        try  {
            log.info("Retrieving all the authors...");
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            log.error("InterruptedException: " + e.getMessage());
            e.printStackTrace();
        }

        // returning a list containing all the authors
        return Arrays.asList(
                new Author("Patricia", "Brown", null),
                new Author("James", "Smith", "1964-07-01"),
                new Author("Mary", "Williams", "1988-11-19")
        );
    }
}

/*
1 виклик 
піде до БД (імітація вигрузки даних) = +- 3 секунди

2023-02-14T17:53:15.988+02:00  INFO 317987 --- [nio-8091-exec-2] c.example.cachingdemo1.AuthorController  : Request received!
2023-02-14T17:53:15.991+02:00  INFO 317987 --- [nio-8091-exec-2] com.example.cachingdemo1.AuthorService   : Retrieving all the authors...
2023-02-14T17:53:18.992+02:00  INFO 317987 --- [nio-8091-exec-2] c.example.cachingdemo1.AuthorController  : Data retrieved!


2 виклик 
дані візьмуться з кешу = +- 5 мілісекунд

2023-02-14T17:54:03.640+02:00  INFO 317987 --- [nio-8091-exec-3] c.example.cachingdemo1.AuthorController  : Request received!
2023-02-14T17:54:03.641+02:00  INFO 317987 --- [nio-8091-exec-3] c.example.cachingdemo1.AuthorController  : Data retrieved!


 */