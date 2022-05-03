package com.formation.FormationsManagement.Controller.Admin;

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

import com.formation.FormationsManagement.Entities.Profil;
import com.formation.FormationsManagement.Services.ProfileService;
import com.formation.FormationsManagement.payload.Response.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/profil")
public class ProfilController {
	@Autowired
	private ProfileService profilService;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<Profil> findAll() {
        return profilService.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public MessageResponse save(@RequestBody Profil profil) {
        return profilService.save(profil);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public MessageResponse update(@RequestBody Profil profil) {
        return profilService.update(profil);
    }

   @GetMapping("/find/{code}")
   @PreAuthorize("hasRole('ADMIN')")
    public Profil findById(@PathVariable("code") Long id) {
        return profilService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public MessageResponse delete(@PathVariable Long id) {
        return profilService.delete(id);
    }
}
