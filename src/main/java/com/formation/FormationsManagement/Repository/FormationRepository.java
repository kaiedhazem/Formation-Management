package com.formation.FormationsManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.FormationsManagement.Entities.Formation;


public interface FormationRepository extends JpaRepository<Formation, Long> {
	boolean existsByTitre(String titre);
}
