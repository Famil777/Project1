package com.example.demo.repository;

import com.example.demo.entity.Hall;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {

    @Query("select h from Hall h where h.hallId = ?1")
    Optional<Hall> findByHallId(Long hallId);

}
