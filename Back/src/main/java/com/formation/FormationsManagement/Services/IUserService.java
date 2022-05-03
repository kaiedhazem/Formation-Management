package com.formation.FormationsManagement.Services;

import java.util.List;

import com.formation.FormationsManagement.Entities.*;
import com.formation.FormationsManagement.payload.Response.MessageResponse;

public interface IUserService {

	public MessageResponse save(User user);
    public MessageResponse update(User user);
    public MessageResponse delete(Long id);
    public List<User> findAll();
    public User  findByCode(Long code);
}
