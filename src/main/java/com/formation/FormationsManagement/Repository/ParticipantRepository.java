package com.formation.FormationsManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.FormationsManagement.Entities.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

}
