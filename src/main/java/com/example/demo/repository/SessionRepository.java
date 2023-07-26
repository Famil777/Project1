package com.example.demo.repository;

import com.example.demo.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

//    @Modifying
    @Query("delete from Session s WHERE s.sessionId = :sessionId")
    List<Session> deleteBySessionId(@Param("sessionId") Long sessionId);
}
