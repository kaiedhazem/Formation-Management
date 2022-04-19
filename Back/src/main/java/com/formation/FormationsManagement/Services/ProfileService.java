package com.formation.FormationsManagement.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.formation.FormationsManagement.Entities.Profil;
import com.formation.FormationsManagement.Repository.ProfilRepository;
import com.formation.FormationsManagement.payload.Response.MessageResponse;

@Service 
public class ProfileService implements IProfileService {
	@Autowired
	ProfilRepository profilRepo;
	@Override
	public MessageResponse save(Profil profil) {
		  boolean existe = profilRepo.existsByLibelle(profil.getLibelle());
	        if (existe){
	            return new MessageResponse("Echec ! Ce profil exist déja !");
	        }
	        profilRepo.save(profil);
	        return new MessageResponse("Succès Opération réalisée avec succès.");
	}

	@Override
	public MessageResponse update(Profil profil) {
		boolean existe = profilRepo.existsById(profil.getId());
        if (!existe){
            boolean existe1 = profilRepo.existsByLibelle(profil.getLibelle());
            return new MessageResponse("Echec ! Cet profil existe déja !");

        }
        profilRepo.save(profil);
        return new MessageResponse("Succès Opération réalisée avec succès.");
	}

	@Override
	public MessageResponse delete(Long id) {
		Profil profil = findById(id);
          if (profil==null){
              return new MessageResponse("Echec Cet enregistrement n'existe pas !");
          }
          profilRepo.delete(profil);
          return new MessageResponse("Succès L'enregistrement à été supprimé avec succès.");
	}

	@Override
	public List<Profil> findAll() {
		// TODO Auto-generated method stub
    	return profilRepo.findAll();

	}

	@Override
	public Profil findById(Long id) {
		// TODO Auto-generated method stub
		Profil profil = profilRepo.findById(id).orElse(null);
        return profil;
	}

}
