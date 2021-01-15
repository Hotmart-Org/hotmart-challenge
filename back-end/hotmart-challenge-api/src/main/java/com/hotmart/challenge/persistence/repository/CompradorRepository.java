package com.hotmart.challenge.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotmart.challenge.domain.model.Comprador;

@Repository
public interface CompradorRepository extends JpaRepository<Comprador, Long> {

}
