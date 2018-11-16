package com.ParallelDemo.dao;

import java.util.Date; 
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ParallelDemo.bean.CustomerDemo;
import com.ParallelDemo.bean.Passbook;
import com.ParallelDemo.exception.InvalidInputException;

public class ImpDao implements IDao {
	CustomerDemo cd = new CustomerDemo();
	
	private EntityManager entityManager;
	
	public ImpDao(){
		//entityManager = JPAUtilDao.getEntityManager();
	}

	public boolean customerDetails(CustomerDemo cd) {
		entityManager = JPAUtilDao.getEntityManager();
		
		if(entityManager.find(CustomerDemo.class,cd.getMobno()) != null){
			throw new InvalidInputException(" : Number Already Exists!!!");
		}else{
			
		entityManager.getTransaction().begin();
		entityManager.persist(cd);
		String createTransaction=new Date()+" - Account created!";
		Passbook trans = new Passbook(createTransaction,cd);
		entityManager.persist(trans);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
		}
	}

	@Override
	public CustomerDemo deposit(String mobno) {
		entityManager = JPAUtilDao.getEntityManager();
		if(entityManager.find(CustomerDemo.class,mobno)!=null){
			CustomerDemo customer = entityManager.find(CustomerDemo.class, mobno);
			entityManager.close();
			return customer;
		}else{
			throw new InvalidInputException(" : Deposit Not Done Mobile Number Doesn't Exist!");
		}
	
	}

	@Override
	public CustomerDemo withdraw(String mobno) {
		entityManager = JPAUtilDao.getEntityManager();
		if(entityManager.find(CustomerDemo.class,mobno)!=null){
			CustomerDemo customer = entityManager.find(CustomerDemo.class, mobno);
			entityManager.close();
			return customer;
		}else{
			throw new InvalidInputException(" : WithDraw Not Done Mobile Number Doesn't Exist!");
		}
		
	}
	
	@Override
	public CustomerDemo showBalance(String mobno) {
		entityManager = JPAUtilDao.getEntityManager();
		if(entityManager.find(CustomerDemo.class,mobno)!=null){
			CustomerDemo customer = entityManager.find(CustomerDemo.class, mobno);
			entityManager.close();
			return customer;
		}else{
			throw new InvalidInputException(" : Mobile Number Doesn't Exist!");
		}
	}

	@Override
	public CustomerDemo displayDetails(String mobno) {
		entityManager = JPAUtilDao.getEntityManager();
		if(entityManager.find(CustomerDemo.class,mobno)!=null){
			CustomerDemo customer = entityManager.find(CustomerDemo.class, mobno);
			entityManager.close();
			return customer;
		}else{
			throw new InvalidInputException(" : Mobile Number Doesn't Exist!");
		}
	}
	
	@Override
	public CustomerDemo fundTransfer(String mobno) {
		entityManager = JPAUtilDao.getEntityManager();
		CustomerDemo  customer=null;
		if(entityManager.find(CustomerDemo.class,mobno)!=null){
			 customer=entityManager.find(CustomerDemo.class,mobno);
			entityManager.close();
		}
		 return customer;
	}
	
	@Override
	public List<Passbook> printTransactions(String mobno,int pin){
		entityManager = JPAUtilDao.getEntityManager();
		List<Passbook> trans;
		if(entityManager.find(CustomerDemo.class,mobno) != null) {
			CustomerDemo customer=entityManager.find(CustomerDemo.class,mobno);
			if(customer.getPin()==pin){
			String jql="SELECT * FROM transactions where mobno=?";
			 Query query =
				      entityManager.createNativeQuery(jql,Passbook.class);
			 query.setParameter(1, mobno);
			 trans = query.getResultList();
			 }else{
				 throw new InvalidInputException(" : PIN is Incorrect!!!");
			 }
			 entityManager.close();
			 return trans;
		}
		else {
			throw new InvalidInputException(" : Invalid MobileNo.");
		}
	}

	
	@Override
	public void beginTransaction() {
		entityManager = JPAUtilDao.getEntityManager();
		entityManager.getTransaction().begin();
		//entityManager.close();
	}

	@Override
	public void commitTransaction() {
		entityManager = JPAUtilDao.getEntityManager();
		entityManager.getTransaction().commit();
		//entityManager.close();
	}
	
	@Override
	public void update(CustomerDemo customer){
		entityManager = JPAUtilDao.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(customer);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public void transactions(String log,CustomerDemo cd) {
		entityManager = JPAUtilDao.getEntityManager();
		entityManager.getTransaction().begin();
		Passbook transaction = new Passbook(log,cd);
		transaction.setCustomer(cd);
		entityManager.persist(transaction);
		entityManager.getTransaction().commit();
		//entityManager.flush();
		entityManager.close();
	}
}



