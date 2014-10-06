package se.berglund.datastorage;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import se.berglund.models.Whiteboard;

public class WhiteboardManager {

	public WhiteboardManager() {
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public ArrayList<Whiteboard> getAllWhiteboards() {

		SessionFactory sessFactory = HibernateSessionFactory
				.getSessionFactory();
		Session session = sessFactory.openSession();
		Transaction tr = session.beginTransaction();

		String strSql = "from Whiteboard w";
		Query query = session.createQuery(strSql);
		@SuppressWarnings("rawtypes")
		List whiteboards = query.list();

		tr.commit();
		System.out.println("GET all Whiteboards");

		return (ArrayList<Whiteboard>) whiteboards;
	}

	public void addWhiteboard(Whiteboard whiteboard) {
		SessionFactory sessFactory = HibernateSessionFactory
				.getSessionFactory();
		Session session = sessFactory.openSession();
		Transaction tr = session.beginTransaction();

		session.save(whiteboard);
		tr.commit();

		System.out.println("Create new Whiteboard " + whiteboard.getName());

	}

	public void deleteWhiteboard(Whiteboard whiteboard) {
		SessionFactory sessFactory = HibernateSessionFactory
				.getSessionFactory();
		Session session = sessFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.delete(whiteboard);
		tr.commit();

		System.out.println("Deleted Whiteboard " + whiteboard.getName());
	}

}
