package se.berglund.datastorage;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import se.berglund.models.Whiteboard;

public class WhiteboardManager {

	private static WhiteboardManager firstInstance = null;

	private WhiteboardManager() {
	}

	public static WhiteboardManager getInstance() {
		synchronized (WhiteboardManager.class) {

			if (firstInstance == null) {
				firstInstance = new WhiteboardManager();
			}
			return firstInstance;
		}
	}

	public ArrayList<Whiteboard> getAllWhiteboards() {

		SessionFactory sessFactory = HibernateSessionFactory
				.getSessionFactory();
		Session session = sessFactory.openSession();
		Transaction tr = session.beginTransaction();

		String selectSQL = "from Whiteboard w";
		Query query = session.createQuery(selectSQL);
		@SuppressWarnings("unchecked")
		ArrayList<Whiteboard> whiteboards = (ArrayList<Whiteboard>) query
				.list();

		tr.commit();
		return (ArrayList<Whiteboard>) whiteboards;
	}

	public void addWhiteboard(Whiteboard whiteboard) {
		SessionFactory sessFactory = HibernateSessionFactory
				.getSessionFactory();
		Session session = sessFactory.openSession();
		Transaction tr = session.beginTransaction();

		session.save(whiteboard);
		tr.commit();
	}

	public void deleteWhiteboard(Whiteboard whiteboard) {
		SessionFactory sessFactory = HibernateSessionFactory
				.getSessionFactory();
		Session session = sessFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.delete(whiteboard);
		tr.commit();
	}

}
