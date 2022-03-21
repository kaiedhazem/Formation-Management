package com.formation.FormationsManagement.Services;

import java.util.List;

import com.formation.FormationsManagement.Entities.Domaine;
import com.formation.FormationsManagement.payload.Response.MessageResponse;

public interface IDomaineService {
	public MessageResponse save(Domaine domaine);
    public MessageResponse update(Domaine domaine);
    public MessageResponse delete(Long id);
    public List<Domaine> findAll();
    public Domaine findById(Long id);
}
