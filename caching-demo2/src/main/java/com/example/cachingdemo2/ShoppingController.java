package com.example.cachingdemo2;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shopping-items")
@RequiredArgsConstructor
public class ShoppingController {

    private final ShoppingDao shoppingdao;

    @PostMapping
    public Shopping save(@RequestBody Shopping shopping) {
        return shoppingdao.save(shopping);
    }

    @PutMapping("/{id}")
    @CachePut(key = "#id", value = "Shopping")
    public Shopping updateShop(@PathVariable int id, @RequestBody Shopping shopping) {
        Shopping s1 = shoppingdao.findProductById(id);
        s1.setName(shopping.getName());

        return shoppingdao.save(s1);
    }

    @GetMapping
    public List<Shopping> getAllProducts() {
        return shoppingdao.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id", value = "Shopping")
    public Shopping findItems(@PathVariable int id) {
        return shoppingdao.findProductById(id);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id", value = "Shopping")
    public String remove(@PathVariable int id) {
        return shoppingdao.deleteProduct(id);
    }
}

/*
    All the RequestMappings are defined in this class.
    @Cacheable annotation is used for retrieving data from the database and storing in Redis Cache.
    Caching also provides the feature that the data should be sent to cache only a particular condition is fulfilled. The syntax can be described as-
    @Cacheable(key="#id", value ="Shopping", unless="#result.price >20")
    @CachePut annotation updates the data in Redis Cache when the update is applied on the database.
    @CacheEvict annotation deletes the data from the cache when the user wants to delete the data from the database.

 */