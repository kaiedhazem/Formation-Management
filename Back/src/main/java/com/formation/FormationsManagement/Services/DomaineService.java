package com.formation.FormationsManagement.Services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.FormationsManagement.Entities.Domaine;
import com.formation.FormationsManagement.Repository.DomaineRepository;
import com.formation.FormationsManagement.payload.Response.MessageResponse;
@Service
public class DomaineService implements IDomaineService{
	@Autowired
	DomaineRepository domaineRepository;


    @Transactional
    @Override
    public MessageResponse save(Domaine domaine) {
        boolean existe = domaineRepository.existsByLibelle(domaine.getLibelle());
        if (existe){
            return new MessageResponse("Echec ! Cette domaine existe déja !");
        }
        domaineRepository.save(domaine);
        return new MessageResponse("Succès Opération réalisée avec succès.");
    }


    @Transactional
    @Override
    public MessageResponse update(Domaine domaine) {
        boolean existe = domaineRepository.existsById(domaine.getId());
        if (!existe){
            boolean existe1 = domaineRepository.existsByLibelle(domaine.getLibelle());
            return new MessageResponse("Echec ! Cette domaine existe déja !");

        }
        domaineRepository.save(domaine);
        return new MessageResponse("Succès Opération réalisée avec succès.");
    }

    @Transactional
    @Override
    public MessageResponse delete(Long id) {
        Domaine domaine = findById(id);
        if (domaine==null){
            return new MessageResponse("Echec Cet enregistrement n'existe pas !");
        }
        domaineRepository.delete(domaine);
        return new MessageResponse("Succès L'enregistrement à été supprimé avec succès.");
    }

    @Override
    public List<Domaine> findAll() {

        return domaineRepository.findAll();
    }

    @Override
    public Domaine findById(Long id) {
        Domaine domaine = domaineRepository.findById(id).orElse(null);
        return domaine;
    }

}
