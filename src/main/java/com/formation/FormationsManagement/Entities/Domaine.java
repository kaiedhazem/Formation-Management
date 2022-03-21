package com.formation.FormationsManagement.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Domaine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
	@Column(unique = true,nullable = false)
	private String libelle;
	@OneToMany(mappedBy="domaine")
	private List<Formation> formations ;
	public Domaine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Domaine(String libelle) {
		super();
		this.libelle = libelle;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public List<Formation> getFormations() {
		return formations;
	}
	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}
	@Override
	public String toString() {
		return "Domaine [IdDomaine=" + id + ", libelle=" + libelle + ", formations=" + formations + "]";
	}
	
	
}
