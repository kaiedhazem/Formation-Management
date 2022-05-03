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


import com.formation.FormationsManagement.Entities.Pays;
import com.formation.FormationsManagement.Services.PaysService;
import com.formation.FormationsManagement.payload.Response.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/pays")
public class PaysController {
	@Autowired
	private PaysService paysService;
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<Pays> findAll() {
        return paysService.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public MessageResponse save(@RequestBody Pays pays) {
        return paysService.save(pays);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public MessageResponse update(@RequestBody Pays pays) {
        return paysService.update(pays);
    }

   @GetMapping("/find/{code}")
   @PreAuthorize("hasRole('ADMIN')")
    public Pays findById(@PathVariable("code") Long id) {
        return paysService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public MessageResponse delete(@PathVariable Long id) {
        return paysService.delete(id);
    }
}
