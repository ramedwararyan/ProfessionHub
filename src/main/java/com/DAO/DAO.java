package com.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.entity.registerEntity;


public class DAO {

	 public void saveUser(registerEntity user) {

		 SessionFactory factory = new Configuration()
                 .configure("hibernate.cfg.xml")
                 .addAnnotatedClass(registerEntity.class)
                 .buildSessionFactory();

		 Session session = factory.getCurrentSession();
		 
		 try {
			
			 session.beginTransaction();
				session.save(user);
				session.getTransaction().commit();
		 } finally {
				session.close();
				factory.close();
			}
			
}
	  public boolean validate(String email, String password) {

	        org.hibernate.Transaction transaction = null;
	        registerEntity user = null;
	        
	        SessionFactory factory = new Configuration()
	                 .configure("hibernate.cfg.xml")
	                 .addAnnotatedClass(registerEntity.class)
	                 .buildSessionFactory();

			 Session session = factory.getCurrentSession();
			 
			 try {
				
				// start a transaction
		            transaction = session.beginTransaction();
		            // get an user object
		            user = (registerEntity) session.createQuery("FROM login U WHERE U.email = :email").setParameter("email", email)
		                .uniqueResult();

		            if (user != null && user.getPassword().equals(password)) {
		                return true;
		            }
		            // commit transaction
		            transaction.commit();
		        
			 }catch (Exception e) {
		            if (transaction != null) {
		                transaction.rollback();
		            }
		            e.printStackTrace();
		        }
		        return false;
		    } 
			 
			
}
