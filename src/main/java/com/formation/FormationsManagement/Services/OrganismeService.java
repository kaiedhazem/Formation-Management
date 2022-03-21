package com.formation.FormationsManagement.Services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.formation.FormationsManagement.Entities.Organisme;
import com.formation.FormationsManagement.Repository.OrganismeRepository;
import com.formation.FormationsManagement.payload.Response.MessageResponse;

@Service
public class OrganismeService implements IOrganismeService {
    @Autowired
    OrganismeRepository organismeRepo;
    @Transactional
	@Override
	public MessageResponse save(Organisme organisme) {
    	 boolean existe = organismeRepo.existsByLibelle(organisme.getLibelle());
         if (existe){
             return new MessageResponse("Echec ! Cet organisme existe déja !");
         }
         organismeRepo.save(organisme);
         return new MessageResponse("Succès Opération réalisée avec succès.");
	}
    @Transactional
	@Override
	public MessageResponse update(Organisme organisme) {
    	 boolean existe = organismeRepo.existsById(organisme.getId());
         if (!existe){
             boolean existe1 = organismeRepo.existsByLibelle(organisme.getLibelle());
             return new MessageResponse("Echec ! Cet organisme existe déja !");

         }
         organismeRepo.save(organisme);
         return new MessageResponse("Succès Opération réalisée avec succès.");
	}
    @Transactional
	@Override
	public MessageResponse delete(Long id) {
    	  Organisme organisme = findById(id);
          if (organisme==null){
              return new MessageResponse("Echec Cet enregistrement n'existe pas !");
          }
          organismeRepo.delete(organisme);
          return new MessageResponse("Succès L'enregistrement à été supprimé avec succès.");
	}
    @Transactional
	@Override
	public List<Organisme> findAll() {
		// TODO Auto-generated method stub
    	return organismeRepo.findAll();
	}
    @Transactional
	@Override
	public Organisme findById(Long id) {
		// TODO Auto-generated method stub
    	Organisme organisme = organismeRepo.findById(id).orElse(null);
        return organisme;
	}
	
}