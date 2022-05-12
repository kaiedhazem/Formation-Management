package com.formation.FormationsManagement.Services;

import java.util.List;

import com.formation.FormationsManagement.Entities.Pays;
import com.formation.FormationsManagement.payload.Response.MessageResponse;

public interface IPaysService {
	public MessageResponse save(Pays pays);
    public MessageResponse update(Pays pays);
    public MessageResponse delete(Long id);
    public List<Pays> findAll();
    public Pays  findById(Long id);
}
