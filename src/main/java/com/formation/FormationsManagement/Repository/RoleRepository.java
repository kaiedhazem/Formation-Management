package com.formation.FormationsManagement.Repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import com.formation.FormationsManagement.Entities.*;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
