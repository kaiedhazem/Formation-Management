package com.formation.FormationsManagement.Controller.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.formation.FormationsManagement.Entities.Organisme;
import com.formation.FormationsManagement.Services.OrganismeService;
import com.formation.FormationsManagement.payload.Response.MessageResponse;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/organisme")
public class OrganismeController {
	@Autowired
	private OrganismeService organismeService;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
    public List<Organisme> findAll() {
        return organismeService.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public MessageResponse save(@RequestBody Organisme organisme) {
        return organismeService.save(organisme);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public MessageResponse update(@RequestBody Organisme organisme) {
        return organismeService.update(organisme);
    }

   @GetMapping("/find/{code}")
   @PreAuthorize("hasRole('ADMIN')")
    public Organisme findById(@PathVariable("code") Long id) {
        return organismeService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public MessageResponse delete(@PathVariable Long id) {
        return organismeService.delete(id);
    }
}
