package com.formation.FormationsManagement.Controller.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.FormationsManagement.Entities.Participant;
import com.formation.FormationsManagement.Entities.Session;
import com.formation.FormationsManagement.Services.ParticipantService;
import com.formation.FormationsManagement.Services.SessionService;
import com.formation.FormationsManagement.payload.Response.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/session")
public class SessionController {
	@Autowired
	private SessionService sessionservice;
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<Session> findAll() {
        return sessionservice.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public MessageResponse save(@RequestBody Session session) {
        return sessionservice.save(session);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public MessageResponse update(@RequestBody Session session) {
        return sessionservice.update(session);
    }

   @GetMapping("/find/{code}")
   @PreAuthorize("hasRole('USER')")
    public Session findById(@PathVariable("code") Long id) {
        return sessionservice.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER')")
    public MessageResponse delete(@PathVariable Long id) {
        return sessionservice.delete(id);
    }
}
