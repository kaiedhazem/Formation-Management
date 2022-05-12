package com.formation.FormationsManagement.Entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Session implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
	@NotBlank
	@Size(max = 50)
	private String lieu;
	@Size(max = 50)
	private String date_debut;
	@Size(max = 50)
	private String date_fin;
	private int nb_participant;
	@ManyToOne
	 @JoinColumn(name = "organismeId", nullable = false)
	private Organisme organisme;
	@ManyToOne
	 @JoinColumn(name = "formationId", nullable = false)
	private Formation formation;
	@ManyToOne
	 @JoinColumn(name = "formateurId", nullable = false)
	private Formateur formateur;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "participant_session", 
	joinColumns = @JoinColumn(name = "session_id"), 
	inverseJoinColumns = @JoinColumn(name = "participant_id"))
	private Set<Participant> participants = new HashSet<>();
	public Session() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Session(@NotBlank @Size(max = 50) String lieu, @Size(max = 50) String date_debut,
			@Size(max = 50) String date_fin, int nb_participant, Organisme organisme, Formation formation,
			Formateur formateur) {
		super();
		this.lieu = lieu;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.nb_participant = nb_participant;
		this.organisme = organisme;
		this.formation = formation;
		this.formateur = formateur;
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public String getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(String date_debut) {
		this.date_debut = date_debut;
	}
	public String getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(String date_fin) {
		this.date_fin = date_fin;
	}
	public int getNb_participant() {
		return nb_participant;
	}
	public void setNb_participant(int nb_participant) {
		this.nb_participant = nb_participant;
	}
	public Organisme getOrganisme() {
		return organisme;
	}
	public void setOrganisme(Organisme organisme) {
		this.organisme = organisme;
	}
	public Formation getFormation() {
		return formation;
	}
	public void setFormation(Formation formation) {
		this.formation = formation;
	}
	public Formateur getFormateur() {
		return formateur;
	}
	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}
	
	public Set<Participant> getParticipants() {
		return participants;
	}
	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}
	@Override
	public String toString() {
		return "Session [id=" + id + ", lieu=" + lieu + ", date_debut=" + date_debut + ", date_fin=" + date_fin
				+ ", nb_participant=" + nb_participant + ", organisme=" + organisme + ", formation=" + formation
				+ ", formateur=" + formateur + ", participants=" + participants + "]";
	}

	
	
}
