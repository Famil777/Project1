package com.example.demo.repository;

import com.example.demo.entity.Users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {

    @Query("Select u from Users u where u.userId = :userId")
    Optional<Users> findByUserId(@Param("userId") Long userId);

}
