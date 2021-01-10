package com.arcana.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcana.model.Reading;
import com.arcana.model.User;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, Long> {
   

    List<Reading> findByUser(User user);
}