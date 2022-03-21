package com.formation.FormationsManagement.Entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

	@Entity
	@Table(uniqueConstraints = { 
				@UniqueConstraint(columnNames = "email") 
			})
	public class User implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long code;
		@NotBlank
		@Size(max = 50)
		@Email
		private String email;
		@NotBlank
		@Size(max = 120)
		private String password;
		
		
		@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(	name = "user_roles", 
					joinColumns = @JoinColumn(name = "user_id"), 
					inverseJoinColumns = @JoinColumn(name = "role_id"))
		private Set<Role> roles = new HashSet<>();
		
		
		public User() {
		}
        

		public User(@NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password,
				Set<Role> roles) {
			super();
			this.email = email;
			this.password = password;
			this.roles = roles;
		}


		public Long getCode() {
			return code;
		}


		public void setCode(Long code) {
			this.code = code;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public Set<Role> getRoles() {
			return roles;
		}


		public void setRoles(Set<Role> roles) {
			this.roles = roles;
		}
		
		
		
	
}

