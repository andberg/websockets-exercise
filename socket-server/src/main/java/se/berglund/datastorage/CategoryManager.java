package se.berglund.datastorage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import se.berglund.models.Category;
import se.berglund.models.Postit;

/* CategoryManager-class takes a Category from the MessageHandler
 * and contact the MySQL database via Hibernate 
 * and can execute CUD-functionality. 
 * */

public class CategoryManager {

	public CategoryManager() {
	}

	public void createCategory(Category category) {
		SessionFactory sessFactory = HibernateSessionFactory
				.getSessionFactory();
		Session session = sessFactory.openSession();
		Transaction tr = session.beginTransaction();

		try {
			session.save(category);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}
	}

	public void updateCategory(Category category) {

		SessionFactory sessFactory = HibernateSessionFactory
				.getSessionFactory();
		Session session = sessFactory.openSession();
		Transaction tr = session.beginTransaction();

		try {
			session.update(category);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}
	}

	public void deleteCategory(Category category) {

		SessionFactory sessFactory = HibernateSessionFactory
				.getSessionFactory();
		Session session = sessFactory.openSession();
		Transaction tr = session.beginTransaction();

		if (category.getPostits().iterator().hasNext()) {
			SessionFactory sessionFactory = HibernateSessionFactory
					.getSessionFactory();
			Session sessionPostit = sessionFactory.openSession();
			Transaction transaction = sessionPostit.beginTransaction();
			try {
				for (Postit post : category.getPostits()) {
					sessionPostit.delete(post);
				}
				transaction.commit();
			} catch (Exception e) {
				e.printStackTrace();
				transaction.rollback();
			}
		}
		try {
			session.delete(category);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}
	}
}
