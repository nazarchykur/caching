package com.example.cachingdemo2;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@NoArgsConstructor
public class ShoppingDao {
    public static final String HASH_KEY = "Shopping";
    
    private RedisTemplate redisTemplate;

    @Autowired
    public ShoppingDao(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Shopping save(Shopping shopping) {
        redisTemplate.opsForHash().put(HASH_KEY, shopping.getId(), shopping);
        return shopping;
    }

    public List<Shopping> findAll(){
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    public Shopping findProductById(int id){
        return (Shopping) redisTemplate.opsForHash().get(HASH_KEY,id);
    }


    public String deleteProduct(int id){
        redisTemplate.opsForHash().delete(HASH_KEY,id);
        return "shopping item deleted successfully !!";
    }
}


/*
    The repository class for the Shopping can be defined as follows.
    The Hash in Redis is used for storing a number of key-value pairs which are effecient and requires less memory.

 */