package com.example.demo.specification;


import com.example.demo.entity.enums.Genre;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Movie;

@Component
public class MovieSpecification {

    public static Specification<Movie> containsName(String name) {
        return (root, query, cb) -> {
            return cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    public static  Specification<Movie> findByGenre(Genre genre){
        return (root, query, cb) -> {
            return  cb.equal(root.get("genre") ,genre );
        };
    }


    public static  Specification<Movie> greaterThanRating(Double rating){
        return (root, query, cb) ->{
            return cb.greaterThan(root.get("rating") , rating);
        };
    }



}
