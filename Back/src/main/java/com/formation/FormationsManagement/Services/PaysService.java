package com.formation.FormationsManagement.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.FormationsManagement.Entities.Domaine;
import com.formation.FormationsManagement.Entities.Pays;
import com.formation.FormationsManagement.Repository.PaysRepository;
import com.formation.FormationsManagement.payload.Response.MessageResponse;

@Service
public class PaysService implements IPaysService{
	@Autowired
	PaysRepository paysRepo;
	@Override
	public MessageResponse save(Pays pays) {
        boolean existe = paysRepo.existsByNom(pays.getNom());
        if (existe){
            return new MessageResponse("Echec ! Ce pays exist déja !");
        }
        paysRepo.save(pays);
        return new MessageResponse("Succès Opération réalisée avec succès.");
	}

	@Override
	public MessageResponse update(Pays pays) {
		   boolean existe = paysRepo.existsById(pays.getId());
	        if (!existe){
	            boolean existe1 = paysRepo.existsByNom(pays.getNom());
	            return new MessageResponse("Echec ! Cette domaine existe déja !");

	        }
	        paysRepo.save(pays);
	        return new MessageResponse("Succès Opération réalisée avec succès.");
	}

	@Override
	public MessageResponse delete(Long id) {
		  Pays pays = findById(id);
	        if (pays==null){
	            return new MessageResponse("Echec Cet enregistrement n'existe pas !");
	        }
	        paysRepo.delete(pays);
	        return new MessageResponse("Succès L'enregistrement à été supprimé avec succès.");
	}

	@Override
	public List<Pays> findAll() {
        return paysRepo.findAll();

	}

	@Override
	public Pays findById(Long id) {
	  Pays pays = paysRepo.findById(id).orElse(null);
	        return pays;
	}

}
