package com.formation.FormationsManagement.Controller.Admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.formation.FormationsManagement.Entities.Domaine;
import com.formation.FormationsManagement.Services.DomaineService;
import com.formation.FormationsManagement.payload.Response.MessageResponse;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/domaine")
public class DomaineController {
	@Autowired
	private DomaineService domaineService;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
    public List<Domaine> findAll() {
        return domaineService.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public MessageResponse save(@RequestBody Domaine domaine) {
        return domaineService.save(domaine);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public MessageResponse update(@RequestBody Domaine domaine) {
        return domaineService.update(domaine);
    }

   @GetMapping("/find/{code}")
   @PreAuthorize("hasRole('ADMIN')")
    public Domaine findById(@PathVariable("code") Long id) {
        return domaineService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public MessageResponse delete(@PathVariable Long id) {
        return domaineService.delete(id);
    }
}

