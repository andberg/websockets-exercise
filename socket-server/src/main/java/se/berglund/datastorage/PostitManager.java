package se.berglund.datastorage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import se.berglund.models.Postit;

/* PostitManager-class takes a Postit from the MessageHandler
 * and contact the MySQL database via Hibernate 
 * and can execute CUD-functionality. 
 * */

public class PostitManager {


	public PostitManager() {
	}

	
	public void createPostit(Postit postit) {
		SessionFactory sessFactory = HibernateSessionFactory
				.getSessionFactory();
		Session session = sessFactory.openSession();
		Transaction tr = session.beginTransaction();
	
		try {
			session.save(postit);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}
	}

	public void updatePostit(Postit postit) {
		SessionFactory sessFactory = HibernateSessionFactory
				.getSessionFactory();
		Session session = sessFactory.openSession();
		Transaction tr = session.beginTransaction();

		try {
			session.update(postit);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}
	}

	public void deletePostit(Postit postit) {
		SessionFactory sessFactory = HibernateSessionFactory
				.getSessionFactory();
		Session session = sessFactory.openSession();
		Transaction tr = session.beginTransaction();

		try {
			session.delete(postit);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}
	}
}
