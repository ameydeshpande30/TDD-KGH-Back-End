package tdd.kgh.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;


import tdd.kgh.models.Room;
@Repository
public class RoomDAOHibernateImpl implements RoomDao {
	
	private EntityManager entityManager;
	
	// set up constructor injection
	@Autowired
	public RoomDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	@Transactional
	public List<Room> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query
		Query<Room> theQuery =
				currentSession.createQuery("from Room", Room.class);
		
		// execute query and get result list
		List<Room> rooms = theQuery.getResultList();
		
		// return the results		
		return rooms;
	}
	
	public void add(Room room) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		
		// save employee
		currentSession.saveOrUpdate(room);
		
	}

}
