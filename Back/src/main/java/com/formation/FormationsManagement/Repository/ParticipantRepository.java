package com.formation.FormationsManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.FormationsManagement.Entities.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
	boolean existsByNom(String nom);
	List<Participant> findByType(String type);

}
