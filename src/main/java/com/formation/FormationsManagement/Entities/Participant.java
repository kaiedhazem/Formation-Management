package com.formation.FormationsManagement.Entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.*;

@Entity
@Table(uniqueConstraints = { 
		@UniqueConstraint(columnNames = "nom"),
		@UniqueConstraint(columnNames = "prenom"), 
		@UniqueConstraint(columnNames = "email") 
	})
public class Participant implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Size(max = 50)
	private String nom;
	@Size(max = 50)
	private String prenom;
	@Size(max = 50)
	private String type;  // type de participant national ou international
	@Size(max = 50)
	@Email
	private String email;
	@Size(max = 50)
	private int tel;
	@ManyToOne
	@MapsId("organismeId")
	private Organisme organisme;
	@ManyToOne
	@MapsId("paysId")
	private Pays pays;
	@ManyToOne
	@MapsId("profilId")
	private Profil profil;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "participant_session", 
				joinColumns = @JoinColumn(name = "participant_id"), 
				inverseJoinColumns = @JoinColumn(name = "session_id"))
	private Set<Session> sessions;
	public Participant() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Participant(@NotBlank @Size(max = 50) String nom, @Size(max = 50) String prenom, @Size(max = 50) String type,
			@Size(max = 50) @Email String email, @Size(max = 50) int tel) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.type = type;
		this.email = email;
		this.tel = tel;
	}
	

	public Participant(@NotBlank @Size(max = 50) String nom, @Size(max = 50) String prenom, @Size(max = 50) String type,
			@Size(max = 50) @Email String email, @Size(max = 50) int tel, Organisme organisme) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.type = type;
		this.email = email;
		this.tel = tel;
		this.organisme = organisme;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTel() {
		return tel;
	}
	public void setTel(int tel) {
		this.tel = tel;
	}
	public Organisme getOrganisme() {
		return organisme;
	}
	public void setOrganisme(Organisme organisme) {
		this.organisme = organisme;
	}
	
	public Pays getPays() {
		return pays;
	}
	public void setPays(Pays pays) {
		this.pays = pays;
	}
	public Profil getProfil() {
		return profil;
	}
	public void setProfil(Profil profil) {
		this.profil = profil;
	}
	
	public Set<Session> getSessions() {
		return sessions;
	}

	public void setSessions(Set<Session> sessions) {
		this.sessions = sessions;
	}

	@Override
	public String toString() {
		return "Participant [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", type=" + type + ", email=" + email
				+ ", tel=" + tel + ", organisme=" + organisme + ", pays=" + pays + ", profil=" + profil + ", sessions="
				+ sessions + "]";
	}

	
	
	
}
