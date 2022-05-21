package com.formation.FormationsManagement.Controller.User;

import java.util.HashMap;
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
import com.formation.FormationsManagement.Services.ParticipantService;
import com.formation.FormationsManagement.payload.Response.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/participant")
public class ParticipantController {
	@Autowired
	private ParticipantService participantService;
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<Participant> findAll() {
        return participantService.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public MessageResponse save(@RequestBody Participant participant) {
        return participantService.save(participant);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public MessageResponse update(@RequestBody Participant participant) {
        return participantService.update(participant);
    }

   @GetMapping("/find/{code}")
   @PreAuthorize("hasRole('USER')")
    public Participant findById(@PathVariable("code") Long id) {
        return participantService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER')")
    public MessageResponse delete(@PathVariable Long id) {
        return participantService.delete(id);
    }
    @GetMapping("/typecount")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
     public HashMap<String,Integer> countParticipantType() {
    	List<Participant> internes = participantService.participantType("international");
    	List<Participant> externes = participantService.participantType("national");
    	HashMap<String, Integer> result = new HashMap<String, Integer>();
    	result.put("international", internes.size());
    	result.put("national", externes.size());
         return result;
     }
}
