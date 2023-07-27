package com.example.demo.specification;

import com.example.demo.entity.Movie;
import com.example.demo.entity.enums.Genre;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieSpecification {

    public static Specification<Movie> containsName(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static  Specification<Movie> findByGenres(List<Genre> genres){
        return (root, query, cb) -> {
            if (genres == null) {return null;}
            return root.join("genres").in(genres);
        };
    }

    public static  Specification<Movie> greaterThanRating(Double rating){
        return (root, query, cb) -> cb.greaterThan(root.get("rating") , rating);

    }

}
