package com.formation.FormationsManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.FormationsManagement.Entities.Pays;

public interface PaysRepository extends JpaRepository<Pays, Long>{
	boolean existsByNom(String nom);
}
