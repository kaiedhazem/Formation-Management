package com.formation.FormationsManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.FormationsManagement.Entities.Organisme;

public interface OrganismeRepository extends JpaRepository<Organisme, Long> {
	boolean existsByLibelle(String libelle);
}
