package com.formation.FormationsManagement.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.FormationsManagement.Entities.Formateur;
import com.formation.FormationsManagement.Entities.Participant;
import com.formation.FormationsManagement.Repository.ParticipantRepository;
import com.formation.FormationsManagement.payload.Response.MessageResponse;

@Service
public class ParticipantService implements IParticipantService {
	@Autowired
	ParticipantRepository participantrepo;
	@Override
	public MessageResponse save(Participant participant) {
		  boolean existe = participantrepo.existsByNom(participant.getNom());
	      if (existe){
	          return new MessageResponse("Echec ! Cet participant exist déja !");
	      }
	      participantrepo.save(participant);
	      return new MessageResponse("Succès Opération réalisée avec succès.");
	}

	@Override
	public MessageResponse update(Participant participant) {
		boolean existe = participantrepo.existsById(participant.getId());
	       if (!existe){
	
	           return new MessageResponse("Echec ! Cet formateur n'existe pas déja !");

	       }
	       participantrepo.save(participant);
	       return new MessageResponse("Succès Opération réalisée avec succès.");
	}

	@Override
	public MessageResponse delete(Long id) {
		Participant participant= findById(id);
	    if (participant==null){
	        return new MessageResponse("Echec Cet enregistrement n'existe pas !");
	    }
	    participantrepo.delete(participant);
	    return new MessageResponse("Succès L'enregistrement à été supprimé avec succès.");
	}

	@Override
	public List<Participant> findAll() {
		 return participantrepo.findAll();
	}

	@Override
	public Participant findById(Long id) {
		Participant participant = participantrepo.findById(id).orElse(null);
	    return participant;
	}

}
