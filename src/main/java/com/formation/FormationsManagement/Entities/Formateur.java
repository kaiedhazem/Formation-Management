package com.formation.FormationsManagement.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(uniqueConstraints = { 
		@UniqueConstraint(columnNames = "nom"),
		@UniqueConstraint(columnNames = "prenom"), 
		@UniqueConstraint(columnNames = "email") 
	})
public class Formateur implements Serializable {
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
	private String type;  // interne ou externe
	@Size(max = 50)
	@Email
	private String email;
	@Size(max = 50)
	private int tel;
	@ManyToOne
	@MapsId("organismeId")
	private Organisme organisme;
	  @OneToMany(mappedBy="formateur")
			private List<Session> session ;
	public Formateur() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Formateur(@NotBlank @Size(max = 50) String nom, @Size(max = 50) String prenom, @Size(max = 50) String type,
			@Size(max = 50) @Email String email, @Size(max = 50) int tel) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.type = type;
		this.email = email;
		this.tel = tel;
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
	public List<Session> getSession() {
		return session;
	}
	public void setSession(List<Session> session) {
		this.session = session;
	}
	@Override
	public String toString() {
		return "Formateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", type=" + type + ", email=" + email
				+ ", tel=" + tel + ", organisme=" + organisme + ", session=" + session + "]";
	}
	  
	  
}
