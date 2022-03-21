package com.formation.FormationsManagement.Services;

import java.util.List;

import com.formation.FormationsManagement.Entities.Profil;
import com.formation.FormationsManagement.payload.Response.MessageResponse;

public interface IProfileService {
	public MessageResponse save(Profil profil);
    public MessageResponse update(Profil profil);
    public MessageResponse delete(Long id);
    public List<Profil> findAll();
    public Profil  findById(Long id);
}
