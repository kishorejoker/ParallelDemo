package com.ParallelDemo.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JPAUtilDao {

	public static EntityManager getEntityManager() {
		
		return Persistence.createEntityManagerFactory("ParallelDemo").createEntityManager();
	}
	

}
