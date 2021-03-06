package com.formation.FormationsManagement.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Entity
public class Organisme implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Size(max = 50)
	private String libelle;
	  @OneToMany(mappedBy="organisme", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
		private List<Participant> participants ;
	  @OneToMany(mappedBy="organisme", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
		private List<Formateur> formateurs ;
	  @OneToMany(mappedBy="organisme", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
		private List<Session> session ;
	public Organisme() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Organisme(@NotBlank @Size(max = 50) String libelle) {
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
	/*
	 * public List<Participant> getParticipants() { return participants; } public
	 * void setParticipants(List<Participant> participants) { this.participants =
	 * participants; }
	 */
	/*
	 * public List<Formateur> getFormateurs() { return formateurs; } public void
	 * setFormateurs(List<Formateur> formateurs) { this.formateurs = formateurs; }
	 */
	/*
	 * public List<Session> getSession() { return session; } public void
	 * setSession(List<Session> session) { this.session = session; }
	 */
	@Override
	public String toString() {
		return "Organisme [id=" + id + ", libelle=" + libelle +  "]";
	}
	
	
	  
}
