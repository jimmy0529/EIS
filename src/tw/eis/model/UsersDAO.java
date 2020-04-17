package tw.eis.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class UsersDAO implements IUsersDAO{
	private SessionFactory sessionFactory;

	@Autowired
	public UsersDAO(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<Users> findUsers(String userName, String userPassword) {
		Session session = sessionFactory.getCurrentSession();
		Query<Users> query = session.createQuery("from Users where UserName = :userName and UserPassword = :userPassword", Users.class);
		query.setParameter("userName", userName);
		query.setParameter("userPassword", userPassword);
		List<Users> list = query.list();
		return list;
	}
	
	public List<Users> findUsersByID(int userID) {
		Session session = sessionFactory.getCurrentSession();
		Query<Users> query = session.createQuery("from Users where EmployeeID = :userID", Users.class);
		query.setParameter("userID", userID);
		List<Users> list = query.list();
		return list;
	}
	
	public boolean updateUsersPassword(String userName, String userPassword) {
		Session session = sessionFactory.getCurrentSession();
		Query<Users> query = session.createQuery("from Users where UserName = :userName", Users.class);
		query.setParameter("userName", userName);
		List<Users> list = query.list();
		if(list.size()<=0) {
			return false;
		}
		Query<?> query2  = session.createQuery("update Users set UserPassword=:userPassword where UserName = :userName");
		query2.setParameter("userName", userName);
		query2.setParameter("userPassword", userPassword);
		query2.executeUpdate();
		return true;
	}
}