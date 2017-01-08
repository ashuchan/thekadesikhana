package com.dev.backend.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.dev.backend.dao.AbstractDao;
import com.dev.backend.dao.OrderDao;
import com.dev.backend.dto.Order;
import com.dev.backend.dto.User;

public class OrderDaoImpl extends AbstractDao implements OrderDao {

	@Override
	public Order getOrder(String orderId) {
		System.out.println("Fetching order for id: "+ orderId);
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("orderId", orderId));
		return (Order) criteria.uniqueResult();
	}

	@Override
	public List<Order> getOrdersByCustomer(String userId) {
		System.out.println("Fetching order for id: "+ userId);
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("user", userId));
		return (List<Order>) criteria.uniqueResult();
	}
	
	@Override
	public boolean createOrder(Order order) {
		try{
			getSession().saveOrUpdate(order);
			getSession().flush();
			return true;
		} catch( Exception e) {
			//TODO: Log
		}
		return false;
	}
	
	@Override
	public String updateOrderStatus(String orderId, String status) {
		try{
			// TODO: Need to fix this. How to put update query
			getSession().update("order_status", status);
		} catch( Exception e) {
			//TODO: Log
		}
		return status;
	}
}
