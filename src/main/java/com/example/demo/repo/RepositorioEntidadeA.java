package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EntidadeA;

@Repository
public interface RepositorioEntidadeA extends JpaRepository<EntidadeA, Long> {
    
}
