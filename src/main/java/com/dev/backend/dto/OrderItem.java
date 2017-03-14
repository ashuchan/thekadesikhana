package com.dev.backend.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="order_item")
public class OrderItem implements Serializable {
	
	@Id
	@Column(name="item_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonIgnore
	private int itemId;

	@ManyToOne
	@JoinColumn(name="order_id")
	@JsonIgnore
	private Order order;
	
	@OneToOne
	@JoinColumn(name="menu_id")
	@JsonProperty
	private Menu menu;
	
	@Column(name="qty")
	@JsonProperty
	private int quantity;
	
	public OrderItem() {
		
	}

	public OrderItem(Menu menu, int quantity) {
		this.menu = menu;
		this.quantity = quantity;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
}
