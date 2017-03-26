package com.dev.backend.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="feedback")
public class Feedback {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	@JsonProperty
	private int id;
	
	@Column(name="name")
	@JsonProperty
	private String name;
	
	@Column(name="message")
	@JsonProperty
	private String message;
	
	@Column(name="contact")
	@JsonProperty
	private String contact;
	
	@Column(name="mail")
	@JsonProperty
	private String mail;

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
