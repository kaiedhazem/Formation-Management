package com.formation.FormationsManagement.Services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.FormationsManagement.Entities.*;
import com.formation.FormationsManagement.Repository.RoleRepository;
import com.formation.FormationsManagement.Repository.UserRepository;
import com.formation.FormationsManagement.payload.Response.MessageResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
public class UserService implements IUserService {
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	RoleRepository roleRepository;

	@Override
	public MessageResponse save(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageResponse update(User user) {
		boolean existe = userRepo.existsById(user.getCode());
        if (!existe){
            boolean existe1 = userRepo.existsByUsername(user.getUsername());
            return new MessageResponse("Echec ! Cet user existe déja !");

        }
        user.setPassword(encoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(userRole);
		user.setRoles(roles);
        userRepo.save(user);
        return new MessageResponse("Succès Opération réalisée avec succès.");
	}

	@Override
	public MessageResponse delete(Long id) {
		// TODO Auto-generated method stub
		User user = findByCode(id);
        if (user==null){
            return new MessageResponse("Echec Cet enregistrement n'existe pas !");
        }
        userRepo.delete(user);
        return new MessageResponse("Succès L'enregistrement à été supprimé avec succès.");
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public User findByCode(Long id) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(id).orElse(null);
        return user;
	}
	
	
	
}
