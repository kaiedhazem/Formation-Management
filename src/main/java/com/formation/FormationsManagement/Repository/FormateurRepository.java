package com.formation.FormationsManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.FormationsManagement.Entities.Formateur;

public interface FormateurRepository extends JpaRepository<Formateur, Long> {

}
