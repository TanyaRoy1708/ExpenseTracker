package com.dao;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.entity.User;

public class UserDao {

	private SessionFactory factory=null;
	private Session session = null;
	private Transaction  txn = null;

	public UserDao(SessionFactory factory) {
		super();
		this.factory = factory;
	}
	
	public boolean saveuser(User user) {
		
		boolean f = false;
		
		try {
			
			session = factory.openSession();
			txn=session.beginTransaction();
			
			session.save(user);
			txn.commit();
			f= true;
			
		}
		catch(Exception e) {
			if(txn!=null) {
				f=false;
				e.printStackTrace();
			}
		}
		return f;
	}
	
	public User login(String email,String password) {
		User u = null;
		
		session = factory.openSession();
		Query q= session.createQuery("from User where email=:em and password=:ps");
		
		q.setParameter("em", email);
		q.setParameter("ps", password);
		
		u = (User)q.uniqueResult();
		
				
		return u;
	}
}
