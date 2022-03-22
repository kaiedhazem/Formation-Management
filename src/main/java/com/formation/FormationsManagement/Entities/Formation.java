package com.formation.FormationsManagement.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Formation implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Size(max = 50)
	private String titre;
	@NotBlank
	@Size(max = 50)
	private String type;
	
	private int annee;
	
	private int nb_session;
	
	private int duree;
	private double budget;
	
	//({"hibernateLazyInitializer", "handler"})
    @ManyToOne()
    @JoinColumn(name = "domaineId", nullable = false)
	private Domaine domaine;
	 @OneToMany(mappedBy="formation")
	private List<Session> session ;
	public Formation() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	public Formation(@NotBlank @Size(max = 50) String titre, @NotBlank @Size(max = 50) String type, int annee,
			int nb_session, int duree, double budget, Domaine domaine) {
		super();
		this.titre = titre;
		this.type = type;
		this.annee = annee;
		this.nb_session = nb_session;
		this.duree = duree;
		this.budget = budget;
		this.domaine = domaine;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public int getNb_session() {
		return nb_session;
	}
	public void setNb_session(int nb_session) {
		this.nb_session = nb_session;
	}
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public Domaine getDomaine() {
		return domaine;
	}
	public void setDomaine(Domaine domaine) {
		this.domaine = domaine;
	}

	/*
	 * public List<Session> getSession() { return session; } public void
	 * setSession(List<Session> session) { this.session = session; }
	 */
	@Override
	public String toString() {
		return "Formation [id=" + id + ", titre=" + titre + ", type=" + type + ", annee=" + annee + ", nb_session="
				+ nb_session + ", duree=" + duree + ", budget=" + budget + ", domaine=" + domaine + "]";
	}
	  
	
}
