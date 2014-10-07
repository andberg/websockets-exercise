package se.berglund.datastorage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import se.berglund.models.Category;
import se.berglund.models.Postit;

public class CategoryManager {

	public CategoryManager() {
	}

	public void updateCategory(Category category) {
		SessionFactory sessFactory = HibernateSessionFactory
				.getSessionFactory();
		Session session = sessFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.update(category);
		tr.commit();
	}

	public void createCategory(Category category) {
		SessionFactory sessFactory = HibernateSessionFactory
				.getSessionFactory();
		Session session = sessFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.save(category);
		tr.commit();
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

			for (Postit post : category.getPostits()) {
				sessionPostit.delete(post);
			}
			transaction.commit();
		}

		session.delete(category);
		tr.commit();
	}
}
