package com.practice.ecommauth.Repository;

import com.practice.ecommauth.Models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {

    Optional<Session> findByTokenAndUser_Id(String token,Long Id);

    Optional<Session> findByUser_Id(Long id);

    void deleteByTokenAndUser_Id(String token,Long id);
}
