package com.formation.FormationsManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.FormationsManagement.Entities.Session;


public interface SessionRepository extends JpaRepository<Session, Long> {

}
