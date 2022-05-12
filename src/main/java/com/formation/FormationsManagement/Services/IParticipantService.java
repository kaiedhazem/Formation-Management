package com.formation.FormationsManagement.Services;

import java.util.List;


import com.formation.FormationsManagement.Entities.Participant;
import com.formation.FormationsManagement.payload.Response.MessageResponse;

public interface IParticipantService {
	public MessageResponse save(Participant participant);
    public MessageResponse update(Participant participant);
    public MessageResponse delete(Long id);
    public List<Participant> findAll();
    public Participant  findById(Long id);
    public List<Participant> participantType(String type);

}
