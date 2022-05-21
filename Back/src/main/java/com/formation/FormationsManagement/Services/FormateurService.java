package com.formation.FormationsManagement.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.FormationsManagement.Entities.Formateur;
import com.formation.FormationsManagement.Repository.FormateurRepository;
import com.formation.FormationsManagement.payload.Response.MessageResponse;

@Service
public class FormateurService implements IFormateurService{
	@Autowired
	FormateurRepository formateurrepo;
	@Override
	public MessageResponse save(Formateur formateur) {
		  boolean existe = formateurrepo.existsByNom(formateur.getNom());
	      if (existe){
	          return new MessageResponse("Echec ! Cet formateur exist déja !");
	      }
	      formateurrepo.save(formateur);
	      return new MessageResponse("Succès Opération réalisée avec succès.");
	}

	@Override
	public MessageResponse update(Formateur formateur) {
		boolean existe = formateurrepo.existsById(formateur.getId());
	       if (!existe){
	
	           return new MessageResponse("Echec ! Cet formateur n'existe pas déja !");

	       }
	       formateurrepo.save(formateur);
	       return new MessageResponse("Succès Opération réalisée avec succès.");
	}

	@Override
	public MessageResponse delete(Long id) {
		Formateur formateur = findById(id);
	    if (formateur==null){
	        return new MessageResponse("Echec Cet enregistrement n'existe pas !");
	    }
	    formateurrepo.delete(formateur);
	    return new MessageResponse("Succès L'enregistrement à été supprimé avec succès.");
	}

	@Override
	public List<Formateur> findAll() {
		 return formateurrepo.findAll();
	}

	@Override
	public Formateur findById(Long id) {
		Formateur formateur = formateurrepo.findById(id).orElse(null);
	    return formateur;
	}
	@Override
	public List<Formateur> typeFormateur(String type) {
		List<Formateur> formateur = formateurrepo.findByType(type);
		return formateur;
	}
}
