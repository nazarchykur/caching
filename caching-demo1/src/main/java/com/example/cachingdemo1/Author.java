package com.example.cachingdemo1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    private int id;
    private String name;
    private String surname;
    private String birthDate;
    
}
