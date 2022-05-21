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


import com.formation.FormationsManagement.Entities.Formation;
import com.formation.FormationsManagement.Services.FormationService;
import com.formation.FormationsManagement.payload.Response.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/formation")
public class FormationController {
	@Autowired
	private FormationService formationService;
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<Formation> findAll() {
        return formationService.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public MessageResponse save(@RequestBody Formation formation) {
        return formationService.save(formation);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public MessageResponse update(@RequestBody Formation formation) {
        return formationService.update(formation);
    }

   @GetMapping("/find/{code}")
   @PreAuthorize("hasRole('USER')")
    public Formation findById(@PathVariable("code") Long id) {
        return formationService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER')")
    public MessageResponse delete(@PathVariable Long id) {
        return formationService.delete(id);
    }
}
