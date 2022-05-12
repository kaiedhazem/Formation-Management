package com.formation.FormationsManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.FormationsManagement.Entities.Formateur;

public interface FormateurRepository extends JpaRepository<Formateur, Long> {
	boolean existsByNom(String nom);
	List<Formateur> findByType(String type);

}
