package com.example.demo.specification;



import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Movie;

@Component
public class MovieSpecification {

    public static Specification<Movie> containsName(String name){
        return (root , query , cb) -> {
            return cb.like(cb.lower(root.get("name")), "%" + name.toString() +"%");
        };
    }
    
}
