package com.formation.FormationsManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.FormationsManagement.Entities.Profil;


public interface ProfilRepository extends JpaRepository<Profil, Long>{
	boolean existsByLibelle(String libelle);
}
