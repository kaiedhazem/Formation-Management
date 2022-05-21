package com.formation.FormationsManagement.Services;

import java.util.List;

import com.formation.FormationsManagement.Entities.Session;
import com.formation.FormationsManagement.payload.Response.MessageResponse;

public interface ISessionService {
	public MessageResponse save(Session session);
    public MessageResponse update(Session session);
    public MessageResponse delete(Long id);
    public List<Session> findAll();
    public Session  findById(Long id);
}
