package com.dev.backend.dto;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="menu")
public class Menu {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	@JsonIgnore
	private int id;
	
	@Column(name="date")
	@JsonProperty
	private Date date;
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn(name="cuisine")
	@JsonProperty
	private Cuisine cuisine;
	
	@Column(name="items")
	@JsonProperty
	private String items;
	
	@Column(name="url_mobile")
	@JsonProperty
	private String urlMobile;
	
	@Column(name="url_tablet")
	@JsonProperty
	private String urlTablet;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Cuisine getCuisine() {
		return cuisine;
	}

	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getUrlMobile() {
		return urlMobile;
	}

	public void setUrlMobile(String urlMobile) {
		this.urlMobile = urlMobile;
	}

	public String getUrlTablet() {
		return urlTablet;
	}

	public void setUrlTablet(String urlTablet) {
		this.urlTablet = urlTablet;
	}
	
}
