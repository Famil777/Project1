package com.example.demo.specification;



import com.example.demo.entity.enums.Genre;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Movie;


import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {

    public static Specification<Movie> containsName(String name) {
        return (root, query, cb) -> {
            return cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    public static  Specification<Movie> findByGenres(List<Integer> genres){
        return (root, query, cb) -> {
//            return   cb.in(root.get("genres")).value(genres);
            return root.get("genres").in(genres);
        };
    }

    public static  Specification<Movie> greaterThanRating(Double rating){
        return (root, query, cb) ->{
            return cb.greaterThan(root.get("rating") , rating);
        };

    }




}
