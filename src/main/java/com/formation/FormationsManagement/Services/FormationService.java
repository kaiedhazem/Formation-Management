package com.formation.FormationsManagement.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.FormationsManagement.Entities.Formation;
import com.formation.FormationsManagement.Repository.FormationRepository;
import com.formation.FormationsManagement.payload.Response.MessageResponse;

@Service
public class FormationService implements IFormationService{
@Autowired
FormationRepository formationrepo;

@Override
public MessageResponse save(Formation formation) {
	  boolean existe = formationrepo.existsByTitre(formation.getTitre());
      if (existe){
          return new MessageResponse("Echec ! Cet formation exist déja !");
      }
      formationrepo.save(formation);
      return new MessageResponse("Succès Opération réalisée avec succès.");// TODO Auto-generated method stub
}

@Override
public MessageResponse update(Formation formation) {
	   boolean existe = formationrepo.existsById(formation.getId());
       if (!existe){
           boolean existe1 = formationrepo.existsByTitre(formation.getTitre());
           return new MessageResponse("Echec ! Cette formation existe déja !");

       }
       formationrepo.save(formation);
       return new MessageResponse("Succès Opération réalisée avec succès.");
}

@Override
public MessageResponse delete(Long id) {
	Formation formation = findById(id);
    if (formation==null){
        return new MessageResponse("Echec Cet enregistrement n'existe pas !");
    }
    formationrepo.delete(formation);
    return new MessageResponse("Succès L'enregistrement à été supprimé avec succès.");
}

@Override
public List<Formation> findAll() {
	// TODO Auto-generated method stub
	 return formationrepo.findAll();
}

@Override
public Formation findById(Long id) {
	// TODO Auto-generated method stub
	Formation formation = formationrepo.findById(id).orElse(null);
    return formation;
}
}
