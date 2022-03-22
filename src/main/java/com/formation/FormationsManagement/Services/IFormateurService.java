package com.formation.FormationsManagement.Services;

import java.util.List;

import com.formation.FormationsManagement.Entities.Formateur;
import com.formation.FormationsManagement.payload.Response.MessageResponse;

public interface IFormateurService {
	public MessageResponse save(Formateur formateur);
    public MessageResponse update(Formateur formateur);
    public MessageResponse delete(Long id);
    public List<Formateur> findAll();
    public Formateur  findById(Long id);
}
