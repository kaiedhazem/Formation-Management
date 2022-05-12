package com.formation.FormationsManagement.Services;

import java.util.List;

import com.formation.FormationsManagement.Entities.Organisme;
import com.formation.FormationsManagement.payload.Response.MessageResponse;

public interface IOrganismeService {
	public MessageResponse save(Organisme organisme);
    public MessageResponse update(Organisme organisme);
    public MessageResponse delete(Long id);
    public List<Organisme> findAll();
    public Organisme  findById(Long id);

}
