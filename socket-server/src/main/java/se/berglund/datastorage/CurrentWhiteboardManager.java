package se.berglund.datastorage;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import se.berglund.models.Category;
import se.berglund.models.Postit;

public class CurrentWhiteboardManager {

	// THREAD SECURITY for maps

	private int currentWhiteboardId;

	public CurrentWhiteboardManager() {
	}

	public CurrentWhiteboardManager(int id) {
		this.setCurrentWhiteboardId(id);
	}

	public int getCurrentWhiteboardId() {
		return currentWhiteboardId;
	}

	public void setCurrentWhiteboardId(int currentWhiteboardId) {
		this.currentWhiteboardId = currentWhiteboardId;
	}

	public ArrayList<Category> getAllCategoriesForCurrentWhiteboard() {

		SessionFactory sessFactory = HibernateSessionFactory
				.getSessionFactory();
		Session session = sessFactory.openSession();
		Transaction tr = session.beginTransaction();

		Query query = session
				.createQuery("from Category c WHERE whiteboardId = :id ");
		query.setParameter("id", currentWhiteboardId);

		@SuppressWarnings("unchecked")
		ArrayList<Category> categories = (ArrayList<Category>) query.list();
		tr.commit();

		return categories;

	}

	public ArrayList<Postit> getAllPostitsForCurrentWhiteboard() {
		
		SessionFactory sessFactory = HibernateSessionFactory
				.getSessionFactory();
		Session session = sessFactory.openSession();
		Transaction tr = session.beginTransaction();

		Query query = session
				.createQuery("from Postit p WHERE whiteboardId = :id ");
		query.setParameter("id", currentWhiteboardId);

		@SuppressWarnings("unchecked")
		ArrayList<Postit> postits = (ArrayList<Postit>) query.list();
		tr.commit();

		return postits;
	}

}
