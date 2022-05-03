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

import com.formation.FormationsManagement.Entities.Formateur;
import com.formation.FormationsManagement.Services.FormateurService;
import com.formation.FormationsManagement.payload.Response.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/formateur")
public class FormateurController {
	@Autowired
	private FormateurService formateurService;
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<Formateur> findAll() {
        return formateurService.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public MessageResponse save(@RequestBody Formateur formateur) {
        return formateurService.save(formateur);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public MessageResponse update(@RequestBody Formateur formateur) {
        return formateurService.update(formateur);
    }

   @GetMapping("/find/{code}")
   @PreAuthorize("hasRole('USER')")
    public Formateur findById(@PathVariable("code") Long id) {
        return formateurService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER')")
    public MessageResponse delete(@PathVariable Long id) {
        return formateurService.delete(id);
    }
}
