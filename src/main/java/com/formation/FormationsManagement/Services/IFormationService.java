package com.formation.FormationsManagement.Services;

import java.util.List;

import com.formation.FormationsManagement.Entities.Formation;
import com.formation.FormationsManagement.payload.Response.MessageResponse;

public interface IFormationService {
	public MessageResponse save(Formation formation);
    public MessageResponse update(Formation formation);
    public MessageResponse delete(Long id);
    public List<Formation> findAll();
    public Formation  findById(Long id);
}
