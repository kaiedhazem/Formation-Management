package com.formation.FormationsManagement.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.FormationsManagement.Entities.Formateur;
import com.formation.FormationsManagement.Entities.Session;
import com.formation.FormationsManagement.Repository.FormateurRepository;
import com.formation.FormationsManagement.Repository.SessionRepository;
import com.formation.FormationsManagement.payload.Response.MessageResponse;

@Service
public class SessionService implements ISessionService {
	@Autowired
	SessionRepository sessionrepo;
	@Override
	public MessageResponse save(Session session) {
		
		System.out.println(session.getParticipants());
		sessionrepo.save(session);
	      return new MessageResponse("Succès Opération réalisée avec succès.");
	}

	@Override
	public MessageResponse update(Session session) {
		boolean existe = sessionrepo.existsById(session.getId());
	       if (!existe){
	
	           return new MessageResponse("Echec ! Cet formateur n'existe pas déja !");

	       }
	       sessionrepo.save(session);
	       return new MessageResponse("Succès Opération réalisée avec succès.");
	}

	@Override
	public MessageResponse delete(Long id) {
		Session session = findById(id);
	    if (session==null){
	        return new MessageResponse("Echec Cet enregistrement n'existe pas !");
	    }
	    sessionrepo.delete(session);
	    return new MessageResponse("Succès L'enregistrement à été supprimé avec succès.");
	}

	@Override
	public List<Session> findAll() {
		 return sessionrepo.findAll();
	}

	@Override
	public Session findById(Long id) {
		Session session = findById(id);
	    return session;
	}

}
