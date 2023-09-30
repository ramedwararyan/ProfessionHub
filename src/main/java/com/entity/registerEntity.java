package com.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity(name="login")
@Table(name = "login")
public class registerEntity{


	   
	        @Id		
			int id;
	        
	        @Column(name = "name")
			String name;
			
			@Column(name = "email")
			String email;
			
			@Column(name = "password")
			String password;

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
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

			public registerEntity(int id, String name, String email, String password) {
				super();
				this.id = id;
				this.name = name;
				this.email = email;
				this.password = password;
			}

			public registerEntity() {
				super();
			}

			

			public registerEntity(String name, String email, String password) {
				super();
				this.name = name;
				this.email = email;
				this.password = password;
			}

			@Override
			public String toString() {
				return "registerEntity [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
						+ "]";
			}
			
			

			

		
			
			
	}

	

